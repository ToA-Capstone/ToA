package capstone.app.toa.api;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

import capstone.app.toa.api.manager.TodoManager;
import capstone.app.toa.api.object.Community;
import capstone.app.toa.api.object.Todo;

import capstone.app.toa.api.manager.DatabaseManager;
import capstone.app.toa.api.manager.UserManager;

public class ToaApi {

    private static ToaApi instance = null;

    public static ToaApi getInstance() {
        if (instance == null) {
            instance = new ToaApi();
        }
        return instance;
    }

    private DatabaseManager databaseManager = new DatabaseManager();
    private UserManager userManager = new UserManager();
    private TodoManager todoManager = new TodoManager();

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    public UserManager getUserManager() {
        return userManager;
    }
    public TodoManager getTodoManager() {
        return todoManager;
    }

    private HashMap<String, Community> communitys = new HashMap<>();

    public void setCommunity(Community community) {
        setCommunity(community.getName(), community);
    }
    public void setCommunity(String name, Community community) {
        communitys.put(name, community);
    }

    public boolean removeCommunity(Community community) {
        return removeCommunity(community.getName());
    }
    public boolean removeCommunity(String name) {
        if (existsCommunity(name)) {
            communitys.remove(name);
            return true;
        }
        return false;
    }

    public boolean existsCommunity(String name) {
        return communitys.containsKey(name);
    }
    public Community getCommunity(String name) {
        return communitys.get(name);
    }

}
