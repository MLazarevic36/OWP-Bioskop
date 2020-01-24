package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import cinema.entity.User;
import cinema.entity.User.Role;

public class UserDAO {
	

	public static User get(String username, String password) throws Exception {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int index = 1;
				Integer id = rs.getInt(index++);
				String username_rs = rs.getString(index++);
				String password_rs = rs.getString(index++);
				String registrationdate = rs.getString(index++);
				Role role = Role.valueOf(rs.getString(index++));
				
				Date date = sdf.parse(registrationdate);
				
				User user = new User();
				user.setId(id);
				user.setUsername(username_rs);
				user.setPassword(password_rs);
				user.setRegistrationDate(date);
				user.setDateOutput(registrationdate);
				user.setRole(role);
				
				return user;
			}
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return null;
		
	}
	
	public static boolean add(User user) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO users (username, password, registrationdate, role) " + "VALUES (?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, user.getUsername());
			ps.setString(index++, user.getPassword());
			ps.setString(index++, user.getRegistrationDate().toString());
			ps.setString(index++, user.getRole().toString());
			
			return ps.executeUpdate() == 1;
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
	}
	
public static User get(String username) throws Exception {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String query = "SELECT * FROM users WHERE username = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int index = 1;
				Integer id = rs.getInt(index++);
				String username_rs = rs.getString(index++);
				String password_rs = rs.getString(index++);
				String registrationdate = rs.getString(index++);
				Role role = Role.valueOf(rs.getString(index++));
				
				Date date = sdf.parse(registrationdate);
				
				User user = new User();
				user.setId(id);
				user.setUsername(username_rs);
				user.setPassword(password_rs);
				user.setRegistrationDate(date);
				user.setDateOutput(registrationdate);
				user.setRole(role);
				
				return user;
			}
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return null;
		
	}
	
	
}
