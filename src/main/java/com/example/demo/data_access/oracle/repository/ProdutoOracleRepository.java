package com.example.demo.data_access.oracle.repository;

import com.example.demo.data_access.oracle.model.ProdutoOracle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
                p.endereco_foto
            FROM 
                PRODUTOS p
            INNER JOIN 
                ESTOQUES e ON p.produto_id = e.produto_id
            LEFT JOIN
                IMAGENS_PRODUTOS i ON p.produto_id = i.produto_id
            WHERE 
                p.ATIVO = 'S'
            AND
                p.SINCRONIZADO_API = 'N'
            AND E.empresa_id = '10.654.550/0001-88'
           """, nativeQuery = true)
    List<Object[]> buscarProdutosParaTransferencia();

    @Modifying
    @Transactional
    @Query(value = "UPDATE PRODUTOS SET SINCRONIZADO_API = 'S' WHERE PRODUTO_ID =:PRODUTO_ID", nativeQuery = true)
    int marcarComoSincronizado(@Param("PRODUTO_ID")Long produto_id);
}
