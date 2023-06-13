package capstone.app.toa.ui.friend;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.databinding.AddFriendsBinding;

public class FriendFragment extends Fragment {

    private static ListView listView;
    private static Activity activity;

    private AddFriendsBinding binding;
    private String search_uid = null, search_email = null, search_name = null;
    private int listView_Select = -1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = AddFriendsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setHasOptionsMenu(true);

        Button btn_Search = binding.addFriendButtonSearch,
                btn_AddFriend = binding.addFriendButtonAdd,
                btn_RemoveFriend = binding.addFriendButtonMinus;
        EditText editText_Search = binding.addFriendEditTextSearch;
        TextView textView_Result = binding.addFriendTextViewSearchView;
        listView = binding.addFriendListViewFriends;
        activity = getActivity();

        updateFriendList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView_Select = position;
            }
        });
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent = editText_Search.getText().toString();

                if (searchContent == null || searchContent.length() < 1) {
                    textView_Result.setText("검색하실 이메일을 입력하세요!");
                    return;
                }

                if (!searchContent.contains("@") || !searchContent.contains(".")) {
                    textView_Result.setText("이메일 형식이 아닙니다!");
                    return;
                }

                ToaApi.getDatabaseManager().getReference("users").addListenerForSingleValueEvent(new CustomValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String email = ds.child("email").getValue(String.class);

                            if (email.equalsIgnoreCase(searchContent)) {
                                search_uid = ds.getKey();
                                search_email = email;
                                search_name = ds.child("displayname").getValue(String.class);
                                textView_Result.setText(search_name + " - " + search_email);
                                return;
                            }
                        }
                        search_uid = null;
                        search_email = null;
                        search_name = null;
                        textView_Result.setText("검색 결과 없음");
                    }
                });
            }
        });
        btn_AddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search_uid != null && search_email != null && search_name != null) {
                    if (ToaApi.getUserManager().getUid().equals(search_uid)) {
                        search_uid = null;
                        search_email = null;
                        search_name = null;
                        textView_Result.setText("자기 자신과 친구가 될 수 없습니다!");
                    } else {
                        ToaApi.getUserManager().addFriend(search_uid);
                        ToaApi.getDatabaseManager().updateFriends();
                        search_uid = null;
                        search_email = null;
                        search_name = null;
                        textView_Result.setText("친구 추가 되었습니다.");
                    }
                }
            }
        });
        btn_RemoveFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView_Select < 0) {
                    return;
                }

                ToaApi.getUserManager().removeFriend(listView_Select);
                ToaApi.getDatabaseManager().updateFriends();

                if (ToaApi.getUserManager().getFriends().size() < 1) {
                    updateFriendList();
                }

                listView_Select = -1;
            }
        });

        return root;
    }

    public static void updateFriendList() {
        if (listView != null) {
            ToaApi.getDatabaseManager().getReference("users").addListenerForSingleValueEvent(new CustomValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ArrayList<String> list = new ArrayList<>(ToaApi.getUserManager().getFriends());

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String uid = ds.getKey();

                        for (int i = 0; i < list.size(); i++) {
                            String fuid = list.get(i);
                            if (uid.equals(fuid)) {
                                list.set(i, ds.child("displayname").getValue(String.class) + " - " + ds.child("email").getValue(String.class));
                            }
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(adapter);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        listView = null;
        activity = null;
    }
}
