package org.exzmple.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.exzmple.Mapper.UserMapper;
import org.exzmple.entity.User;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public User getByPhone(String phone){
        return super.getOne(new QueryWrapper<User>().eq("phone",phone));
    }
}
