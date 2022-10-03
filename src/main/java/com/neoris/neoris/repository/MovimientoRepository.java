package com.neoris.neoris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neoris.neoris.entity.Movimiento;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query(nativeQuery = true, value = "SELECT  " +
            "MO.FECHA, " +
            "PE.NOMBRE, " +
            "CU.NUMERO_CUENTA, " +
            "CU.TIPO_CUENTA, " +
            "CASE WHEN MO.TIPO_MOVIMIENTO = 'DEPOSITO' THEN CU.SALDO+MO.SALDO ELSE CU.SALDO-MO.SALDO END SALDO_DISPONIBLE, " +
            "MO.STATUS, " +
            "MO.SALDO MOVIMIENTO, " +
            "CU.SALDO  SALDO_INICIAL " +
            "FROM TBL_CUENTA CU  " +
            "LEFT JOIN TBL_CLIENTE CL ON CL.ID = CU.CLIENTE_ID " +
            "LEFT JOIN TBL_PERSONA PE ON PE.ID = CL.ID_PERSONA " +
            "LEFT JOIN TBL_MOVIMIENTO MO ON MO.CUENTA_ID=CU.ID " +
            "WHERE MO.FECHA >= :fechainicial AND MO.FECHA  <= :fechafinal AND CLIENTE_ID=:clienteid")
    List<Map<String, Object>> estadoCuenta(@Param("fechainicial") String fechainicial, @Param("fechafinal") String fechafinal, @Param("clienteid") Long clienteid);

}
