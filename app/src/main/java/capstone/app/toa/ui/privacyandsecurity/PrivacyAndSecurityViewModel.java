package capstone.app.toa.ui.privacyandsecurity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrivacyAndSecurityViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PrivacyAndSecurityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}