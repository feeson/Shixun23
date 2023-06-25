package com.sisp.dao;

import com.sisp.entity.OptionEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OptionEntityMapper {
    @Select("SELECT * FROM option WHERE id = #{id}")
    @Results(id = "optionResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "content", property = "content"),
            @Result(column = "created_by", property = "createdBy"),
            @Result(column = "creation_date", property = "creationDate"),
            @Result(column = "last_updated_by", property = "lastUpdatedBy"),
            @Result(column = "last_update_date", property = "lastUpdateDate")
    })
    OptionEntity findOptionById(int id);

    @Select("SELECT * FROM option WHERE parent_question_id = #{parentQuestionId}")
    @ResultMap("optionResultMap")
    List<OptionEntity> findOptionsByQuestionId(int parentQuestionId);
}