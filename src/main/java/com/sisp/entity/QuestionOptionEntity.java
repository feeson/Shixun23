package com.sisp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "question_option")
@Entity
public class QuestionOptionEntity {
    @Id
    @GeneratedValue(generator = "UUID.randomUUID")
    @Column(name = "id",nullable = false)
    private String id;

    @Column(name="parent_question_id",nullable = false)
    private String parentQuestionId;

    @Column(name="child_option_id",nullable = false)
    private String childOptionId;

    @Column(name = "child_type",nullable = false)
    private int childType=0;

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
}
