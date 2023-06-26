package com.sisp.service;

import com.sisp.dao.QuestionnaireEntityMapper;
import com.sisp.entity.QuestionnaireEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.utils.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionnaireService {
    private final static Logger logger = LoggerFactory.getLogger(QuestionnaireService.class);
    @Resource
    QuestionnaireEntityMapper questionnaireEntityMapper;

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

    @Transactional
    public HttpResponseEntity modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) throws SQLException {
        String currentUserId = SpringSecurityUtil.getCurrentUsername();
        long currentTime = new Date().getTime();
        questionnaireEntity.setLastUpdatedBy(currentUserId);
        questionnaireEntity.setLastUpdateDate(new java.sql.Date(currentTime));
        try {
            questionnaireEntityMapper.updateByPrimaryKeySelective(questionnaireEntity);
        } catch (Exception e) {
            logger.error("Error : questionnaireEntityMapper.updateByPrimaryKeySelective(questionnaireEntity)\n" + questionnaireEntity);
            throw new SQLException("修改失败");
        }
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
}
