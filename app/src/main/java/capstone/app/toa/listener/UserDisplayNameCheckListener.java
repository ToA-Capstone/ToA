package capstone.app.toa.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;

public class UserDisplayNameCheckListener extends CustomValueEventListener {

    private boolean changed = false;

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String displayName = snapshot.getValue(String.class);
        String googleDisplayName = ToaApi.getUserManager().getDisplayName();

        if (!changed && (displayName == null || !displayName.equals(googleDisplayName))) {
            ToaApi.getDatabaseManager().getUserReference().child("displayname").setValue(googleDisplayName);
            changed = true;
        }

    }

}
