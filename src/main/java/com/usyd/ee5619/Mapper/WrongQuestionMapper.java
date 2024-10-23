package com.usyd.ee5619.Mapper;

import com.usyd.ee5619.Entity.WrongQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface WrongQuestionMapper {
    @Select("SELECT * FROM wrong_question WHERE user_id = #{user.userId}")
    List<WrongQuestion> findWrongQuestionsByUserId(int userId);

    @Select("SELECT * FROM wrong_question WHERE wrong_number > #{wrongNumber}")
    List<WrongQuestion> findWrongQuestionsByWrongNumberGreaterThan(Integer wrongNumber);

    @Select("SELECT * FROM wrong_question WHERE unit = #{unit} AND last_wrong_time = #{lastWrongTime}")
    List<WrongQuestion> findWrongQuestionByUnitAndTime(String unit, LocalDateTime lastWrongTime);

    @Select("SELECT * FROM wrong_question WHERE wrong_id = #{wrongId}")
    WrongQuestion findById(int wrongId);

    @Select("SELECT * FROM wrong_question")
    List<WrongQuestion> findALl();

    @Insert("INSERT INTO wrong_question (user_id, wrong_content, last_wrong_time, wrong_number)" +
            "VALUES (#{userId}, #{wrongContent}, #{lastWrongTime}, #{wrongNumber})")
    void save(WrongQuestion wrongQuestion);

    @Delete("DELETE FROM wrong_question WHERE wrong_id = #{wrongId}")
    void deleteById(int wrongId);

}
