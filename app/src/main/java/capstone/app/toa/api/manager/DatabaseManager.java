package capstone.app.toa.api.manager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import capstone.app.toa.api.ToaApi;

public class DatabaseManager {

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://toa-capstone-990b0-default-rtdb.asia-southeast1.firebasedatabase.app");
    private DatabaseReference reference;

    public void test() {
        String uid = ToaApi.getInstance().getUserManager().getUid();
        reference = database.getReference("users").child(uid);

        reference.setValue("선재");

        DatabaseReference ch = reference.child("");
    }
}
