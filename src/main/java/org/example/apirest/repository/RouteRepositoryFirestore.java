package org.example.apirest.repository;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.model.Document;
import org.example.apirest.model.route.Route;
import org.example.apirest.model.route.RouteFireStore;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class RouteRepositoryFirestore {

    private static final String PATH = "routes";

    private final Firestore firestore;

    @SneakyThrows
    public RouteFireStore findById(Long id) {
        return firestore
                .collection(PATH)
                .document(id.toString())
                .get()
                .get()
                .toObject(RouteFireStore.class);
    }

    @SneakyThrows
    public List<RouteFireStore> findAll() {
        return firestore
                .collection(PATH)
                .get()
                .get()
                .toObjects(RouteFireStore.class);
    }

    @SneakyThrows
    public RouteFireStore save(RouteFireStore route) {
        DocumentReference routeRerence = firestore
                .collection(PATH)
                .document(route.getId().toString());

        routeRerence.set(route);

        return routeRerence.get().get().toObject(RouteFireStore.class);
    }

    public void delete(RouteFireStore route) {
        firestore
                .collection(PATH)
                .document(route.getId().toString())
                .delete();
    }
}
