package DAO;


import shitilei.Division;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DivisionDAO {

    //提交时间初始化值
    private static String dateStr="2000-01-01 12:00:00";
    private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Date startDate;

    static {
        try {
            startDate = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public DivisionDAO(){
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
            String sql="select count(*) from divisionform";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                total++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return total;
    }

    public void add(Division division) {

        String sql = "insert into divisionform values(null,?,?,?,?,?,?)";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            Timestamp timestamp=new Timestamp(startDate.getTime());
            ps.setInt(1, division.getTaskId());
            ps.setInt(2, division.geteNo());
            ps.setString(3, division.getdName());
            ps.setString(4, division.getdState());
            ps.setFloat(5, division.getdIntegeral());
            ps.setTimestamp(6,timestamp);
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

    public void update(Division division) {

        String sql = "update divisionform set dTaskid= ?,dEmnno = ? , dName = ? , dStata = ?,dIntegeral=? ,dDate=?  where dfID = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            Date date=division.getdDate();
            Timestamp timestamp=new Timestamp(date.getTime());
            ps.setInt(1, division.getTaskId());
            ps.setInt(2, division.geteNo());
            ps.setString(3, division.getdName());
            ps.setString(4, division.getdState());
            ps.setFloat(5, division.getdIntegeral());
            ps.setTimestamp(6,timestamp);
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try(Connection c=getConnection();Statement s=c.createStatement()){

            String sql = "delete from divisionform where dfID = " + id;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Division get(int dfID) {
        Division division= null;


        try (Connection c=getConnection();Statement s=c.createStatement();){
            String sql = "select * from divisionform where dfID = " + dfID;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                division=new Division();
                int taskId=rs.getInt("dTaskid");
                int eNo=rs.getInt("dEmnno");
                String dName = rs.getString("dName");
                String dState=rs.getString("dStata");
                float dIntegeral=rs.getFloat("dIntegeral");
                Timestamp time=rs.getTimestamp("dDate");

                division.setdID(dfID);
                division.setTaskId(taskId);
                division.seteNo(eNo);
                division.setdName(dName);
                division.setdState(dState);
                division.setdIntegeral(dIntegeral);
                /*
                @TODO
                可能会出问题
                */
                division.setdDate(time);


            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return division;
    }


    public List<Division> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Division> list(int start, int count) {
        List<Division> ems= new ArrayList<Division>();

        String sql = "select * from divisionform order by dfID desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Division division=new Division();
                int dID=rs.getInt("dfID");
                int taskId=rs.getInt("dTaskid");
                int eNo=rs.getInt("dEmnno");
                String dName = rs.getString("dName");
                String dState=rs.getString("dStata");
                float dIntegeral=rs.getFloat("dIntegeral");
                Timestamp time=rs.getTimestamp("dDate");

                division.setdID(dID);
                division.setTaskId(taskId);
                division.seteNo(eNo);
                division.setdName(dName);
                division.setdState(dState);
                division.setdIntegeral(dIntegeral);
                /*
                @TODO
                可能会出问题
                */
                division.setdDate(time);
                ems.add(division);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return ems;
    }

/*
    public static void main(String args[]){
        List<Division> divisions=new DivisionDAO().list();
        for (Division division:divisions){
            System.out.println(division.geteNo());
        }

    }

 */
}
