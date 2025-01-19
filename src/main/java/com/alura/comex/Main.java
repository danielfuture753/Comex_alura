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
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));

        informeSintetico.procesarPedidos(pedidos);

        System.out.println("#### INFORME DE VALORES TOTALES");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %d%n",
                informeSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUCTOS VENDIDOS: %d%n",
                informeSintetico.getTotalDeProductosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %d%n",
                informeSintetico.getTotalDeCategorias());
        /*
         * System.out.printf("- MONTO DE VENTAS: %s%n",
         * informeSintetico.format(informeSintetico.getMontoDeVentas().setScale(2,
         * RoundingMode.HALF_DOWN)));
         */
        System.out.printf("- MONTO DE VENTAS: %s%n",
                formatoMoneda.format(informeSintetico.getMontoDeVentas().setScale(2, RoundingMode.HALF_DOWN)));
        Pedido pedidoMasBarato = informeSintetico.getPedidoMasBarato();
        BigDecimal montoMasBarato = pedidoMasBarato.getPrecio()
                .multiply(new BigDecimal(pedidoMasBarato.getCantidad()))
                .setScale(2, RoundingMode.HALF_DOWN);
        System.out.printf("- PEDIDO MAS BARATO: %s (%s)%n",
                formatoMoneda.format(montoMasBarato),
                pedidoMasBarato.getProducto());

        Pedido pedidoMasCaro = informeSintetico.getPedidoMasCaro();
        BigDecimal montoMasCaro = pedidoMasCaro.getPrecio()
                .multiply(new BigDecimal(pedidoMasCaro.getCantidad()))
                .setScale(2, RoundingMode.HALF_DOWN);
        System.out.printf("- PEDIDO MAS CARO: %s (%s)%n",
                formatoMoneda.format(montoMasCaro),
                pedidoMasCaro.getProducto());

    }
}
