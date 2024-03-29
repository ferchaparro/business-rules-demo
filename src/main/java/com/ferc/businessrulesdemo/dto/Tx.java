package com.ferc.businessrulesdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tx {
    private int monto;
    private String cuentaTarjeta;
    private int empleado;
    private int caja;
    private int tienda;
    private String huellaGerente;
}
