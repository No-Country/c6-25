package com.nocountry.ncc625m.service.impl;

import com.nocountry.ncc625m.auth.security.JwtTokenProvider;
import com.nocountry.ncc625m.dto.request.MovimientoRequest;
import com.nocountry.ncc625m.dto.response.*;
import com.nocountry.ncc625m.entity.MovimientoEntity;
import com.nocountry.ncc625m.entity.UserEntity;
import com.nocountry.ncc625m.exception.EmailNotFoundException;
import com.nocountry.ncc625m.exception.IdNotFoundException;
import com.nocountry.ncc625m.exception.InsufficientBalanceException;
import com.nocountry.ncc625m.exception.JwtBadRequestException;
import com.nocountry.ncc625m.mapper.MovimientoMapper;
import com.nocountry.ncc625m.repository.UserRepository;
import com.nocountry.ncc625m.service.ClienteService;
import com.nocountry.ncc625m.utility.MovimientoTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Override
    public MovimientoResponse createDebit(String token, MovimientoRequest movimientoRequest) {

        token = token.replace("Bearer ", "");
        String email = tokenProvider.getJWTUsername(token);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new JwtBadRequestException(HttpStatus.NOT_FOUND, "User Unauthenticated"));
        validateSufficientBalance(user, movimientoRequest);
        Double finalBalance = user.getBalance() - movimientoRequest.getAmount();

        MovimientoEntity movimiento = movimientoMapper.requestToEntity(user, movimientoRequest, MovimientoTypeEnum.DEBITO, finalBalance);

        user.getMovimientos().add(movimiento);
        user.setBalance(finalBalance);
        userRepository.save(user);

        return movimientoMapper.entityToResponse(movimiento);
    }

    @Override
    public MovimientoResponse createDeposit(String token, MovimientoRequest movimientoRequest) {

        token = token.replace("Bearer ", "");
        String email = tokenProvider.getJWTUsername(token);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new JwtBadRequestException(HttpStatus.NOT_FOUND, "User Unauthenticated"));
        Double finalBalance = user.getBalance() + movimientoRequest.getAmount();

        MovimientoEntity movimiento = movimientoMapper.requestToEntity(user, movimientoRequest, MovimientoTypeEnum.DEPOSITO, finalBalance);

        user.getMovimientos().add(movimiento);
        user.setBalance(finalBalance);
        userRepository.save(user);

        return movimientoMapper.entityToResponse(movimiento);
    }

    /*@Override
    public MovimientoResponse externalPayment(Long id, MovimientoRequest movimientoRequest) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        MovimientoEntity movimiento = movimientoMapper.requestToEntity(user, movimientoRequest, MovimientoTypeEnum.PAGO_EXTERNO, user.getBalance());

        user.getMovimientos().add(movimiento);
        userRepository.save(user);

        return movimientoMapper.entityToResponse(movimiento);
    }*/

    @Override
    public MovimientoResponse transferTo(String token, Long idTo, MovimientoRequest movimientoRequest) {

        token = token.replace("Bearer ", "");
        String email = tokenProvider.getJWTUsername(token);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new JwtBadRequestException(HttpStatus.NOT_FOUND, "User Unauthenticated"));
        UserEntity userTo = userRepository.findById(idTo).orElseThrow(() -> new IdNotFoundException(idTo));

        validateSufficientBalance(user, movimientoRequest);

        Double finalBalance = user.getBalance() - movimientoRequest.getAmount();
        Double finalBalanceTo = userTo.getBalance() + movimientoRequest.getAmount();

        MovimientoEntity movimientoFrom = movimientoMapper.transferToEntity(user,
                                                                        userTo.getFirstName()+" "+userTo.getLastName(),
                                                                        movimientoRequest.getAmount(),
                                                                        user.getFirstName()+" "+user.getLastName(),
                                                                        MovimientoTypeEnum.DEBITO,
                                                                        finalBalance);

        MovimientoEntity movimientoTo = movimientoMapper.transferToEntity(userTo,
                                                                        userTo.getFirstName()+" "+userTo.getLastName(),
                                                                        movimientoRequest.getAmount(),
                                                                        user.getFirstName()+" "+user.getLastName(),
                                                                        MovimientoTypeEnum.DEPOSITO,
                                                                        finalBalanceTo);

        user.getMovimientos().add(movimientoFrom);
        userTo.getMovimientos().add(movimientoTo);

        userRepository.save(user);
        userRepository.save(userTo);

        return movimientoMapper.entityToResponse(movimientoFrom);

    }

    @Override
    public UserResponse getById(Long id) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
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