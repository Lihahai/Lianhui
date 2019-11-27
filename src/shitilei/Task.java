package shitilei;

import java.util.Date;

public class Task {
    private int tID;
    private String taskType;
    private String tGroup;
    private String tCourse;
    private String tKons;//知识点
    private String tTeacher;
    private Date dDate;
    private String taskState;

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String gettGroup() {
        return tGroup;
    }

    public void settGroup(String tGroup) {
        this.tGroup = tGroup;
    }

    public String gettCourse() {
        return tCourse;
    }

    public void settCourse(String tCourse) {
        this.tCourse = tCourse;
    }

    public String gettKons() {
        return tKons;
    }

    public void settKons(String tKons) {
        this.tKons = tKons;
    }

    public String gettTeacher() {
        return tTeacher;
    }

    public void settTeacher(String tTeacher) {
        this.tTeacher = tTeacher;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

}
