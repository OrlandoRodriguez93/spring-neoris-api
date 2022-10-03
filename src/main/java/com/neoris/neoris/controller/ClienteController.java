package com.neoris.neoris.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.neoris.neoris.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.neoris.neoris.exceptions.ErrorMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.neoris.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> all() {
        List<ClienteDTO> clientes = clienteService.all();
        if (clientes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> get(@PathVariable(name = "id") Long id) {
        ClienteDTO clienteDTO = clienteService.get(id);
        if (clienteDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        ClienteDTO clienteDTO1 = clienteService.create(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        ClienteDTO clienteDTO1 = clienteService.update(clienteDTO);
        return ResponseEntity.ok(clienteDTO1);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable("id") Long id) {
        ClienteDTO clienteDTO = clienteService.delete(id);
        return ResponseEntity.ok(clienteDTO);
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
