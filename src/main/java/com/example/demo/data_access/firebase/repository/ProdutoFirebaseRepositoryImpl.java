package com.example.demo.data_access.firebase.repository;

import com.example.demo.data_access.firebase.dto.ProdutoFirebaseDTO;
import com.example.demo.data_access.ProdutoDestinoRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoFirebaseRepositoryImpl implements ProdutoDestinoRepository {
    private final Firestore firestore;
    private static final String FIREBASE_COLLECTION = "produtos";

    public ProdutoFirebaseRepositoryImpl(FirebaseApp firebaseApp){
        this.firestore = FirestoreClient.getFirestore(firebaseApp);
    }

    @Override
    public void salvarProdutos(List<ProdutoFirebaseDTO> produtos){

        for (ProdutoFirebaseDTO produto: produtos){
            String documentId = firestore.collection(FIREBASE_COLLECTION).document().getId();

            firestore.collection(FIREBASE_COLLECTION)
                    .document(documentId)
                    .set(produto);
        }
    }
}
