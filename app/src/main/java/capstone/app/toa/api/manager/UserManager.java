package capstone.app.toa.api.manager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.service.listener.UserCommunitysChangeListener;
import capstone.app.toa.service.listener.UserFriendsChangeListener;

public class UserManager {

    private static ToaApi api = ToaApi.getInstance();
    private FirebaseAuth auth;

    /**
     * FirebaseAuth Instance를 가져옴
     * @return FirebaseAuth.getInstance();
     *
     */
    public FirebaseAuth getAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    private ArrayList<String> friends = new ArrayList<>();

    public ArrayList<String> getFriends() {
        return friends;
    }

    /**
     * 친구 목록에 친구를 추가합니다.
     * @param uid User Uid
     * @return 추가 된다면 True, 이미 추가되어 있다면 False
     */
    public boolean addFriend(String uid) {
        if (existsFriend(uid)) {
            return false;
        }
        friends.add(uid);
        updateCommunitys();
        return true;
    }

    /**
     * 친구 목록에서 친구를 제거합니다.
     * @param uid User Uid
     * @return 삭제 된다면 True, 이미 삭제되어 있다면 False
     */
    public boolean removeFriend(String uid) {
        if (existsFriend(uid)) {
            friends.remove(uid);
            updateCommunitys();
            return true;
        }
        return false;
    }
    public void updateFriends() {
//        api.getDatabase().getUserFriendsReference().setValue(friends);
    }

    /**
     * 친구 목록에서 친구가 있는지 확인합니다.
     * @param uid User Uid
     * @return 데이터가 존재하면 True, 존재하지 않으면 False
     */
    public boolean existsFriend(String uid) {
        return friends.contains(uid);
    }

    private ArrayList<String> communitys = new ArrayList<>();

    public ArrayList<String> getCommunitys() {
        return communitys;
    }

    /**
     * 유저 커뮤니티 목록에 해당 이름의 커뮤니티를 추가합니다.
     * @param name 커뮤니티 이름
     * @return 해당 이름의 커뮤니티를 추가했다면 True, 이미 추가되어 있다면 False
     */
    public boolean addCommunity(String name) {
        if (existsCommunity(name)) {
            return false;
        }
        communitys.add(name);
        updateCommunitys();
        return true;
    }

    /**
     * 유저의 커뮤니티 목록에서 해당 이름의 커뮤니티를 제거합니다.
     * @param name 커뮤니티 이름
     * @return 해당 이름의 커뮤니티를 제거했다면 True, 이미 제거되어 있다면 False
     */
    public boolean removeCommunity(String name) {
        if (existsCommunity(name)) {
            communitys.remove(name);
            updateCommunitys();
            return true;
        }
        return false;
    }

    public void updateCommunitys() {
//        api.getDatabase().getUserCommunitysReference().setValue(communitys);
    }

    /**
     * 유저의 커뮤니티 목록에서 해당 이름의 커뮤니티가 있는지 확인합니다.
     * @param name 커뮤니티 이름
     * @return 해당 이름의 커뮤니티가 존재하면 True, 존재하지 않는다면 False
     */
    public boolean existsCommunity(String name) {
        return communitys.contains(name);
    }

    /**
     * FirebaseAuth에서 FirebaseUser를 가져옴
     * @return 로그인일 경우 FirebaseUser, 로그아웃일 경우 null
     */
    public FirebaseUser get() {
        return getAuth().getCurrentUser();
    }

    /**
     * Firebase에 등록된 Google 계정의 Uid를 가져옴
     * @return 로그인일 경우 Uid, 로그아웃일 경우 null
     */
    public String getUid() {
        return isLogin() ? get().getUid() : null;
    }

    /**
     * Firebase에 등록된 Google 계정의 Email을 가져옴
     * @return 로그인일 경우 Email, 로그아웃일 경우 null
     */
    public String getEmail() {
        return isLogin() ? get().getEmail() : null;
    }

    /**
     * 로그인인지 확인합니다.
     * @return 로그인일 경우 true, 로그아웃일 경우 false
     */
    public boolean isLogin() {
        return get() != null;
    }


}
