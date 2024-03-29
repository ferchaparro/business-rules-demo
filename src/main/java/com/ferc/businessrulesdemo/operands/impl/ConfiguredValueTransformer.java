package com.ferc.businessrulesdemo.operands.impl;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;
import com.ferc.businessrulesdemo.operands.Transformer;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("configuredValue")
public class ConfiguredValueTransformer implements Transformer<Integer> {
    @Override
    public Integer transform(Tx tx, ValidatorConfig config) {
        return config.getConfigValue();
    }
}
