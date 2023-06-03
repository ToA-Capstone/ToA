package capstone.app.toa.api.manager;

import com.google.firebase.auth.FirebaseAuth;

public class LoginManager {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public String getUserUid() {
        if (firebaseAuth.getCurrentUser() == null) {
            return null;
        }
        return firebaseAuth.getCurrentUser().getUid();
    }

}
