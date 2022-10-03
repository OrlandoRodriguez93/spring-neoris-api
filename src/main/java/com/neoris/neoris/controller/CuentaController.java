package com.neoris.neoris.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.neoris.dto.CuentaDTO;
import com.neoris.neoris.exceptions.ErrorMessage;
import com.neoris.neoris.service.CuentaService;
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
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> all() {
        List<CuentaDTO> cuentas = cuentaService.all();
        if (cuentas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CuentaDTO> get(@PathVariable(name = "id") Long id) {
        CuentaDTO cuentaDTO = cuentaService.get(id);
        if (cuentaDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cuentaDTO);
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> create(@Valid @RequestBody CuentaDTO cuentaDTO, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        CuentaDTO cuentaDTO1 = cuentaService.create(cuentaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaDTO1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CuentaDTO cuentaDTO) {
        cuentaDTO.setId(id);
        CuentaDTO cuentaDTO1 = cuentaService.update(cuentaDTO);
        return ResponseEntity.ok(cuentaDTO1);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CuentaDTO> delete(@PathVariable("id") Long id) {
        CuentaDTO cuentaDTO = cuentaService.delete(id);
        return ResponseEntity.ok(cuentaDTO);
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
