package com.sisp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisp.entity.OptionEntity;
import com.sisp.entity.QuestionEntity;
import com.sisp.entity.QuestionnaireEntity;
import com.sisp.entity.UserEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.service.QuestionnaireService;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = QuestionnaireControllerTest.class)
public class QuestionnaireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionnaireService questionnaireService;

    @Test
    public void testAddQuestionnaireInfo_ValidQuestionnaire_Success() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setQuestionnaireName("Sample Questionnaire");
        questionnaire.setQuestionnaireDescription("This is a sample questionnaire");
        questionnaire.setReleaseTime(new Timestamp(System.currentTimeMillis()));
        questionnaire.setDeadline(new Timestamp(System.currentTimeMillis() + 86400000)); // 24小时后的截止时间

        // 模拟问卷服务返回成功的响应
        given(questionnaireService.addQuestionnaireInfo(questionnaire)).willReturn(
                HttpResponseEntityFactory.get200());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/addQuestionnaire")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\": 200}"));
    }

    @Test
    public void testAddQuestionnaireInfo_IncompleteQuestionnaire_Forbidden() throws Exception {
        // 准备测试数据（不完整的问卷信息）
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setQuestionnaireName("Sample Questionnaire");
        questionnaire.setQuestionnaireDescription("This is a sample questionnaire");
        questionnaire.setReleaseTime(new Timestamp(System.currentTimeMillis()));

        // 执行POST请求并验证结果
        mockMvc.perform(post("/addQuestionnaire")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAddQuestionnaireInfo_InsertFailed_BadRequest() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setQuestionnaireName("Sample Questionnaire");
        questionnaire.setQuestionnaireDescription("This is a sample questionnaire");
        questionnaire.setReleaseTime(new Timestamp(System.currentTimeMillis()));
        questionnaire.setDeadline(new Timestamp(System.currentTimeMillis() + 86400000));

        // 模拟问卷服务抛出SQLException异常
        given(questionnaireService.addQuestionnaireInfo(questionnaire)).willThrow(new SQLException());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/addQuestionnaire")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testModifyQuestionnaireInfo_UpdateFields_Success() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("123");
        questionnaire.setQuestionnaireName("Updated Questionnaire Name");
        questionnaire.setQuestionnaireDescription("Updated Questionnaire Description");

        // 模拟问卷服务返回成功的响应
        given(questionnaireService.modifyQuestionnaireInfo(questionnaire)).willReturn(HttpResponseEntityFactory.get200());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/modifyQuestionnaireInfo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\": 200}"));
    }

    @Test
    public void testModifyQuestionnaireInfo_AddQuestionsAndOptions_Success() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("123");

        QuestionEntity question = new QuestionEntity();
        question.setType(1);
        question.setContent("New Question");
        question.setRequiredFlag(1);

        OptionEntity option1 = new OptionEntity();
        option1.setContent("Option 1");

        OptionEntity option2 = new OptionEntity();
        option2.setContent("Option 2");

        question.setOptions(Arrays.asList(option1, option2));
        questionnaire.setQuestions(Collections.singletonList(question));

        // 模拟问卷服务返回成功的响应
        given(questionnaireService.modifyQuestionnaireInfo(questionnaire)).willReturn(HttpResponseEntityFactory.get200());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/modifyQuestionnaireInfo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\": 200}"));
    }

    @Test
    public void testModifyQuestionnaireInfo_ModifyFailed_BadRequest() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("123");
        questionnaire.setQuestionnaireName("Updated Questionnaire Name");

        // 模拟问卷服务抛出SQLException异常
        given(questionnaireService.modifyQuestionnaireInfo(questionnaire)).willThrow(new SQLException());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/modifyQuestionnaireInfo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testDeleteQuestionnaireById_Success() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("123");

        // 模拟问卷服务返回成功的响应
        given(questionnaireService.deleteQuestionnaireById(questionnaire)).willReturn(HttpResponseEntityFactory.get200());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/deleteQuestionnaireById")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\": 200}"));
    }

    @Test
    public void testDeleteQuestionnaireById_DeleteFailed_BadRequest() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("123");

        // 模拟问卷服务抛出SQLException异常
        given(questionnaireService.deleteQuestionnaireById(questionnaire)).willThrow(new SQLException());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/deleteQuestionnaireById")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isBadRequest());
    }
    // TC_01: 测试根据问卷ID查询答题结果
    @Test
    public void testQueryQuestionnaireAnswer_Success() throws Exception {
        String questionnaireId = "123456";

        mockMvc.perform(MockMvcRequestBuilders.post("/queryQuestionnaireAnswer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":\"" + questionnaireId + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"status\":\"success\",\"data\":\"{...}\"}"));
    }

    // TC_02: 测试未提供问卷ID的情况
    @Test
    public void testQueryQuestionnaireAnswer_InvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/queryQuestionnaireAnswer")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // TC_03: 测试提供不存在的问卷ID的情况
    @Test
    public void testQueryQuestionnaireAnswer_NonexistentId() throws Exception {
        String questionnaireId = "999999";

        mockMvc.perform(MockMvcRequestBuilders.post("/queryQuestionnaireAnswer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":\"" + questionnaireId + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"status\":\"success\",\"data\":{}}"));
    }

    // TC_04: 测试提供存在的问卷ID且deleteFlag为1的情况
    @Test
    public void testQueryQuestionnaireAnswer_IsDeleted() throws Exception {
        String questionnaireId = "123456";

        mockMvc.perform(MockMvcRequestBuilders.post("/deleteQuestionnaireById")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":\"" + questionnaireId + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"status\":\"success\"}"));
    }
    @Test
    public void testQueryQuestionnaireListByQuestionnaireIdUserId_ValidInput_Success() throws Exception {
        // Prepare valid questionnaire ID and user information
        String questionnaireId = "valid-questionnaire-id";
        String userId = "valid-user-id";

        // Build the request body
        QuestionnaireController.queryQuestionnaireListByQuestionnaireIdUserIdDto requestBody = new QuestionnaireController.queryQuestionnaireListByQuestionnaireIdUserIdDto();
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(questionnaireId);
        requestBody.setQuestionnaire(questionnaireEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        requestBody.setUserEntity(userEntity);

        // Send the POST request
        MockHttpServletRequestBuilder requestBuilder = post("/queryQuestionnaireByQuestionnaireIdUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestBody));
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{}"));
    }

    @Test
    public void testQueryQuestionnaireListByQuestionnaireIdUserId_MissingInput_BadRequest() throws Exception {
        // Send the POST request without providing request body
        MockHttpServletRequestBuilder requestBuilder = post("/queryQuestionnaireByQuestionnaireIdUserId")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testQueryQuestionnaireListByQuestionnaireIdUserId_NonexistentQuestionnaireId_Success() throws Exception {
        // Prepare nonexistent questionnaire ID and valid user information
        String questionnaireId = "nonexistent-questionnaire-id";
        String userId = "valid-user-id";

        // Build the request body
        QuestionnaireController.queryQuestionnaireListByQuestionnaireIdUserIdDto requestBody = new QuestionnaireController.queryQuestionnaireListByQuestionnaireIdUserIdDto();
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(questionnaireId);
        requestBody.setQuestionnaire(questionnaireEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        requestBody.setUserEntity(userEntity);
        // Send the POST request
        MockHttpServletRequestBuilder requestBuilder = post("/queryQuestionnaireByQuestionnaireIdUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestBody));
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"questionnaires\": []}"));
    }
    @Test
    public void testQueryQuestionnaireListByQuestionnaireIdUserId_NonexistentUserId_Success() throws Exception {
        // Prepare valid questionnaire ID and nonexistent user ID
        String questionnaireId = "valid-questionnaire-id";
        String userId = "nonexistent-user-id";

        // Build the request body
        QuestionnaireController.queryQuestionnaireListByQuestionnaireIdUserIdDto requestBody = new QuestionnaireController.queryQuestionnaireListByQuestionnaireIdUserIdDto();
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(questionnaireId);
        requestBody.setQuestionnaire(questionnaireEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        requestBody.setUserEntity(userEntity);
        // Send the POST request
        MockHttpServletRequestBuilder requestBuilder = post("/queryQuestionnaireByQuestionnaireIdUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestBody));
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"questionnaires\": []}"));
    }
    @Resource
    private QuestionnaireController questionnaireController;
    @Test
    public void testPublishQuestionnaire_Success() throws SQLException {
        // 构造请求的问卷对象
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("valid-questionnaire-id");

        // 模拟问卷发布成功的情况
        Mockito.when(questionnaireService.publishQuestionnaire(questionnaire)).thenReturn(new HttpResponseEntity(
                ));

        // 发送POST请求并获取响应结果
        HttpResponseEntity response = questionnaireController.publishQuestionnaire(questionnaire);

        // 验证返回的状态码是否为200
        assertEquals("OK","200", response.getCode());
    }

    @Test
    public void testPublishQuestionnaire_Failure() throws SQLException {
        // 构造请求的问卷对象
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("invalid-questionnaire-id");

        // 模拟问卷发布失败的情况
        Mockito.when(questionnaireService.publishQuestionnaire(questionnaire)).thenThrow(new SQLException());

        // 发送POST请求并获取响应结果
        HttpResponseEntity response = questionnaireController.publishQuestionnaire(questionnaire);

        // 验证返回的状态码是否为400
        assertEquals("OK","400", response.getCode());
    }

    @Test
    public void testAnalyseQuestionnaireById_ValidId_Success() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("validId");

        // 模拟问卷服务返回成功的响应
        given(questionnaireService.analyseQuestionnaireById(questionnaire)).willReturn(HttpResponseEntityFactory.get200());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/analyseQuestionnaireById")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\": 200}"));
    }

    @Test
    public void testAnalyseQuestionnaireById_InvalidId_BadRequest() throws Exception {
        // 准备测试数据
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("invalidId");

        // 模拟问卷服务返回失败的响应
        given(questionnaireService.analyseQuestionnaireById(questionnaire)).willReturn(HttpResponseEntityFactory.get400());

        // 执行POST请求并验证结果
        mockMvc.perform(post("/analyseQuestionnaireById")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(questionnaire)))
                .andExpect(status().isBadRequest());
    }

    // 辅助方法：将对象转换为JSON字符串
    private static String asJsonString(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
