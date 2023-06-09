package capstone.app.toa.service.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.api.object.Todo;

public class UserTodosChangeListener extends CustomValueEventListener {

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        GenericTypeIndicator<ArrayList<Todo>> typeIndicator = new GenericTypeIndicator<ArrayList<Todo>>() {};
        ArrayList<Todo> list = snapshot.getValue(typeIndicator);

        if (list != null) {
            api.getTodoManager().toList().clear();
            api.getTodoManager().toList().addAll(list);
            onChanged();
        }
    }

    /**
     * 데이터가 바뀐 뒤 실행되는 메소드
     */
    public void onChanged() {

    }

}
