package com.example.demo.services;

import com.example.demo.data_access.firebase.model.Pedido;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PedidoService {


    private static final String COLLECTION_NAME = "pedidos";

    public List<Pedido> buscarTodosOsPedidos() throws ExecutionException, InterruptedException{

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Pedido> pedidos = new ArrayList<>();

        for (QueryDocumentSnapshot document: documents){
            pedidos.add(document.toObject(Pedido.class));
        }

        return pedidos;
    }
}
