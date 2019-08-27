package com.data;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class database {
//	private static String jdbcDriver = "com.mysql.jdbc.Driver";// mysql连接驱动,无需改
	
//	public static String connStr="jdbc:mysql://localhost:3306/apk?useSSL=true&useUnicode=true&characterEncoding=utf8";
//	public static String username = "root";//数据库用户名
//	public static String userpassword = "root";//数据库密码
//	private static Connection conn;
//	
//	static {
//		try {
//			Class.forName(jdbcDriver);// 加载mysql驱动类
//			conn = DriverManager.getConnection(connStr, username, userpassword);
//			// 驱动利用驱动地址，数据库用户名，密码创建连接
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	//以上基本是固定格式的

	
	public static void DBinit(String fileDir) throws Exception  {  
		List<File> fileList = new ArrayList<File>(); 
	    File file = new File(fileDir); 
	    File[] files = file.listFiles();// 获取目录下的所有文件或文件夹 
	    if (files == null) {// 如果目录为空，直接退出 
	      return; 
	    } 
	    // 遍历，目录下的所有文件 
	    for (File f : files) { 
	      if (f.isFile()) { 
	        fileList.add(f); 
	      } else if (f.isDirectory()) { 
	        test(f.getAbsolutePath()); 
	      } 
	    } 
	    
	    
        Class.forName("com.mysql.jdbc.Driver");  
           
        //一开始必须填一个已经存在的数据库  
        String url = "jdbc:mysql://localhost:3306/temp?useUnicode=true&characterEncoding=utf-8";     
        Connection conn = DriverManager.getConnection(url, "root", "301635");  
        Statement stat = conn.createStatement();  
           
        //创建数据库hello  
        stat.executeUpdate("create database localText");  
           

        //打开创建的数据库  
        stat.close();  
        conn.close();  
        url = "jdbc:mysql://localhost:3306/localText?useUnicode=true&characterEncoding=utf-8";  
        conn = DriverManager.getConnection(url, "root", "301635");  
        stat = conn.createStatement();  
           
        //创建表test  
        stat.executeUpdate("create table test(file_name varchar(100))");  
           
        //添加数据  
        stat.executeUpdate("insert into test values(f1.getName())");   
        
        //查询数据  
        ResultSet result = stat.executeQuery("select * from localText");  
        while (result.next())  
        {  
            System.out.println(result.getString("file_name"));  
        }  
           
        //关闭数据库  
        result.close();  
        stat.close();  
        conn.close();  
    }  
	private static void test(String fileDir) { 
		
	    List<File> fileList = new ArrayList<File>(); 
	    File file = new File(fileDir); 
	    File[] files = file.listFiles();// 获取目录下的所有文件或文件夹 
	    
	    if (files == null) {// 如果目录为空，直接退出 
	      return; 
	    } 
	    // 遍历，目录下的所有文件 
	    for (File f : files) { 
	      if (f.isFile()) { 
	        fileList.add(f); 
	      } else if (f.isDirectory()) { 
	        test(f.getAbsolutePath()); 
	      } 
	    } 
	    for (File f1 : fileList) { 
	      System.out.println(f1.getName()); 
	    } 
	  } 
}