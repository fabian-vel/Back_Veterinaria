package com.veterinaria_back.controller;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.service.DueñoService;
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
public class DueñoController {

    private DueñoService dueñoService;

    public DueñoController(DueñoService dueñoService) {
        this.dueñoService = dueñoService;
    }

    @GetMapping("/dueño/getdueño")
    public ResponseEntity<ResponseMessage<List<Dueño>>> getDueño() {
        List<Dueño> dueños;
        try {
            dueños = dueñoService.findGetAllDueño();
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, dueños));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }

    @PostMapping("/dueño/createdueño")
    public ResponseEntity<ResponseMessage<?>> createOrUpdateDueño(@Valid @RequestBody Dueño dueño) {
        try {
            dueñoService.findInsertOrUpdateDueño(dueño);
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, null));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }

    @DeleteMapping("/dueño/deletedueño/{id}")
    public ResponseEntity<ResponseMessage<?>> deleteDueño(@Valid @PathVariable int id) {
        try {
            dueñoService.findDeleteDueño(id);
            return ResponseEntity.ok(new ResponseMessage<>(MessagesConstant.SUCCESS_CODE, MessagesConstant.SUCCESS_MESSAGE, null));
        }catch (CustomException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(MessagesConstant.ERROR_DEFAULT_CODE, String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, e.getMessage()),null));
        }
    }
}
