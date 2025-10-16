package com.example.demo.data_access;

import com.example.demo.data_access.firebase.dto.ProdutoFirebaseDTO;
import java.util.List;

public interface ProdutoDestinoRepository {
    void salvarProdutos(List<ProdutoFirebaseDTO> produtos);
}