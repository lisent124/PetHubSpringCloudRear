package org.exzmple.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.exzmple.entity.Interactives;
import org.exzmple.mapper.InteractiveMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractiveService extends ServiceImpl<InteractiveMapper, Interactives> {
    private QueryWrapper<Interactives> queryWrapper;

    public Interactives getOneByBlogIdAndUserId(int blogId,int userId){
        QueryWrapper<Interactives> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("blog_id",blogId).last("limit 1");

        return super.getOne(queryWrapper);
    }

    public Interactives getOneByBlogIdAndUserId(String blogId, String userId) {
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("blog_id",blogId).last("limit 1");

        return super.getOne(queryWrapper);

    }

    public List<Interactives> getMany(int count,int blogId){
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id",blogId).last("limit "+count);

        return super.list(queryWrapper);

    }

    public List<Interactives> getMany(int blogId){
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id",blogId).last("limit "+500);

        return super.list(queryWrapper);

    }

}
