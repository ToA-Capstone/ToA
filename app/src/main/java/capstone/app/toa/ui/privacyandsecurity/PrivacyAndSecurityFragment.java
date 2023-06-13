package capstone.app.toa.ui.privacyandsecurity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import capstone.app.toa.api.fragment.ToaFragment;
import capstone.app.toa.databinding.FragmentPrivacyAndSecurityBinding;

public class PrivacyAndSecurityFragment extends ToaFragment {

    private FragmentPrivacyAndSecurityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPrivacyAndSecurityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView nameText = binding.fragmentPrivacyAndSecurityTextviewMyname,
                    emailText = binding.fragmentPrivacyAndSecurityTextviewMyemail,
                    idText = binding.fragmentPrivacyAndSecurityTextviewMyid;

        if (api.getUserManager().isLogin()) {
            nameText.setText(api.getUserManager().get().getDisplayName());
            emailText.setText(api.getUserManager().getEmail());
            idText.setText(api.getUserManager().getUid());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}