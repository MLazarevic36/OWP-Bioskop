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
import cinema.dao.SeatDAO;
import cinema.dao.TheaterDAO;
import cinema.entity.Seat;
import cinema.entity.Theater;

public class SeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theater_name = request.getParameter("theater_name");
		try {
			Theater theater = TheaterDAO.getByName(theater_name);
			Integer theater_id = theater.getId();
			List<Seat> seats = SeatDAO.getAll(theater_id);
			Map<String, Object> data = new HashMap<>();
			data.put("seats", seats);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			System.out.println(theater_name);
			System.out.println(theater_id);
			System.out.println(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			switch(action) {
			case "takeSeat": {
				String id = request.getParameter("id");
				Integer seat_id = Integer.parseInt(id);
				SeatDAO.updateSeatTaken(seat_id);
			}
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
			
	}

}
