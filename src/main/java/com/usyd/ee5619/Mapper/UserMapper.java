package com.usyd.ee5619.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.usyd.ee5619.Entity.User;


@Mapper
public interface UserMapper {
    @Insert("insert into Users (user_name, password, email, phone, create_time, grade, level, last_login_time) " +
            "values " +
            "(#{userName},#{password},#{email},#{phone},#{createTime},#{grade},#{level},#{lastLoginTime})")
    void insert(User user);
}
