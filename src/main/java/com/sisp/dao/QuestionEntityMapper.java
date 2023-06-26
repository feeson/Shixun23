package com.sisp.dao;

import com.sisp.entity.QuestionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionEntityMapper {
    @Select("SELECT * FROM question WHERE id = #{id}")
    @Results(id = "questionResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "type", property = "type"),
            @Result(column = "content", property = "content"),
            @Result(column = "required_flag", property = "requiredFlag"),
            @Result(column = "created_by", property = "createdBy"),
            @Result(column = "creation_date", property = "creationDate"),
            @Result(column = "last_updated_by", property = "lastUpdatedBy"),
            @Result(column = "last_update_date", property = "lastUpdateDate"),
            @Result(property = "options", column = "id", javaType = List.class,
                    many = @Many(select = "com.sisp.dao.OptionEntityMapper.findOptionsByQuestionId")),
            @Result(property = "questions", column = "id", javaType = List.class,
                    many = @Many(select = "com.sisp.dao.QuestionEntityMapper.findQuestionsByParentId"))
    })
    QuestionEntity findQuestionById(String id);

    @Select("SELECT * FROM question WHERE id IN (SELECT question_id FROM questionnaire_question WHERE delete_flag = 0 AND questionnaire_id = #{questionnaireId})")
    @ResultMap("questionResultMap")
    List<QuestionEntity> findQuestionsByQuestionnaireId(String questionnaireId);
    @Select("SELECT * FROM question WHERE id IN (SELECT child_option_id FROM question_option WHERE delete_flag = 0 AND parent_question_id = #{parentQuestionId})")
    @ResultMap("questionResultMap")
    List<QuestionEntity> findQuestionsByParentId(String parentId);

    @Insert("INSERT INTO question (id, type, content, required_flag, created_by, creation_date, last_updated_by, last_update_date) VALUES (#{id}, #{type}, #{content}, #{requiredFlag}, #{createdBy}, #{creationDate}, #{lastUpdatedBy}, #{lastUpdateDate})")
    int insertQuestion(QuestionEntity questionEntity);

    @Select("SELECT COUNT(*) FROM question WHERE id = #{id}")
    int queryQuestionExist(QuestionEntity questionEntity);
}