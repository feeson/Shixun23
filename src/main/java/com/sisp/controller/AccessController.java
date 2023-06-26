package com.sisp.controller;

import com.sisp.dao.QuestionnaireEntityMapper;
import com.sisp.entity.QuestionnaireEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AccessController {
    @Resource
    private QuestionnaireEntityMapper questionnaireEntityMapper;
    @GetMapping(value = "/pages/answerSheet/{questionnaireId:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}", produces = MediaType.TEXT_HTML_VALUE)
    public void handleAccessLink(@PathVariable String questionnaireId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(questionnaireId);
        int effectRow = questionnaireEntityMapper.queryQuestionnaireExist(
                questionnaireEntity);
        RequestDispatcher dispatcher;
        if (effectRow!=1){
            dispatcher = request.getRequestDispatcher(
                    "/pages/answerSheet/404.html");
        }else {
            // 将请求转发到目标视图
            dispatcher = request.getRequestDispatcher(
                    "/pages/answerSheet/index.html");
        }
        dispatcher.forward(request, response);
    }
}
