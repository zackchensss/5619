package com.usyd.ee5619.controller;

import com.usyd.ee5619.Common.Result;
import com.usyd.ee5619.DTO.StudyTimeDTO;
import com.usyd.ee5619.Server.StudyTimeService;
import com.usyd.ee5619.Server.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The function of the data shown at the home page
 */
@RestController
@Slf4j
public class StudyTimeController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudyTimeService studyTimeService;
    @PostMapping("/api/setStudyTime")
    public Result<String> startStudy(@RequestBody StudyTimeDTO studyTimeDTO){
        String username = studyTimeDTO.getUserName();
        int userId = userService.findUserIdByUsername(username);
        String time = studyTimeDTO.getTime();
        studyTimeService.saveStudyTime(userId,time);
        return Result.success("record success");
    }
}
