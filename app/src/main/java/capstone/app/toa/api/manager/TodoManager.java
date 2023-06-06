package capstone.app.toa.api.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import capstone.app.toa.api.data.TodoData;
import capstone.app.toa.api.listener.TodoRefreshListener;

public class TodoManager {

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
