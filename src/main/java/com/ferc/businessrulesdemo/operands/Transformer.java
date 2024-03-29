package com.ferc.businessrulesdemo.operands;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;


public interface Transformer<T> {
    T transform(Tx tx, ValidatorConfig config);
}
