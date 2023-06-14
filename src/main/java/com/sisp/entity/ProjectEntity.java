package com.sisp.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    public boolean integrityCheck(){
//        if (userId==null||userId.trim().equals(""))
//            return false;
//        if (projectName==null||projectName.trim().equals(""))
//            return false;
//        return projectContent != null && !projectContent.trim().equals("");
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(
                this) != Hibernate.getClass(o)) return false;
        ProjectEntity that = (ProjectEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
