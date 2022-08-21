package com.nocountry.ncc625m.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@Table
@NoArgsConstructor
@Data
@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;
    private String Description;
    @Column(name = "timestamp")
    private Timestamp timestamp;

}
