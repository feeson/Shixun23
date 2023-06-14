package com.sisp.controller;

import com.sisp.entity.ProjectEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.ServiceLoader;

@RestController
@Api(tags = "项目API")
public class ProjectController {
    private final static Logger logger= LoggerFactory.getLogger(
            ProjectController.class);
    @Resource
    ProjectService projectService;

    @PostMapping("/queryProjectList")
    @ApiOperation(value = "多条件模糊查询项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id:字符串严格等于",dataTypeClass = String.class),
            @ApiImplicitParam(name = "userId",value = "userId:字符串严格等于",dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectName",value = "projectName:模糊等于",dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectContent",value = "projectContent:模糊等于",dataTypeClass = String.class),
            @ApiImplicitParam(name = "createdBy",value = "创建用户的Id:字符串严格等于",dataTypeClass = String.class),
            @ApiImplicitParam(name = "creationDate",value = "创建当天:等于YYYY-MM-DD",dataTypeClass = String.class),
            @ApiImplicitParam(name = "lastUpdatedBy",value = "最后一次修改用户的Id:字符串严格等于",dataTypeClass = String.class),
            @ApiImplicitParam(name = "lastUpdateDate",value = "最后一次修改当天:等于YYYY-MM-DD",dataTypeClass = String.class)
    })
    @ApiResponse(responseCode = "200",description = "OK",content = @Content(mediaType = "application/json", schema = @Schema(implementation = HttpResponseEntity.class)))
    HttpResponseEntity queryProjectList(@RequestBody Optional<ProjectEntity> projectEntity){
        ProjectEntity project =projectEntity.orElseGet(ProjectEntity::new);
        return projectService.queryProjectList(project);
    }

    @PostMapping("/addProjectInfo")
    @ApiOperation(value = "添加项目信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "403",description = "项目参数信息不完整"),
            @ApiResponse(responseCode = "400",description = "插入失败,请检查异常信息")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "userId",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectName",value = "projectName",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectContent",value = "projectContent",required = true,dataTypeClass = String.class)
    })
    HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity project){
        if(!project.integrityCheck())
            return HttpResponseEntityFactory.get403();
        else
            try {
                return projectService.addProjectInfo(project);
            }catch (SQLException e){
                return HttpResponseEntityFactory.get400();
            }
    }

    @PostMapping("/modifyProjectInfo")
    @ApiOperation(value = "根据ID修改项目信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "修改失败,ID是否正确？")
    })
    @ApiImplicitParam(name = "id",value = "项目id",required = true,dataTypeClass = String.class)
    HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity project){
        try {
            return projectService.modifyProjectInfo(project);
        }catch (SQLException e){
            return HttpResponseEntityFactory.get400();
        }
    }


    @PostMapping("/deleteProjectById")
    @ApiOperation(value = "根据ID删除项目")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "删除失败，ID是否正确？")
    })
    @ApiImplicitParam(name = "id",value = "项目id",required = true,dataTypeClass = String.class)
    HttpResponseEntity deleteProjectById(@RequestBody ProjectEntity project){

        try {
            return projectService.deleteProjectById(project);
        }catch (SQLException e){
            return HttpResponseEntityFactory.get400();
        }
    }
}
