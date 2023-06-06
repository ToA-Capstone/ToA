package capstone.app.toa.api.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import capstone.app.toa.api.listener.TodoRefreshListener;

public class CommunityData {

    private String name;
    private HashSet<String> users = new HashSet<>();

    public HashSet<String> getUsers() {
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
            users.remove(uid);
            return true;
        }
        return false;
    }
    public boolean existsUser(String uid) {
        return users.contains(uid);
    }

    private ArrayList<TodoData> list = new ArrayList<>();

    public ArrayList<TodoData> getList() {
        return list;
    }

    public void add(TodoData data) {
        if (data.getUUID() == null) {
            data.setUUID(UUID.randomUUID());
        }
        list.add(data);
    }
    public boolean remove(TodoData data) {
        return list.remove(data);
    }
    public boolean remove(UUID id) {
        for (TodoData data : list) {
            if (data.getUUID().equals(id)) {
                return list.remove(data);
            }
        }
        return false;
    }
    public TodoData get(UUID uuid) {
        for (TodoData data : list) {
            if (data.getUUID().equals(uuid)) {
                return data;
            }
        }
        return null;
    }

    private HashSet<TodoRefreshListener> listeners = new HashSet<>();

    public HashSet<TodoRefreshListener> getListeners() {
        return listeners;
    }

    public void registerListener(TodoRefreshListener listener) {
        listeners.add(listener);
    }

}
