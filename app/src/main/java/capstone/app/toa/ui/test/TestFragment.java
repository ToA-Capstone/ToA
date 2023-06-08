package capstone.app.toa.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.shashank.sony.fancytoastlib.FancyToast;

import capstone.app.toa.LoginActivity;
import capstone.app.toa.api.ToaApi;
import capstone.app.toa.api.fragment.ToaFragment;
import capstone.app.toa.databinding.FragmentTestBinding;

public class TestFragment extends ToaFragment {

    private FragmentTestBinding binding;

    private Button logoutButton, testButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TestViewModel slideshowViewModel = new ViewModelProvider(this).get(TestViewModel.class);

        binding = FragmentTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        logoutButton = binding.logoutButton;
        testButton = binding.firebaseTest;

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToaApi.getInstance().getUserManager().getAuth().signOut();
                FancyToast.makeText(getActivity(), "로그아웃", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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