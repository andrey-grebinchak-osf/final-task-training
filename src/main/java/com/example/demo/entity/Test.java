package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    private Long userId;
    private String displayId;
    private String name;
    private LocalDateTime creationDate;
    private Boolean active;

    private Long customerId;

}
