package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cinema.entity.ProjectionType;

public class ProjectionTypeDAO {
	
	public static List<ProjectionType> getAll() {
		List<ProjectionType> projection_types = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM projection_types";
			
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				String name = rs.getString(index++);
				
				ProjectionType projectionType = new ProjectionType();
				projectionType.setId(id);
				projectionType.setName(name);
				
				projection_types.add(projectionType);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return projection_types;
	}
	
	public static ProjectionType get(Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM projection_types WHERE id = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int index = 1;
				Integer id_rs = rs.getInt(index++);
				String name_rs = rs.getString(index++);
				
				ProjectionType projectionType = new ProjectionType();
				projectionType.setId(id_rs);
				projectionType.setName(name_rs);
				return projectionType;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}

}
