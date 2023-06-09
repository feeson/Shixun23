package com.sisp.controller;

import com.sisp.entity.ProjectEntity;
import com.sisp.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Controller("/project/")
public class ProjectController {
    @Resource
    ProjectService projectService;
    @GetMapping("/query")
    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity){
        return projectService.queryProjectList(projectEntity);
    }

}
