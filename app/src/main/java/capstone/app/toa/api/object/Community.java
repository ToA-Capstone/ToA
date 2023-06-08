package capstone.app.toa.api.object;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.service.listener.CommunityOwnerChangeListener;
import capstone.app.toa.service.listener.CommunityTodoChangeListener;
import capstone.app.toa.service.listener.CommunityUsersChangeListener;

public class Community {

    private static transient ToaApi api = ToaApi.getInstance();
    private transient DatabaseReference db;

    public Community(String name) {
        this.name = name;

        db = api.getDatabase().getCommunityReference(name);

        CustomValueEventListener listener = new CommunityOwnerChangeListener(this);
        listeners.put(name + "_owner", listener);
        db.addValueEventListener(listener);

        listeners.put(name + "_todos", listener = new CommunityTodoChangeListener(this));
        db.addValueEventListener(listener);

        listeners.put(name + "_users", listener = new CommunityUsersChangeListener(this));
        db.addValueEventListener(listener);
    }

    // 커뮤니티 이름, 주인
    private transient String name;

    public String getName() {
        return name;
    }

    private long created_at = -1;

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
    public long getCreated_at() {
        return created_at;
    }

    private String owner = null;

    public void setOwner(String owner) {
        this.owner = owner;
        db.child("owner").setValue(owner);
    }
    public String getOwner() {
        return owner;
    }

    // 커뮤니티 TodoList
    private ArrayList<Todo> todos = new ArrayList<>();

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
        updateTodo();
    }
    public void addTodo(int index, Todo todo) {
        todos.add(index, todo);
        updateTodo();
    }
    public void removeTodo(Todo todo) {
        todos.remove(todo);
        updateTodo();
    }
    public void removeTodo(int index) {
        todos.remove(index);
        updateTodo();
    }
    public void updateTodo() {
        db.child("todos").setValue(todos);
    }

    // 커뮤니티에 소속된 유저들 Uid
    private ArrayList<String> users = new ArrayList<>();

    public ArrayList<String> getUsers() {
        return users;
    }

    public boolean addUser(String uid) {
        if (existsUser(uid)) {
            return false;
        }
        users.add(uid);
        updateUsers();
        return true;
    }
    public boolean removeUser(String uid) {
        if (existsUser(uid)) {
            users.remove(uid);
            updateUsers();
            return true;
        }
        return false;
    }
    public boolean existsUser(String uid) {
        return users.contains(uid);
    }
    public void updateUsers() {
        db.child("users").setValue(users);
    }

    private static transient HashMap<String, CustomValueEventListener> listeners = new HashMap<>();

    public void delete() {
        DatabaseReference db = api.getDatabase().getCommunityReference(name);
        db.child("owner").removeEventListener(listeners.get(name + "_owner"));
        db.child("todos").removeEventListener(listeners.get(name + "_todos"));
        db.child("users").removeEventListener(listeners.get(name + "_users"));
    }
}
