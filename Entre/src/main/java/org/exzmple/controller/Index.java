package org.exzmple.controller;


import org.exzmple.Mapper.UserMapper;
import org.exzmple.entity.User;
import org.exzmple.pojo.Result;
import org.exzmple.pojo.ResultCode;
import org.exzmple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Index {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService service;

    @Value("${name}")
    String name;

    @GetMapping("/")
    public String test(){
        return name;
    }

    @PostMapping("/register")
    public Result register(HttpServletResponse response, @RequestParam String phone, @RequestParam String password0, @RequestParam String password){

        if(phone.length()!=11)
            return Result.failure(ResultCode.NO_REQUEST_DATA);
        User user = service.getByPhone(phone);
        if(user != null)
            return Result.failure(ResultCode.USER_REGISTERED);
        if(!password.equals(password0))
            return Result.failure(ResultCode.PASSWD_NOT_THE_SAME);
        if(password.length()<8 || password.length()>=64)
            return Result.failure(ResultCode.PASSWD_LEN_ERROR);

        user.setPhone(phone);
        user.setPassword(password);
        user.setName("用户"+phone);
        user.setHead_picture("static/lisent.png");

        return Result.success("注册成功");
    }

    @PostMapping("/login")
    public Result<User> login(HttpServletResponse response,@RequestParam String phone, @RequestParam String password){
        if(phone.length()!=11)
            return Result.failure(ResultCode.NO_REQUEST_DATA);
        User user = service.getByPhone(phone);
        if(user == null)
            return Result.failure(ResultCode.USER_IS_NOT_EXIST);
//        if(!user.getPassword().equals(password))
//            return Result.failure(ResultCode.PASSWD_NOT_THE_SAME);

        response.addHeader("id", String.valueOf(user.getId()));
        Cookie cookie = new Cookie("id","signed_"+user.getId());
        response.addCookie(cookie);
        return Result.success("登录成功");
    }

}
