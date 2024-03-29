package com.ferc.businessrulesdemo.operators.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import com.ferc.businessrulesdemo.operands.Transformer;
import org.springframework.stereotype.Component;

@Component("lt")
public class LTOperator implements ILTOperator<Integer> {
    @Override
    public boolean execute(Transformer<Integer> operand1, Transformer<Integer> operand2, Tx tx, ValidatorConfig config) {
        return operand1.transform(tx, config) < operand2.transform(tx, config);
    }
}
