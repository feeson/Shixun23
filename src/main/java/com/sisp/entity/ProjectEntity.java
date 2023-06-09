package com.sisp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Data
@Table(name = "project")
@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(generator = "UUID.randomUUID")
    @Column(name = "id",nullable = false)
    private String id;
    @Column(name = "userId",nullable = false)
    private String userId;
    @Column(name = "projectName",nullable = false)
    private String projectName;
    @Column(name = "projectContent",nullable = false)
    private String projectContent;
    @Column(name = "createdBy",nullable = false)
    private String createdBy;
    @Column(name = "creationDate",nullable = false)
    private Date creationDate;
    @Column(name = "lastUpdatedBy",nullable = false)
    private String lastUpdatedBy;
    @Column(name = "lastUpdateDate",nullable = false)
    private Date lastUpdateDate;
}
