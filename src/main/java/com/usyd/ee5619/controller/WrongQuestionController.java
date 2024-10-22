package com.usyd.ee5619.controller;

import com.usyd.ee5619.Entity.WrongQuestion;
import com.usyd.ee5619.Service.WrongQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

        Map<LocalDate, List<WrongQuestion>> wrongQuestionsGroupedByDate =
                wrongQuestions.stream().collect(Collectors.groupingBy(
                        wrongQuestion ->
                                wrongQuestion.getLastWrongTime().toLocalDate()
                ));

        List<Map<String, Object>> responseData = new ArrayList<>();
        wrongQuestionsGroupedByDate.forEach(
                (date, questions) -> {
                    Map<String, Object> dateGroup = new HashMap<>();
                    dateGroup.put("date", date.toString());
                    List<Map<String, String>> questionList = questions.stream()
                            .map(q -> {
                                Map<String, String> questionData = new HashMap<>();
                                questionData.put("id", String.valueOf(q.getWrongId()));
                                questionData.put("name", q.getWrongContent());
                                return questionData;
                            }).collect(Collectors.toList());
                    dateGroup.put("questions", questionList);
                    responseData.add(dateGroup);
                }
        );

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
        List<WrongQuestion> wrongQuestions = wrongQuestionService.getAllWrongQuestions();

        // filter out wrong questions
        List<String> questionContents = wrongQuestions.stream()
                .filter(wq -> wq.getLastWrongTime().toLocalDate().equals(date) &&
                        wq.getWrongContent().equalsIgnoreCase(unit))
                .map(WrongQuestion::getWrongContent)
                .collect(Collectors.toList());

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
