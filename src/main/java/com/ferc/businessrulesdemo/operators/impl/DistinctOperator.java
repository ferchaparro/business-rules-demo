package com.ferc.businessrulesdemo.operators.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import com.ferc.businessrulesdemo.operands.Transformer;
import com.ferc.businessrulesdemo.operators.Operator;
import org.springframework.stereotype.Component;

@Component("ne")
public class DistinctOperator implements Operator<String> {
    @Override
    public boolean execute(Transformer<String> operand1, Transformer<String> operand2, Tx tx, ValidatorConfig config) {
        return !operand1.transform(tx, config).equals(operand2.transform(tx, config));
    }
}
