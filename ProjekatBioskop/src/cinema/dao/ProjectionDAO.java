package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cinema.entity.Movie;
import cinema.entity.Projection;
import cinema.entity.ProjectionType;
import cinema.entity.Theater;

public class ProjectionDAO {
	
public static List<Projection> getAll() {
		
		List<Projection> projections = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM projections ORDER BY movie, dateandtime";
			
			ps = con.prepareStatement(query);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				int title = rs.getInt(index++);
				int projection_type = rs.getInt(index++);
				int theater_rs = rs.getInt(index++);
				String date_and_time = rs.getString(index++);
				double price = rs.getDouble(index++);
				int admin_creator = rs.getInt(index++);
				
				Theater theater = TheaterDAO.get(theater_rs);
				Movie movie = MovieDAO.get(title);
				ProjectionType projectionType = ProjectionTypeDAO.get(projection_type);
				Projection projection = new Projection();
				projection.setId(id);
				projection.setMovie(movie.getTitle());
				projection.setProjectionType(projectionType.getName());
				projection.setTheater(theater.getName());
				Date date = sdf.parse(date_and_time);
				projection.setDateOutput(date_and_time);
				projection.setDateAndTime(date);
				projection.setTicketPrice(price);
				projection.setAdminCreator(admin_creator);
				
				projections.add(projection);
				
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return projections;
	}

	public static boolean addProjection(Projection projection) {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) "
						   + "VALUES (?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			int index = 1;
			Movie movie = MovieDAO.getByTitle(projection.getMovie());
			ps.setInt(index++, movie.getId());
			ProjectionType projectionType = ProjectionTypeDAO.getByName(projection.getProjectionType());
			ps.setInt(index++, projectionType.getId());
			Theater theater = TheaterDAO.getByName(projection.getTheater());
			ps.setInt(index++, theater.getId());
			ps.setString(index++, projection.getDateOutput());
			ps.setDouble(index++, projection.getTicketPrice());
			ps.setInt(index++, projection.getAdminCreator());
			
			return ps.executeUpdate() == 1;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	public static Projection get(Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM projections WHERE id = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			if(rs.next()) {
				int index = 1;
				int id_rs = rs.getInt(index++);
				int title = rs.getInt(index++);
				int projection_type = rs.getInt(index++);
				int theater_rs = rs.getInt(index++);
				String date_and_time = rs.getString(index++);
				double price = rs.getDouble(index++);
				int admin_creator = rs.getInt(index++);
				
				Theater theater = TheaterDAO.get(theater_rs);
				Movie movie = MovieDAO.get(title);
				ProjectionType projectionType = ProjectionTypeDAO.get(projection_type);
				Projection projection = new Projection();
				projection.setId(id_rs);
				projection.setMovie(movie.getTitle());
				projection.setProjectionType(projectionType.getName());
				projection.setTheater(theater.getName());
				Date date = sdf.parse(date_and_time);
				projection.setDateOutput(date_and_time);
				projection.setDateAndTime(date);
				projection.setTicketPrice(price);
				projection.setAdminCreator(admin_creator);
				
				return projection;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static Projection getLast() throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM projections WHERE id = (SELECT MAX(id) FROM projections)";
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			if(rs.next()) {
				int index = 1;
				int id_rs = rs.getInt(index++);
				int title = rs.getInt(index++);
				int projection_type = rs.getInt(index++);
				int theater_rs = rs.getInt(index++);
				String date_and_time = rs.getString(index++);
				double price = rs.getDouble(index++);
				int admin_creator = rs.getInt(index++);
				
				Theater theater = TheaterDAO.get(theater_rs);
				Movie movie = MovieDAO.get(title);
				ProjectionType projectionType = ProjectionTypeDAO.get(projection_type);
				Projection projection = new Projection();
				projection.setId(id_rs);
				projection.setMovie(movie.getTitle());
				projection.setProjectionType(projectionType.getName());
				projection.setTheater(theater.getName());
				Date date = sdf.parse(date_and_time);
				projection.setDateOutput(date_and_time);
				projection.setDateAndTime(date);
				projection.setTicketPrice(price);
				projection.setAdminCreator(admin_creator);
				
				return projection;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static List<Projection> getAllByMovie(Integer movie_id) {
		
		List<Projection> projections = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM projections WHERE movie = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, movie_id);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				int title = rs.getInt(index++);
				int projection_type = rs.getInt(index++);
				int theater_rs = rs.getInt(index++);
				String date_and_time = rs.getString(index++);
				double price = rs.getDouble(index++);
				int admin_creator = rs.getInt(index++);
				
				Theater theater = TheaterDAO.get(theater_rs);
				Movie movie = MovieDAO.get(title);
				ProjectionType projectionType = ProjectionTypeDAO.get(projection_type);
				Projection projection = new Projection();
				projection.setId(id);
				projection.setMovie(movie.getTitle());
				projection.setProjectionType(projectionType.getName());
				projection.setTheater(theater.getName());
				Date date = sdf.parse(date_and_time);
				projection.setDateOutput(date_and_time);
				projection.setDateAndTime(date);
				projection.setTicketPrice(price);
				projection.setAdminCreator(admin_creator);
				
				projections.add(projection);
				
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return projections;
	}
	
	public static List<Projection> searchProjections(String movie, String projectionType, String theater,
													String startDate, String endDate, String minPrice, String maxPrice) {
		
		List<Projection> projections = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM projections WHERE admincreator = 1";
			
			if (movie != null && movie != "") {
				query += " AND movie = ?";
			}
			if (projectionType != null && projectionType != "") {
				query += " AND projectiontype = ?";
			}
			if (theater != null && theater != "") {
				query += " AND theater = ?";
			}
			if (startDate != null && startDate != "" && endDate != null && endDate != "") {
				query += " AND dateandtime BETWEEN ? AND ?";
			}
			if (minPrice != null && minPrice != "") {
				query += " AND ticketprice >= ?";
			}
			if (maxPrice != null && maxPrice != "") {
				query += " AND ticketprice <= ?";
			}
			
			
			ps = con.prepareStatement(query);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			int indexx = 1;
			if (movie != null && movie != "") {
				ps.setInt(indexx++, Integer.parseInt(movie));
			}
			if (projectionType != null && projectionType != "") {
				ps.setString(indexx++, projectionType);
			}
			if (theater != null && theater != "") {
				ps.setString(indexx++, theater);
			}
			if (startDate != null && startDate != "" && endDate != null && endDate != "") {
				ps.setString(indexx++, startDate);
				ps.setString(indexx++, endDate);
			}
			if (minPrice != null && minPrice != "") {
				ps.setDouble(indexx++, Double.parseDouble(minPrice));
			}
			if (maxPrice != null && maxPrice != "") {
				ps.setDouble(indexx++, Double.parseDouble(maxPrice));
			}
			
			rs = ps.executeQuery();
			
			System.out.println(query);
			
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				int title = rs.getInt(index++);
				int projection_type = rs.getInt(index++);
				int theater_rs = rs.getInt(index++);
				String date_and_time = rs.getString(index++);
				double price = rs.getDouble(index++);
				int admin_creator = rs.getInt(index++);
				
				Theater theater_object = TheaterDAO.get(theater_rs);
				Movie movie_obj = MovieDAO.get(title);
				ProjectionType projectionType_object = ProjectionTypeDAO.get(projection_type);
				Projection projection = new Projection();
				projection.setId(id);
				projection.setMovie(movie_obj.getTitle());
				projection.setProjectionType(projectionType_object.getName());
				projection.setTheater(theater_object.getName());
				Date date = sdf.parse(date_and_time);
				projection.setDateOutput(date_and_time);
				projection.setDateAndTime(date);
				projection.setTicketPrice(price);
				projection.setAdminCreator(admin_creator);
				
				projections.add(projection);
				
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return projections;
	}
	
}








