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
@Table(name = "question")
@Entity
public class QuestionEntity {
    @Id
    @GeneratedValue(generator = "UUID.randomUUID")
    @Column(name = "id",nullable = false)
    private String id;

    @Column(name="type",nullable = false)
    private int type=0;

    @Column(name="content",nullable = false)
    private String content;

    @Column(name="required_flag",nullable = false)
    private int requiredFlag=0;

    @Column(name="created_by",nullable = false)
    private String createdBy;

    @Column(name="creation_date",nullable = false)
    private Date creationDate;

    @Column(name="last_updated_by",nullable = false)
    private String lastUpdatedBy;

    @Column(name="last_update_date",nullable = false)
    private Date lastUpdateDate;

    @Transient
    List<OptionEntity> options;

    @Transient
    List<QuestionEntity> questionEntities;
}
