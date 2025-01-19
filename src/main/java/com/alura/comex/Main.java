package com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;

import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        ProcesadorDeCsv procesadorDeCsv = new ProcesadorDeCsv();
        ArrayList<Pedido> pedidos = procesadorDeCsv.procesadorDeCsv();

        InformeSintetico informeSintetico = new InformeSintetico();

        informeSintetico.procesarPedidos(pedidos);

        System.out.println("#### INFORME DE VALORES TOTALES");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", informeSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUCTOS VENDIDOS: %s\n", informeSintetico.getTotalDeProductosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", informeSintetico.getTotalDeCategorias());
        System.out.printf("- MONTO DE VENTAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("es", "AR"))
                .format(informeSintetico.getMontoDeVentas().setScale(2, RoundingMode.HALF_DOWN))); 
        System.out.printf("- PEDIDO MAS BARATO: %s (%s)\n",
                NumberFormat.getCurrencyInstance(new Locale("es", "AR"))
                        .format(informeSintetico.getPedidoMasBarato().getPrecio()
                                .multiply(new BigDecimal(informeSintetico.getPedidoMasBarato().getCantidad()))
                                .setScale(2, RoundingMode.HALF_DOWN)),
                informeSintetico.getPedidoMasBarato().getProducto());
        System.out.printf("- PEDIDO MAS CARO: %s (%s)\n",
                NumberFormat.getCurrencyInstance(new Locale("es", "AR"))
                        .format(informeSintetico.getPedidoMasCaro().getPrecio()
                                .multiply(new BigDecimal(informeSintetico.getPedidoMasCaro().getCantidad()))
                                .setScale(2, RoundingMode.HALF_DOWN)),
                informeSintetico.getPedidoMasCaro().getProducto());
    }

}
