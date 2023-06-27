package com.sisp.controller;

import com.sisp.dao.QuestionnaireEntityMapper;
import com.sisp.entity.QuestionnaireEntity;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        assertNotNull(webApplicationContext);
    }

    @Test
    public void testHandleAccessLink_whenQuestionnaireExists_shouldReturnIndexHtml() throws Exception {
        // Mock the behavior of questionnaireEntityMapper.queryQuestionnaireExist()
        when(questionnaireEntityMapper.queryQuestionnaireExist(any(QuestionnaireEntity.class))).thenReturn(1);

        // Perform the GET request
        mockMvc.perform(get("/pages/answerSheet/{questionnaireId}", "12345678-abcd-1234-efgh-9876543210ab"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/pages/answerSheet/index.html"));
    }

    @Test
    public void testHandleAccessLink_whenQuestionnaireDoesNotExist_shouldReturn404Html() throws Exception {
        // Mock the behavior of questionnaireEntityMapper.queryQuestionnaireExist()
        when(questionnaireEntityMapper.queryQuestionnaireExist(any(QuestionnaireEntity.class))).thenReturn(0);

        // Perform the GET request
        mockMvc.perform(get("/pages/answerSheet/{questionnaireId}", "12345678-abcd-1234-efgh-9876543210ab"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/pages/answerSheet/404.html"));
    }
}