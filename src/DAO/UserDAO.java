package DAO;

import shitilei.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public UserDAO(){
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
            String sql="select count(*) from user";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                total=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return total;
    }

    public void add(User user) {

        String sql = "insert into user values(?,?,?,?)";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, user.getuAccount());
            ps.setString(2, user.getuPwd());
            ps.setInt(3 ,user.geteNo());
            ps.setInt(4, user.getuGrage());

            ps.execute();

            /*
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                e1.seteID(id);
            }

             */
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(User user) {

        String sql = "update user set uaccount= ?, upassword = ? , uGrage = ? where eNo = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, user.getuAccount());

            ps.setString(2, user.getuPwd());
            ps.setInt(3,user.getuGrage());
            ps.setInt(4, user.geteNo());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int eNo) {
        try(Connection c=getConnection();Statement s=c.createStatement()){

            String sql = "delete from user where eNo = " + eNo;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public User get(String uAccount) {
        User user = null;


        try (Connection c=getConnection();Statement s=c.createStatement();){
            String sql = "select * from user where uaccount = " + uAccount;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                user = new User();


                String uPwd = rs.getString("upassword");
                int uGrage = rs.getInt("uGrage");
                int eNo =rs.getInt("eNo");

                user.setuAccount(uAccount);
                user.setuPwd(uPwd);
                user.seteNo(eNo);
                user.setuGrage(uGrage);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return user;
    }

    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<User> list(int start, int count) {
        List<User> users= new ArrayList<User>();

        String sql = "select * from user order by eNo desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user=new User();
                String uAccount=rs.getString("uaccount");
                String uPwd=rs.getString("upassword");
                int eNo=rs.getInt("eNo");
                int uGrage=rs.getInt("uGrage");

                user.setuAccount(uAccount);
                user.setuPwd(uPwd);
                user.seteNo(eNo);
                user.setuGrage(uGrage);
                users.add(user);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return users;
    }

    /*
    public static void main(String args[]){
        List<User> users=new UserDAO().list();
        for (User user:users){
            System.out.println(user.geteNo());
        }

    }

     */

}
