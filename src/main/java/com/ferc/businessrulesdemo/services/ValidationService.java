package com.ferc.businessrulesdemo.services;

import com.ferc.businessrulesdemo.dto.Tx;
import com.ferc.businessrulesdemo.entities.ValidatorConfig;

import java.util.List;

public interface ValidationService {
    boolean validate(Tx tx, List<ValidatorConfig> configs);
}
