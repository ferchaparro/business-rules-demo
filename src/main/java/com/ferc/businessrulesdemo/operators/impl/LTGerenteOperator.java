package com.ferc.businessrulesdemo.operators.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import com.ferc.businessrulesdemo.operands.Transformer;
import com.ferc.businessrulesdemo.operators.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ltGerente")
@RequiredArgsConstructor
public class LTGerenteOperator implements Operator<Integer> {

    private final ILTOperator<Integer> ltOperator;
    @Override
    public boolean execute(Transformer<Integer> operand1, Transformer<Integer> operand2, Tx tx, ValidatorConfig config) {
        return ltOperator.execute(operand1, operand2, tx, config) || tx.getHuellaGerente()!=null;
    }
}
