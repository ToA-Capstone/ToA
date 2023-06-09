package capstone.app.toa.api.manager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.api.object.Community;
import capstone.app.toa.service.listener.CommunityOwnerChangeListener;
import capstone.app.toa.service.listener.CommunityTodosChangeListener;
import capstone.app.toa.service.listener.CommunityUsersChangeListener;
import capstone.app.toa.service.listener.UserCommunitysChangeListener;
import capstone.app.toa.service.listener.UserCreatedCheckListener;
import capstone.app.toa.service.listener.UserFriendsChangeListener;
import capstone.app.toa.service.listener.UserTodosChangeListener;

public class DatabaseManager {

    private static ToaApi api = ToaApi.getInstance();

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://toa-capstone-990b0-default-rtdb.asia-southeast1.firebasedatabase.app");
    private DatabaseReference userReference,
                                communityReference,
                                userFriendsReference,
                                userCommunitysReference,
                                userTodosReference;

    private CustomValueEventListener userFriendsChangeListener,
                                        userCommunitysChangeListener,
                                        userTodosChangeListener;

    private HashMap<String, CustomValueEventListener> listeners = new HashMap<>();

    /**
     * DatabaseManager를 세팅합니다.
     */
    public void init() {
        checkCreated_At();

        if (userFriendsReference == null) {
            userFriendsReference = api.getDatabaseManager().getUserReference().child("friends");
            userFriendsReference.addValueEventListener(userFriendsChangeListener = new UserFriendsChangeListener());
        }
        if (userCommunitysReference == null) {
            userCommunitysReference = api.getDatabaseManager().getUserReference().child("communitys");
            userCommunitysReference.addValueEventListener(userCommunitysChangeListener = new UserCommunitysChangeListener());
        }
        if (userTodosReference == null) {
            userTodosReference = api.getDatabaseManager().getUserReference().child("todos");
            userTodosReference.addValueEventListener(userTodosChangeListener = new UserTodosChangeListener());
        }
    }
    /**
     * DatabaseManager의 세팅을 초기화합니다.
     */
    public void reset() {
        userFriendsReference.removeEventListener(userFriendsChangeListener);
        userFriendsReference = null;
        userCommunitysReference.removeEventListener(userCommunitysChangeListener);
        userCommunitysReference = null;
        userTodosReference.removeEventListener(userTodosChangeListener);
        userTodosReference = null;

        for (Community community : api.getCommunityManager().toMap().values()) {
            teardownCommunity(community);
        }
    }

    public void checkCreated_At() {
        getUserReference().child("created_at").addListenerForSingleValueEvent(new UserCreatedCheckListener());
    }

    /**
     * 사용자 데이터 전부를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @return Users Firebase DataReference
     */
    public DatabaseReference getUserReference() {
        if (userReference == null) {
            userReference = database.getReference("users");
        }
        return userReference;
    }
    /**
     * 커뮤니티 데이터 전부를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @return Communitys Firebase DataReference
     */
    public DatabaseReference getCommunityReference() {
        if (communityReference == null) {
            communityReference = database.getReference("communitys");
        }
        return communityReference;
    }

    /**
     * 사용자의 Friend데이터를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @return User Friend Firebase DataReference
     */
    public DatabaseReference getUserFriendsReference() {
        return userFriendsReference;
    }
    /**
     * 사용자의 Community데이터를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @return User Community Firebase DataReference
     */
    public DatabaseReference getUserCommunitysReference() {
        return userCommunitysReference;
    }
    /**
     * 사용자의 Todo데이터를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @return User Todo Firebase DataReference
     */
    public DatabaseReference getUserTodosReference() {
        return userTodosReference;
    }


    /**
     * Community의 데이터를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @param name Community Name
     * @return Community Firebase DataReference
     */
    public DatabaseReference getCommunityReference(String name) {
        return getCommunityReference().child(name);
    }
    /**
     * Community의 Owner데이터를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @param name Community Name
     * @return Community Owner Firebase DataReference
     */
    public DatabaseReference getCommunityOwnerReference(String name) {
        return getCommunityReference(name).child("owner");
    }
    /**
     * Community의 User데이터를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @param name Community Name
     * @return Community User Firebase DataReference
     */
    public DatabaseReference getCommunityUsersReference(String name) {
        return getCommunityReference(name).child("users");
    }
    /**
     * Community의 Todo데이터를 조작할 수 있는 Firebase DatabaseReference를 가져옵니다.
     * @param name Community Name
     * @return Community Todo Firebase DataReference
     */
    public DatabaseReference getCommunityTodosReference(String name) {
        return getCommunityReference(name).child("todos");
    }

    /**
     * 사용자의 Friend 목록을 Firebase에 올립니다.
     */
    public void updateFriends() {
        getUserFriendsReference().setValue(api.getUserManager().getFriends());
    }
    /**
     * 사용자의 Community 목록을 Firebase에 올립니다.
     */
    public void updateCommunitys() {
        getUserCommunitysReference().setValue(api.getUserManager().getCommunitys());
    }
    /**
     * 사용자의 Todo데이터가 담긴 리스트 데이터를 Firebase에 올립니다.
     */
    public void updateTodos() {
        getUserTodosReference().setValue(api.getTodoManager().toList());
    }

    /**
     * Community 클래스의 Owner 데이터를 Firebase에 올립니다.
     * @param community
     */
    public void updateCommunityOwner(Community community) {
        getCommunityOwnerReference(community.getName()).setValue(community.getOwner());
    }
    /**
     * Community 클래스의 Users List 데이터를 Firebase에 올립니다.
     * @param community
     */
    public void updateCommunityUsers(Community community) {
        getCommunityUsersReference(community.getName()).setValue(community.getUsers());
    }
    /**
     * Community 클래스의 Todo데이터가 담긴 리스트 데이터를 Firebase에 올립니다.
     * @param community Community
     */
    public void updateCommunityTodos(Community community) {
        getCommunityTodosReference(community.getName()).setValue(community.getTodos());
    }

    /**
     * Community 데이터를 FireBase에서 실시간으로 불러오는 세팅을 합니다.
     * @param community Community
     */
    public void setupCommunity(Community community) {
        CustomValueEventListener listener;

        getCommunityOwnerReference(community.getName()).addValueEventListener(listener = new CommunityOwnerChangeListener(community));
        listeners.put(community.getName() + "_owner", listener);

        getCommunityUsersReference(community.getName()).addValueEventListener(listener = new CommunityUsersChangeListener(community));
        listeners.put(community.getName() + "_users", listener);

        getCommunityTodosReference(community.getName()).addValueEventListener(listener = new CommunityTodosChangeListener(community));
        listeners.put(community.getName() + "_todos", listener);
    }
    /**
     * Community 데이터를 FireBase에서 실시간으로 불러오는 세팅을 해제합니다.
     * @param community Community
     */
    public void teardownCommunity(Community community) {
        CustomValueEventListener listener;

        listener = listeners.get(community.getName() + "_owner");
        if (listener != null) {
            getCommunityOwnerReference(community.getName()).removeEventListener(listener);
            listeners.remove(community.getName() + "_owner");
        }

        listener = listeners.get(community.getName() + "_users");
        if (listener != null) {
            getCommunityUsersReference(community.getName()).removeEventListener(listener);
            listeners.remove(community.getName() + "_users");
        }

        listener = listeners.get(community.getName() + "_todos");
        if (listener != null) {
            getCommunityTodosReference(community.getName()).removeEventListener(listener);
            listeners.remove(community.getName() + "_todos");
        }
    }

    /**
     * Firebase 데이터에서 해당 Community의 데이터를 전부 삭제합니다.
     * (커뮤니티 주인이 커뮤니티 삭제 및 해체등 했을 때 사용)
     * @param community Community
     */
    public void deleteCommunity(Community community) {
        teardownCommunity(community);
        getCommunityReference(community.getName()).removeValue();
    }

}
