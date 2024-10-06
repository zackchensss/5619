package com.usyd.ee5619.controller;

import com.usyd.ee5619.Common.Result;
import com.usyd.ee5619.DTO.UserDTO;
import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/api/signin")
    public Result<String> signIn(UserDTO userDTO){
        log.info("Save the user,{}",userDTO);
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setCreateTime(LocalDateTime.now());
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.insert(user);
        return Result.success();
    }
}
