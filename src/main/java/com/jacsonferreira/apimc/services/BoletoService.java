package com.jacsonferreira.apimc.services;

import java.util.Calendar;
import java.util.Date;

import com.jacsonferreira.apimc.domain.BoletoPayment;

import org.springframework.stereotype.Service;

@Service
public class BoletoService {
    public void preencherPagamentoComBoleto(BoletoPayment boletoPayment, Date instanteDoPedido) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        boletoPayment.setDateExpiry(cal.getTime() );
    }
}