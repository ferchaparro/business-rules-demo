package com.ferc.businessrulesdemo.operands.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("montoCaja")
public class MontoCajaTransformer implements IMontoCajaTransformer<Integer> {

    @Override
    public Integer transform(Tx tx, ValidatorConfig config) {
        return tx.getCaja() * 1000;
    }
}
