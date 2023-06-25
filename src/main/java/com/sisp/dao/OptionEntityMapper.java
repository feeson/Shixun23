package com.sisp.dao;

import com.sisp.entity.OptionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OptionEntityMapper {
    @Select("SELECT * FROM options WHERE id = #{id}")
    @Results(id = "optionResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "content", property = "content"),
            @Result(column = "created_by", property = "createdBy"),
            @Result(column = "creation_date", property = "creationDate"),
            @Result(column = "last_updated_by", property = "lastUpdatedBy"),
            @Result(column = "last_update_date", property = "lastUpdateDate")
    })
    OptionEntity findOptionById(String id);

    @Select("SELECT * FROM options WHERE id IN (SELECT child_option_id FROM question_option WHERE delete_flag = 0 AND parent_question_id = #{parentQuestionId})")
    @ResultMap("optionResultMap")
    List<OptionEntity> findOptionsByQuestionId(String parentQuestionId);
}