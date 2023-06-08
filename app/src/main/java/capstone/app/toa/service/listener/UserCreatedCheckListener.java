package capstone.app.toa.service.listener;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import capstone.app.toa.api.listener.CustomValueEventListener;

public class UserCreatedCheckListener extends CustomValueEventListener {

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Long created_at = snapshot.getValue(Long.class);

        boolean changed = false;

        if (created_at == null || created_at < 0) {
            api.getDatabaseManager().getUserReference().child("created_at").setValue(System.currentTimeMillis());
            changed = true;
        }


    }

    /**
     * 데이터가 바뀐 뒤 실행되는 메소드
     */
    public void onChanged() {}

}
