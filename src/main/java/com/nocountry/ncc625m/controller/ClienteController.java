package com.nocountry.ncc625m.controller;

import com.nocountry.ncc625m.dto.request.MovimientoRequest;
import com.nocountry.ncc625m.dto.response.MovimientoResponse;
import com.nocountry.ncc625m.dto.response.UserResponse;
import com.nocountry.ncc625m.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/newDebit")
    public ResponseEntity<MovimientoResponse> makeDebit(@RequestHeader(name = "Authorization") String token,
                                                        @Valid @RequestBody MovimientoRequest movimientoRequest){

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.createDebit(token, movimientoRequest));

    }

    @PostMapping("/newDeposit")
    public ResponseEntity<MovimientoResponse> makeDeposit(@RequestHeader(name = "Authorization") String token,
                                                          @Valid @RequestBody MovimientoRequest movimientoRequest){

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.createDeposit(token, movimientoRequest));

    }

    /*@PostMapping("/{id}/externalPayment")
    public ResponseEntity<MovimientoResponse> externalPayment(@PathVariable Long id, @Valid @RequestBody MovimientoRequest movimientoRequest){

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.externalPayment(id, movimientoRequest));
    }*/

    @PostMapping()
    public ResponseEntity<MovimientoResponse> transferTo(@RequestHeader(name = "Authorization") String token,
                                                         @RequestParam(name = "transferTo", required = true) Long idTo,
                                                         @Valid @RequestBody MovimientoRequest movimientoRequest){

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.transferTo(token, idTo, movimientoRequest));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getById(id));

    }
}
