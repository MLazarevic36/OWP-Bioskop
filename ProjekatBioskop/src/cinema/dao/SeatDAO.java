package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cinema.entity.Movie;
import cinema.entity.Seat;

public class SeatDAO {
	
	public static List<Seat> getAll(Integer id) {
		List<Seat> seats = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM seats WHERE theater = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				Integer id_rs = rs.getInt(index++);
				Integer number = rs.getInt(index++);
				Integer theater = rs.getInt(index++);
				Integer available = rs.getInt(index++);
				
				Seat seat = new Seat();
				seat.setId(id_rs);
				seat.setNumber(number);
				seat.setTheater(theater);
				seat.setAvailable(available);
				
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
				int available_rs = rs.getInt(index++);
				
				Seat seat = new Seat();
				seat.setId(id_rs);
				seat.setNumber(number_rs);
				seat.setTheater(theater_rs);
				seat.setAvailable(available_rs);
				return seat;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static boolean updateSeatTaken(Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE seats SET available = 0 WHERE id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			return ps.executeUpdate() == 1;
					
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
	}

}
