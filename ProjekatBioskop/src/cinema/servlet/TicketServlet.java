package cinema.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cinema.dao.MovieDAO;
import cinema.dao.SeatDAO;
import cinema.dao.TicketDAO;
import cinema.entity.Ticket;

public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				String user_id = request.getParameter("id");
				Integer buyer_id = Integer.parseInt(user_id);
				List<Ticket> tickets = TicketDAO.getAll(buyer_id); 
				
				Map<String, Object> data = new HashMap<>();
				data.put("tickets", tickets);
				
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(data);
				
				response.setContentType("application/json");
				response.getWriter().write(jsonData);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			switch(action) {
			case "addTicket": {
				String projection = request.getParameter("projection");
				String seat = request.getParameter("seat");
				String buyer = request.getParameter("buyer");
				Ticket ticket = new Ticket();
				Integer projection_id = Integer.parseInt(projection);
				ticket.setProjection(projection_id);
				Integer seat_id = Integer.parseInt(seat);
				ticket.setSeat(seat_id);
				Integer buyer_id = Integer.parseInt(buyer);
				ticket.setBuyer(buyer_id);
				TicketDAO.addTicket(ticket);
			}
			case "deleteTicket": {
				String id = request.getParameter("id");
				Integer delete_id = Integer.parseInt(id);
				Ticket ticket = TicketDAO.get(delete_id);
				TicketDAO.delete(delete_id);
				SeatDAO.updateSeatAvailable(ticket.getProjection(), ticket.getSeat());
				break;
			}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
