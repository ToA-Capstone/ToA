package capstone.app.toa.service.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.api.object.Todo;

public class UserTodoChangeListener extends CustomValueEventListener {

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        GenericTypeIndicator<ArrayList<Todo>> typeIndicator = new GenericTypeIndicator<ArrayList<Todo>>() {};
        ArrayList<Todo> list = snapshot.getValue(typeIndicator);

        if (list != null) {
            api.getTodos().clear();
            api.getTodos().addAll(list);
        }
    }

}
