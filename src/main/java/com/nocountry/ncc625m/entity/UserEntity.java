package com.nocountry.ncc625m.entity;

import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@Table(name = "usuarios")
@NoArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE User SET soft_delete = true WHERE id=?")
@FilterDef(
        name = "deletedUserFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedUserFilter",
        condition = "deleted = :isDeleted"
)
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @NotBlank(message = "the name can't  be blank")
    private String firstName;

    @NotBlank(message = "the lastName can't  be blank")
    private String lastName;

    @NotBlank
    @Email(message = "enter a correct email")
    private String email;

    @NotBlank
    @NotEmpty(message = "the password can't be null")
    private String password;

    private String photo;

    private Double balance;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<MovimientoEntity> movimientos;

    private boolean softDelete;

    private Timestamp timestamp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_roles",
            joinColumns = {
                @JoinColumn(name = "usuario_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "rol_id")
            })
    private Set<RoleEntity> rol;


}
