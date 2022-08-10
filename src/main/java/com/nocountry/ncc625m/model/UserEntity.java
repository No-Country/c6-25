package com.nocountry.ncc625m.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name can not be empty")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Lastname can not be empty")
    private String lastname;

    @Column(nullable = false)
    @NotEmpty(message = "Lastname can not be empty")
    private String password;

    @Column
    private double balance;

    @Column
    private String photo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Movement> movements;

}
