package com.usyd.ee5619.Mapper;

import com.usyd.ee5619.Entity.Vocabulary;
import com.usyd.ee5619.Entity.WrongQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface VocabularyMapper {
    @Select("SELECT * FROM vocabulary WHERE user_id = #{user.userId}")
    List<Vocabulary> findByUserId(int userId);

    @Select("SELECT * FROM vocabulary WHERE create_time = #{createDate}")
    List<Vocabulary> findByCreateTime(LocalDate createDate);

    @Delete("DELETE FROM vocabulary WHERE vocabulary_content = #{vocabularyContent} AND create_time = #{createDate}")
    void deleteAllByVocabularyContentAndCreateDate(String vocabularyContent, LocalDate createDate);

    @Insert("INSERT INTO vocabulary (user_id, vocabulary_content, create_date)" +
            "VALUES (#{userId}, #{vocabularyContent}, #{createDate})")
    void save(Vocabulary vocabulary);
}
