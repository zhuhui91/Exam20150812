package com.hand.Exam1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Examutil {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("请输入 Country ID:");
		String cityId=input.next();
		getConnection();
		select(cityId);
		
	}
	public static Connection getConnection(){  //连接函数
		Connection conn=null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver"); //注册mysql驱动程序
	    	  conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","");
			//System.out.println("成功连接到数据库：sakila");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}

	public static void select(String cyID){  //添加/插入记录
		Connection conn=getConnection();
		try{
			Statement stmt = conn.createStatement();
			String cty="select city_id,city from city where country_id=";
			cty+=cyID;
			ResultSet rs = stmt.executeQuery(cty);
			
			 //Statement st=conn.createStatement();
			 System.out.println("Country Span 的城市->S");
			 System.out.println("城市 ID|" + "\t"
						+ "城市名称|");

 while (rs.next()) {
		System.out.println(rs.getString("city_id") + "\t"
				+ rs.getString("city"));
	}
			 
			 conn.close(); //关闭数据库
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
