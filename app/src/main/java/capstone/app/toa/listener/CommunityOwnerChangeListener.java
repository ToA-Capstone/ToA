package capstone.app.toa.listener;

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

    public Community getCommunity() {
        return community;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String owner = snapshot.getValue(String.class);

        if (owner != null) {
            community.setOwner(owner);
            onChanged();
        }
    }

    /**
     * 데이터가 바뀐 뒤 실행되는 메소드
     */
    public void onChanged() {
    }

}
