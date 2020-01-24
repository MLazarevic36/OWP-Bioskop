package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cinema.entity.ProjectionType;
import cinema.entity.Theater;

public class TheaterDAO {
	
	public static List<Theater> getAll() {
		List<Theater> theaters = new ArrayList<>();
		List<ProjectionType> projectionTypes = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM theaters";
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				String name = rs.getString(index++);
				int projection_type = rs.getInt(index++);
				
				Theater theater = new Theater();
				theater.setId(id);
				theater.setName(name);
				ProjectionType pt = ProjectionTypeDAO.get(projection_type);
				projectionTypes.add(pt);
				theater.setProjectionTypes((ArrayList<ProjectionType>) projectionTypes);
				
				theaters.add(theater);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return theaters;
	}
	
	public static Theater get(Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM theaters WHERE id = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int index = 1;
				Integer id_rs = rs.getInt(index++);
				String name_rs = rs.getString(index++);
				int projection_type_rs = rs.getInt(index++);
				ProjectionType projectionType = ProjectionTypeDAO.get(projection_type_rs);
				List<ProjectionType> projectionTypes = new ArrayList<>();
				projectionTypes.add(projectionType);
				
				Theater theater = new Theater();
				theater.setId(id_rs);
				theater.setName(name_rs);
				theater.setProjectionTypes((ArrayList<ProjectionType>) projectionTypes);
				return theater;
				
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static Theater getByName(String name) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM theaters WHERE name = ?";
			
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int index = 1;
				Integer id_rs = rs.getInt(index++);
				String name_rs = rs.getString(index++);
				Integer pt_rs = rs.getInt(index++);
				
				Theater theater = new Theater();
				theater.setId(id_rs);
				theater.setName(name_rs);
				List<ProjectionType> projectionTypes = new ArrayList<>();
				ProjectionType projectionType = ProjectionTypeDAO.get(pt_rs);
				projectionTypes.add(projectionType);
				theater.setProjectionTypes((ArrayList<ProjectionType>) projectionTypes);
				return theater;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}

}
