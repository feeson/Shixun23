package com.sisp.service;

import com.sisp.dao.*;
import com.sisp.entity.*;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.utils.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionnaireService {
    private final static Logger logger = LoggerFactory.getLogger(QuestionnaireService.class);
    @Resource
    QuestionnaireEntityMapper questionnaireEntityMapper;
    @Resource
    QuestionnaireQuestionEntityMapper questionnaireQuestionEntityMapper;

    @Resource
    QuestionService questionService;

    @Value("${data.accessQuestionnaireUrl}")
    String accessQuestionnaireUrl;

    public HttpResponseEntity queryQuestionnaireList(QuestionnaireEntity questionnaireEntity) {
        List<QuestionnaireEntity> questionnaireEntities = questionnaireEntityMapper.queryQuestionnaireList(questionnaireEntity);
        HttpResponseEntity httpResponseEntity = HttpResponseEntityFactory.get200(questionnaireEntities);
        return httpResponseEntity;
    }

    @Transactional
    public HttpResponseEntity addQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) throws SQLException {
        String currentUserId = SpringSecurityUtil.getCurrentUsername();
        long currentTime = new Date().getTime();
        questionnaireEntity.setId(String.valueOf(UUID.randomUUID()));
        questionnaireEntity.setDeleteFlag(0);
        questionnaireEntity.setCreatedBy(currentUserId);
        questionnaireEntity.setCreationDate(new java.sql.Date(currentTime));
        questionnaireEntity.setLastUpdatedBy(currentUserId);
        questionnaireEntity.setLastUpdateDate(new java.sql.Date(currentTime));
        try {
            questionnaireEntityMapper.insert(questionnaireEntity);
        } catch (Exception e) {
            logger.error("Error: questionnaireEntityMapper.insert(questionnaireEntity)\n" + questionnaireEntity + "\n");
            logger.error(e.getMessage());
            throw new SQLException("插入失败");
        }
        return HttpResponseEntityFactory.get200(questionnaireEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public HttpResponseEntity modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) throws SQLException {
        String currentUserId = SpringSecurityUtil.getCurrentUsername();
        //TODO 检查问卷是否存在
        QuestionnaireEntity findExistQ = new QuestionnaireEntity();
        findExistQ.setId(questionnaireEntity.getId());
        int findExistQs = questionnaireEntityMapper.queryQuestionnaireExist(
                findExistQ);
        if (findExistQs!=1)
            throw new SQLException("问卷不存在");
        long currentTime = new Date().getTime();
        questionnaireEntity.setLastUpdatedBy(currentUserId);
        questionnaireEntity.setLastUpdateDate(new java.sql.Date(currentTime));
        try {
            int effectRow = questionnaireEntityMapper.updateByPrimaryKeySelective(questionnaireEntity);
            if (effectRow!=1)
                throw new SQLException("更新失败");
        }catch (Exception e){
            logger.error("Error : questionnaireEntityMapper.updateByPrimaryKeySelective(questionnaireEntity)\n"+questionnaireEntity);
            logger.error(e.getMessage());
            throw new SQLException("更新失败");
        }

        List<QuestionEntity> questions = questionnaireEntity.getQuestions();
        questions.stream().parallel().forEach(question -> {
            try {
                questionService.addQuestionInfo(question, currentUserId, currentTime);
            }catch (Exception e){
                try {
                    throw e;
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            //TODO 建立文件问题联系
            QuestionnaireQuestionEntity questionnaireQuestionEntity = new QuestionnaireQuestionEntity();
            questionnaireQuestionEntity.setId(String.valueOf(UUID.randomUUID()));
            questionnaireQuestionEntity.setQuestionnaireId(questionnaireEntity.getId());
            questionnaireQuestionEntity.setQuestionId(question.getId());
            questionnaireQuestionEntity.setDeleteFlag(0);
            questionnaireQuestionEntity.setCreatedBy(currentUserId);
            questionnaireQuestionEntity.setCreationDate(new java.sql.Date(currentTime));
            questionnaireQuestionEntity.setLastUpdatedBy(currentUserId);
            questionnaireQuestionEntity.setLastUpdateDate(new java.sql.Date(currentTime));
            try {
                int effectRow = questionnaireQuestionEntityMapper.insertQuestionnaireQuestion(questionnaireQuestionEntity);
                if (effectRow!=1)
                    throw new SQLException("插入失败");
            }catch (Exception e){
                logger.error("Error : questionnaireQuestionEntityMapper.insert(questionnaireQuestionEntity)\n"+questionnaireQuestionEntity);
                logger.error(e.getMessage());
            }
        });
        return HttpResponseEntityFactory.get200();
    }

    @Transactional
    public HttpResponseEntity deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity) throws SQLException {
        if (questionnaireEntityMapper.deleteQuestionnaireById(questionnaireEntity) == 1) {
            return HttpResponseEntityFactory.get200();
        } else {
            logger.error("Error: questionnaireEntityMapper.deleteQuestionnaireById(questionnaireEntity)\n" + questionnaireEntity);
            throw new SQLException("删除失败");
        }
    }

    @Transactional
    public HttpResponseEntity publishQuestionnaire(QuestionnaireEntity questionnaire) throws SQLException {
        if (questionnaire.getId()==null)
            return HttpResponseEntityFactory.get400();
        QuestionnaireEntity findExistQ = new QuestionnaireEntity();
        findExistQ.setId(questionnaire.getId());
        int findExistQs = questionnaireEntityMapper.queryQuestionnaireExist(
                findExistQ);
        if (findExistQs!=1)
            return HttpResponseEntityFactory.get400();
        findExistQ.setAccessLink(accessQuestionnaireUrl+"/accessQuestionnaire/"+questionnaire.getId());
        findExistQ.setLastUpdatedBy(SpringSecurityUtil.getCurrentUsername());
        findExistQ.setLastUpdateDate(new java.sql.Date(new Date().getTime()));
        int effectRow = questionnaireEntityMapper.updateByPrimaryKeySelective(
                findExistQ);
        if (effectRow!=1)
            throw new SQLException("更新失败");
        return HttpResponseEntityFactory.get200();
    }
}
