package capstone.app.toa.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;

public class UserEmailCheckListener extends CustomValueEventListener {

    private boolean changed = false;

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String email = snapshot.getValue(String.class);
        String googleEmail = ToaApi.getUserManager().getEmail();

        if (!changed && (email == null || !email.equals(googleEmail))) {
            ToaApi.getDatabaseManager().getUserReference().child("email").setValue(googleEmail);
            changed = true;
        }

    }

}
