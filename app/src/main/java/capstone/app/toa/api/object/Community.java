package capstone.app.toa.api.object;

import java.util.ArrayList;

public class Community {

    // 커뮤니티 이름
    private String name;

    // 커뮤니티 생성 시간
    private long created_at = -1;

    // 커뮤니티 주인
    private String owner = null;

    // 커뮤니티 TodoList
    private ArrayList<Todo> todos = new ArrayList<>();

    // 커뮤니티에 소속된 유저들 Uid
    private ArrayList<String> users = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
    public long getCreated_at() {
        return created_at;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getOwner() {
        return owner;
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
    }
    public void addTodo(int index, Todo todo) {
        todos.add(index, todo);
    }
    public void removeTodo(Todo todo) {
        todos.remove(todo);
    }
    public void removeTodo(int index) {
        todos.remove(index);
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public boolean addUser(String uid) {
        if (existsUser(uid)) {
            return false;
        }
        users.add(uid);
        return true;
    }
    public boolean removeUser(String uid) {
        if (existsUser(uid)) {
            return users.remove(uid);
        }
        return false;
    }
    public boolean existsUser(String uid) {
        return users.contains(uid);
    }

}
