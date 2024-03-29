package com.ferc.businessrulesdemo.services.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import com.ferc.businessrulesdemo.exceptions.ValidationException;
import com.ferc.businessrulesdemo.operands.Transformer;
import com.ferc.businessrulesdemo.operators.Operator;
import com.ferc.businessrulesdemo.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {
    private final ApplicationContext applicationContext;


    @Override
    public boolean validate(Tx tx, List<ValidatorConfig> configs) {
        configs.forEach(config -> {
            var operator = applicationContext.getBean(config.getOperator().getBean(), Operator.class);
            var operand1 = applicationContext.getBean(config.getOperand1().getBean(), Transformer.class);
            var operand2 = applicationContext.getBean(config.getOperand2().getBean(), Transformer.class);
            if (!operator.execute(operand1, operand2, tx, config)) {
                throw new ValidationException(config.getTipoAccionError(), config.getMensajeError());
            }
        });
        return true;

    }
}
