package iut.outili.profTrackerAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import iut.outili.profTrackerAPI.Firebase.FirebaseInitializer;
import reactor.core.publisher.Mono;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.v1.FirestoreClient;
import com.google.firebase.database.*;
import com.google.firebase.internal.NonNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfRestController {

    Boolean isFirebaseInitilised = false;
    DatabaseReference professeursRef;
    Firestore db;

    public void initFirebase() {
        FirebaseInitializer.initialize();
        isFirebaseInitilised = true;
        professeursRef = FirebaseDatabase.getInstance().getReference("professeurs");
    }

    // tester connexion firebase
    @GetMapping("/listerprofs")
    public Mono<String[]> test() {
        String[] result = FirebaseInitializer.displayProf();
        return Mono.just(result);
    }

    // @GetMapping("/listerprofs")
    public Mono<String[]> getProfs() {

        if (!isFirebaseInitilised) {
            initFirebase();
        }

        return Mono.create(data -> {

            List<String> profInfoList = new ArrayList<>();

            // récupération des données
            professeursRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("Données récupérées");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Professeur professeur = snapshot.getValue(Professeur.class);
                        System.out.println(professeur.toString());
                        if (professeur != null) {
                            profInfoList.add(professeur.toString());
                        }
                    }
                    System.out.println(profInfoList.toString());
                    String[] profsArray = profInfoList.toArray(new String[0]);
                    data.success(profsArray); // émet la liste quand elle est prête
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    data.error(databaseError.toException());
                }
            });

        });
    }
}
