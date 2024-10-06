package com.usyd.ee5619.DTO;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserDTO implements Serializable {
    private String name;
    private String grade;
    private String difficulty;
    private String email;
    private String password;
}