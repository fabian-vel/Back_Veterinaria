package com.veterinaria_back.controller;

import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.service.PacienteService;
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
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/paciente/createpaciente")
    public ResponseEntity<ResponseMessage<?>> createOrUpdatePaciente(@Valid @RequestBody Paciente paciente) {
        try {
            pacienteService.findSaveOrUpdatePaciente(paciente);
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, null));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }

    @DeleteMapping("/paciente/deletepaciente/{id}")
    public ResponseEntity<ResponseMessage<?>> deletePaciente(@Valid @PathVariable int id) {
        try {
            pacienteService.findDeletePacinete(id);
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, null));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }

    @GetMapping("/paciente/getpaciente")
    public ResponseEntity<ResponseMessage<List<Paciente>>> getPaciente() throws RuntimeException {
        List<Paciente> pacientes;
        try {
            pacientes = pacienteService.findGetAllPaciente();
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, pacientes));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }
}
