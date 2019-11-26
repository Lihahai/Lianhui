package DAO;

import shitilei.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public EmployeeDAO(){
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
        try(Connection c=getConnection();Statement s=c.createStatement();){
            String sql="select count(*) from employeeform";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                total=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return total;
    }

    public void add(Employee e1) {

        String sql = "insert into employeeform values(null,?,?,?,?,?)";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, e1.geteNo());
            ps.setString(2, e1.geteName());
            ps.setInt(3, e1.geteMouthIntegral());
            ps.setInt(4, e1.geteSumIntegral());
            ps.setInt(5, e1.geteExtraIntegral());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                e1.seteID(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(Employee e1) {

        String sql = "update employeeform set eName= ?, eMouthIntegral = ? , eSumIntegral = ?,eExtraIntegral=? where eNo = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, e1.geteName());

            ps.setInt(2, e1.geteMouthIntegral());
            ps.setInt(3, e1.geteSumIntegral());
            ps.setInt(4, e1.geteExtraIntegral());
            ps.setInt(5, e1.geteNo());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try(Connection c=getConnection();Statement s=c.createStatement()){

            String sql = "delete from employeeform where eNo = " + id;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Employee get(int eNo) {
        Employee e1 = null;


        try (Connection c=getConnection();Statement s=c.createStatement();){
            String sql = "select * from employeeform where id = " + eNo;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                e1 = new Employee();
                int eID = rs.getInt("eID");
                String eName = rs.getString("eName");
                int eMouthIntegral = rs.getInt("eMouthIntegral");
                int eSumIntegral = rs.getInt("eSumIntegral");
                int eExtraIntegral = rs.getInt("eExtraIntegral");

                e1.seteID(eID);
                e1.seteNo(eNo);
                e1.seteName(eName);
                e1.seteMouthIntegral(eMouthIntegral);
                e1.seteSumIntegral(eSumIntegral);
                e1.seteExtraIntegral(eExtraIntegral);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return e1;
    }

    public List<Employee> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Employee> list(int start, int count) {
        List<Employee> ems= new ArrayList<Employee>();

        String sql = "select * from employeeform order by eID desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee e1 = new Employee();
                int eID = rs.getInt("eID");
                int eNo=rs.getInt("eNo");
                String eName = rs.getString("eName");
                int eMouthIntegral = rs.getInt("eMouthIntegral");
                int eSumIntegral = rs.getInt("eSumIntegral");
                int eExtraIntegral = rs.getInt("eExtraIntegral");

                e1.seteID(eID);
                e1.seteNo(eNo);
                e1.seteName(eName);
                e1.seteMouthIntegral(eMouthIntegral);
                e1.seteSumIntegral(eSumIntegral);
                e1.seteExtraIntegral(eExtraIntegral);
                ems.add(e1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return ems;
    }

/*
    public static void main(String args[]){
        List<Employee> employees=new EmployeeDAO().list();
        for (Employee employee:employees){
            System.out.println(employee.geteName());
        }

    }

 */

}
