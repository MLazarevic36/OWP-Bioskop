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
	
	public static List<Movie> searchMoviesByTitle(String title) {
		List<Movie> movies = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM movies WHERE title LIKE ?";
		
		try {
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, "%" + title + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				index = 1;
				int id = rs.getInt(index++);
				String title_rs = rs.getString(index++);
				String duration = rs.getString(index++);
				String distributor_rs = rs.getString(index++);
				String originCountry_rs = rs.getString(index++);
				int yearOfProduction_rs = rs.getInt(index++);
				
				Movie movie = new Movie();
				movie.setId(id);
				movie.setTitle(title_rs);
				movie.setDuration(duration);
				movie.setDistributor(distributor_rs);
				movie.setOriginCountry(originCountry_rs);
				movie.setYearOfProduction(yearOfProduction_rs);
				
				movies.add(movie);
				
				System.out.println(ps);
				
			}
			
		} catch (Exception e) {
			System.out.println("SQL query error!");
			System.out.println(ps);
			e.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return movies;
		
	}
	
	public static List<Movie> searchMoviesByDistributor(String distributor) {
		List<Movie> movies = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM movies WHERE distributor LIKE ?";
		
		try {
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, "%" + distributor + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				index = 1;
				int id = rs.getInt(index++);
				String title_rs = rs.getString(index++);
				String duration = rs.getString(index++);
				String distributor_rs = rs.getString(index++);
				String originCountry_rs = rs.getString(index++);
				int yearOfProduction_rs = rs.getInt(index++);
				
				Movie movie = new Movie();
				movie.setId(id);
				movie.setTitle(title_rs);
				movie.setDuration(duration);
				movie.setDistributor(distributor_rs);
				movie.setOriginCountry(originCountry_rs);
				movie.setYearOfProduction(yearOfProduction_rs);
				
				movies.add(movie);
				
				System.out.println(ps);
				
			}
			
		} catch (Exception e) {
			System.out.println("SQL query error!");
			System.out.println(ps);
			e.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return movies;
		
	}
	
	public static List<Movie> searchMoviesByOriginCountry(String originCountry) {
		List<Movie> movies = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM movies WHERE origincountry LIKE ?";
		
		try {
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, "%" + originCountry + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				index = 1;
				int id = rs.getInt(index++);
				String title_rs = rs.getString(index++);
				String duration = rs.getString(index++);
				String distributor_rs = rs.getString(index++);
				String originCountry_rs = rs.getString(index++);
				int yearOfProduction_rs = rs.getInt(index++);
				
				Movie movie = new Movie();
				movie.setId(id);
				movie.setTitle(title_rs);
				movie.setDuration(duration);
				movie.setDistributor(distributor_rs);
				movie.setOriginCountry(originCountry_rs);
				movie.setYearOfProduction(yearOfProduction_rs);
				
				movies.add(movie);
				
				System.out.println(ps);
				
			}
			
		} catch (Exception e) {
			System.out.println("SQL query error!");
			System.out.println(ps);
			e.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return movies;
		
	}
	
	public static List<Movie> searchMoviesByYearOfProduction(String yearOfProduction) {
		List<Movie> movies = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Integer year = Integer.parseInt(yearOfProduction);
		
		String query = "SELECT * FROM movies WHERE yearofproduction = ?";
		
		try {
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, year);
			rs = ps.executeQuery();
			while (rs.next()) {
				index = 1;
				int id = rs.getInt(index++);
				String title_rs = rs.getString(index++);
				String duration = rs.getString(index++);
				String distributor_rs = rs.getString(index++);
				String originCountry_rs = rs.getString(index++);
				int yearOfProduction_rs = rs.getInt(index++);
				
				Movie movie = new Movie();
				movie.setId(id);
				movie.setTitle(title_rs);
				movie.setDuration(duration);
				movie.setDistributor(distributor_rs);
				movie.setOriginCountry(originCountry_rs);
				movie.setYearOfProduction(yearOfProduction_rs);
				
				movies.add(movie);
				
				System.out.println(ps);
				
			}
			
		} catch (Exception e) {
			System.out.println("SQL query error!");
			System.out.println(ps);
			e.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return movies;
		
	}
	
	public static List<Movie> searchMoviesByDuration(Integer durationMin, Integer durationMax) {
		List<Movie> movies = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "SELECT * FROM movies WHERE duration BETWEEN ? AND ?";
		
		try {
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, durationMin);
			ps.setInt(index++, durationMax);
			rs = ps.executeQuery();
			while (rs.next()) {
				index = 1;
				int id = rs.getInt(index++);
				String title_rs = rs.getString(index++);
				String duration = rs.getString(index++);
				String distributor_rs = rs.getString(index++);
				String originCountry_rs = rs.getString(index++);
				int yearOfProduction_rs = rs.getInt(index++);
				
				Movie movie = new Movie();
				movie.setId(id);
				movie.setTitle(title_rs);
				movie.setDuration(duration);
				movie.setDistributor(distributor_rs);
				movie.setOriginCountry(originCountry_rs);
				movie.setYearOfProduction(yearOfProduction_rs);
				
				movies.add(movie);
						
			}
			
		} catch (Exception e) {
			System.out.println("SQL query error!");
			System.out.println(ps);
			e.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return movies;
		
	}
	
	public static boolean addMovie(Movie movie) {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) "
							+ "VALUES (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, movie.getTitle());
			ps.setString(index++, movie.getDuration());
			ps.setString(index++, movie.getDistributor());
			ps.setString(index++, movie.getOriginCountry());
			ps.setInt(index++, movie.getYearOfProduction());
			
			return ps.executeUpdate() == 1;
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	
	public static boolean delete(Integer id) {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		
		try {
			
			String query = "DELETE FROM movies WHERE id = ?";
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
	
	public static Movie get(Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM movies WHERE id = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int index = 1;
				Integer id_rs = rs.getInt(index++);
				String title_rs = rs.getString(index++);
				String duration_rs = rs.getString(index++);
				String distributor_rs = rs.getString(index++);
				String origin_country_rs = rs.getString(index++);
				int year_of_production_rs = rs.getInt(index++);
				
				Movie movie = new Movie();
				movie.setId(id_rs);
				movie.setTitle(title_rs);
				movie.setDuration(duration_rs);
				movie.setDistributor(distributor_rs);
				movie.setOriginCountry(origin_country_rs);
				movie.setYearOfProduction(year_of_production_rs);
				
				return movie;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static Movie getByTitle(String title) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM movies WHERE title = ?";
			
			ps = con.prepareStatement(query);
			ps.setString(1, title);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int index = 1;
				Integer id_rs = rs.getInt(index++);
				String title_rs = rs.getString(index++);
				String duration_rs = rs.getString(index++);
				String distributor_rs = rs.getString(index++);
				String origin_country_rs = rs.getString(index++);
				int year_of_production_rs = rs.getInt(index++);
				
				Movie movie = new Movie();
				movie.setId(id_rs);
				movie.setTitle(title_rs);
				movie.setDuration(duration_rs);
				movie.setDistributor(distributor_rs);
				movie.setOriginCountry(origin_country_rs);
				movie.setYearOfProduction(year_of_production_rs);
				
				return movie;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static boolean update(Movie movie) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE movies SET title = ?, duration = ?, distributor = ?, origincountry = ?, yearofproduction = ? "
						   + "WHERE id = ?";
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setString(index++, movie.getTitle());
			ps.setString(index++, movie.getDuration());
			ps.setString(index++, movie.getDistributor());
			ps.setString(index++, movie.getOriginCountry());
			ps.setInt(index++, movie.getYearOfProduction());
			ps.setInt(index++, movie.getId());
			
			return ps.executeUpdate() == 1;
					
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
	}
	
	
}










