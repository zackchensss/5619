package com.usyd.ee5619.Entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WrongQuestion {
    private int wrongId;
    private int userId;
    private String wrongContent;
    private LocalDateTime lastWrongTime;
    private Integer wrongNumber;
}