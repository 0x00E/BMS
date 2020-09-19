package bms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JDBC{
	
	private static Connection dm;
	private static String jdbc="jdbc:mysql://127.0.0.1/bms";
	private static String username="root";
	private static String password="root";
	private static ResultSet rs;
	private static PreparedStatement ps;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not find.");
		}
		isAvailable();
	}
	
	public static boolean isAvailable(){
		try {
			dm=DriverManager.getConnection(jdbc, username, password);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return dm!=null;
	}
	
	public static void close() {
			try {
				dm.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}	
	public static boolean execute(String sql,Object... obj){
		boolean isTrue=false;
		try {
			ps = dm.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			isTrue=ps.execute();	
		} catch (SQLException e) {
			System.err.println("SQL Error:"+e.getMessage());
		}
		return isTrue;
	}
	public static int executeUpdate(String sql,Object... obj) throws SQLException {
			int lines=0;
			try {
				ps = dm.prepareStatement(sql);
				for(int i=0;i<obj.length;i++){
					ps.setObject(i+1, obj[i]);
				}
				lines=ps.executeUpdate();
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}
			
			return lines;
	}
	public static ArrayList<LinkedHashMap<Integer, Object>> executeQuery(String sql,Object... obj){
		
		
		try {
			PreparedStatement ps=dm.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			rs=ps.executeQuery();
			ArrayList<LinkedHashMap<Integer, Object>> al= new ArrayList<>();
			while(rs.next()){
				LinkedHashMap<Integer, Object> hm=new LinkedHashMap<>();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
					hm.put(i, rs.getObject(i));
				}
				al.add(hm);
			}
			return al;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	public static ArrayList<LinkedHashMap<String, Object>> executeQueryC(String sql,Object... obj){
		
		try {
			PreparedStatement ps=dm.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			ResultSet rs=ps.executeQuery();
			
			ArrayList<LinkedHashMap<String, Object>> al = new ArrayList<>();
			do{
				if(rs.isBeforeFirst()){
					rs.next();
				}
				{
					LinkedHashMap<String, Object> hm=new LinkedHashMap<>();
					for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
						
							try {
								if(Double.parseDouble(rs.getObject(i).toString())==0){
									hm.put(rs.getMetaData().getColumnName(i), "");
									continue;
								}
							} catch (Exception e) {}
							try {
								hm.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
							} catch (Exception e) {
								hm.put(rs.getMetaData().getColumnName(i), "");
								continue;
							}
					}
					al.add(hm);
				}
			}while(rs.next());
			return al;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}