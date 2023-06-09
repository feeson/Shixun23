package com.sisp.Dao;

import com.sisp.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
            "AND userId = #{userId}",
            "</when>",
            "<when test='projectName != null'>",
            "AND projectName like `%#{projectName}%`",
            "</when>",
            "<when test='projectContent!=null'>",
            "AND projectContent like `%#{projectContent}%`",
            "</when>",
            "<when test='createdBy !=null'>",
            "AND createdBy = #{createdBy}",
            "</when>",
            "<when test='creationDate!=null'>",
            "AND creationDate = #{creationDate}",
            "</when>",
            "<when test='lastUpdatedBy!=null'>",
            "AND lastUpdatedBy = #{lastUpdatedBy}",
            "</when>",
            "<when test='lastUpdateDate!=null'>",
            "AND lastUpdateDate = #{id}",
            "</when>",
            "</script>"
    })
    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity);

    int insert(ProjectEntity projectEntity);

    int updateByPrimaryKeySelective(ProjectEntity projectEntity);

    int deleteProjectById(ProjectEntity projectEntity);
}
