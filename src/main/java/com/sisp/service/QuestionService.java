package com.sisp.service;

import com.sisp.dao.QuestionEntityMapper;
import com.sisp.dao.QuestionOptionEntityMapper;
import com.sisp.entity.OptionEntity;
import com.sisp.entity.QuestionEntity;
import com.sisp.entity.QuestionOptionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {
    private final static Logger logger = LoggerFactory.getLogger(QuestionService.class);
    @Resource
    QuestionEntityMapper questionEntityMapper;
    @Resource
    OptionService optionService;

    @Resource
    QuestionOptionEntityMapper questionOptionEntityMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public QuestionEntity addQuestionInfo(QuestionEntity question, String currentUserId,
                                          long currentTime) throws Exception {
        question.setId("q"+ UUID.randomUUID());
        question.setCreatedBy(currentUserId);
        question.setCreationDate(new java.sql.Date(currentTime));
        question.setLastUpdatedBy(currentUserId);
        question.setLastUpdateDate(new java.sql.Date(currentTime));
        try {
            //TODO 添加question option 联系
            int effectRow = questionEntityMapper.insertQuestion(question);
            if (effectRow!=1)
                throw new SQLException("插入失败");
        }catch (Exception e){
            logger.error("Error : questionEntityMapper.insertQuestion(question)\n"+question);
            logger.error(e.getMessage());
            throw e;
        }
        switch (question.getType()) {
            case 0, 1 -> {
                List<OptionEntity> options = question.getOptions();
                options.stream().parallel().forEach(option -> {
                    try {
                        optionService.addOptionInfo(option, currentUserId,
                                                          currentTime);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        linkQuestionOption(question, currentUserId, currentTime,
                                           option.getId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            case 2 -> {

            }
            default -> {
                List<QuestionEntity> childQuestions = question.getQuestions();
                childQuestions.stream().parallel().forEach(childQuestion -> {
                    try {
                        addQuestionInfo(childQuestion, currentUserId,
                                                currentTime);
                    } catch (Exception e) {
                        try {
                            throw e;
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    try {
                        linkQuestionOption(question, currentUserId, currentTime,
                                           childQuestion.getId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        return question;
    }

    private void linkQuestionOption(QuestionEntity parentQuestion,
                                    String currentUserId, long currentTime,
                                    String childID) throws Exception{
        QuestionOptionEntity questionOptionEntity = new QuestionOptionEntity();
        questionOptionEntity.setId(
                String.valueOf(UUID.randomUUID()));
        questionOptionEntity.setParentQuestionId(parentQuestion.getId());
        questionOptionEntity.setChildOptionId(childID);
        questionOptionEntity.setDeleteFlag(0);
        questionOptionEntity.setCreatedBy(currentUserId);
        questionOptionEntity.setCreationDate(
                new java.sql.Date(currentTime));
        questionOptionEntity.setLastUpdatedBy(currentUserId);
        questionOptionEntity.setLastUpdateDate(
                new java.sql.Date(currentTime));
        try {
            int effectRow = questionOptionEntityMapper.insertQuestionOption(
                    questionOptionEntity);
            if (effectRow!= 1)
                throw new SQLException("插入失败");
        }catch (Exception e){
            logger.error(
                    "Error : questionOptionEntityMapper.insertQuestionOption(questionOptionEntity)\n" + questionOptionEntity);
            logger.error(e.getMessage());
            throw e;
        }
    }
}
