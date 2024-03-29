package com.ferc.businessrulesdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidatorConfig {
    private int configValue;
    private OperandEntity operand1;
    private OperandEntity operand2;
    private OperatorEntity operator;
    private String mensajeError;
    private String tipoAccionError;
}
