package com.codemaniac.taxi.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDto {
    private Long invoiceId;
    private LocalDate date;
    private double bookingFee;
    private double tripFare;
    private double tips;
    private double salesTax;
    private double total;
}
