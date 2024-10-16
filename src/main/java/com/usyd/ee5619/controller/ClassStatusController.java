package com.usyd.ee5619.controller;

import com.usyd.ee5619.Common.Result;
import com.usyd.ee5619.DTO.CourseStatusDTO;
import com.usyd.ee5619.Service.CourseStatusService;
import com.usyd.ee5619.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ClassStatusController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseStatusService courseStatusService;
    @PostMapping("/api/setCourseStatus")
    public Result<String> setCourseStatus(@RequestBody CourseStatusDTO courseStatusDTO) {
        String username = courseStatusDTO.getUserName();
        int userId = userService.findUserIdByUsername(username);
        courseStatusService.saveCourseStatus(userId, courseStatusDTO);

        return Result.success("record success");
    }
}
