package com.src.pojo.DTO;
import lombok.Data;

import java.io.Serializable;
@Data
public class SigninDTO implements Serializable {
    private String name;
    private String grade;
    private String difficulty;
    private String email;
    private String password;
}