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
public class CourseStatus {
    private int courseId;
    private int userId;
    private String course;
    private int completeNum;
    private LocalDate completionDate;
}
