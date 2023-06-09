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

    public void checkCreated_At() {
        getUserReference().child("created_at").addListenerForSingleValueEvent(new UserCreatedCheckListener());
    }

    public DatabaseReference getUserReference() {
        if (userReference == null) {
            userReference = database.getReference("users");
        }
        return userReference;
    }

    public DatabaseReference getCommunityReference() {
        if (communityReference == null) {
            communityReference = database.getReference("communitys");
        }
        return communityReference;
    }

    public DatabaseReference getUserFriendsReference() {
        if (userFriendsReference == null) {
            userFriendsReference = api.getDatabaseManager().getUserReference().child("friends");
            userFriendsReference.addValueEventListener(userFriendsChangeListener = new UserFriendsChangeListener());
        }
        return userFriendsReference;
    }
    public DatabaseReference getUserCommunitysReference() {
        if (userCommunitysReference == null) {
            userCommunitysReference = api.getDatabaseManager().getUserReference().child("communitys");
            userCommunitysReference.addValueEventListener(userCommunitysChangeListener = new UserCommunitysChangeListener());
        }
        return userCommunitysReference;
    }
    public DatabaseReference getUserTodosReference() {
        if (userTodosReference == null) {
            userTodosReference = api.getDatabaseManager().getUserReference().child("todos");
            userTodosReference.addValueEventListener(userTodosChangeListener = new UserTodosChangeListener());
        }
        return userTodosReference;
    }
    public DatabaseReference getCommunityReference(String name) {
        return getCommunityReference().child(name);
    }
    public DatabaseReference getCommunityOwnerReference(String name) {
        return getCommunityReference(name).child("owner");
    }
    public DatabaseReference getCommunityUsersReference(String name) {
        return getCommunityReference(name).child("users");
    }
    public DatabaseReference getCommunityTodosReference(String name) {
        return getCommunityReference(name).child("todos");
    }

    public void updateFriends() {
        getUserFriendsReference().setValue(api.getUserManager().getFriends());
    }
    public void updateCommunitys() {
        getUserCommunitysReference().setValue(api.getUserManager().getCommunitys());
    }
    public void updateTodos() {
        getUserTodosReference().setValue(api.getTodoManager().toList());
    }

    public void updateCommunityOwner(Community community) {
        getCommunityOwnerReference(community.getName()).setValue(community.getOwner());
    }
    public void updateCommunityUsers(Community community) {
        getCommunityUsersReference(community.getName()).setValue(community.getUsers());
    }
    public void updateCommunityTodos(Community community) {
        getCommunityTodosReference(community.getName()).setValue(community.getTodos());
    }

    public void setupCommunity(Community community) {
        CustomValueEventListener listener;

        getCommunityOwnerReference(community.getName()).addValueEventListener(listener = new CommunityOwnerChangeListener(community));
        listeners.put(community.getName() + "_owner", listener);

        getCommunityUsersReference(community.getName()).addValueEventListener(listener = new CommunityUsersChangeListener(community));
        listeners.put(community.getName() + "_users", listener);

        getCommunityTodosReference(community.getName()).addValueEventListener(listener = new CommunityTodosChangeListener(community));
        listeners.put(community.getName() + "_todos", listener);
    }
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
    public void deleteCommunity(Community community) {
        teardownCommunity(community);
        getCommunityReference(community.getName()).removeValue();
    }

}
