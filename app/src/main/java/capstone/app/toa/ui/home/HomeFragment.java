package capstone.app.toa.ui.home;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Calendar;

import capstone.app.toa.R;
import capstone.app.toa.api.fragment.ToaFragment;
import capstone.app.toa.databinding.FragmentHomeBinding;
import capstone.app.toa.api.object.Todo;

public class HomeFragment extends ToaFragment implements GestureDetector.OnGestureListener {

    private FragmentHomeBinding binding;

    private static LinearLayout layout_list;
    private static FragmentActivity activity;

    private GestureDetector gestureDetector;
    private Button button_add;
    private LinearLayout layout_required;
    private TextView required_title, required_text;
    private View todo_required1,todo_required2, alert_addlist;

    private EditText editTitle_alert,inputtodo;
    private TextView textview_deadline;
    private Button button_deadline;

    private View layout_deadline_date, layout_deadline_time;
    private TimePicker timePicker;
    private DatePicker datePicker;
    private int selectYear, selectMonth, selectDay;
    private int hour, minute;

    private long timeCombinedValue;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        button_add = binding.ButtonAdd;
        layout_required = binding.layoutRequired;

        //추천list(정적)
        todo_required1=View.inflate(getActivity(),R.layout.todo_required,null);
        required_text=todo_required1.findViewById(R.id.required_text);
        required_title=todo_required1.findViewById(R.id.required_title);
        required_title.setText("캡스톤보고서");
        required_text.setText("보고서 작성\n설문조사 작성");
        layout_required.addView(todo_required1);

        todo_required2=View.inflate(getActivity(),R.layout.todo_required,null);
        required_text=todo_required2.findViewById(R.id.required_text);
        required_title=todo_required2.findViewById(R.id.required_title);
        required_title.setText("캡스톤 발표");
        required_text.setText("ppt 만들기\n대본만들기");
        layout_required.addView(todo_required2);


        layout_list = binding.LayoutList;
        activity = getActivity();

        gestureDetector = new GestureDetector(getContext(), this);

        button_add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //리스트 내용 입력 alert창
                alert_addlist=View.inflate(getActivity(), R.layout.alert_addlist, null);
                editTitle_alert = alert_addlist.findViewById(R.id.editTitle_alert);
                inputtodo = alert_addlist.findViewById(R.id.input_Todo);
                button_deadline = alert_addlist.findViewById(R.id.button_deadLine);
                textview_deadline = alert_addlist.findViewById(R.id.textview_deadline);

                editTitle_alert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editTitle_alert.setText("");
                    }
                });

                inputtodo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inputtodo.setText("");
                    }
                });

                AlertDialog.Builder addAlert=new AlertDialog.Builder(getActivity());
                addAlert.setView(alert_addlist);

                //기한 설정 (TimePicker, DatePicker)
                button_deadline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //날짜
                        layout_deadline_date=View.inflate(getActivity(), R.layout.datepicker,null);
                        AlertDialog.Builder deadline_date=new AlertDialog.Builder(getActivity());
                        datePicker=layout_deadline_date.findViewById(R.id.datepicker);
                        deadline_date.setView(layout_deadline_date);
                        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                                selectYear=year;
                                selectMonth=month;
                                selectDay=day;
                            }
                        });
                        deadline_date.setPositiveButton("다음", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //시간
                                layout_deadline_time=View.inflate(getActivity(), R.layout.timepicker, null);
                                AlertDialog.Builder deadline_time=new AlertDialog.Builder(getActivity());
                                timePicker=layout_deadline_time.findViewById(R.id.timepicker);
                                deadline_time.setView(layout_deadline_time);
                                deadline_time.setPositiveButton("완료", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Calendar calendar = Calendar.getInstance();

                                        hour = timePicker.getCurrentHour();
                                        minute = timePicker.getCurrentMinute();
                                        textview_deadline.setText(selectYear + "년 " +
                                                (selectMonth+1) + "월 " +
                                                selectDay + "일\n" +
                                                hour + "시 " +
                                                minute + "분");
                                        calendar.set(selectYear, selectMonth, selectDay, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                                        timeCombinedValue = calendar.getTimeInMillis();
                                    }
                                });
                                deadline_time.show();
                            }
                        });
                        deadline_date.show();
                    }
                });

                addAlert.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //db에 todo리스트 업로드
                        Todo todo = new Todo();

                        todo.setTitle(editTitle_alert.getText().toString());
                        todo.setContent(inputtodo.getText().toString());
                        todo.setEnded_At(timeCombinedValue);

                        api.getTodoManager().add(todo);
                        api.getDatabaseManager().updateTodos();
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
        layout_list = null;
        activity = null;
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

    public static void updateTodo() {
        if (activity != null && layout_list != null) {
            ArrayList<Todo> list = api.getTodoManager().toList();

            for (int i = 0; i < list.size(); i++) {
                Todo todo = list.get(i);

                View view = View.inflate(activity, R.layout.todo_widget, null);

                TextView content_todo = view.findViewById(R.id.content_todo),
                        title_todo = view.findViewById(R.id.title_todo),
                        todo_deadline = view.findViewById(R.id.todo_deadline),
                        index_todo = view.findViewById(R.id.index_todo);

                Button button_del = view.findViewById(R.id.button_trash_can);

                Calendar calendar = Calendar.getInstance();

                calendar.setTimeInMillis(todo.getEnded_At());

                title_todo.setText(todo.getTitle());
                content_todo.setText(todo.getContent());
                index_todo.setText(String.valueOf(i));

                todo_deadline.setText(
                        calendar.get(Calendar.YEAR) + "년 " +
                                (calendar.get(Calendar.MONTH) + 1) + "월 " +
                                calendar.get(Calendar.DAY_OF_MONTH) + "일 " +
                                calendar.get(Calendar.HOUR_OF_DAY) + "시 " +
                                calendar.get(Calendar.MINUTE) + "분 까지"
                );

                //Todo View 생성
                layout_list.addView(view);


                button_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //index Textview에서 가져온 값으로 삭제처리
                        int index = Integer.parseInt(index_todo.getText().toString());
                        if (index < api.getTodoManager().toList().size()) {
                            api.getTodoManager().remove(api.getTodoManager().toList().get(index));
                            api.getDatabaseManager().updateTodos();
                        }
                    }
                });
            }
        }
    }
    public static void resetTodo() {
        if (layout_list != null) {
            layout_list.removeAllViews();
        }
    }
}