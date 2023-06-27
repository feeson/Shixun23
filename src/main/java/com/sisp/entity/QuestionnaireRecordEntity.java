package com.sisp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "questionnaire_record")
@Getter
@Setter
@Entity
public class QuestionnaireRecordEntity {
    @Id
    @GeneratedValue(generator = "UUID.randomUUID")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name="questionnaire_id",nullable = false)
    private String questionnaireId;

    @Column(name="user_id",nullable = false)
    private String userId;

    @Column(name="commit_time",nullable = false)
    private Timestamp commitTime;

    @Column(name="delete_flag",nullable = false)
    private Integer deleteFlag;

    @Column(name="created_by",nullable = false)
    private String createdBy;

    @Column(name="creation_date",nullable = false)
    private Timestamp creationDate;

    @Column(name="last_updated_by",nullable = false)
    private String lastUpdatedBy;

    @Column(name="last_update_date",nullable = false)
    private Timestamp lastUpdateDate;

}
