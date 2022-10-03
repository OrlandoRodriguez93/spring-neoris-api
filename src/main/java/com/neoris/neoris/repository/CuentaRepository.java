package com.neoris.neoris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.neoris.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Cuenta findByNumeroCuenta(String numeroCuenta);

}
