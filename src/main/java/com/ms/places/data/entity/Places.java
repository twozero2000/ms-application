package com.ms.places.data.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Setter
@Getter
@Table(name = "t_places")
public class Places {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name="place_id")
    private String placeId;
    @Column(name="address")
    private String address;
    @Column(name="latitude")
    private BigDecimal latitude;
    @Column(name="longitude")
    private BigDecimal longitude;
    @Column(name="crt_time")
    private LocalDateTime createdTime;
}
