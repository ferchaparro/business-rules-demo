package com.ferc.businessrulesdemo.operators;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import com.ferc.businessrulesdemo.operands.Transformer;

public interface Operator<T> {
    boolean execute(Transformer<T> operand1, Transformer<T> operand2, Tx tx, ValidatorConfig config);
}
