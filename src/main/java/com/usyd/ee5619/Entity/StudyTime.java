package com.usyd.ee5619.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyTime {
    private int sessionId;
    private int userId;
    private LocalDate date;
    private String studyTime; //format:('HH:mm')
}
