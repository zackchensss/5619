package com.usyd.ee5619.Service;

import com.usyd.ee5619.DTO.UserDTO;
import com.usyd.ee5619.DTO.UserLoginDTO;
import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void save(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
        user.setCreateTime(LocalDateTime.now());
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    public User signin(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUserName();
        User user = userMapper.getByUsername(username);
        return user;
    }

    public int findUserIdByUsername(String username) {
        User user = userMapper.getByUsername(username);
        return user.getUserId();
    }
}
