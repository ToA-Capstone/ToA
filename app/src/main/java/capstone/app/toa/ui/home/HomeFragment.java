package capstone.app.toa.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import capstone.app.toa.R;
import capstone.app.toa.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements GestureDetector.OnGestureListener {

    private FragmentHomeBinding binding;

    private GestureDetector gestureDetector;
    private Button button_add;
    private LinearLayout layout_list, layout_required;
    private View todo_widget, todo_required, alert_addlist;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        button_add = binding.ButtonAdd;
        layout_list = binding.LayoutList;
        layout_required = binding.layoutRequired;

        gestureDetector = new GestureDetector(getContext(), this);


        button_add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //customview test
                todo_widget=View.inflate(getActivity(), R.layout.todo_widget,null);
                todo_required=View.inflate(getActivity(), R.layout.todo_required,null);
                layout_list.addView(todo_widget);
                layout_required.addView(todo_required);
                //test

                //리스트 내용 입력 alert창
                alert_addlist=View.inflate(getActivity(), R.layout.alert_addlist, null);
                AlertDialog.Builder addAlert=new AlertDialog.Builder(getActivity());
                addAlert.setView(alert_addlist);
                addAlert.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //입력 내용 데이터베이스에 추가
                    }
                });
                addAlert.setNegativeButton("취소",null);
                addAlert.show();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        if(e1.getY()>e2.getY()){
            return true;
        }
        return false;
    }
    public boolean onDown(MotionEvent e){return false;}
    public void onShowPress(MotionEvent e){}
    public boolean onSingleTapUp(MotionEvent e){return false;}
    public boolean onScroll(MotionEvent e,MotionEvent e2,float a, float b){return false;}
    public void onLongPress(MotionEvent e){}
}