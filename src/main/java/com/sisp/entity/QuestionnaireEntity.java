package com.sisp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "questionnaire")
@Entity
public class QuestionnaireEntity {
    @Id
    @GeneratedValue(generator = "UUID.randomUUID")
    @Column(name = "id",nullable = false)
    private String id;

    @Column(name = "project_id",nullable = false)
    private String projectId;

    @Column(name = "questionnaire_name",nullable = false)
    private String questionnaireName;

    @Column(name = "questionnaire_description",nullable = false)
    private String questionnaireDescription;

    @Column(name = "survey_type",nullable = false)
    private int surveyType=0;

    @Column(name="release_time")
    private String releaseTime;

    @Column(name="deadline")
    private String deadline;

    @Column(name="access_link")
    private String accessLink;

    @Column(name="delete_flag",nullable = false)
    private int deleteFlag=0;

    @Column(name="created_by",nullable = false)
    private String createdBy;

    @Column(name="creation_date",nullable = false)
    private Date creationDate;

    @Column(name="last_updated_by",nullable = false)
    private String lastUpdatedBy;

    @Column(name="last_update_date",nullable = false)
    private Date lastUpdateDate;

    @Transient
    private List<QuestionEntity> questions;

    public boolean isQuestionnaireValid() {
        return questionnaireName != null
               && questionnaireDescription != null
               && releaseTime != null
               && deadline != null
//               && accessLink != null
                ;
    }
}
