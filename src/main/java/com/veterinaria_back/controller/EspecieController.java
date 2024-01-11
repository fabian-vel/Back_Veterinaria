package com.veterinaria_back.controller;

import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.service.EspecieService;
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
public class EspecieController {

    private EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping("/especie/getespecies")
    public ResponseEntity<ResponseMessage<List<Especie>>> getEspecie() throws RuntimeException {
        List<Especie> especies;
        try {
            especies = especieService.findGetAllEspecies();
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, especies));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }

    @GetMapping("/especie/getespecie/{id}")
    public ResponseEntity<ResponseMessage<Especie>> getEspecieById(@Valid @PathVariable int id) throws RuntimeException {
        Especie especie;
        try {
            especie = especieService.findGetEspecieById(id);
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, especie));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }
}
