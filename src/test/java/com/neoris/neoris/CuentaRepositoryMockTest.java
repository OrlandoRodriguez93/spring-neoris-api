package com.neoris.neoris;

import com.neoris.neoris.entity.Cuenta;
import com.neoris.neoris.enums.TipoCuenta;
import com.neoris.neoris.repository.CuentaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CuentaRepositoryMockTest {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Test
    public void whenSaveCuentaOK_ThenReturnList(){

        Cuenta cuenta = Cuenta.builder()
                .id(11L)
                .numeroCuenta("123456")
                .tipoCuenta(TipoCuenta.AHORRO)
                .saldo(100.00)
                .build();
        cuentaRepository.save(cuenta);

        List<Cuenta> founds = cuentaRepository.findAll();

        Assertions.assertThat(founds.size()).isEqualTo(4);
    }
}
