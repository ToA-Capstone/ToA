package capstone.app.toa.ui.friend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.listener.CustomValueEventListener;
import capstone.app.toa.databinding.AddFriendsBinding;

public class FriendFragment extends Fragment {

    private AddFriendsBinding binding;

    private String search_uid = null, search_email = null,search_name = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = AddFriendsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setHasOptionsMenu(true);

        Button btn_Search = binding.addFriendButtonSearch,
                btn_AddFriend = binding.addFriendButtonAdd;
        EditText editText_Search = binding.addFriendEditTextSearch;
        TextView textView_Result = binding.addFriendTextViewSearchView;

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToaApi.getDatabaseManager().getReference("users").addListenerForSingleValueEvent(new CustomValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String searchContent = editText_Search.getText().toString();
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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
