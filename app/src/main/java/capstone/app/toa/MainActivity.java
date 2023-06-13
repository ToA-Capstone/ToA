package capstone.app.toa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import capstone.app.toa.api.ToaApi;
import capstone.app.toa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static ToaApi api = ToaApi.getInstance();

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Button button_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!api.getUserManager().isLogin()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        api.init();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,
                                                                R.id.nav_commu_create,
                                                                R.id.nav_commu_manage,
                                                                R.id.nav_commu_sched,
                                                                R.id.nav_privacy_and_security,
                                                                R.id.nav_test)
                                                .setOpenableLayout(drawer)
                                                .build();

        TextView text = navigationView.getHeaderView(0).findViewById(R.id.nav_header_TextView_name);
        text.setText(api.getUserManager().get().getDisplayName());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //친구목록화면 버튼
        button_friend = binding.appBarMain.appBarMainButtonFriend;

        button_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navController.getCurrentDestination().getId() != R.id.nav_list_friend) {
                    navController.navigate(R.id.nav_list_friend);
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}