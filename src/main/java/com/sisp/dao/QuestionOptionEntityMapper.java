package com.sisp.dao;

import com.sisp.entity.OptionEntity;
import com.sisp.entity.QuestionEntity;
import com.sisp.entity.QuestionOptionEntity;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionOptionEntityMapper {
    @Select("SELECT * FROM question_option WHERE parent_question_id = #{parentQuestionId}")
    @Results(id = "optionResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "parent_question_id", property = "parentQuestionId"),
            @Result(column = "child_option_id", property = "childOptionId"),
            @Result(column = "child_type", property = "childType"),
            @Result(column = "delete_flag", property = "deleteFlag"),
            @Result(column = "created_by", property = "createdBy"),
            @Result(column = "creation_date", property = "creationDate"),
            @Result(column = "last_updated_by", property = "lastUpdatedBy"),
            @Result(column = "last_update_date", property = "lastUpdateDate")
//            @Result(property = "childQuestion", column = "child_option_id", javaType = QuestionEntity.class,
//                    one = @One(select = "com.sisp.dao.QuestionEntityMapper.findQuestionById")),
//            @Result(property = "childOption", column = "child_option_id", javaType = OptionEntity.class,
//                    one = @One(select = "com.sisp.dao.OptionEntityMapper.findOptionById"))
    })
    List<QuestionOptionEntity> findOptionsByQuestionId(int parentQuestionId);

    @Select("SELECT * FROM question_option WHERE id = #{id}")
    @Results(id = "optionResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "parent_question_id", property = "parentQuestionId"),
            @Result(column = "child_option_id", property = "childOptionId"),
            @Result(column = "child_type", property = "childType"),
            @Result(column = "created_by", property = "createdBy"),
            @Result(column = "creation_date", property = "creationDate"),
            @Result(column = "last_updated_by", property = "lastUpdatedBy"),
            @Result(column = "last_update_date", property = "lastUpdateDate"),
            @Result(property = "childQuestion", column = "child_option_id", javaType = QuestionEntity.class,
                    one = @One(select = "com.sisp.dao.QuestionEntityMapper.findQuestionById")),
            @Result(property = "childOption", column = "child_option_id", javaType = OptionEntity.class,
                    one = @One(select = "com.sisp.dao.OptionEntityMapper.findOptionById"))
    })
    QuestionOptionEntity findOptionById(int id);
}
