package capstone.app.toa.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.api.object.Todo;
import capstone.app.toa.ui.home.HomeFragment;

public class UserTodosChangeListener extends CustomValueEventListener {

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        GenericTypeIndicator<ArrayList<Todo>> typeIndicator = new GenericTypeIndicator<ArrayList<Todo>>() {};
        ArrayList<Todo> list = snapshot.getValue(typeIndicator);

        if (list != null) {
            ToaApi.getTodoManager().toList().clear();
            ToaApi.getTodoManager().toList().addAll(list);
            onChanged();
        }
    }

    /**
     * 데이터가 바뀐 뒤 실행되는 메소드
     */
    public void onChanged() {
        HomeFragment.resetTodo();
        HomeFragment.updateTodo();
    }

}
