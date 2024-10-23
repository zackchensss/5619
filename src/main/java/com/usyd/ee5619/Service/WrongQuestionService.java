package com.usyd.ee5619.Service;

import com.usyd.ee5619.Entity.ErrorData;
import com.usyd.ee5619.Entity.ResponseData;
import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Entity.WrongQuestion;
import com.usyd.ee5619.Mapper.UserMapper;
import com.usyd.ee5619.Mapper.WrongQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WrongQuestionService {
    @Autowired
    private WrongQuestionMapper wrongQuestionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<WrongQuestion> getWrongQuestionByUserId(int userId) {
        return wrongQuestionMapper.findWrongQuestionsByUserId(userId);
    }

    public WrongQuestion getWrongQuestionById(Integer wrongId) {
        WrongQuestion wrongQuestion = wrongQuestionMapper.findById(wrongId);
        if (wrongQuestion != null) {
            return wrongQuestion;
        } else {
            throw new RuntimeException("WrongQuestion not found with id: " + wrongId);
        }
    }

    public List<WrongQuestion> getAllWrongQuestions() {
        return wrongQuestionMapper.findALl();
    }

    public List<WrongQuestion> getWrongQuestionsByUnitAndTime(String unit, LocalDateTime lastWrongTime) {
        return wrongQuestionMapper.findWrongQuestionByUnitAndTime(unit, lastWrongTime);
    }

    public void saveWrongQuestion(WrongQuestion wrongQuestion) {
        wrongQuestionMapper.save(wrongQuestion);
    }

    public void deleteWrongQuestion(int wrongId) {
        wrongQuestionMapper.deleteById(wrongId);
    }

    public void addToMistakeBook(String unit, List<String> questions, LocalDate date, String userName){
        for (String questionContent : questions) {
            WrongQuestion wrongQuestion = new WrongQuestion();
            wrongQuestion.setUnit(unit);
            wrongQuestion.setWrongContent(questionContent);
            wrongQuestion.setUnit(unit);
            wrongQuestion.setLastWrongTime(date.atStartOfDay());
            // set initial wrong number as 1
            wrongQuestion.setWrongNumber(1);
            User user = userMapper.getByUsername(userName);
            wrongQuestion.setUserId(user.getUserId());
            saveWrongQuestion(wrongQuestion);
        }
    }

    public ResponseData getWrongQuestionsResponse() {
        List<WrongQuestion> wrongQuestions =  wrongQuestionMapper.findALl();
        Map<String, List<String>> dateToUnitsMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (WrongQuestion question : wrongQuestions) {
            String date = question.getLastWrongTime().format(formatter);
            String unit = question.getUnit();

            dateToUnitsMap.computeIfAbsent(date, k -> new ArrayList<>()).add(unit);
        }

        List<ErrorData> dataList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : dateToUnitsMap.entrySet()) {
            ErrorData errorData = new ErrorData();
            errorData.setDate(entry.getKey());
            errorData.setUnits(entry.getValue());
            dataList.add(errorData);
        }

        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("success");
        responseData.setData(dataList);

        return responseData;
    }

}
