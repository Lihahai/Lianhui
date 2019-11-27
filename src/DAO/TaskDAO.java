package DAO;

import shitilei.Task;

import java.net.StandardSocketOptions;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDAO {
    public TaskDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/itcast?characterEncoding=UTF-8", "root",
                "admin");
    }

    public int getTotal() {
        int total = 0;
        try(Connection c=getConnection(); Statement s=c.createStatement();){
            String sql="select count(*) from taskform";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                total++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return total;
    }

    public void add(Task task) {

        String sql = "insert into taskform values(null,?,?,?,?,?,?)";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            Date date=task.getdDate();
            Timestamp timestamp=new Timestamp(date.getTime());
            ps.setString(1, task.getTaskType());
            ps.setString(2, task.gettGroup());
            ps.setString(3, task.gettCourse());
            ps.setString(4, task.gettKons());
            ps.setString(5, task.gettTeacher());
            ps.setTimestamp(6, timestamp);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            /*
            if (rs.next()) {
                int id = rs.getInt(1);
                e1.seteID(id);
            }

             */
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public void update(Task task) {

        String sql = "update taskform set tTasktype= ?,tGroup = ? , tCourse = ? , tKons = ?,tTeacher=? ,tTime=?  where tID = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            Date date=task.getdDate();
            Timestamp timestamp=new Timestamp(date.getTime());
            ps.setString(1, task.getTaskType());
            ps.setString(2, task.gettGroup());
            ps.setString(3, task.gettCourse());
            ps.setString(4, task.gettKons());
            ps.setString(5, task.gettTeacher());
            ps.setTimestamp(6, timestamp);
            ps.setInt(7,task.gettID());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }
    public void delete(int id) {
        try(Connection c=getConnection();Statement s=c.createStatement()){

            String sql = "delete from taskform where tID = " + id;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public Task get(int tID) {
        Task task = null;


        try (Connection c=getConnection();Statement s=c.createStatement();){
            String sql = "select * from taskform where id = " + tID;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                task = new Task();
                String taskType = rs.getString("taskType");
                String group=rs.getString("tGroup");
                String course=rs.getString("tCourse");
                String kons=rs.getString("tKons");
                String teacher=rs.getString("tTeacher");
                Timestamp time=rs.getTimestamp("tTime");
                String taskState=rs.getString("tTaskstate");

                task.settID(tID);
                task.setTaskType(taskType);
                task.settGroup(group);
                task.settCourse(course);
                task.settKons(kons);
                task.settTeacher(teacher);

                /*
                @TODO
                可能会出问题
                */
                task.setdDate(time);
                task.setTaskState(taskState);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return task;
    }

    public List<Task> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Task> list(int start, int count) {
        List<Task> ems= new ArrayList<Task>();

        String sql = "select * from taskform order by tID desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task task=new Task();
                int tID=rs.getInt("tID");
                String taskType = rs.getString("tTaskType");
                String group=rs.getString("tGroup");
                String course=rs.getString("tCourse");
                String kons=rs.getString("tKons");
                String teacher=rs.getString("tTeacher");
                Timestamp time=rs.getTimestamp("tTime");
                String taskState=rs.getString("tTaskstate");

                task.settID(tID);
                task.setTaskType(taskType);
                task.settGroup(group);
                task.settCourse(course);
                task.settKons(kons);
                task.settTeacher(teacher);

                /*
                @TODO
                可能会出问题
                */
                task.setdDate(time);
                task.setTaskState(taskState);
                ems.add(task);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return ems;
    }

/*
    public static void main(String args[]){
        List<Task> tasks=new TaskDAO().list();
        for (Task task:tasks){
            System.out.println(task.gettCourse());
        }

    }
    */



}


