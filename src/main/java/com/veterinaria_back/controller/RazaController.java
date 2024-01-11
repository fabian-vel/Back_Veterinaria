package com.veterinaria_back.controller;

import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.service.RazaService;
import com.veterinaria_back.util.MessagesConstant;
import com.veterinaria_back.util.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class RazaController {

    private RazaService razaService;

    public RazaController(RazaService razaService) {
        this.razaService = razaService;
    }

    @GetMapping("/raza/getRazas")
    public ResponseEntity<ResponseMessage<List<Raza>>> getRaza() {
        List<Raza> razas;
        try {
            razas = razaService.findGetAllRaza();
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, razas));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }

    @GetMapping("/raza/getoneraza/{id}")
    public ResponseEntity<ResponseMessage<Raza>> getOneRaza(@Valid @PathVariable int id) {
        Raza raza;
        try {
            raza = razaService.findGetRazaById(id);
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, raza));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }

    @GetMapping("/raza/getrazaespecie/{id_especie}")
    public ResponseEntity<ResponseMessage<List<Raza>>> getRazaByEspecie(@Valid @PathVariable int id_especie) {
        List<Raza> razas;
        try {
            razas = razaService.findGetRazaByEspecie(id_especie);
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, razas));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }
}
