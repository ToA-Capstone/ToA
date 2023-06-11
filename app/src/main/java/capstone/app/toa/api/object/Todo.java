package capstone.app.toa.api.object;

public class Todo {

    // 순서대로 제목, 내용, 제작 유저 (커뮤니티만), 삭제 유저 (커뮤니티만), 마지막 수정 유저 (커뮤니티만)
    private String title = null, content = null, created_User = null, deleted_User = null, updated_User = null;

    // 완료 여부, 중요 여부, 삭제 여부
    private boolean complete = false, important = false, deleted = false;

    // 반복 타입 [0: 년, 1: 월, 2: 주, 3: 일, -1: 반복 X], 반복 간격
    private int repeat_Type = -1, repeat_Period = -1;

    // 알람 시간, 끝나는 시간, 생성 시간, 삭제 시간, 마지막 수정 시간
    private long alarm_At = -1, ended_At = -1, created_At = -1, deleted_At = -1, updated_At = -1;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    public boolean isComplete() {
        return complete;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
    public boolean isImportant() {
        return important;
    }

    public void setRepeat_Type(int repeat_Type) {
        this.repeat_Type = repeat_Type;
    }
    public int getRepeat_Type() {
        return repeat_Type;
    }

    public void setRepeat_Period(int repeat_Period) {
        this.repeat_Period = repeat_Period;
    }
    public int getRepeat_Period() {
        return repeat_Period;
    }

    public void setAlarm_At(long alarm_At) {
        this.alarm_At = alarm_At;
    }
    public long getAlarm_At() {
        return alarm_At;
    }

    public void setEnded_At(long ended_At) {
        this.ended_At = ended_At;
    }
    public long getEnded_At() {
        return ended_At;
    }

    public void setCreated_User(String created_User) {
        this.created_User = created_User;
    }
    public String getCreated_User() {
        return created_User;
    }

    public void setCreated_At(long created_At) {
        this.created_At = created_At;
    }
    public long getCreated_At() {
        return created_At;
    }

    public void setDeleted_User(String deleted_User) {
        this.deleted_User = deleted_User;
    }
    public String getDeleted_User() {
        return deleted_User;
    }

    public void setDeleted_At(long deleted_At) {
        this.deleted_At = deleted_At;
    }
    public long getDeleted_At() {
        return deleted_At;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public boolean isDeleted() {
        return deleted;
    }

    public void setUpdated_User(String updated_User) {
        this.updated_User = updated_User;
    }
    public String getUpdated_User() {
        return updated_User;
    }

    public void setUpdated_At(long updated_At) {
        this.updated_At = updated_At;
    }
    public long getUpdated_At() {
        return updated_At;
    }

}
