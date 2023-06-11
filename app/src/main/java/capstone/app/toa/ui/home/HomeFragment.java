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

import java.util.ArrayList;
import java.util.Calendar;

import capstone.app.toa.R;
import capstone.app.toa.api.fragment.ToaFragment;
import capstone.app.toa.databinding.FragmentHomeBinding;
import capstone.app.toa.api.object.Todo;

public class HomeFragment extends ToaFragment implements GestureDetector.OnGestureListener {

    private FragmentHomeBinding binding;

    private GestureDetector gestureDetector;
    private Button button_add;
    private LinearLayout layout_list, layout_required;
    private View todo_required, alert_addlist;
    private ArrayList<View> todo_widget;

    private EditText editTitle_alert,inputtodo;
    private EditText text_todo;
    private TextView title_todo, textview_deadline, todo_deadline;
    private Button button_deadline, button_del;
    private TextView index_todo;
    private String title, text;
    private Todo todo;
    private ArrayList<Todo> Todo_input;

    private View layout_deadline_date, layout_deadline_time;
    private TimePicker timePicker;
    private DatePicker datePicker;
    private int selectYear, selectMonth, selectDay;
    private int hour, minute;

    private Calendar calendar = Calendar.getInstance();
    private long timeCombinedValue;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

                //Todo리스트 출력 View
                todo_widget.add(View.inflate(getActivity(),R.layout.todo_widget,null)); //nullpointerexception ㅠㅠㅠㅠㅠ
                text_todo=todo_widget.get(todo_widget.size()-1).findViewById(R.id.Text_todo);
                title_todo=todo_widget.get(todo_widget.size()-1).findViewById(R.id.Title_todo);
                todo_deadline=todo_widget.get(todo_widget.size()-1).findViewById(R.id.todo_deadline);
                button_del=todo_widget.get(todo_widget.size()-1).findViewById(R.id.button_trash_can);
                index_todo=todo_widget.get(todo_widget.size()-1).findViewById(R.id.index_todo);

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
                                        hour=timePicker.getCurrentHour();
                                        minute=timePicker.getCurrentMinute();
                                        textview_deadline.setText(
                                                Integer.toString(selectYear)+"년 "+
                                                Integer.toString(selectMonth+1)+"월 "+
                                                Integer.toString(selectDay)+"일\n"+
                                                hour+"시 "+minute+"분"
                                        );
                                        calendar.set(
                                                selectYear, selectMonth, selectDay,
                                                timePicker.getCurrentHour(),
                                                timePicker.getCurrentMinute()
                                        );
                                        timeCombinedValue=calendar.getTimeInMillis();
                                    }
                                });
                                deadline_time.show();
                            }
                        });
                        deadline_date.show();
                    }
                });
                button_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //index Textview에서 가져온 값으로 삭제처리
                        int index=Integer.parseInt(index_todo.getText().toString());
                        api.getTodoManager().remove(api.getTodoManager().toList().get(index));
                        layout_list.removeView(todo_widget.get(index));
                    }
                });

                addAlert.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //db에 todo리스트 업로드
                        todo = new Todo();
                        title=editTitle_alert.getText().toString();
                        text=inputtodo.getText().toString();
                        todo.setTitle(title);
                        todo.setContent(text);
                        todo.setEnded_At(timeCombinedValue);
                        api.getTodoManager().add(todo);
                        //api.getDatabaseManager().updateTodos(); DB update 메소드?

                        //db에서 리스트 내려오기
                        Todo_input=api.getTodoManager().toList();

                        calendar.setTimeInMillis(Todo_input.get(Todo_input.size()-1).getEnded_At());

                        text=Todo_input.get(Todo_input.size()-1).getContent();
                        title=Todo_input.get(Todo_input.size()-1).getTitle();

                        selectYear=calendar.get(Calendar.YEAR);
                        selectMonth=calendar.get(Calendar.MONTH)+1;
                        selectDay=calendar.get(Calendar.DAY_OF_MONTH);
                        hour=calendar.get(Calendar.HOUR_OF_DAY);
                        minute=calendar.get(Calendar.MINUTE);

                        text_todo.setText(text);
                        title_todo.setText(title);
                        index_todo.setText(Todo_input.size()-1);
                        todo_deadline.setText(
                                selectYear+"년 "+
                                selectMonth+"월 "+
                                selectDay+"일 "+
                                hour+"시 "+
                                minute+"분 까지"
                        );

                        //Todo View 생성
                        layout_list.addView(todo_widget.get(todo_widget.size()-1));
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