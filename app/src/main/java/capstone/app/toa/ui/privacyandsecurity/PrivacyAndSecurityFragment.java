package capstone.app.toa.ui.privacyandsecurity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import capstone.app.toa.api.fragment.ToaFragment;
import capstone.app.toa.databinding.FragmentPrivacyAndSecurityBinding;

public class PrivacyAndSecurityFragment extends ToaFragment {

    private FragmentPrivacyAndSecurityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PrivacyAndSecurityViewModel slideshowViewModel = new ViewModelProvider(this).get(PrivacyAndSecurityViewModel.class);

        binding = FragmentPrivacyAndSecurityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}