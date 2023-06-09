package com.sisp.Dao;

import com.sisp.entity.ProjectEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectEntityMapper {
    @Select({
            "<script>",
            "SELECT * FROM project",
            "WHERE 1=1",
            "<when test='id!=null'>",
            "AND id = #{id}",
            "</when>",
            "<when test='userId!=null'>",
            "AND user_id like concat('%',#{userId},'%')",
            "</when>",
            "<when test='projectName != null'>",
            "AND project_name like concat('%',#{projectName},'%')",
            "</when>",
            "<when test='projectContent!=null'>",
            "AND project_content like concat('%',#{projectContent},'%')",
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
    @Results(id = "projectResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "project_name",property = "projectName"),
            @Result(column = "project_content",property = "projectContent"),
            @Result(column = "created_by",property = "createdBy"),
            @Result(column = "creation_date",property = "creationDate"),
            @Result(column = "last_updated_by",property = "lastUpdatedBy"),
            @Result(column = "last_update_date",property = "lastUpdateDate")
    })
    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity);

    @Insert("INSERT INTO project (id,user_id,project_name,project_content,created_by,creation_date,last_updated_by,last_update_date) " +
            "VALUES(#{id},#{userId},#{projectName},#{projectContent},#{createdBy},#{creationDate},#{lastUpdatedBy},#{lastUpdateDate})")
    int insert(ProjectEntity projectEntity);


    @Update({
            "<script>",
            "UPDATE project",
            "<when test='userId!=null'>",
            "SET ",
            "user_id = #{userId},",
            "</when>",
            "<when test='projectName != null'>",
            "project_name = #{projectName},",
            "</when>",
            "<when test='projectContent!=null'>",
            "project_content = #{projectContent},",
            "</when>",
            "<when test='createdBy !=null'>",
            "created_by = #{createdBy},",
            "</when>",
            "<when test='creationDate!=null'>",
            "creation_date = #{creationDate},",
            "</when>",
            "<when test='lastUpdatedBy!=null'>",
            "last_updated_by = #{lastUpdatedBy},",
            "</when>",
            "<when test='lastUpdateDate!=null'>",
            "last_update_date = #{lastUpdateDate}",
            "</when>",
            "WHERE id = #{id}",
            "</script>"
    })
    int updateByPrimaryKeySelective(ProjectEntity projectEntity);

    int deleteProjectById(ProjectEntity projectEntity);
}
