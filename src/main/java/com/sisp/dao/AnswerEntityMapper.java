package com.sisp.dao;


import com.sisp.entity.AnswerEntity;
import com.sisp.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnswerEntityMapper {
    @Insert("INSERT INTO answer (id,content,questionnaire_id,question_id,user_id,delete_flag,created_by,creation_date,last_updated_by,last_update_date) VALUES (#{id},#{content},#{questionnaireId},#{questionId},#{userId},#{deleteFlag},#{createdBy},#{creationDate},#{lastUpdatedBy},#{lastUpdateDate})")
    int insertAnswer(AnswerEntity answerEntity);

    @Select({
            "<script>",
            "SELECT * FROM answer",
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
    List<AnswerEntity> queryAnswerList(AnswerEntity answerEntity);
}
