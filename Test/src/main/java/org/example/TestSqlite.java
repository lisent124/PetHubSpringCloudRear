package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSqlite {
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            //这一行要使用我们搭建SQLite时的url
            c = DriverManager.getConnection("jdbc:sqlite:D:\\Projects\\javaProjects\\PetHubSpringCloud\\db.sqlite3");

            stmt = c.createStatement();
            //包括这里执行的查询语句也需要根据你建立的表格来决定
            ResultSet rs = stmt.executeQuery( "SELECT * FROM petHub_blog;" );
            while ( rs.next() ) {
                String Num = rs.getString("id");
                String Name = rs.getString("user_id");
                System.out.println("我的id是： "+Num);
                System.out.println("我的userId是： "+Name);

            }
            rs.close();
            stmt.close();
            c.close();


        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
