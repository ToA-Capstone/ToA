package capstone.app.toa.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Arrays;

import capstone.app.toa.LoginActivity;
import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.object.Todo;
import capstone.app.toa.databinding.FragmentTestBinding;

public class TestFragment extends Fragment {

    private FragmentTestBinding binding;

    private Button logoutButton, testButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        logoutButton = binding.logoutButton;
        testButton = binding.firebaseTest;



        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToaApi.getUserManager().getAuth().signOut();
                ToaApi.reset();
                FancyToast.makeText(getActivity(), "로그아웃", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Todo todo = new Todo();
//
//                todo.setTitle("ㅁㄴㅇㄹ");
//                todo.setContent("asdfasdfadsfg");
//                todo.setEnded_At(System.currentTimeMillis() + 10000);
//                todo.setCreated_At(System.currentTimeMillis());
//                ToaApi.getTodoManager().add(todo);
                getActivity().finish();
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