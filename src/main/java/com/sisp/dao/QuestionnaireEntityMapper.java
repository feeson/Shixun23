package com.sisp.dao;

import com.sisp.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionnaireEntityMapper {
    @Select({
            "<script>",
            "SELECT * FROM questionnaire",
            "WHERE 1=1",
            "<when test='id != null'>",
            "AND id = #{id}",
            "</when>",
            "<when test='projectId != null'>",
            "AND project_id = #{projectId}",
            "</when>",
            "<when test='questionnaireName != null'>",
            "AND questionnaire_name like concat('%', #{questionnaireName}, '%')",
            "</when>",
            "<when test='questionnaireDescription != null'>",
            "AND questionnaire_description like concat('%', #{questionnaireDescription}, '%')",
            "</when>",
            "<when test='surveyType != null'>",
            "AND survey_type = #{surveyType}",
            "</when>",
            "<when test='releaseTime != null'>",
            "AND release_time = #{releaseTime}",
            "</when>",
            "<when test='deadline != null'>",
            "deadline = #{deadline},",
            "</when>",
            "<when test='accessLink != null'>",
            "AND access_link = #{accessLink}",
            "</when>",
            "<when test='deleteFlag != null'>",
            "AND delete_flag = #{deleteFlag}",
            "</when>",
            "<when test='createdBy !=null'>",
            "AND created_by = #{createdBy}",
            "</when>",
            "<when test='creationDate!=null'>",
            "AND creation_date = #{creationDate}",
            "</when>",
            "<when test='lastUpdatedBy!=null'>",
            "AND last_updated_by = #{lastUpdatedBy}",
            "</when>",
            "<when test='lastUpdateDate!=null'>",
            "AND last_update_date = #{lastUpdatedDate}",
            "</when>",
            "</script>"
    })
    @Results(id = "questionnaireResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "questionnaire_name", property = "questionnaireName"),
            @Result(column = "questionnaire_description", property = "questionnaireDescription"),
            @Result(column = "survey_type", property = "surveyType"),
            @Result(column = "release_time", property = "releaseTime"),
            @Result(column = "deadline", property = "deadline"),
            @Result(column = "access_link", property = "accessLink"),
            @Result(column = "delete_flag", property = "deleteFlag"),
            @Result(column = "created_by",property = "createdBy"),
            @Result(column = "creation_date",property = "creationDate"),
            @Result(column = "last_updated_by",property = "lastUpdatedBy"),
            @Result(column = "last_update_date",property = "lastUpdateDate"),
            @Result(property = "questions", column = "id", javaType = List.class,
                    many = @Many(select = "com.sisp.dao.QuestionEntityMapper.findQuestionsByQuestionnaireId"))
    })
    List<QuestionnaireEntity> queryQuestionnaireList(QuestionnaireEntity questionnaireEntity);

    @Insert("INSERT INTO questionnaire (id, project_id, questionnaire_name, questionnaire_description, survey_type, release_time, deadline, access_link, delete_flag, created_by, creation_date, last_updated_by, last_update_date) " +
            "VALUES (#{id}, #{projectId}, #{questionnaireName}, #{questionnaireDescription}, #{surveyType}, #{releaseTime}, #{deadline}, #{accessLink}, #{deleteFlag}, #{createdBy}, #{creationDate}, #{lastUpdatedBy}, #{lastUpdateDate})")
    int insert(QuestionnaireEntity questionnaireEntity);

    @Update({
            "<script>",
            "UPDATE questionnaire",
            "SET ",
            "<when test='projectId != null'>",
            "project_id = #{projectId},",
            "</when>",
            "<when test='questionnaireName != null'>",
            "questionnaire_name = #{questionnaireName},",
            "</when>",
            "<when test='questionnaireDescription != null'>",
            "questionnaire_description = #{questionnaireDescription},",
            "</when>",
            "<when test='surveyType != null'>",
            "survey_type = #{surveyType},",
            "</when>",
            "<when test='releaseTime != null'>",
            "release_time = #{releaseTime},",
            "</when>",
            "<when test='deadline != null'>",
            "deadline = #{deadline},",
            "</when>",
            "<when test='accessLink != null'>",
            "access_link = #{accessLink},",
            "</when>",
            "<when test='deleteFlag != null'>",
            "delete_flag = #{deleteFlag},",
            "</when>",
            "<when test='lastUpdatedBy != null'>",
            "last_updated_by = #{lastUpdatedBy},",
            "</when>",
            "<when test='lastUpdateDate != null'>",
            "last_update_date = #{lastUpdateDate}",
            "</when>",
            "WHERE id = #{id}",
            "</script>"
    })
    int updateByPrimaryKeySelective(QuestionnaireEntity questionnaireEntity);

    @Delete("DELETE FROM questionnaire WHERE id = #{id}")
    int deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity);

}
