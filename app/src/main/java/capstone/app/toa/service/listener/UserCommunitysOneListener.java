package capstone.app.toa.service.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.api.object.Community;

public class UserCommunitysOneListener extends CustomValueEventListener {

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        GenericTypeIndicator<ArrayList<String>> typeIndicator = new GenericTypeIndicator<ArrayList<String>>() {};
        ArrayList<String> list = snapshot.getValue(typeIndicator);

        if (list != null) {
            api.getUser().getCommunitys().clear();
            api.getUser().getCommunitys().addAll(list);

            for (String name : api.getUser().getCommunitys()) {
                api.getDatabase().getCommunityReference(name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Community community = new Community(name);
                            api.setCommunity(community);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        }
    }

    /**
     * 데이터가 바뀐 뒤 실행되는 메소드
     */
    public void onChanged() {}

}
