package capstone.app.toa.service.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.api.object.Community;
import capstone.app.toa.api.object.Todo;

public class CommunityUsersChangeListener extends CustomValueEventListener {

    public CommunityUsersChangeListener(Community community) {
        this.community = community;
    }

    private Community community;

    public Community getCommunity() {
        return community;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        GenericTypeIndicator<ArrayList<String>> typeIndicator = new GenericTypeIndicator<ArrayList<String>>() {};
        ArrayList<String> list = snapshot.getValue(typeIndicator);

        if (list != null) {
            community.getUsers().clear();
            community.getUsers().addAll(list);
            onChanged();
        }
    }

    /**
     * 데이터가 바뀐 뒤 실행되는 메소드
     */
    public void onChanged() {}

}
