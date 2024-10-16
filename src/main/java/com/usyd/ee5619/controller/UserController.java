package com.usyd.ee5619.controller;

import com.usyd.ee5619.Common.Result;
import com.usyd.ee5619.DTO.UserDTO;
import com.usyd.ee5619.DTO.UserLoginDTO;
import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Service.UserService;
import com.usyd.ee5619.VO.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The function of users signing in and signing up
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/api/signup")
    public Result<UserDTO> signUp(@RequestBody UserDTO userDTO){
        log.info("Save the user,{}",userDTO);
        userService.save(userDTO);
        return Result.success();
    }
    @PostMapping("/api/signin")
    public Result<UserLoginVO> signIn(@RequestBody UserLoginDTO userLoginDTO){
        User user = userService.signin(userLoginDTO);
        if(user == null){
            return Result.error("The account is not existed");
        }
        String password = user.getPassword();
        if(!password.equals(userLoginDTO.getPassword())){
            return Result.error("The password is wrong");
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user,userLoginVO);
        return Result.success(userLoginVO);
    }
}
