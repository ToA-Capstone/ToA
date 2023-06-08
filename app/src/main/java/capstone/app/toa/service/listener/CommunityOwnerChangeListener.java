package capstone.app.toa.service.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.api.object.Community;
import capstone.app.toa.api.object.Todo;

public class CommunityOwnerChangeListener extends CustomValueEventListener {

    public CommunityOwnerChangeListener(Community community) {
        this.community = community;
    }

    private Community community;

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String owner = snapshot.getValue(String.class);

        if (owner != null) {
            community.setOwner(owner);
        }
    }

}
