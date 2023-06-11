package com.sisp.controller;

import com.sisp.entity.ProjectEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/project/")
@Api(tags = "项目API")
public class ProjectController {
    @Resource
    ProjectService projectService;

    @GetMapping("/query")
    @ApiOperation(value = "多条件模糊查询项目")
    @ApiResponse(responseCode = "200",description = "OK",content = @Content(mediaType = "application/json", schema = @Schema(implementation = HttpResponseEntity.class)))
    HttpResponseEntity queryProjectList(ProjectEntity projectEntity){
        return projectService.queryProjectList(projectEntity);
    }

    @PutMapping("/addProject")
    @ApiOperation(value = "添加项目信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "403",description = "项目参数信息不完整"),
            @ApiResponse(responseCode = "400",description = "数据库插入失败")
    })
    HttpResponseEntity addProjectInfo(ProjectEntity project){
        if(!project.integrityCheck())
            return HttpResponseEntityFactory.get403();
        else
            try {
                return projectService.addProjectInfo(project);
            }catch (SQLException e){
                return HttpResponseEntityFactory.get400();
            }
    }


}
