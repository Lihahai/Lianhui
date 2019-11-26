package DAO;

import java.sql.*;

public class ConnectionManager {
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://127.0.0.1:3306/itcast?useSSL=false&serverTimezone=UTC";
    private static final String user="root";
    private static final String pwd="admin";
    public static Connection conn;
    public static Connection getaConnection(){
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            conn= DriverManager.getConnection(url,user,pwd);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeResultSet(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
                rs=null;
            }
        }catch (SQLException el){
            el.printStackTrace();
        }
    }

    public static void closeStatement(PreparedStatement pStmt){
        try {
            if(pStmt!=null){
                pStmt.close();
                pStmt=null;
            }
        }catch (SQLException e2){
            e2.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn){
        try {
            if(conn!=null&&(!conn.isClosed())){
                conn.close();
            }
        }catch (SQLException e3){
            e3.printStackTrace();
        }
    }



    public static ResultSet select(String sql) {
        ResultSet res = null;

        conn =ConnectionManager.getaConnection();

        Statement state = null;

        if (!(conn == null)) {

            try {
                state = conn.createStatement();
                res = state.executeQuery(sql);
            } catch (SQLException e) {
                return null;
            }

        }

        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                return null;
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                return null;
            }
        }

        return res;
    }

    public int insert(String sql) {
        int iId = -1;

        conn = getaConnection();

        Statement state = null;

        if (conn != null) {
            try {
                state = conn.createStatement();

                int res = state.executeUpdate(sql,
                        Statement.RETURN_GENERATED_KEYS);

                if (res != 0) {
                    ResultSet rs = state.getGeneratedKeys();
                    if (rs.next()) {
                        iId = rs.getInt(1);
                    }
                }
            } catch (SQLException e) {
                iId = -1;
            }

        }

        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }

        return iId;
    }

    public boolean update(String sql) {
        boolean updated = false;

        Connection con = getaConnection();
        Statement state = null;

        if (con != null) {
            try {
                state = con.createStatement();

                int res = state.executeUpdate(sql);

                if (res == 0) {
                    updated = false;
                } else {
                    updated = true;
                }
            } catch (SQLException e) {
                updated = false;
            }

        }

        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        return updated;
    }

    public static boolean delete(String sql) {
        boolean deleted = false;

        Connection con = getaConnection();

        Statement state = null;

        if (con != null) {
            try {
                state = con.createStatement();

                int res = state.executeUpdate(sql);

                if (res == 0) {
                    deleted = false;
                } else {
                    deleted = true;
                }

            } catch (SQLException e) {
                deleted = false;
            }

        }

        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        return deleted;
    }



}
