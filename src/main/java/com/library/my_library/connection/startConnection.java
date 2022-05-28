package com.library.my_library.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class startConnection {
    Connection con;
    public startConnection(){

    }
    public Connection getCon() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylibrary","root","");
        return con;
    }
    public void endCon() throws SQLException {
        if(con != null) {
            con.close();
        }
    }
}
