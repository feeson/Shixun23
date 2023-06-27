package com.sisp.controller;

import com.sisp.entity.QuestionEntity;
import com.sisp.entity.QuestionnaireEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.service.QuestionnaireService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionnaireControllerTest {
    private QuestionnaireEntity getDefault() {
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        questionnaire.setId("id");
        questionnaire.setQuestionnaireName("name");
        questionnaire.setDeleteFlag(0);
        questionnaire.setProjectId("projectId");
        questionnaire.setAccessLink("link");
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        questionnaire.setQuestions(questions);
        questionnaire.setSurveyType(0);
        questionnaire.setReleaseTime(new Timestamp(System.currentTimeMillis()));
        questionnaire.setCreatedBy("createdBy");
        questionnaire.setCreationDate(new Date(new java.util.Date().getTime()));
        questionnaire.setLastUpdatedBy("lastUpdatedBy");
        questionnaire.setLastUpdateDate(new Date(new java.util.Date().getTime()));
        return questionnaire;
    }

    @Autowired
    private QuestionnaireController questionnaireController;

    @Test
    @WithMockUser(username = "name",password = "pwd")
    public void testQueryQuestionnaireList() throws Exception {
        questionnaireController.queryQuestionnaireList(Optional.empty());
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    public void testAddQuestionnaireInfo() throws Exception {
        questionnaireController.addQuestionnaireInfo(getDefault());

        QuestionnaireEntity questionnaire = getDefault();
        questionnaire.setProjectId("08f28fa7-a335-4c5e-817e-4423a7832749");
        questionnaireController.addQuestionnaireInfo(questionnaire);

    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    public void testModifyQuestionnaireInfo() throws Exception {
        questionnaireController.modifyQuestionnaireInfo(getDefault());

        QuestionnaireEntity questionnaire = getDefault();
        questionnaire.setId("e3e317f2-3d1f-42cf-a610-f0e1c746a16e");
        questionnaireController.modifyQuestionnaireInfo(getDefault());
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    public void testDeleteQuestionnaireById() throws Exception {
        questionnaireController.deleteQuestionnaireById(getDefault());

        QuestionnaireEntity questionnaire = getDefault();
        questionnaire.setId("e3e317f2-3d1f-42cf-a610-f0e1c746a16e");
        questionnaireController.deleteQuestionnaireById(getDefault());
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    public void testPublishQuestionnaire() throws Exception {
        questionnaireController.publicQuestionnaire(getDefault());

        QuestionnaireEntity questionnaire = getDefault();
        questionnaire.setId("e3e317f2-3d1f-42cf-a610-f0e1c746a16e");
        questionnaireController.publicQuestionnaire(getDefault());
    }
}
