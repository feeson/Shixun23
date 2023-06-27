package com.sisp.controller;

import com.sisp.entity.AnswerEntity;
import com.sisp.entity.QuestionnaireEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.service.AnswerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
public class AnswerController {
    private final static Logger logger= LoggerFactory.getLogger(AnswerController.class);

    @Resource
    private AnswerService answerService;
    @PostMapping("/queryAnswerList")
    @ApiOperation(value = "多条件模糊查询问卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问卷ID: 字符串严格等于", dataTypeClass = String.class),
            @ApiImplicitParam(name = "projectId", value = "项目ID: 字符串严格等于", dataTypeClass = String.class),
            @ApiImplicitParam(name = "questionnaireName", value = "问卷名称: 模糊等于", dataTypeClass = String.class),
            @ApiImplicitParam(name = "questionnaireDescription", value = "问卷描述: 模糊等于", dataTypeClass = String.class),
            @ApiImplicitParam(name = "surveyType", value = "问卷类型: 整型严格等于", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "releaseTime", value = "发布时间: 严格等于", dataTypeClass = String.class),
            @ApiImplicitParam(name = "accessLink", value = "访问链接: 严格等于", dataTypeClass = String.class),
            @ApiImplicitParam(name = "deleteFlag", value = "删除标记: 整型严格等于", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "createdBy", value = "创建用户的ID: 字符串严格等于", dataTypeClass = String.class),
            @ApiImplicitParam(name = "creationDate", value = "创建日期: 等于YYYY-MM-DD", dataTypeClass = String.class),
            @ApiImplicitParam(name = "lastUpdatedBy", value = "最后一次修改用户的ID: 字符串严格等于", dataTypeClass = String.class),
            @ApiImplicitParam(name = "lastUpdateDate", value = "最后一次修改日期: 等于YYYY-MM-DD", dataTypeClass = String.class)
    })
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HttpResponseEntity.class)))
    public HttpResponseEntity queryAnswerList(@RequestBody Optional<QuestionnaireEntity> questionnaireEntity) {
        return answerService.getAnswerByQuestionnaireId(questionnaireEntity.get().getId());
    }
}
