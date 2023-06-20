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
    @Column(name = "user_id",nullable = false)
    private String userId;
    @Column(name = "project_name",nullable = false)
    private String projectName;
    @Column(name = "project_content",nullable = false)
    private String projectContent;
    @Column(name = "created_by",nullable = false)
    private String createdBy;
    @Column(name = "creation_date",nullable = false)
    private Date creationDate;
    @Column(name = "last_updated_by",nullable = false)
    private String lastUpdatedBy;
    @Column(name = "last_update_date",nullable = false)
    private Date lastUpdateDate;

    public boolean isProjectNameContentValid(){
        return projectName != null && projectContent != null;
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
