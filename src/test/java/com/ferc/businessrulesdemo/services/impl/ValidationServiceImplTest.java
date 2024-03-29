package com.ferc.businessrulesdemo.services.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.OperandEntity;
import com.ferc.businessrulesdemo.entities.OperatorEntity;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import com.ferc.businessrulesdemo.exceptions.ValidationException;
import com.ferc.businessrulesdemo.services.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationServiceImplTest {

    @Autowired
    private ValidationService validationService;

    private ValidatorConfig config1;
    private ValidatorConfig config2;
    private ValidatorConfig config3;
    private ValidatorConfig config4;

    @BeforeEach
    void setUp() {
        var lt = OperatorEntity.builder().bean("lt").build();
        var ltGerente = OperatorEntity.builder().bean("ltGerente").build();
        var gt = OperatorEntity.builder().bean("gt").build();
        var distinct = OperatorEntity.builder().bean("distinct").build();

        var montoCaja = OperandEntity.builder().bean("montoCaja").build();
        var configuredValue = OperandEntity.builder().bean("configuredValue").build();
        var monto = OperandEntity.builder().bean("monto").build();
        var montoCajaMasMontoTx = OperandEntity.builder().bean("montoCajaMasMontoTx").build();

        config1 = ValidatorConfig.builder()
                .operator(lt)
                .operand1(montoCaja)
                .operand2(configuredValue)
                .configValue(30_000)
                .mensajeError("El monto de caja no puede ser mayor a 30,000")
                .tipoAccionError("RECHAZAR")
                .build();

        config2 = ValidatorConfig.builder()
                .operator(ltGerente)
                .operand1(monto)
                .operand2(configuredValue)
                .configValue(3_000)
                .mensajeError("para montos mayores a 3,000, necesita autorizacion de gerente")
                .tipoAccionError("REQUIERE_AUTORIZACION_GERENTE")
                .build();

        config3 = ValidatorConfig.builder()
                .operator(lt)
                .operand1(monto)
                .operand2(configuredValue)
                .configValue(5_000)
                .mensajeError("no se pueden hacer transacciones mayores a 5,000")
                .tipoAccionError("RECHAZAR")
                .build();

        config4 = ValidatorConfig.builder()
                .operator(lt)
                .operand1(montoCajaMasMontoTx)
                .operand2(configuredValue)
                .configValue(30_000)
                .mensajeError("El monto de caja mas el monto de la transaccion no puede ser mayor a 30,000")
                .tipoAccionError("RECHAZAR")
                .build();
    }

    @Test
    void deberiaPasarTodasLasValidaciones() {
        var reglas = List.of(config1, config2, config3, config4);
        var tx = Tx.builder()
                .monto(1000)
                .caja(28)
                .build();
        assertTrue(validationService.validate(tx, reglas));
    }

    @Test
    void deberiaFallarPorMontoCajaMayorA30_000() {
        var reglas = List.of(config1, config2, config3, config4);
        var tx = Tx.builder()
                .monto(1000)
                .caja(31)
                .build();
        var error = assertThrows(ValidationException.class, () -> validationService.validate(tx, reglas));
        assertEquals("RECHAZAR", error.getAction());
        assertEquals("El monto de caja no puede ser mayor a 30,000", error.getMessage());
    }

    @Test
    void deberiaFallarPorMontoMayorA3_000() {
        var reglas = List.of(config1, config2, config3, config4);
        var tx = Tx.builder()
                .monto(3001)
                .caja(2)
                .build();
        var error = assertThrows(ValidationException.class, () -> validationService.validate(tx, reglas));
        assertEquals("REQUIERE_AUTORIZACION_GERENTE", error.getAction());
        assertEquals("para montos mayores a 3,000, necesita autorizacion de gerente", error.getMessage());
    }

    @Test
    void deberiaValidarOkPorMontoMayorA3_000HuellaGerente() {
        var reglas = List.of(config1, config2, config3, config4);
        var tx = Tx.builder()
                .monto(3001)
                .caja(2)
                .huellaGerente("template")
                .build();
        assertTrue(validationService.validate(tx, reglas));
    }

    @Test
    void deberiaFallarPorMontoMayorA5_000() {
        var reglas = List.of(config1, config2, config3, config4);
        var tx = Tx.builder()
                .monto(5001)
                .caja(2)
                .huellaGerente("template")
                .build();
        var error = assertThrows(ValidationException.class, () -> validationService.validate(tx, reglas));
        assertEquals("RECHAZAR", error.getAction());
        assertEquals("no se pueden hacer transacciones mayores a 5,000", error.getMessage());
    }

    @Test
    void deberiaFallarPorMontoCajaMasMontoTxMayorA30_000() {
        var reglas = List.of(config1, config2, config3, config4);
        var tx = Tx.builder()
                .monto(1001)
                .caja(29)
                .build();
        var error = assertThrows(ValidationException.class, () -> validationService.validate(tx, reglas));
        assertEquals("RECHAZAR", error.getAction());
        assertEquals("El monto de caja mas el monto de la transaccion no puede ser mayor a 30,000", error.getMessage());
    }

}