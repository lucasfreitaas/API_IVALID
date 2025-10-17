package com.example.demo.data_access.firebase.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoFirebaseDTO {
    private Integer daysValidity;
    private String name;
    private Double oldPrice;
    private Integer quantity;
    private String urlImagem;
    private Double newPrice;
    private Integer percentualDesc;
    private String status;
    private Long dateVenc;
}
