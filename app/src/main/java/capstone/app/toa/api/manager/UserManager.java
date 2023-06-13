package capstone.app.toa.api.manager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class UserManager {

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
    private ArrayList<String> communitys = new ArrayList<>();

    /**
     * 사용자의 Friend 리스트을 가져옵니다.
     * @return User Friend List
     */
    public ArrayList<String> getFriends() {
        return friends;
    }
    /**
     * 사용자의 Community 리스트를 불러옵니다.
     * @return User Community List
     */
    public ArrayList<String> getCommunitys() {
        return communitys;
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
            return true;
        }
        return false;
    }
    public void removeFriend(int index) {
        friends.remove(index);
    }
    /**
     * 친구 목록에서 친구가 있는지 확인합니다.
     * @param uid User Uid
     * @return 데이터가 존재하면 True, 존재하지 않으면 False
     */
    public boolean existsFriend(String uid) {
        return friends.contains(uid);
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
            return true;
        }
        return false;
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
    public FirebaseUser a() {
        return getAuth().getCurrentUser();
    }
    /**
     * Firebase에 등록된 Google 계정의 Uid를 가져옴
     * @return 로그인일 경우 Uid, 로그아웃일 경우 null
     */
    public String getUid() {
        return isLogin() ? a().getUid() : null;
    }

    /**
     * Firebase에 등록된 Google 계정의 Email을 가져옴
     * @return 로그인일 경우 Email, 로그아웃일 경우 null
     */
    public String getEmail() {
        return isLogin() ? a().getEmail() : null;
    }

    /**
     * Firebase에 등록된 Google 계정의 DisplayName을 가져옴
     * @return 로그인일 경우 Email, 로그아웃일 경우 null
     */
    public String getDisplayName() {
        return isLogin() ? a().getDisplayName() : null;
    }
    /**
     * 로그인인지 확인합니다.
     * @return 로그인일 경우 true, 로그아웃일 경우 false
     */
    public boolean isLogin() {
        return a() != null;
    }


}
