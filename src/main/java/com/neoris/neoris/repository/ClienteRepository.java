package com.neoris.neoris.repository;

import com.neoris.neoris.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.neoris.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCuentas(Cuenta cuenta);

}
