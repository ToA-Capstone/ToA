package capstone.app.toa.api;

import capstone.app.toa.api.manager.CommunityManager;
import capstone.app.toa.api.manager.TodoManager;

import capstone.app.toa.api.manager.DatabaseManager;
import capstone.app.toa.api.manager.UserManager;

public class ToaApi {

    /**
     * 초기 사용자가 로그인이 완료되었을 때 꼭, 딱 한번 사용해야한다.
     */
    public static void init() {
        databaseManager.init();
    }
    /**
     * 사용자가 로그인되어 있을 때 사용하면 안되며, 사용자가 로그아웃할 때 꼭, 딱 한번 사용해야한다.
     */
    public static void reset() {
        databaseManager.reset();
        todoManager.toList().clear();
        communityManager.toMap().clear();
        userManager.getCommunitys().clear();
        userManager.getFriends().clear();
    }

    private static DatabaseManager databaseManager = new DatabaseManager();
    private static UserManager userManager = new UserManager();
    private static TodoManager todoManager = new TodoManager();
    private static CommunityManager communityManager = new CommunityManager();

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    public static UserManager getUserManager() {
        return userManager;
    }
    public static TodoManager getTodoManager() {
        return todoManager;
    }
    public static CommunityManager getCommunityManager() {
        return communityManager;
    }

}
