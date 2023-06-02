package capstone.app.toa.api;

import capstone.app.toa.api.manager.LoginManager;

public class ToaApi {

    private static ToaApi instance = null;

    public static ToaApi getInstance() {
        if (instance == null) {
            instance = new ToaApi();
        }
        return instance;
    }

    private LoginManager loginManager = new LoginManager();

    public LoginManager getLoginManager() {
        return loginManager;
    }
}
