package capstone.app.toa.service.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

import capstone.app.toa.api.listener.CustomValueEventListener;

public class UserCommunitysChangeListener extends CustomValueEventListener {

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        GenericTypeIndicator<ArrayList<String>> typeIndicator = new GenericTypeIndicator<ArrayList<String>>() {};
        ArrayList<String> list = snapshot.getValue(typeIndicator);

        if (list != null) {
            api.getUser().getCommunitys().clear();
            api.getUser().getCommunitys().addAll(list);
        }
    }

}
