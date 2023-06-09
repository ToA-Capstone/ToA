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

    /**
     * 초기 사용자가 로그인이 완료되었을 때 꼭, 딱 한번 사용해야한다.
     */
    public void init() {
        databaseManager.init();
    }
    /**
     * 사용자가 로그인되어 있을 때 사용하면 안되며, 사용자가 로그아웃할 때 꼭, 딱 한번 사용해야한다.
     */
    public void reset() {
        databaseManager.reset();
        todoManager.toList().clear();
        communityManager.toMap().clear();
        userManager.getCommunitys().clear();
        userManager.getFriends().clear();
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
