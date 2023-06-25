package com.sisp.dao;

import com.sisp.entity.QuestionnaireQuestionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionnaireQuestionEntityMapper {
    @Select("SELECT * FROM questionnaire_question WHERE questionnaire_id = #{questionnaireId}")
    @Results(id = "questionResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "questionnaire_id", property = "questionnaireId"),
            @Result(column = "question_id", property = "questionId"),
            @Result(column = "delete_flag", property = "deleteFlag"),
            @Result(column = "created_by", property = "createdBy"),
            @Result(column = "creation_date", property = "creationDate"),
            @Result(column = "last_updated_by", property = "lastUpdatedBy"),
            @Result(column = "last_update_date", property = "lastUpdateDate")
    })
    List<QuestionnaireQuestionEntity> findQuestionsByQuestionnaireId(int questionnaireId);

}
