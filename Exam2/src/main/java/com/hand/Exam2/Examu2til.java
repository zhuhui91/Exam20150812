package com.hand.Exam2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Examu2til {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("请输入 Customer ID:");
		String CustomerID=input.next();
		getConnection();
		select(CustomerID);
		
	}
	public static Connection getConnection(){  //连接函数
		Connection conn=null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver"); //注册mysql驱动程序
	    	  conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","");
			System.out.println("成功连接到数据库：sakila");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}

	public static void select(String ctud){  //添加/插入记录
		Connection conn=getConnection();
		try{
			
			Statement stmt = conn.createStatement();
			
			String ctd="select first_name,last_name from customer where customer_id="; //customer table
			ctd+=ctud;
			ResultSet rc = stmt.executeQuery(ctd);			
			while (rc.next()) {
				System.out.println(rc.getString("first_name")+"."+rc.getString("last_name")+" 租用的 Film->");
			}
			
			String rvd="select inventory_id from rental where customer_id="; //rental table
			rvd+=ctud;
			ResultSet rv = stmt.executeQuery(rvd);
			String fvt[]=new String[255]; //获取inventory_id
			int i=0;
			while (rv.next()) {
				fvt[i]=rv.getString("inventory_id");
				i++;
			}
			

			String filmdate[]=new String[255];  //获取rental_date
			int dt=0;
			for(int k=0;k<i-1.;k++){
				String rdte="select rental_date from rental where inventory_id="; //inventory table
				rdte+=fvt[k];
				ResultSet rde = stmt.executeQuery(rdte);
			while (rde.next()) {
				filmdate[dt]=rde.getString("rental_date");
			}
			dt++;
			}
			
			String filmid[]=new String[255]; //获取film_id
			int j=0;
			for(int k=0;k<i-1.;k++){
				String rvtd="select film_id from inventory where inventory_id="; //inventory table
				rvtd+=fvt[k];
				ResultSet rvf = stmt.executeQuery(rvtd);
			while (rvf.next()) {
				filmid[j]=rvf.getString("film_id");
			}
			j++;
			}
			
			String filmtile[]=new String[255]; //获取title
			int ct=0;
			for(int k=0;k<j-1.;k++){
				String rfltd="select title from film where film_id="; //film table
				rfltd+=fvt[k];
				ResultSet rtf = stmt.executeQuery(rfltd);
			while (rtf.next()) {
				filmtile[ct]=rtf.getString("title");
			}
			ct++;
			}
			System.out.println("Film ID|"+"Film 名称|"+"\t"+"租用时间");
			for(int k=0;k<ct-1;k++){
				System.out.println(filmid[k]+"|"+"\t"+filmtile[k]+"|"+"\t"+filmdate[k]);
			}
			
			 conn.close(); //关闭数据库
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

