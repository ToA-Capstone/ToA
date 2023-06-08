package capstone.app.toa.api.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import capstone.app.toa.api.ToaApi;

public abstract class CustomValueEventListener implements ValueEventListener {

    protected static ToaApi api = ToaApi.getInstance();

    @Override
    public void onCancelled(@NonNull DatabaseError error) {}

}
