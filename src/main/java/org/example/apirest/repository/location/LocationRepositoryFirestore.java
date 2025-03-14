package org.example.apirest.repository.location;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.example.apirest.model.location.Location;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class LocationRepositoryFirestore{
    private static final String ROUTE_PATH = "routes";
    private static final String LOCATION_PATH = "locations";
    
    private final Firestore firestore;
    
    @SneakyThrows
    public List<Location> findAllByRouteId(Long id){
        return firestore
                .collection(ROUTE_PATH + "/" + id.toString() + "/" + LOCATION_PATH)
                .get()
                .get()
                .toObjects(Location.class);
    }

    @SneakyThrows
    public Location findByIdInRoute(Long locationId, Long routeId){
        return firestore
                .collection(ROUTE_PATH + "/" + routeId.toString() + "/" + LOCATION_PATH)
                .document(locationId.toString())
                .get()
                .get()
                .toObject(Location.class);
    }

    @SneakyThrows
    public Location saveInRoute(Location location , Long id) {

        DocumentReference routeRerence = firestore
                .collection(ROUTE_PATH + "/" + id.toString() + "/" + LOCATION_PATH)
                .document();

        routeRerence.set(location).get();

        return routeRerence.get()
                .get()
                .toObject(Location.class);
    }

    public void deleteFromRoute(Location location , Long id) {
        firestore
                .collection(ROUTE_PATH + "/" + id.toString() + "/" + LOCATION_PATH)
                .document(location.getId().toString())
                .delete();
    }

    @SneakyThrows
    public void deleteAllFromRoute(Long routeId) {
        com.google.cloud.firestore.DocumentReference ref = firestore
                .collection(ROUTE_PATH)
                .document(routeId.toString());

        ref.listCollections().forEach(
                collection -> collection.listDocuments().forEach(
                        DocumentReference::delete
                )
        );

        ref.delete();
    }
}
