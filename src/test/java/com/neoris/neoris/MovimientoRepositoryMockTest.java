package com.neoris.neoris;

import com.neoris.neoris.entity.Cuenta;
import com.neoris.neoris.entity.Movimiento;
import com.neoris.neoris.enums.TipoMovimiento;
import com.neoris.neoris.enums.ValorMovimiento;
import com.neoris.neoris.repository.MovimientoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MovimientoRepositoryMockTest {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Test
    public void whenSaveMovimientoOK_ThenReturnList(){

        Movimiento movimiento = new Movimiento();
        movimiento.setId(11L);
        movimiento.setTipoMovimiento(TipoMovimiento.RETIRO);
        movimiento.setValor(ValorMovimiento.CREDITO);
        movimiento.setFecha(new Date());
        movimiento.setSaldo(100.00);
        movimientoRepository.save(movimiento);

        List<Movimiento> founds = movimientoRepository.findAll();

        Assertions.assertThat(founds.size()).isEqualTo(1);
    }
}
