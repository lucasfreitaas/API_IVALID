package com.example.demo.controllers;

import com.example.demo.data_access.firebase.model.Pedido;
import com.example.demo.services.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidosController {

    private final PedidoService pedidoService;

    public PedidosController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listarPedidos() throws ExecutionException, InterruptedException {
        return pedidoService.buscarTodosOsPedidos();
    }
}
