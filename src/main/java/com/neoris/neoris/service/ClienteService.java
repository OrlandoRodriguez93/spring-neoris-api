package com.neoris.neoris.service;

import java.util.List;

import com.neoris.neoris.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import com.neoris.neoris.entity.Cliente;

@Service
public interface ClienteService {

    public List<ClienteDTO> all();

    public ClienteDTO get(Long id);

    public ClienteDTO create(ClienteDTO clienteDTO);

    public ClienteDTO update(ClienteDTO clienteDTO);

    public ClienteDTO delete(Long id);

}
