package com.usyd.ee5619.controller;

import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Entity.Vocabulary;
import com.usyd.ee5619.Mapper.UserMapper;
import com.usyd.ee5619.Service.VocabularyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class VocabularyController {
    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/getVocabularyList")
    public Map<String, Object> getVocabularyList(@RequestBody String userName) {
        User user = userMapper.getByUsername(userName);
        List<Vocabulary> vocabularyList =
                vocabularyService.getVocabularyListByUserId(user.getUserId());
        return Map.of(
                "code", 0,
                "message", "success",
                "data", vocabularyList
        );
    }

    @PostMapping("/addToVocabulary")
    public Map<String, Object> addToVocabulary(@RequestBody Map<String, Object> params) {
        String userName = (String) params.get("userName");
        List<String> questions = (List<String>) params.get("questions");
        LocalDate createDate = LocalDate.parse((String) params.get("date"));
        vocabularyService.addToVocabulary(userName, questions, createDate);
        return Map.of(
                "code", 0,
                "message", "add success"
        );
    }

    @DeleteMapping("/deleteVocabularyWord")
    public Map<String, Object> deleteVocabularyWord(@RequestBody Map<String, Object> params) {
        String userName = (String) params.get("userName");
        String word = (String) params.get("word");
        LocalDate createDate = LocalDate.parse((String) params.get("date"));
        vocabularyService.deleteVocabularyContent(userName, word, createDate);
        return Map.of(
                "code", 0,
                "message", "success"
        );
    }
}
