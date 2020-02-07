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

import cinema.entity.Movie;
import cinema.entity.Projection;
import cinema.entity.ProjectionType;
import cinema.entity.Seat;
import cinema.entity.Theater;
import cinema.dao.MovieDAO;
import cinema.dao.ProjectionDAO;
import cinema.dao.ProjectionTypeDAO;
import cinema.dao.SeatDAO;
import cinema.dao.TheaterDAO;

public class ProjectionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Projection> projections = ProjectionDAO.getAll();
		
		Map<String, Object> data = new HashMap<>();
		data.put("projections", projections);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		try {
			switch(action) {
				case "add": {
					Integer movie = Integer.parseInt(request.getParameter("movie"));
					Integer projectionType = Integer.parseInt(request.getParameter("projectionType"));
					Integer theater = Integer.parseInt(request.getParameter("theater"));
					String date = request.getParameter("date");
					Double ticket = Double.parseDouble(request.getParameter("ticket"));
					String time = request.getParameter("time");
					Projection projection = new Projection();
					Movie movieObject = MovieDAO.get(movie);
					projection.setMovie(movieObject.getTitle());
					ProjectionType projectionTypeObject = ProjectionTypeDAO.get(projectionType);
					projection.setProjectionType(projectionTypeObject.getName());
					Theater theaterObject = TheaterDAO.get(theater);
					projection.setTheater(theaterObject.getName());
					String full_date = date + ' ' + time;
					projection.setDateOutput(full_date);
					projection.setTicketPrice(ticket);
					projection.setAdminCreator(1);
					
					ProjectionDAO.addProjection(projection);
					
					Projection lastProjection = ProjectionDAO.getLast();
					
					List<Seat> seats = SeatDAO.getAll(theaterObject.getName());
			
					for(Seat i : seats){
						SeatDAO.addSeatAvailable(lastProjection.getId(), i.getNumber());
					}
					
					break;
				}
				case "getProjection": {
					Integer id = Integer.parseInt(request.getParameter("id"));
					Projection projection = ProjectionDAO.get(id);
					Map<String, Object> data = new HashMap<>();
					data.put("projection", projection);
					ObjectMapper mapper = new ObjectMapper();
					String jsonDataProjection = mapper.writeValueAsString(data);
					
					response.setContentType("application/json");
					response.getWriter().write(jsonDataProjection);
				}
				case "getProjectionsByMovie": {
					String movie_id = request.getParameter("id");
					Integer id = Integer.parseInt(movie_id);
					List<Projection> projections = ProjectionDAO.getAllByMovie(id);
					
					Map<String, Object> data = new HashMap<>();
					data.put("projections", projections);
					
					ObjectMapper mapper = new ObjectMapper();
					String jsonData = mapper.writeValueAsString(data);
					
					response.setContentType("application/json");
					response.getWriter().write(jsonData);
				}
				case "deleteProjectionLogic": {
					String projection_id = request.getParameter("id");
					Integer id = Integer.parseInt(projection_id);
					ProjectionDAO.logicDelete(id);
					System.out.println(projection_id);
					break;
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
