package DAO;



import shitilei.Integral;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntegralDAO {
    public IntegralDAO(){
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

    public void add(Integral integral) {

        String sql = "insert into integral values(null,?,?,?)";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, integral.geteNo());
            ps.setInt(2, integral.getiMouth());
            ps.setFloat(3, integral.getiNumer());
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

    public void update(Integral integral) {

        String sql = "update integral set eNo= ?,iMouth = ? , iNumber = ?  where Iid = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, integral.geteNo());
            ps.setInt(2, integral.getiMouth());
            ps.setFloat(3, integral.getiNumer());
            ps.execute();

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int Iid) {
        try(Connection c=getConnection();Statement s=c.createStatement()){

            String sql = "delete from integral where tID = " + Iid;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<Integral> get(int eNo) {
        Integral integral = null;
        List<Integral> integrals=new ArrayList<>();

        try (Connection c=getConnection();Statement s=c.createStatement();){
            String sql = "select * from integral where id = " + eNo;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                integral=new Integral();
                int id=rs.getInt("Iid");
                int iMouth=rs.getInt("iMouth");
                float iNumer=rs.getFloat("iNumer");

                integral.setiID(id);
                integral.seteNo(eNo);
                integral.setiMouth(iMouth);
                integral.setiNumer(iNumer);
                integrals.add(integral);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return integrals;
    }

    public List<Integral> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Integral> list(int start, int count) {
        List<Integral> ems= new ArrayList<Integral>();

        String sql = "select * from integral order by Iid desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integral integral=new Integral();
                int id=rs.getInt("Iid");
                int eNo=rs.getInt("eNo");
                int iMouth=rs.getInt("iMouth");
                float iNumer=rs.getFloat("iNumer");

                integral.setiID(id);
                integral.seteNo(eNo);
                integral.setiMouth(iMouth);
                integral.setiNumer(iNumer);


                ems.add(integral);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return ems;
    }

    public static void main(String args[]){
        List<Integral> integrals=new IntegralDAO().list();
        for (Integral integral:integrals){
            System.out.println(integral.getiNumer());
        }

    }

}
