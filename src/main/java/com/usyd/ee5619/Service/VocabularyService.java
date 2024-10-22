package com.usyd.ee5619.Service;

import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Entity.Vocabulary;
import com.usyd.ee5619.Mapper.UserMapper;
import com.usyd.ee5619.Mapper.VocabularyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VocabularyService {
    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Vocabulary> getVocabularyListByUserId(int userId) {
        return vocabularyMapper.findByUserId(userId);
    }

    public void addToVocabulary(String userName, List<String> contents, LocalDate createDate) {
        for (String word : contents) {
            Vocabulary vocabulary = new Vocabulary();
            User user = userMapper.getByUsername(userName);
            vocabulary.setUserId(user.getUserId());
            vocabulary.setVocabularyContent(word);
            vocabulary.setCreateDate(createDate);
            vocabularyMapper.save(vocabulary);
        }
    }

    public void deleteVocabularyContent(String userName, String vocabularyContent, LocalDate createDate) {
        vocabularyMapper.deleteAllByVocabularyContentAndCreateDate(vocabularyContent, createDate);
    }
}
