package capstone.app.toa.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

import capstone.app.toa.R;
import capstone.app.toa.api.fragment.ToaFragment;
import capstone.app.toa.databinding.FragmentHomeBinding;
import capstone.app.toa.api.object.Todo;

public class HomeFragment extends ToaFragment implements GestureDetector.OnGestureListener {

    private FragmentHomeBinding binding;

    private GestureDetector gestureDetector;
    private Button button_add;
    private LinearLayout layout_list, layout_required;
    private View todo_widget, todo_required, alert_addlist;

    private EditText editTitle_alert,inputtodo;
    private EditText text_todo;
    private TextView title_todo;
    private String title, text;
    private Todo todo;
    private ArrayList<Todo> Todo_input;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        button_add = binding.ButtonAdd;
        layout_list = binding.LayoutList;
        layout_required = binding.layoutRequired;

        gestureDetector = new GestureDetector(getContext(), this);


        button_add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //리스트 내용 입력 alert창
                alert_addlist=View.inflate(getActivity(), R.layout.alert_addlist, null);
                editTitle_alert = alert_addlist.findViewById(R.id.editTitle_alert);
                inputtodo = alert_addlist.findViewById(R.id.input_Todo);

                //Todo리스트 출력 View
                todo_widget=View.inflate(getActivity(),R.layout.todo_widget,null);
                text_todo=todo_widget.findViewById(R.id.Text_todo);
                title_todo=todo_widget.findViewById(R.id.Title_todo);

                AlertDialog.Builder addAlert=new AlertDialog.Builder(getActivity());
                addAlert.setView(alert_addlist);
                addAlert.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //db에 todo리스트 업로드
                        todo = new Todo();
                        title=editTitle_alert.getText().toString();
                        text=inputtodo.getText().toString();
                        todo.setTitle(title);
                        todo.setContent(text);
                        api.getTodoManager().add(todo);
                        //api.getDatabaseManager().updateTodos(); DB update 메소드?

                        //db에서 리스트 내려오기
                        Todo_input=api.getTodoManager().toList();
                        text=Todo_input.get(Todo_input.size()-1).getContent();
                        title=Todo_input.get(Todo_input.size()-1).getTitle();
                        text_todo.setText(text);
                        title_todo.setText(title);

                        //Todo View 생성
                        layout_list.addView(todo_widget);
                    }
                });
                addAlert.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();
                    }
                });
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