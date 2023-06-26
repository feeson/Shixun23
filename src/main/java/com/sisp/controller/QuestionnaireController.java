package com.sisp.controller;

import com.sisp.entity.QuestionnaireEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.service.QuestionnaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Optional;

@RestController
@Api(tags = "问卷API")
public class QuestionnaireController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);

    @Resource
    private QuestionnaireService questionnaireService;

    @PostMapping("/queryQuestionnaireList")
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
    public HttpResponseEntity queryQuestionnaireList(@RequestBody Optional<QuestionnaireEntity> questionnaireEntity) {
        QuestionnaireEntity questionnaire = questionnaireEntity.orElseGet(QuestionnaireEntity::new);
        return questionnaireService.queryQuestionnaireList(questionnaire);
    }

    @PostMapping("/addQuestionnaire")
    @ApiOperation(value = "添加问卷信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "问卷参数信息不完整"),
            @ApiResponse(responseCode = "400", description = "插入失败，请检查异常信息")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "questionnaireName", value = "问卷名称", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "questionnaireDescription", value = "问卷描述", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "releaseTime", value = "发布时间", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "deadline", value = "截至时间", required = true, dataTypeClass = String.class),
//            @ApiImplicitParam(name = "accessLink", value = "访问链接", required = true, dataTypeClass = String.class),
    })
    public HttpResponseEntity addQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaire) {
        if (!questionnaire.isQuestionnaireValid()) {
            return HttpResponseEntityFactory.get403();
        } else {
            try {
                return questionnaireService.addQuestionnaireInfo(questionnaire);
            } catch (SQLException e) {
                return HttpResponseEntityFactory.get400();
            }
        }
    }

    @PostMapping("/modifyQuestionnaire")
    @ApiOperation(value = "根据ID修改问卷信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "修改失败，ID是否正确？")
    })
    public HttpResponseEntity modifyQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaire) {
        try {
            return questionnaireService.modifyQuestionnaireInfo(questionnaire);
        } catch (SQLException e) {
            return HttpResponseEntityFactory.get400();
        }
    }

    @PostMapping("/deleteQuestionnaireById")
    @ApiOperation(value = "根据ID删除问卷")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "删除失败，ID是否正确？")
    })
    public HttpResponseEntity deleteQuestionnaireById(@RequestBody QuestionnaireEntity questionnaire) {
        try {
            return questionnaireService.deleteQuestionnaireById(questionnaire);
        } catch (SQLException e) {
            return HttpResponseEntityFactory.get400();
        }
    }

    @PostMapping("/publishQuestionnaire")
    @ApiOperation(value = "发布问卷")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "发布失败，ID是否正确？")
    })
    public HttpResponseEntity publicQuestionnaire(@RequestBody QuestionnaireEntity questionnaire) {
        try {
            return questionnaireService.publishQuestionnaire(questionnaire);
        } catch (SQLException e) {
            return HttpResponseEntityFactory.get400();
        }
    }
}

