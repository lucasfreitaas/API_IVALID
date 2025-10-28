package com.example.demo.data_access.firebase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedido {
    private String name;
    private String productId;
    private int quantity;
    private double subtotal;
}
