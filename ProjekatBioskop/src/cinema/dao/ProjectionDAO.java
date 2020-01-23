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
			String query = "SELECT * FROM projections";
			
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
				System.out.println(date_and_time);
				System.out.println(date);
				
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
