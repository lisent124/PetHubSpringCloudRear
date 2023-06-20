package org.exzmple.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.exzmple.entity.User;
import org.exzmple.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
