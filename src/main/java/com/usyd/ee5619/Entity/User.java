package com.usyd.ee5619.Entity;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int userId;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private LocalDateTime createTime;

    private String grade;

    private int level;

    private LocalDateTime lastLoginTime;

}
