package org.exzmple.controller;

import org.exzmple.entity.User;
import org.exzmple.pojo.Result;
import org.exzmple.pojo.ResultCode;
import org.exzmple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class Index {

    @Autowired
    UserService userService;

    @Value("${BaseUrl}")
    String baseUrl;

    String uploadPath = "D:/Projects/javaProjects/PetHubSpringCloud/";

    @GetMapping("/")
    public Result getUserInfo(@RequestParam String user_id){
        User user = userService.getById(user_id);
        if (user == null)
            return Result.failure(ResultCode.NO_REQUEST_DATA);
        user.setHead_picture(baseUrl+user.getHead_picture());
        return Result.success("返回成功",user);
    }

    @PostMapping("/update")
    public Result updateUser(HttpServletRequest request){
        String userId = request.getHeader("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String location = request.getParameter("location");

        User user = new User();
        user.setId(Integer.parseInt(userId));
        user.setName(name);
        user.setGender(gender);
        user.setLocation(location);

        return Result.success("更新成功");
    }

    private User saveHead(User user,MultipartFile head){
        String temp  = head.getOriginalFilename();
        temp = temp.substring(temp.lastIndexOf("."), temp.length());
        String headUrl = "static/user/"+user.getId()+"/";
        String fileName = user.getName()+"_head"+temp;

        user.setHead_picture(headUrl+fileName);

        File file = new File(uploadPath+headUrl);
        if(!file.isDirectory()){
            file.mkdirs();
        }

        try {
            head.transferTo(new File(file,fileName));  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    @PostMapping("/head")
    public Result updateUserHead(HttpServletRequest request, @RequestParam("head")MultipartFile head){
        String userId = request.getHeader("id");

        User user = userService.getById(Integer.parseInt(userId));
        user = saveHead(user,head);

        userService.updateById(user);
        return Result.success("更新成功");
    }

}
