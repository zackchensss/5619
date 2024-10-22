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
public class Vocabulary {
    private Integer vocabularyId;
    private int userId;
    private String vocabularyContent;
    private LocalDate createDate;
}
