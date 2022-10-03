package com.neoris.neoris.converter;

import com.neoris.neoris.dto.ClienteDTO;
import com.neoris.neoris.entity.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter {

    public ClienteDTO convertEntityToDto(Cliente cliente) {
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        return clienteDTO;
    }

    public Cliente convertDtoToEntity(ClienteDTO clienteDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        return cliente;
    }
}
