package com.example.demo.controllers;

import com.example.demo.services.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transferencia")
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service){
        this.service = service;
    }

    @PostMapping("/produtos")
    public ResponseEntity<String > iniciarTransferenciaDeProdutos(){
        try{
            service.transferirProdutosParaFirebase();

            return ResponseEntity.ok("Transferência de produtos Oracle -> Firestore iniciada com sucesso!");

        } catch (Exception e){
            System.err.println("Erro durante a transferência: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.internalServerError().body("Falha na transferência: " + e.getMessage());
        }
    }
}
