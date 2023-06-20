package org.exzmple.feign;

import org.exzmple.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "UserService")
public interface UserFeign {
    @GetMapping("/")
    public String getUserInfo(@RequestParam String user_id);
}
