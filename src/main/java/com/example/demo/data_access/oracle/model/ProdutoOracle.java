package com.example.demo.data_access.oracle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "PRODUTOS")
@Data
public class ProdutoOracle {
    @Id
    @Column(name = "PRODUTO_ID")
    private Long produto_id;

    @Column(name = "VALIDADE")
    private Integer daysValidity;

    @Column(name = "PRECO_VENDA")
    private Double oldPrice;
}
