package com.sisp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: lucy
 * @Date: 2023-06-01-19:09
 */

@Data
@Table(name = "user_info")
@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID.randomUUID")
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "startTime")
    private Timestamp startTime;
    @Column(name = "stopTime")
    private Timestamp stopTime;
    @Column(name = "status")
    private String status;
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "creationDate")
    private Date creationDate;
    @Column(name = "lastUpdatedBy")
    private String lastUpdatedBy;
    @Column(name = "lastUpdateDate")
    private Date lastUpdateDate;

    @Override
    public String toString() {
        return "UserEntity{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", startTime=" + startTime +
               ", stopTime=" + stopTime +
               ", status='" + status + '\'' +
               ", createdBy='" + createdBy + '\'' +
               ", creationDate=" + creationDate +
               ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
               ", lastUpdateDate=" + lastUpdateDate +
               '}';
    }
}
