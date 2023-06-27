package com.sisp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "answer")
@Entity
@Getter
@Setter
public class AnswerEntity {
    @Id
    @GeneratedValue(generator = "UUID.randomUUID")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "questionnaire_id", nullable = false)
    private String questionnaireId;

    @Column(name = "question_id", nullable = false)
    private String questionId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "delete_flag", nullable = false)
    private Integer deleteFlag;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;

    @Column(name = "last_update_date", nullable = false)
    private Date lastUpdateDate;
}
