package capstone.app.toa.api.object;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import capstone.app.toa.R;

public class navigation {
    private Activity activity;

    public navigation(Activity act){
        activity=act;
    };

    public void onNavigationItemSelected(MenuItem item){
                int itemId=item.getItemId();
                switch(itemId){
                    case R.id.nav_createcommunity:
                        activity.setContentView(R.layout.community_create);
                    case R.id.nav_managementcommunity:
                        activity.setContentView(R.layout.community_choice);
                    case R.id.nav_schedulemanagementcommunity:
                        activity.setContentView(R.layout.community_management);
                }
    }
}
