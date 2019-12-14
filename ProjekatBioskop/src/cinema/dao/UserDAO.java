package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import cinema.entity.User;
import cinema.entity.User.Role;

public class UserDAO {
	
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static User get(String username, String password) throws Exception {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT role, registrationdate FROM users WHERE username = ? AND password = ?";
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, username);
			ps.setString(index++, password);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Role role = Role.valueOf(rs.getString(1));
				String registrationdate = rs.getString(2);
				Timestamp regdate = (Timestamp) DATETIME_FORMAT.parse(registrationdate);
				
				return new User(username, password, regdate, role);
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
	
	
}
