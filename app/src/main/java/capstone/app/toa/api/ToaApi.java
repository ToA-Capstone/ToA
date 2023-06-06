package capstone.app.toa.api;

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

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    private UserManager userManager = new UserManager();

    public UserManager getUserManager() {
        return userManager;
    }

}
