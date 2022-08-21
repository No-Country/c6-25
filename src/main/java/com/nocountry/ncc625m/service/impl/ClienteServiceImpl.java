package com.nocountry.ncc625m.service.impl;

import com.nocountry.ncc625m.dto.request.MovimientoRequest;
import com.nocountry.ncc625m.dto.response.*;
import com.nocountry.ncc625m.entity.MovimientoEntity;
import com.nocountry.ncc625m.entity.UserEntity;
import com.nocountry.ncc625m.exception.IdNotFoundException;
import com.nocountry.ncc625m.exception.InsufficientBalanceException;
import com.nocountry.ncc625m.mapper.MovimientoMapper;
import com.nocountry.ncc625m.repository.UserRepository;
import com.nocountry.ncc625m.service.ClienteService;
import com.nocountry.ncc625m.utility.MovimientoTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Override
    public MovimientoResponse createDebit(Long id, MovimientoRequest movimientoRequest) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        validateSufficientBalance(user, movimientoRequest);
        Double finalBalance = user.getBalance() - movimientoRequest.getAmount();

        MovimientoEntity movimiento = movimientoMapper.requestToEntity(user, movimientoRequest, MovimientoTypeEnum.DEBITO, finalBalance);

        user.getMovimientos().add(movimiento);
        user.setBalance(finalBalance);
        userRepository.save(user);

        return movimientoMapper.entityToResponse(movimiento);
    }

    @Override
    public MovimientoResponse createDeposit(Long id, MovimientoRequest movimientoRequest) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        Double finalBalance = user.getBalance() + movimientoRequest.getAmount();

        MovimientoEntity movimiento = movimientoMapper.requestToEntity(user, movimientoRequest, MovimientoTypeEnum.DEPOSITO, finalBalance);

        user.getMovimientos().add(movimiento);
        user.setBalance(finalBalance);
        userRepository.save(user);

        return movimientoMapper.entityToResponse(movimiento);
    }

    @Override
    public MovimientoResponse externalPayment(Long id, MovimientoRequest movimientoRequest) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        MovimientoEntity movimiento = movimientoMapper.requestToEntity(user, movimientoRequest, MovimientoTypeEnum.PAGO_EXTERNO, user.getBalance());

        user.getMovimientos().add(movimiento);
        userRepository.save(user);

        return movimientoMapper.entityToResponse(movimiento);
    }

    //---------------------------------Métodos privados-----------------------------------------------------------------

    private void validateSufficientBalance(UserEntity cliente, MovimientoRequest request){

        Double initial_balance = cliente.getBalance();
        Double newAmount = request.getAmount();

        if(initial_balance < newAmount){
            throw new InsufficientBalanceException(initial_balance, newAmount);
        }
    }
}