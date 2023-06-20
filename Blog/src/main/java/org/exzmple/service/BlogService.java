package org.exzmple.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.exzmple.entity.Blog;

import org.exzmple.mapper.BlogMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BlogService extends ServiceImpl<BlogMapper, Blog> {

    /**
     * 随机获取Blog
     * @param count
     * @return
     */
    public List getBlogs(int count){
        int maxId =  super.getOne(new QueryWrapper<Blog>()
                .orderByDesc("id")
                .last("limit 1"))
                .getId();
        Random random = new Random();
        List<Blog> blogs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int rand = random.nextInt(maxId+1);
            Blog blog = super.getById(rand);
            if(blog == null){
                i--;
            }
            else {
                blogs.add(blog);
            }
        }
        return blogs;
    }
    public List getBlogsByUserID(int index,int userId){
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>()
                .eq("user_id", userId)
                .orderByDesc("release_time")
                .last("limit " + index*5 + ", 5");
        return super.list(queryWrapper);
    }


}
