package com.sisp.dao;

import com.sisp.entity.QuestionnaireQuestionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionnaireQuestionEntityMapper {
    @Insert("INSERT INTO questionnaire_question (id, questionnaire_id, question_id, delete_flag,created_by, creation_date, last_updated_by, last_update_date) VALUES (#{id}, #{questionnaireId}, #{questionId}, #{deleteFlag},#{createdBy}, #{creationDate}, #{lastUpdatedBy}, #{lastUpdateDate})")
    int insertQuestionnaireQuestion(QuestionnaireQuestionEntity questionnaireQuestionEntity);
}
