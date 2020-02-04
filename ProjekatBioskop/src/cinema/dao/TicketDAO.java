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
import cinema.entity.Seat;
import cinema.entity.Theater;
import cinema.entity.Ticket;
import cinema.entity.User;
import cinema.entity.User.Role;

public class TicketDAO {
	
	public static boolean addTicket(Ticket ticket) {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			String query = "INSERT INTO tickets (projection, seat, dateandtime, price, buyer) "
						   + "VALUES (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			int index = 1;
			Projection projection = ProjectionDAO.get(ticket.getProjection());
			ps.setInt(index++, projection.getId());
			Seat seat = SeatDAO.get(ticket.getSeat());
			ps.setInt(index++, seat.getId());
			Date date = new Date();
			String datefordb = sdf.format(date);
			ps.setString(index++, datefordb);
			ps.setDouble(index++, projection.getTicketPrice());
			User user = UserDAO.get(ticket.getBuyer());
			ps.setInt(index++, user.getId());
			
			SeatDAO.updateSeatTaken(ticket.getProjection(), ticket.getSeat());
			
			return ps.executeUpdate() == 1;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	public static List<Ticket> getAll(Integer user_id) {
		
		List<Ticket> tickets = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM tickets WHERE buyer = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, user_id);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				Integer projection_id = rs.getInt(index++);
				Integer seat_id = rs.getInt(index++);
				String dateAndTimeOfPurchase = rs.getString(index++);
				Date date = sdf.parse(dateAndTimeOfPurchase);
				Double price = rs.getDouble(index++);
				Integer buyer_id = rs.getInt(index++);
				Ticket ticket = new Ticket();
				ticket.setId(id);
				ticket.setProjection(projection_id);
				ticket.setSeat(seat_id);
				ticket.setDateOutput(dateAndTimeOfPurchase);
				ticket.setBuyer(buyer_id);
				ticket.setPrice(price);
				ticket.setDateAndTimeOfPurchase(date);
				
				tickets.add(ticket);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return tickets;
	}
	
public static List<Ticket> getAllByProjection(Integer projection_id) {
		
		List<Ticket> tickets = new ArrayList<>();
		
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM tickets WHERE projection = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, projection_id);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				int id = rs.getInt(index++);
				Integer projectionId = rs.getInt(index++);
				Integer seat_id = rs.getInt(index++);
				String dateAndTimeOfPurchase = rs.getString(index++);
				Date date = sdf.parse(dateAndTimeOfPurchase);
				Double price = rs.getDouble(index++);
				Integer buyer_id = rs.getInt(index++);
				User user = UserDAO.get(buyer_id);
				
				Ticket ticket = new Ticket();
				ticket.setId(id);
				ticket.setProjection(projectionId);
				ticket.setSeat(seat_id);
				ticket.setDateOutput(dateAndTimeOfPurchase);
				ticket.setBuyer(buyer_id);
				ticket.setPrice(price);
				ticket.setDateAndTimeOfPurchase(date);
				ticket.setBuyerName(user.getUsername());
				tickets.add(ticket);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return tickets;
	}
	
	public static Ticket get(Integer id) throws Exception {
		Connection con = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM tickets WHERE id = ?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			if(rs.next()) {
				int index = 1;
				int id_rs = rs.getInt(index++);
				int projection = rs.getInt(index++);
				int seat = rs.getInt(index++);
				String date_and_time = rs.getString(index++);
				double price = rs.getDouble(index++);
				int buyer = rs.getInt(index++);
				
				Ticket ticket = new Ticket();
				ticket.setId(id_rs);
				ticket.setProjection(projection);
				ticket.setSeat(seat);
				ticket.setDateOutput(date_and_time);
				ticket.setPrice(price);
				ticket.setBuyer(buyer);
				
				
				return ticket;
			}
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static boolean delete(Integer id) {
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		
		try {
			
			String query = "DELETE FROM tickets WHERE id = ?";
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

}
