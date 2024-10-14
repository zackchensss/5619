package com.usyd.ee5619.DTO;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserDTO implements Serializable {
    private String userName;
    private String grade;
    private String level;
    private String email;
    private String password;
    private String phone;
}