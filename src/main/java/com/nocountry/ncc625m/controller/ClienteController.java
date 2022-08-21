package com.nocountry.ncc625m.controller;

import com.nocountry.ncc625m.dto.request.MovimientoRequest;
import com.nocountry.ncc625m.dto.response.MovimientoResponse;
import com.nocountry.ncc625m.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/{id}/newDebit")
    public ResponseEntity<MovimientoResponse> makeDebit(@PathVariable Long id, @Valid @RequestBody MovimientoRequest movimientoRequest){

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.createDebit(id, movimientoRequest));

    }

    @PostMapping("/{id}/newDeposit")
    public ResponseEntity<MovimientoResponse> makeDeposit(@PathVariable Long id, @Valid @RequestBody MovimientoRequest movimientoRequest){

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.createDeposit(id, movimientoRequest));

    }

    @PostMapping("/{id}/externalPayment")
    public ResponseEntity<MovimientoResponse> externalPayment(@PathVariable Long id, @Valid @RequestBody MovimientoRequest movimientoRequest){

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.externalPayment(id, movimientoRequest));
    }
}
