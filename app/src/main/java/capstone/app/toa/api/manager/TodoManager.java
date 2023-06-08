package capstone.app.toa.api.manager;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.object.Todo;

public class TodoManager {

    private static ToaApi api = ToaApi.getInstance();

    private DatabaseReference reference;

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }
    public DatabaseReference getReference() {
        if (reference == null) {
            reference = api.getDatabaseManager().getUserReference().child("todos");
        }
        return reference;
    }

    private ArrayList<Todo> list = new ArrayList<>();

    public ArrayList<Todo> toList() {
        return list;
    }

    public void add(Todo todo) {
        list.add(todo);
        updateDB();
    }
    public void add(int index, Todo todo) {
        list.add(index, todo);
        updateDB();
    }
    public void set(int index, Todo todo) {
        list.set(index, todo);
        updateDB();
    }
    public boolean remove(Todo todo) {
        boolean result = list.remove(todo);
        if (result) {
            updateDB();
        }
        return result;
    }

    public void updateDB() {
        getReference().setValue(list);
    }

}
