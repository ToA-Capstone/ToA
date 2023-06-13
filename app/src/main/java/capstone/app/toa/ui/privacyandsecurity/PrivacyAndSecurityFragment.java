package capstone.app.toa.ui.privacyandsecurity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.databinding.FragmentPrivacyAndSecurityBinding;

public class PrivacyAndSecurityFragment extends Fragment {

    private FragmentPrivacyAndSecurityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPrivacyAndSecurityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView nameText = binding.fragmentPrivacyAndSecurityTextviewMyname,
                    emailText = binding.fragmentPrivacyAndSecurityTextviewMyemail,
                    idText = binding.fragmentPrivacyAndSecurityTextviewMyid;

        if (ToaApi.getUserManager().isLogin()) {
            nameText.setText(ToaApi.getUserManager().getDisplayName());
            emailText.setText(ToaApi.getUserManager().getEmail());
            idText.setText(ToaApi.getUserManager().getUid());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}