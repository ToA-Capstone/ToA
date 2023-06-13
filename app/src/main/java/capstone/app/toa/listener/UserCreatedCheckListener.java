package capstone.app.toa.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import capstone.app.toa.api.listener.CustomValueEventListener;

public class UserCreatedCheckListener extends CustomValueEventListener {

    private boolean changed = false;

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Long created_at = snapshot.getValue(Long.class);

        if (!changed && (created_at == null || created_at < 0)) {
            api.getDatabaseManager().getUserReference().child("created_at").setValue(System.currentTimeMillis());
            changed = true;
            onChanged();
        }

    }

    /**
     * 데이터가 바뀐 뒤 실행되는 메소드
     */
    public void onChanged() {}

}
