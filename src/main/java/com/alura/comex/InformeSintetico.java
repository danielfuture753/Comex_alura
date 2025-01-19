package com.alura.comex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InformeSintetico {

    private int totalDeProductosVendidos;
    private int totalDePedidosRealizados;
    private BigDecimal montoDeVentas;
    private Pedido pedidoMasBarato;
    private Pedido pedidoMasCaro;
    private int totalDeCategorias;
    
    public void procesarPedidos(List<Pedido> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            throw new IllegalArgumentException("La lista de pedidos no puede ser nula o vacía");
        }
        
        inicializarContadores();
        Set<String> categorias = new HashSet<>();
        
        pedidos.forEach(pedido -> {
            if (pedido != null) {
                procesarPedido(pedido);
                actualizarPedidosExtremos(pedido);
                procesarCategoria(pedido.getCategoria(), categorias);
            }
        });
        
        totalDeCategorias = categorias.size();
    }
    
    private void inicializarContadores() {
        totalDeProductosVendidos = 0;
        totalDePedidosRealizados = 0;
        montoDeVentas = BigDecimal.ZERO;
        pedidoMasBarato = null;
        pedidoMasCaro = null;
        totalDeCategorias = 0;
    }
    
    private void procesarPedido(Pedido pedido) {
        totalDeProductosVendidos += pedido.getCantidad();
        totalDePedidosRealizados++;
        montoDeVentas = montoDeVentas.add(calcularMontoPedido(pedido));
    }
    
    private BigDecimal calcularMontoPedido(Pedido pedido) {
        return pedido.getPrecio().multiply(new BigDecimal(pedido.getCantidad()));
    }
    
    private void actualizarPedidosExtremos(Pedido pedidoActual) {
        BigDecimal montoPedidoActual = calcularMontoPedido(pedidoActual);
        
        actualizarPedidoMasBarato(pedidoActual, montoPedidoActual);
        actualizarPedidoMasCaro(pedidoActual, montoPedidoActual);
    }
    
    private void actualizarPedidoMasBarato(Pedido pedidoActual, BigDecimal montoPedidoActual) {
        if (pedidoMasBarato == null || 
            montoPedidoActual.compareTo(calcularMontoPedido(pedidoMasBarato)) < 0) {
            pedidoMasBarato = pedidoActual;
        }
    }
    
    private void actualizarPedidoMasCaro(Pedido pedidoActual, BigDecimal montoPedidoActual) {
        if (pedidoMasCaro == null || 
            montoPedidoActual.compareTo(calcularMontoPedido(pedidoMasCaro)) > 0) {
            pedidoMasCaro = pedidoActual;
        }
    }
    
    private void procesarCategoria(String categoria, Set<String> categorias) {
        categorias.add(categoria);
    }

        public int getTotalDeProductosVendidos() {
            return totalDeProductosVendidos;
        }

        public int getTotalDePedidosRealizados() {
            return totalDePedidosRealizados;
        }

        public BigDecimal getMontoDeVentas() {
            return montoDeVentas;
        }

        public Pedido getPedidoMasBarato() {
            return pedidoMasBarato;
        }

        public Pedido getPedidoMasCaro() {
            return pedidoMasCaro;
        }

        public int getTotalDeCategorias() {
            return totalDeCategorias;
        }

    

}