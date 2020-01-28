package cinema.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.dao.TicketDAO;
import cinema.entity.Ticket;

public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
