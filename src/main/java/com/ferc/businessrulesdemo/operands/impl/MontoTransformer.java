package com.ferc.businessrulesdemo.operands.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("monto")
public class MontoTransformer implements IMontoTransformer<Integer> {

    @Override
    public Integer transform(Tx tx, ValidatorConfig config) {
        return tx.getMonto();
    }
}
