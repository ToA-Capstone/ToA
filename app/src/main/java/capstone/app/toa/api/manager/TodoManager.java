package capstone.app.toa.api.manager;

import java.util.ArrayList;

import capstone.app.toa.api.object.Todo;

public class TodoManager {

    private ArrayList<Todo> list = new ArrayList<>();

    /**
     * 사용자의 Todo 리스트를 가져옵니다.
     * @return User Todo List
     */
    public ArrayList<Todo> toList() {
        return list;
    }

    /**
     * Todo티스트에 해당 Todo클래스를 추가합니다.
     * @param todo Todo
     */
    public void add(Todo todo) {
        list.add(todo);
    }

    /**
     * Todo리스트의 index칸에 있는 데이터를 뒤로 밀고 해당 칸에 데이터를 추가합니다.
     * @param index List의 Index값
     * @param todo ToDo
     */
    public void add(int index, Todo todo) {
        list.add(index, todo);
    }

    /**
     * TodoList의 index칸에 있는 데이터를 바꿉니다.
     * @param index List의 Index값
     * @param todo Todo
     */
    public void set(int index, Todo todo) {
        list.set(index, todo);
    }

    /**
     * Todo리스트 안에 있는 Todo데이터를 제거합니다.
     * @param todo Todo
     * @return Todo가 삭제되었다면 True, 안되었다면 False
     */
    public boolean remove(Todo todo) {
        return list.remove(todo);
    }

}
