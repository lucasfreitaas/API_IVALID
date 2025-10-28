package com.example.demo.data_access.firebase.model;

import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido {
    private String endereco;
    private String formaPagamento;
    private List<ItemPedido> itens;
    private String status;
    private Timestamp timestamp;
    private double total;
    private String userId;
}
