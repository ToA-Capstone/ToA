package capstone.app.toa.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.api.object.Community;

public class UserCommunitysChangeListener extends CustomValueEventListener {

    private boolean loaded = false;

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        GenericTypeIndicator<ArrayList<String>> typeIndicator = new GenericTypeIndicator<ArrayList<String>>() {};
        ArrayList<String> list = snapshot.getValue(typeIndicator);

        if (list != null) {
            ToaApi.getUserManager().getCommunitys().clear();
            ToaApi.getUserManager().getCommunitys().addAll(list);

            if (!loaded) {
                for (String name : ToaApi.getUserManager().getCommunitys()) {
                    ToaApi.getDatabaseManager().getCommunityReference().child(name).addListenerForSingleValueEvent(new CustomValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Community community = snapshot.getValue(Community.class);
                                ToaApi.getCommunityManager().set(community);
                                ToaApi.getDatabaseManager().setupCommunity(community);
                            }
                        }
                    });
                }
                loaded = true;
            }

            onChanged();
        }
    }

    /**
     * 데이터가 바뀐 뒤 실행되는 메소드
     */
    public void onChanged() {
    }

}
