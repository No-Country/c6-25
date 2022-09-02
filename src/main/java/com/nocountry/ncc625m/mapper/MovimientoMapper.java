package com.nocountry.ncc625m.mapper;

import com.nocountry.ncc625m.dto.request.MovimientoRequest;
import com.nocountry.ncc625m.dto.response.MovimientoResponse;
import com.nocountry.ncc625m.entity.MovimientoEntity;
import com.nocountry.ncc625m.entity.UserEntity;
import com.nocountry.ncc625m.utility.MovimientoTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Component
public class MovimientoMapper {

    public MovimientoResponse entityToResponse(MovimientoEntity entity) {

        return MovimientoResponse.builder()
                .movimientoId(entity.getMovimientoId())
                .date(entity.getDate())
                .type(entity.getType())
                .amount(entity.getAmount())
                .from(entity.getFrom())
                .to(entity.getTo())
                .final_balance(entity.getFinal_balance())
                .build();
    }

    public Set<MovimientoResponse> entityListToResponse(Set<MovimientoEntity> movimientos) {

        Set<MovimientoResponse> response = new HashSet<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        if(movimientos != null){
            for(MovimientoEntity entity : movimientos){
                if(calendar.getTimeInMillis() < entity.getDate().getTime()){
                    response.add(entityToResponse(entity));
                }
            }
        }

        return response;
    }

    public MovimientoEntity requestToEntity(UserEntity user, MovimientoRequest movimientoRequest, MovimientoTypeEnum type, Double finalBalance ) {

        return MovimientoEntity.builder()
                .usuario(user)
                .type(type)
                .to(movimientoRequest.getTo())
                .from(movimientoRequest.getFrom())
                .amount(movimientoRequest.getAmount())
                .final_balance(finalBalance)
                .softDelete(Boolean.FALSE)
                .build();
    }

    public MovimientoEntity transferToEntity(UserEntity user, String to, Double amount, String from, MovimientoTypeEnum debito, Double finalBalance) {

        return MovimientoEntity.builder()
                .usuario(user)
                .type(debito)
                .to(to)
                .from(from)
                .amount(amount)
                .final_balance(finalBalance)
                .softDelete(Boolean.FALSE)
                .build();
    }
}