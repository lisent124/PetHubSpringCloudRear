package org.exzmple.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.exzmple.entity.*;
import org.exzmple.feign.UserFeign;
import org.exzmple.pojo.Result;
import org.exzmple.pojo.ResultCode;
import org.exzmple.service.BlogService;
import org.exzmple.service.InteractiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class Index {

    @Autowired
    BlogService blogService;

    @Autowired
    InteractiveService interactiveService;

    @Resource
    UserFeign userFeign;

    @Value("${BaseUrl}")
    String baseUrl;

    String uploadPath = "D:/Projects/javaProjects/PetHubSpringCloud/";

    private User getUser(int userId){
        String text = userFeign.getUserInfo(String.valueOf(userId));
        JSONObject jsonObject = JSON.parseObject(text);
        return jsonObject.getObject("data", User.class);
    }

    private Blog saveImage(Blog blog,MultipartFile image){
        String temp  = image.getOriginalFilename();
        temp = temp.substring(temp.lastIndexOf("."), temp.length());
        String headUrl = "static/user/"+blog.getUser_id()+"/blogs/"+blog.getId()+"/";
        String fileName = "blog"+temp;

        blog.setPicture(headUrl+fileName);

        File file = new File(uploadPath+headUrl);
        if(!file.isDirectory()){
            file.mkdirs();
        }

        try {
            image.transferTo(new File(file,fileName));  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return blog;
    }

    @GetMapping("/")
    public Result getBlogs(@RequestParam("self") String self,
                           @RequestHeader("id")int userId){
        List<Blog> blogs =null;
        if(self.equals("false"))
            blogs = blogService.getBlogs(10);
        else
            blogs = blogService.getBlogsByUserID(Integer.parseInt(self),userId);
            if (blogs.isEmpty()) return Result.failure(ResultCode.NO_MORE_BLOGS);

        List<BUEntity> buEntities = new ArrayList<>();

        for(Blog blog:blogs){
            User user = getUser(blog.getUser_id());
            Interactives i = interactiveService.getOneByBlogIdAndUserId(blog.getId(), user.getId());
            user.setLike(i==null?false:i.isLike());
            user.setComment(i==null?null:i.getComment());
            String temp = blog.getPicture()==null||blog.getPicture().equals("")
                    ?null:baseUrl+blog.getPicture();
            blog.setPicture(temp);
            BUEntity buEntity = new BUEntity(user,blog);

            buEntities.add(buEntity);
        }
        return Result.success("hello",buEntities);
    }


    @PostMapping("/")
    public Result createBlogs(@RequestParam(value = "image",required = false) MultipartFile image,
                              @RequestParam("content") String content,
                              @RequestHeader("id")String userId){
        Blog blog = new Blog();
        blog.setUser_id(Integer.parseInt(userId));
        blog.setContent(content);
        blog.setVisible(true);
        if (image!=null)
            blog = saveImage(blog,image);
        blog.setRelease_time(new Timestamp(new Date().getTime()));
        if (blog == null){
            return Result.failure(ResultCode.SERVICE_ERROR);
        }
        blogService.save(blog);
        return Result.success("创建成功");
    }

    @GetMapping("/like")
    public Result like(@RequestHeader("id")String userId,
                       @RequestParam("blog_id")String blogId){
        Blog blog = blogService.getById(blogId);
        if (blog == null)
            return Result.failure(ResultCode.NO_REQUEST_DATA);

        Interactives i = interactiveService.getOneByBlogIdAndUserId(blogId, userId);
        if (i == null){
            Interactives j = new Interactives();
            j.setLike(true);
            j.setUser_id(Integer.parseInt(userId));
            j.setBlog_id(Integer.parseInt(blogId));
            j.setCreate_time(new Timestamp(new Date().getTime()));
            interactiveService.save(j);
            return Result.success("点赞成功");
        }
        i.setLike(!i.isLike());
        boolean update = interactiveService.updateById(i);
        if (!update) return Result.failure(ResultCode.DATABASE_ERROR);
        if (i.isLike())
            return Result.success("点赞");
        else return Result.success("取消点赞");
    }

    @GetMapping("/comment")
    public Result getComment(@RequestParam("blog_id")String blogId){
        Blog blog = blogService.getById(blogId);
        if (blog == null)
            return Result.failure(ResultCode.SUCCESS_WITH_NO_DATA);

        List<Interactives> many = interactiveService.getMany(Integer.parseInt(blogId));
        List<CUEntity> cuEntities = new ArrayList<>();
        for (Interactives i :many){
            if ((!i.isLike() &&(i.getComment()==null||i.getComment().equals(""))))
                break;
            User user = getUser(i.getUser_id());
            CUEntity cuEntity = new CUEntity(i,user);
            cuEntities.add(cuEntity);
        }
        return Result.success("查询成功",cuEntities);
    }

    @PostMapping("/comment")
    public Result createComment(@RequestParam("blog_id")String blogId,
                                @RequestParam("comment")String comment,
                                @RequestHeader("id")String userId){

        Blog blog = blogService.getById(blogId);
        if (blog == null)
            return Result.failure(ResultCode.NO_REQUEST_DATA);
        Interactives item = interactiveService.getOneByBlogIdAndUserId(blogId, userId);

        if (item == null){
            item = new Interactives();
            item.setBlog_id(Integer.parseInt(blogId));
            item.setUser_id(Integer.parseInt(userId));
            item.setLike(false);
            item.setComment(comment);
            item.setCreate_time(new Timestamp(new Date().getTime()));
            interactiveService.save(item);
            return Result.success("创建评论成功");
        }

        item.setComment(comment);
        interactiveService.updateById(item);
        return Result.success("更新评论成功");
    }

    @PostMapping("/delete")
    public Result deleteBlog(@RequestParam("blog_id") String blogId){

        boolean remove = blogService.removeById(blogId);

        if (remove)
            return Result.success("删除成功");
        else return Result.failure(ResultCode.ERROR_REQUEST);
    }

    @GetMapping("/show")
    public Result showBlog(@RequestParam("blog_id") String blogId){
        Blog blog = blogService.getById(blogId);

        if (blog == null){
            return Result.failure(ResultCode.ERROR_REQUEST);
        }
        blog.setVisible(true);
        blogService.updateById(blog);
        return Result.success("显示成功");
    }

    @PostMapping("/show")
    public Result hideBlog(@RequestParam("blog_id") String blogId){
        Blog blog = blogService.getById(blogId);

        if (blog == null){
            return Result.failure(ResultCode.ERROR_REQUEST);
        }
        blog.setVisible(true);
        blogService.updateById(blog);
        return Result.success("隐藏成功");
    }
}
