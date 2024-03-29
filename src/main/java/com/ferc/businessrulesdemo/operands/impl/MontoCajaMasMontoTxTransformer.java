package com.ferc.businessrulesdemo.operands.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import com.ferc.businessrulesdemo.operands.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("montoCajaMasMontoTx")
@RequiredArgsConstructor
public class MontoCajaMasMontoTxTransformer implements Transformer<Integer> {

    private final IMontoTransformer<Integer> montoTransformer;
    private final IMontoCajaTransformer<Integer> montoCajaTransformer;

    @Override
    public Integer transform(Tx tx, ValidatorConfig config) {
        return montoTransformer.transform(tx, config) + montoCajaTransformer.transform(tx, config);
    }
}
