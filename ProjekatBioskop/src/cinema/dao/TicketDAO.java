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
				
//				users.add(user);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return tickets;
	}

}
