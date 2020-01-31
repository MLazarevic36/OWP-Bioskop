package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cinema.entity.Movie;
import cinema.entity.Seat;
import cinema.entity.SeatAvailable;
import cinema.entity.Theater;

public class SeatDAO {
	
	public static List<SeatAvailable> getAll(Integer id) {
		List<SeatAvailable> seats_available = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM seat_availability WHERE projection_id = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				Integer projection_id = rs.getInt(index++);
				Integer seat_id = rs.getInt(index++);
				Integer taken = rs.getInt(index++);
				
				SeatAvailable seat_available = new SeatAvailable();
				seat_available.setProjection_id(projection_id);
				seat_available.setSeat_id(seat_id);
				seat_available.setTaken(taken);
				
				
				seats_available.add(seat_available);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return seats_available;
	}
	
	public static List<Seat> getAll(String theater) {
		List<Seat> seats = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM seats WHERE theater = ?";
			
			Theater theaterObject = TheaterDAO.getByName(theater);
			
			ps = con.prepareStatement(query);
			ps.setInt(1, theaterObject.getId());
			
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				Integer id = rs.getInt(index++);
				Integer number = rs.getInt(index++);
				Integer theater_id = rs.getInt(index++);
				
				Seat seat = new Seat();
				seat.setId(id);
				seat.setNumber(number);
				seat.setTheater(theater_id);
				
				seats.add(seat);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return seats;
	}
	
	public static Seat get(Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM seats WHERE id = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int index = 1;
				Integer id_rs = rs.getInt(index++);
				Integer number_rs = rs.getInt(index++);
				int theater_rs = rs.getInt(index++);
				
				Seat seat = new Seat();
				seat.setId(id_rs);
				seat.setNumber(number_rs);
				seat.setTheater(theater_rs);

				return seat;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static boolean updateSeatTaken(Integer projection_id, Integer seat_id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE seat_availability SET taken = 1 WHERE projection_id = ? and seat_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, projection_id);
			ps.setInt(2, seat_id);
			
			return ps.executeUpdate() == 1;
					
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
	}
	
	public static boolean addSeatAvailable(Integer projection_id, Integer seat_id) {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO seat_availability (projection_id, seat_id, taken) "
							+ "VALUES (?, ?, 0)";
			ps = con.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, projection_id);
			ps.setInt(index++, seat_id);
			
			return ps.executeUpdate() == 1;
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	

}
