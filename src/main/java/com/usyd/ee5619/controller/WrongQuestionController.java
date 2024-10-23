package com.usyd.ee5619.controller;

import com.usyd.ee5619.Entity.WrongQuestion;
import com.usyd.ee5619.Service.WrongQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
public class WrongQuestionController {
    @Autowired
    private WrongQuestionService wrongQuestionService;

    // get wrong questions list by date and unit info
    @GetMapping("/getMistakeList")
    public Map<String, Object> getMistakeList() {
        List<WrongQuestion> wrongQuestions = wrongQuestionService.getAllWrongQuestions();
        Map<String, Object> response = new HashMap<>();

        // group questions by date and unit name for list view display
        Map<LocalDate, Map<String, List<WrongQuestion>>> wrongQuestionsGroupedByDateAndUnit =
                wrongQuestions.stream().collect(Collectors.groupingBy(
                        wrongQuestion ->
                                wrongQuestion.getLastWrongTime().toLocalDate(),
                        Collectors.groupingBy(WrongQuestion::getWrongContent)
                ));

        // construct response data
        List<Map<String, Object>> responseData = new ArrayList<>();
        wrongQuestionsGroupedByDateAndUnit.forEach((date, unitMap) -> {
            Map<String, Object> dateGroup = new HashMap<>();
            dateGroup.put("date", date.toString());
            List<Map<String, String>> unitList = unitMap.keySet().stream()
                    .map(unit -> {
                        Map<String, String> unitData = new HashMap<>();
                        unitData.put("name", unit);
                        return unitData;
                    }).collect(Collectors.toList());
            dateGroup.put("units", unitList);
            responseData.add(dateGroup);
        });

        response.put("code", 0);
        response.put("message", "success");
        response.put("data",responseData);

        return response;
    }

    // get wrong questions under certain date and unit
    @GetMapping("/getMistakeQuestions")
    public Map<String, Object> getMistakeQuestions(
            @RequestParam("unit") String unit,
            @RequestParam("date") String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        LocalDateTime dateToTime = date.atStartOfDay();
        List<WrongQuestion> wrongQuestions = wrongQuestionService.getWrongQuestionsByUnitAndTime(unit, dateToTime);

        // get wrong question contents
        List<Map<String, String>> questionContents = wrongQuestions.stream()
                .map(wrongQuestion -> {
                    Map<String, String> questionData = new HashMap<>();
                    questionData.put("question", wrongQuestion.getWrongContent());
                    return questionData;
                }).collect(Collectors.toList());

        // construct response data
        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("message", "success");
        response.put("data", questionContents);

        return response;
    }

    // add wrong questions to mistake book
    @PostMapping("/addToMistakeBook")
    public Map<String, Object> addToMistakeBook(@RequestBody Map<String, Object> param) {
        String unit = (String) param.get("unit");
        List<String> questions = (List<String>) param.get("questions");
        LocalDate date = LocalDate.parse((String) param.get("data"));
        String userName = (String) param.get("username");

        wrongQuestionService.addToMistakeBook(unit, questions, date, userName);

        return Map.of("code", 0, "message", "add success");
    }
}
