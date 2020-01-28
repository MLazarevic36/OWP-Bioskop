package cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import cinema.entity.Movie;
import cinema.entity.Projection;
import cinema.entity.ProjectionType;
import cinema.entity.Seat;
import cinema.entity.Theater;
import cinema.entity.Ticket;
import cinema.entity.User;

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
			
			return ps.executeUpdate() == 1;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {ps.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}

}
