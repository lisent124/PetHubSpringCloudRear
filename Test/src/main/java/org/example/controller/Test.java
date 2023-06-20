package org.example.controller;

import org.example.entity.Blog;
import org.example.entity.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class Test {
    @Resource
    BlogService blogService;

    @Resource
    RestTemplate restTemplate;

    @GetMapping("/")
    public String test01(){
        Blog blog = blogService.getById(1);
        System.out.println(blog.getReleaseTime()+"\n"+blog.getPicture());
        String hello = restTemplate.getForObject("http://Entre-service/test",String.class);
        return hello;
    }
}
