package capstone.app.toa.api.data;


import java.util.UUID;

public class TodoData {

    private UUID uuid;
    private String title, content, created_User, deleted_User, updated_User;
    private boolean complete, important, deleted;
    private int repeat_Type, repeat_Period;
    private long alarm_At, ended_At, created_At, deleted_At, updated_At;

    public void setUUID(UUID id) {
        this.uuid = id;
    }
    public UUID getUUID() {
        return uuid;
    }

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
