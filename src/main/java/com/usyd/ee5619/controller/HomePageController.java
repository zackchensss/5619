package com.usyd.ee5619.controller;

import com.usyd.ee5619.Common.Result;
import com.usyd.ee5619.Mapper.HomePageMapper;
import com.usyd.ee5619.Service.HomePageService;
import com.usyd.ee5619.Service.UserService;
import com.usyd.ee5619.VO.HomePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    @Autowired
    private HomePageService homePageService;
    @Autowired
    private UserService userService;
    @GetMapping("/api/getHomeData")
    public Result<HomePageVO> getHomeData(@RequestParam String username) {
        int userId = userService.findUserIdByUsername(username);
        HomePageVO homePageVO = homePageService.getHomeData(userId);
        return Result.success(homePageVO);
    }
}
