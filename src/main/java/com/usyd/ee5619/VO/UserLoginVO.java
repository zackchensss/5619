package com.usyd.ee5619.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    private String userName;

    private String password;

    private String email;

    private int phone;

    private String grade;

    private String level;

}
