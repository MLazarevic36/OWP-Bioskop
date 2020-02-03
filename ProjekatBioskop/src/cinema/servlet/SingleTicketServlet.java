package cinema.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cinema.dao.MovieDAO;
import cinema.dao.ProjectionDAO;
import cinema.dao.ProjectionTypeDAO;
import cinema.dao.TheaterDAO;
import cinema.dao.TicketDAO;
import cinema.dao.UserDAO;
import cinema.entity.Movie;
import cinema.entity.Projection;
import cinema.entity.ProjectionType;
import cinema.entity.SingleTicket;
import cinema.entity.Theater;
import cinema.entity.Ticket;
import cinema.entity.User;


public class SingleTicketServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		Ticket ticket;
		SingleTicket singleTicket = new SingleTicket();
		try {
			ticket = TicketDAO.get(id);
			Projection projection = ProjectionDAO.get(ticket.getProjection());
			singleTicket.setMovieTitle(projection.getMovie());
			singleTicket.setDateOutputTimeOfProjection(projection.getDateOutput());
			singleTicket.setTheater(projection.getTheater());
			singleTicket.setPrice(ticket.getPrice());
			singleTicket.setProjectionType(projection.getProjectionType());
			singleTicket.setSeat(ticket.getSeat());
			User user = UserDAO.get(ticket.getBuyer());
			singleTicket.setBuyer(user.getUsername());
			singleTicket.setProjectionId(projection.getId());
			Map<String, Object> data = new HashMap<>();
			data.put("singleTicket", singleTicket);
			ObjectMapper mapper = new ObjectMapper();
			String jsonDataSingleTicket = mapper.writeValueAsString(data);
			
			response.setContentType("application/json");
			response.getWriter().write(jsonDataSingleTicket);
			System.out.println(jsonDataSingleTicket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
