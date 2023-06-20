package com.sisp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "stop_time")
    private Date stopTime;
    @Column(name = "status")
    private String status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Column(name = "last_update_date")
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
