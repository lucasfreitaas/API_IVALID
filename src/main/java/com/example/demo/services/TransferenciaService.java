package com.example.demo.services;

import com.example.demo.data_access.firebase.dto.ProdutoFirebaseDTO;
import com.example.demo.data_access.oracle.repository.ProdutoOracleRepository;
import com.example.demo.data_access.ProdutoDestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    private final ProdutoOracleRepository oracleRepository;
    private final ProdutoDestinoRepository firebaseRepository;

    @Autowired
    public TransferenciaService(ProdutoOracleRepository oracleRepository,
                                ProdutoDestinoRepository destinoRepository){
        this.oracleRepository = oracleRepository;
        this.firebaseRepository = destinoRepository;
    }

    public void transferirProdutosParaFirebase(){
        System.out.println("Iniciando a busca de produtos no Oracle...");
        List<Object[]> produtosOracle = oracleRepository.buscarProdutosParaTransferencia();

        System.out.println("Produtos encontrados: " + produtosOracle.size());

        List<ProdutoFirebaseDTO> produtosFirebase = produtosOracle.stream()
                .map(this::mapearEcalcular)
                .collect(Collectors.toList());

        System.out.println("Mapeamento e cálculos finalizados. Finalizando salvamento!");

        firebaseRepository.salvarProdutos(produtosFirebase);

        System.out.println("Transferência finalizada!");
    }

    private ProdutoFirebaseDTO mapearEcalcular(Object[] dados){
        ProdutoFirebaseDTO firebaseDTO = new ProdutoFirebaseDTO();

        java.math.BigDecimal oldPriceBd = (java.math.BigDecimal) dados[3];
        java.math.BigDecimal quantityBd = (java.math.BigDecimal) dados[4];

        Integer daysValidity = (Integer) dados[1];
        firebaseDTO.setDaysValidity(daysValidity);

        firebaseDTO.setName((String) dados[2]);

        firebaseDTO.setOldPrice(oldPriceBd.doubleValue());

        firebaseDTO.setQuantity(quantityBd.intValue());

        Number imageIdNum = (Number) dados[5];
        firebaseDTO.setImage(String.valueOf(imageIdNum.longValue()));

        firebaseDTO.setNewPrice(0.0);

        firebaseDTO.setPercentualDesc(0);

        firebaseDTO.setStatus("VERDE");

        firebaseDTO.setDateVenc(calcularDataVencimento(daysValidity));

        return firebaseDTO;
    }

    private Long calcularDataVencimento(Integer days){
        if (days == null || days <= 0){
            return null;
        }

        return Instant.now()
                .plus(days, ChronoUnit.DAYS)
                .toEpochMilli();
    }
}
