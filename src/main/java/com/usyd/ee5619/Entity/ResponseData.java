package com.usyd.ee5619.Entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseData implements Serializable {
    private int code;
    private String message;
    private List<ErrorData> data;
}
