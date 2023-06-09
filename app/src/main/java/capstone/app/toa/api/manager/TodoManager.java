package capstone.app.toa.api.manager;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.object.Todo;

public class TodoManager {

    private ArrayList<Todo> list = new ArrayList<>();

    public ArrayList<Todo> toList() {
        return list;
    }

    public void add(Todo todo) {
        list.add(todo);
    }
    public void add(int index, Todo todo) {
        list.add(index, todo);
    }
    public void set(int index, Todo todo) {
        list.set(index, todo);
    }
    public boolean remove(Todo todo) {
        return list.remove(todo);
    }

}
