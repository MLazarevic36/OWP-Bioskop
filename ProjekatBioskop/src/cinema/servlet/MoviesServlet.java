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
				String id = request.getParameter("id");
				Integer delete_id = Integer.parseInt(id);
				MovieDAO.logicDelete(delete_id);
				break;
			}
			case "getMovie": {
				String id = request.getParameter("id");
				Integer get_movie_id = Integer.parseInt(id);
				Movie movie = MovieDAO.get(get_movie_id);
				Map<String, Object> movieObject = new HashMap<>();
				movieObject.put("movie", movie);
				ObjectMapper mapper = new ObjectMapper();
				String jsonDataMovie = mapper.writeValueAsString(movieObject);
				
				response.setContentType("application/json");
				response.getWriter().write(jsonDataMovie);
			}
			case "update": {
				String id = request.getParameter("id");
				String title = request.getParameter("title");
				String duration = request.getParameter("duration");
				String distributor = request.getParameter("distributor");
				String originCountry = request.getParameter("originCountry");
				String yearOfProduction = request.getParameter("yearOfProduction");
				Integer update_id = Integer.parseInt(id);
				Integer update_year = Integer.parseInt(yearOfProduction);
				Movie movie = new Movie();
				movie.setId(update_id);
				movie.setTitle(title);
				movie.setDuration(duration);
				movie.setDistributor(distributor);
				movie.setOriginCountry(originCountry);
				movie.setYearOfProduction(update_year);
				MovieDAO.update(movie);
				Map<String, Object> movieO = new HashMap<>();
				movieO.put("movie", movie);
				ObjectMapper mapper = new ObjectMapper();
				String jsonDataMovie = mapper.writeValueAsString(movieO);
				response.setContentType("application/json");
				response.getWriter().write(jsonDataMovie);
				break;
			}
			case "getMovieByTitle": {
				String title = request.getParameter("title");
				Movie movie = MovieDAO.getByTitle(title);
				Map<String, Object> movieObject = new HashMap<>();
				movieObject.put("movie", movie);
				ObjectMapper mapper = new ObjectMapper();
				String jsonDataMovie = mapper.writeValueAsString(movieObject);
				
				response.setContentType("application/json");
				response.getWriter().write(jsonDataMovie);
				break;
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
