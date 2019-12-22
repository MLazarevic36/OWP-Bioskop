package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cinema.entity.Movie;

public class MovieDAO {

	public static List<Movie> getAll() {
		
		List<Movie> movies = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM movies";
			
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				String title = rs.getString(index++);
				String duration = rs.getString(index++);
				String distributor = rs.getString(index++);
				String originCountry = rs.getString(index++);
				int yearOfProduction = rs.getInt(index++);
				
				Movie movie = new Movie();
				movie.setId(id);
				movie.setTitle(title);
				movie.setDuration(duration);
				movie.setDistributor(distributor);
				movie.setOriginCountry(originCountry);
				movie.setYearOfProduction(yearOfProduction);
				
				movies.add(movie);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return movies;
	}
	
	
	
}
