package capstone.app.toa.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import capstone.app.toa.api.fragment.ToaFragment;
import capstone.app.toa.databinding.CommunityChoiceBinding;

public class CommunityManagementFragment extends ToaFragment {

    private CommunityChoiceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CommunityChoiceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}
