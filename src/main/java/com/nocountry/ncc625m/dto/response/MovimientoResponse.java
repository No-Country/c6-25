package com.nocountry.ncc625m.dto.response;

import com.nocountry.ncc625m.utility.MovimientoTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class MovimientoResponse {

    private Long movimientoId;

    private Timestamp date;

    private MovimientoTypeEnum type;

    private Double amount;

    private Double final_balance;

    private String from;

    private String to;

}
