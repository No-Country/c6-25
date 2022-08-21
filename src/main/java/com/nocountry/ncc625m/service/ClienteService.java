package com.nocountry.ncc625m.service;

import com.nocountry.ncc625m.dto.request.MovimientoRequest;
import com.nocountry.ncc625m.dto.response.MovimientoResponse;
import com.nocountry.ncc625m.entity.MovimientoEntity;

public interface ClienteService {

    MovimientoResponse createDebit(Long id, MovimientoRequest movimientoRequest);
    MovimientoResponse createDeposit(Long id, MovimientoRequest movimientoRequest);
    MovimientoResponse externalPayment(Long id, MovimientoRequest movimientoRequest);
}
