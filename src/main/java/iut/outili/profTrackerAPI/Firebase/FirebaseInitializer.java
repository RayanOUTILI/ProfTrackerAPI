package iut.outili.profTrackerAPI.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class FirebaseInitializer {

    public static void initialize() {

        try {
            FileInputStream serviceAccount = new FileInputStream("androidfilrouge-firebase-secretdata.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://androidfilrouge.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] displayProf() {
        List profs = new ArrayList<String>();

        // on se connecte à la base de données Firestore
        Firestore db = FirestoreClient.getFirestore();

        System.out.println("Liste tables");
        for (CollectionReference collectionRef : db.listCollections()) {
            System.out.println(collectionRef.getId());
        }

        // on affiche tous les professeurs dans la table professeur
        CollectionReference professeurRef = db.collection("professeurs");
        System.out.println("Liste  :");
        // attention noeud intermédiaire entre la table et les données
        professeurRef.listDocuments().forEach(documentReference -> {
            documentReference.listCollections().forEach(collectionReference -> {
                System.out.println(collectionReference.getId());
                profs.add(collectionReference.getId());
            });
        });
        String[] result = (String[]) profs.toArray(new String[0]);

        return result;
    }
}
