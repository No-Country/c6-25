package com.nocountry.ncc625m.service;

import com.nocountry.ncc625m.dto.request.MovimientoRequest;
import com.nocountry.ncc625m.dto.response.MovimientoResponse;
import com.nocountry.ncc625m.dto.response.UserResponse;
import com.nocountry.ncc625m.entity.MovimientoEntity;

public interface ClienteService {

    MovimientoResponse createDebit(String token, MovimientoRequest movimientoRequest);
    MovimientoResponse createDeposit(String token, MovimientoRequest movimientoRequest);
    //MovimientoResponse externalPayment(Long id, MovimientoRequest movimientoRequest);
    MovimientoResponse transferTo(String token, Long idTo, MovimientoRequest movimientoRequest);
    UserResponse getById(Long id);
}
