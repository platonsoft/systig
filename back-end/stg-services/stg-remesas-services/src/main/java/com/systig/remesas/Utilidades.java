package com.systig.remesas;

import com.systig.remesas.modelos.ITransaccion;
import com.systig.remesas.modelos.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

public class Utilidades {
    @Autowired
    private static ITransaccion iTransaccion;

    public static BigDecimal TasaVES(String pais){
        return BigDecimal.ZERO;
    }

    public static void ActualizaCompletados(){
        System.out.println("Tamano:  " + iTransaccion.findAll().size());
        List<Transaccion> g = iTransaccion.findAll().stream()
                .filter(tr -> tr.getStatus().equals("C"))
                /*.peek(t -> {
                    BigDecimal BV = t.getBitVenta();
                    BigDecimal BC = t.getBitCompra();
                    BigDecimal tasa2 = (BV.divide(BC, MathContext.DECIMAL64));
                    BigDecimal totalG = t.getMontoRecibido().multiply(tasa2).subtract(t.getMontoRecibido().multiply(t.getTasa())).divide(tasa2,MathContext.DECIMAL64);
                    t.setGananciaTotal(totalG);
                    t.setComisioPagar(totalG.multiply(new BigDecimal(0.35)));
                    t.setComisionRetenida(t.getGananciaTotal().subtract(t.getComisioPagar()));

                })*/
                .collect(Collectors.toList());

        //iTransaccion.saveAll(g);
    }
}
