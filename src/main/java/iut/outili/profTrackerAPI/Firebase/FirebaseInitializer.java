package iut.outili.profTrackerAPI.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
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
        //données sous le document KbiDScOHElCDo3UIIU3P dans la collection professeurs
        String documentId = "KbiDScOHElCDo3UIIU3P";
        DocumentReference profDocumentRef = db.collection("professeurs").document(documentId);

        try {
            DocumentSnapshot document = profDocumentRef.get().get();
            if (document.exists()) {
                System.out.println("données doc " + documentId + " : " + document.getData());
                profs.add(document.getData().toString());
            } else {
                System.out.println("aucun document trouvé");
            }
        } catch (Exception e) {
            System.out.println("erreur : " + e.getMessage());
        }

        String[] result = (String[]) profs.toArray(new String[0]);

        return result;
    }
}
