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
import cinema.entity.Movie;


public class MoviesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Movie> movies = MovieDAO.getAll(); 
		
		Map<String, Object> data = new HashMap<>();
		data.put("movies", movies);
		
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
				String title = request.getParameter("title");
				String duration = request.getParameter("duration");
				String distributor = request.getParameter("distributor");
				String originCountry = request.getParameter("originCountry");
				String yearOfProduction = request.getParameter("yearOfProduction");
				
				Movie movie = new Movie();
				movie.setTitle(title);
				movie.setDuration(duration);
				movie.setDistributor(distributor);
				movie.setOriginCountry(originCountry);
				movie.setYearOfProduction(Integer.parseInt(yearOfProduction));
				MovieDAO.addMovie(movie);
				break;
			}
			case "delete": {
				Integer id = Integer.parseInt(request.getParameter("id"));
				MovieDAO.delete(id);
				break;
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
