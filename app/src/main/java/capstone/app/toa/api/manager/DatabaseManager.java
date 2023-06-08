package capstone.app.toa.api.manager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.service.listener.UserCommunitysOneListener;
import capstone.app.toa.service.listener.UserTodoChangeListener;
import capstone.app.toa.service.listener.UserCommunitysChangeListener;
import capstone.app.toa.service.listener.UserCreatedCheckListener;
import capstone.app.toa.service.listener.UserFriendsChangeListener;

public class DatabaseManager {

    private static ToaApi api = ToaApi.getInstance();

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://toa-capstone-990b0-default-rtdb.asia-southeast1.firebasedatabase.app");
    private DatabaseReference userReference,
                                communityReference;

    private CustomValueEventListener userFriendListener;

    public void init() {
//        if (userReference == null) {
//            userReference = database.getReference("users").child(api.getUser().getUid());
//            (userFriendsReference = userReference.child("friends")).addValueEventListener(new UserFriendsChangeListener());
//            (userCommunitysReference = userReference.child("communitys")).addListenerForSingleValueEvent(new UserCommunitysOneListener());
//            (userCommunitysReference = userReference.child("communitys")).addValueEventListener(new UserCommunitysChangeListener());
//        }
//        if (userTodoReference == null) {
//            (userTodoReference = getUserReference().child("todos")).addListenerForSingleValueEvent(new UserTodoChangeListener());
//            userTodoReference.addValueEventListener(new UserTodoChangeListener());
//        }
    }

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

}
