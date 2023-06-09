package capstone.app.toa.api;

import capstone.app.toa.api.manager.CommunityManager;
import capstone.app.toa.api.manager.TodoManager;

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
    private CommunityManager communityManager = new CommunityManager();

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    public UserManager getUserManager() {
        return userManager;
    }
    public TodoManager getTodoManager() {
        return todoManager;
    }
    public CommunityManager getCommunityManager() {
        return communityManager;
    }

}
