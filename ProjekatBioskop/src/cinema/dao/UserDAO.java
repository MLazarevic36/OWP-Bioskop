package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cinema.entity.User;
import cinema.entity.User.Role;

public class UserDAO {
	

	public static User get(String username, String password) throws Exception {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String query = "SELECT * FROM users WHERE username = ? AND password = ? AND deleted = 0";
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

	public static List<User> getAll() {
	
		List<User> users = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM users WHERE deleted = 0";
			
			ps = con.prepareStatement(query);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				String username = rs.getString(index++);
				String password = rs.getString(index++);
				String registrationdate = rs.getString(index++);
				String role = rs.getString(index++);
				Date date = sdf.parse(registrationdate);
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setRegistrationDate(date);
				user.setRole(Role.valueOf(role));
				user.setDateOutput(registrationdate);
				
				users.add(user);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return users;
	}
	
	public static User get(Integer id) throws Exception {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String query = "SELECT * FROM users WHERE id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int index = 1;
				Integer id_rs = rs.getInt(index++);
				String username_rs = rs.getString(index++);
				String password_rs = rs.getString(index++);
				String registrationdate = rs.getString(index++);
				Role role = Role.valueOf(rs.getString(index++));
				
				Date date = sdf.parse(registrationdate);
				
				User user = new User();
				user.setId(id_rs);
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
	
	public static boolean updatePassword(String password, Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE users SET password = ? WHERE id = ?";
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, password);
			ps.setInt(index++, id);
			
			return ps.executeUpdate() == 1;
					
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
	}
	
	public static boolean updateRole(String role, Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE users SET role = ? WHERE id = ?";
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, role);
			ps.setInt(index++, id);
			
			return ps.executeUpdate() == 1;
					
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
	}
	
	public static boolean delete(Integer id) {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		
		try {
			
			String query = "DELETE FROM users WHERE id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			return ps.executeUpdate() == 1;
			
		}catch (Exception ex){
			ex.printStackTrace();
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	
	public static boolean logicDelete(Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE users SET deleted = 1 WHERE id = ?";
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, id);
			
			return ps.executeUpdate() == 1;
					
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
	}
	
	public static boolean addUser(String username, String password) {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			String query = "INSERT INTO users (username, password, registrationdate, role, deleted) "
							+ "VALUES (?, ?, ?, ?, 0)";
			ps = con.prepareStatement(query);
			int index = 1;
			Date date = new Date();
			String datefordb = sdf.format(date);
			ps.setString(index++, username);
			ps.setString(index++, password);
			ps.setString(index++, datefordb);
			ps.setString(index++, "USER");
			
			return ps.executeUpdate() == 1;
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	
	public static List<User> searchUsers(String username, String role) {

		List<User> users = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM users WHERE deleted = 0";
		
		if (username != null && username != "") {
			query += " AND username LIKE ?";
		}
		if (role != null && role != "") {
			query += " AND role = ?";
		}
		
		ps = con.prepareStatement(query);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		int indexx = 1;
		if (username != null && username != "") {
			ps.setString(indexx++, "%" + username + "%");
		}
		if (role != null && role != "") {
			ps.setString(indexx++, role);
		}

		rs = ps.executeQuery();
		
		while (rs.next()) {
			int index = 1;
			int id = rs.getInt(index++);
			String username_rs = rs.getString(index++);
			String password = rs.getString(index++);
			String registrationdate = rs.getString(index++);
			String role_rs = rs.getString(index++);
			Date date = sdf.parse(registrationdate);
			User user = new User();
			user.setId(id);
			user.setUsername(username_rs);
			user.setPassword(password);
			user.setRegistrationDate(date);
			user.setRole(Role.valueOf(role_rs));
			user.setDateOutput(registrationdate);
			
			users.add(user);
		
		}
		
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
			return users;
		}
	
}
