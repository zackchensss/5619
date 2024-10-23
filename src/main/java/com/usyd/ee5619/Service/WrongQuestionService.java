package com.usyd.ee5619.Service;

import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Entity.WrongQuestion;
import com.usyd.ee5619.Mapper.UserMapper;
import com.usyd.ee5619.Mapper.WrongQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public void saveWrongQuestion(WrongQuestion wrongQuestion) {
        wrongQuestionMapper.save(wrongQuestion);
    }

    public void deleteWrongQuestion(int wrongId) {
        wrongQuestionMapper.deleteById(wrongId);
    }

    public void addToMistakeBook(String unit, List<String> questions, LocalDate date, String userName){
        for (String questionContent : questions) {
            WrongQuestion wrongQuestion = new WrongQuestion();
            wrongQuestion.setWrongContent(questionContent);
            wrongQuestion.setLastWrongTime(date.atStartOfDay());
            // set initial wrong number as 1
            wrongQuestion.setWrongNumber(1);
            User user = userMapper.getByUsername(userName);
            wrongQuestion.setUserId(user.getUserId());
            wrongQuestionMapper.save(wrongQuestion);
        }
    }
}
