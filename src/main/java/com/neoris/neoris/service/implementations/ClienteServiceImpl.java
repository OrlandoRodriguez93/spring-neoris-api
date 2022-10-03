package com.neoris.neoris.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.neoris.neoris.converter.ClienteConverter;
import com.neoris.neoris.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.neoris.entity.Cliente;
import com.neoris.neoris.repository.ClienteRepository;
import com.neoris.neoris.service.ClienteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteConverter clienteConverter;

    @Override
    public List<ClienteDTO> all() {
        log.info("Obteniendo todos los clientes");
        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAll();

        for (Cliente cliente: clientes){
            ClienteDTO clienteDTO = clienteConverter.convertEntityToDto(cliente);
            clienteDTOList.add(clienteDTO);
        }
        return clienteDTOList;
    }

    @Override
    public ClienteDTO get(Long id) {
        log.info("Obteniendo cliente {}", id);
        Cliente clienteDB = clienteRepository.findById(id).orElse(null);
        return clienteConverter.convertEntityToDto(clienteDB);
    }

    @Override
    public ClienteDTO create(ClienteDTO clienteDTO) {
        log.info("Guardando cliente en la base de datos");

        Cliente cliente = clienteConverter.convertDtoToEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);

        return clienteConverter.convertEntityToDto(cliente);
    }

    @Override
    public ClienteDTO update(ClienteDTO clienteDTO) {
        log.info("Actualizando cliente en la base de datos");
        Cliente clienteDB = clienteRepository.findById(clienteDTO.getId()).orElse(null);
        if (clienteDB == null)
            return null;
        clienteDB.setNombre(clienteDTO.getNombre());
        clienteDB.setDireccion(clienteDTO.getDireccion());
        clienteDB.setEdad(clienteDTO.getEdad());
        clienteDB.setGenero(clienteDTO.getGenero());
        clienteDB.setIdentificacion(clienteDTO.getIdentificacion());
        clienteDB.setTelefono(clienteDTO.getTelefono());
        clienteRepository.save(clienteDB);
        return clienteConverter.convertEntityToDto(clienteDB);
    }

    @Override
    public ClienteDTO delete(Long id) {
        log.info("Eliminando cliente de la base de datos");
        Cliente clienteDB = clienteRepository.findById(id).orElse(null);
        if (clienteDB == null)
            return null;
        clienteDB.setStatus(false);
        clienteRepository.save(clienteDB);
        return clienteConverter.convertEntityToDto(clienteDB);
    }

}
