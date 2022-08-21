package com.nocountry.ncc625m.entity;

import com.nocountry.ncc625m.utility.MovimientoTypeEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "movements")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@SQLDelete(sql = "UPDATE movimientos SET soft_delete = true WHERE movimiento_id=?")
@Where(clause = "soft_delete = false")
public class MovimientoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private Long movimientoId;

    @CreationTimestamp
    private Timestamp date;

    private MovimientoTypeEnum type;

    private Double amount;

    private Double final_balance;

    @Column(name = "_from")
    private String from;

    @Column(name = "_to")
    private String to;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;

}
