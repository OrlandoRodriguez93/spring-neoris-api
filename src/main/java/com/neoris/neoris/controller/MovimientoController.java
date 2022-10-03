package com.neoris.neoris.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.neoris.dto.MovimientoDTO;
import com.neoris.neoris.exceptions.ErrorMessage;
import com.neoris.neoris.exceptions.ErrorResponse;
import com.neoris.neoris.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> all() {
        List<MovimientoDTO> movimientos = movimientoService.all();
        if (movimientos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovimientoDTO> get(@PathVariable(name = "id") Long id) {
        MovimientoDTO movimientoDTO = movimientoService.get(id);
        if (movimientoDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(movimientoDTO);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MovimientoDTO movimientoDTO, BindingResult result) throws ErrorResponse {
        MovimientoDTO movimientoDTO1 = null;
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        try {
            movimientoDTO1 = movimientoService.create(movimientoDTO);
        }catch (ErrorResponse e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoDTO1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody MovimientoDTO movimientoDTO) {
        movimientoDTO.setId(id);
        MovimientoDTO movimientoDTO1 = movimientoService.update(movimientoDTO);
        return ResponseEntity.ok(movimientoDTO1);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MovimientoDTO> delete(@PathVariable("id") Long id) {
        MovimientoDTO movimientoDTO = movimientoService.delete(id);
        return ResponseEntity.ok(movimientoDTO);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
