package cinema.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

public class SearchMoviesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchOptionBox = request.getParameter("searchOptionsBox");
		String searchInput = request.getParameter("searchMovieInput");
		String durationMin = null;
		durationMin = request.getParameter("durationMin");
		String durationMax = null;
		durationMax = request.getParameter("durationMax");
		List<Movie> filteredMovies = new ArrayList<>();
		
		if(searchOptionBox != null) {
			switch(searchOptionBox) {
				case "title":
					filteredMovies = MovieDAO.searchMoviesByTitle(searchInput, durationMin, durationMax);
					break;
				case "distributor":
					filteredMovies = MovieDAO.searchMoviesByDistributor(searchInput, durationMin, durationMax);
					break;
				case "originCountry":
					filteredMovies = MovieDAO.searchMoviesByOriginCountry(searchInput, durationMin, durationMax);
					break;
				case "yearOfProduction":
					filteredMovies = MovieDAO.searchMoviesByYearOfProduction(searchInput, durationMin, durationMax);
					break;
				case "searchMoviesBy":
					filteredMovies = MovieDAO.searchMoviesByDuration(durationMin, durationMax);
					break;
					
				default:
					break;
				}	
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("filteredMovies", filteredMovies);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
