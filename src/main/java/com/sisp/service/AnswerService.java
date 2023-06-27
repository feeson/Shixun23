package com.sisp.service;

import com.sisp.dao.AnswerEntityMapper;
import com.sisp.entity.AnswerEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.utils.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnswerService {
    private final static Logger logger= LoggerFactory.getLogger(AnswerService.class);
    @Resource
    private AnswerEntityMapper answerEntityMapper;

    public HttpResponseEntity getAnswerByQuestionnaireId(String questionnaireId){
        String currentUserId = SpringSecurityUtil.getCurrentUsername();
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setUserId(currentUserId);
        answerEntity.setQuestionnaireId(questionnaireId);
        List<AnswerEntity> answerEntityList = answerEntityMapper.queryAnswerList(answerEntity);
        HttpResponseEntity httpResponseEntity = HttpResponseEntityFactory.get200(
                answerEntityList);
        return httpResponseEntity;
    }
}
