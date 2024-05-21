package com.ms.application.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Data
@Entity
@Setter
@Getter
@Table(name = "t_application")
@TypeDef(name="jsonb", typeClass = JsonBinaryType.class)
public class Application {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name="reference_num")
    private String referenceNum;
    @Column(name="name")
    private String name;
    @Column(name="crt_time")
    private LocalDateTime createdTime;
    @Column(name = "crt_by")
    private String createdBy;
    @Type(type="jsonb")
    @Column(name = "detail", columnDefinition = "jsonb")
    private Serializable details;
}
