package capstone.app.toa.ui.memo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import capstone.app.toa.api.fragment.ToaFragment;
import capstone.app.toa.databinding.FragmentMemoBinding;

public class MemoFragment extends ToaFragment {

    private FragmentMemoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MemoViewModel memoViewModel =
                new ViewModelProvider(this).get(MemoViewModel.class);

        binding = FragmentMemoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        memoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}