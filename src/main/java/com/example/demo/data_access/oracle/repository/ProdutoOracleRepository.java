package com.example.demo.data_access.oracle.repository;

import com.example.demo.data_access.oracle.model.ProdutoOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoOracleRepository extends JpaRepository<ProdutoOracle, Long> {
    @Query(value = """
            SELECT 
                p.produto_id,
                p.validade,
                p.nome_produto,
                p.custo_venda,
                e.estoque_atual,
                i.imagem_id
            FROM 
                PRODUTOS p
            INNER JOIN 
                ESTOQUES e ON p.produto_id = e.produto_id
            LEFT JOIN
                IMAGENS_PRODUTOS i ON p.produto_id = i.produto_id
            WHERE 
                p.ATIVO = 'S'
           """, nativeQuery = true)
    List<Object[]> buscarProdutosParaTransferencia();
}
