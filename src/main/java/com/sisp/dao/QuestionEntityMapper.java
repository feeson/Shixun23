package com.sisp.dao;

import com.sisp.entity.QuestionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    QuestionEntity findQuestionById(int id);

    @Select("SELECT * FROM question WHERE parent_id = #{parentId}")
    @ResultMap("questionResultMap")
    List<QuestionEntity> findQuestionsByParentId(int parentId);
}