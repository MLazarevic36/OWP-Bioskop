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
//		String title = request.getParameter("title");
//		String distributor = request.getParameter("distributor");
//		String originCountry = request.getParameter("originCountry");
//		Integer yearOfProduction = Integer.parseInt(request.getParameter("yearOfProduction"));
		String searchInput = request.getParameter("searchMovieInput");
		List<Movie> filteredMovies = new ArrayList<>();
		
		if(searchOptionBox != null) {
			switch(searchOptionBox) {
				case "title":
					 filteredMovies = MovieDAO.searchMoviesByTitle(searchInput);
					break;
				case "distributor":
					filteredMovies = MovieDAO.searchMoviesByDistributor(searchInput);
					break;
				case "originCountry":
					filteredMovies = MovieDAO.searchMoviesByOriginCountry(searchInput);
					break;
				case "yearOfProduction":
					filteredMovies = MovieDAO.searchMoviesByYearOfProduction(searchInput);
					break;
				case "duration":
					String durationFrom = request.getParameter("durationFrom");
					String durationTo = request.getParameter("durationTo");
					Integer durationF = Integer.parseInt(durationFrom);
					Integer durationT = Integer.parseInt(durationTo);
					filteredMovies = MovieDAO.searchMoviesByDuration(durationF, durationT);
					break;
					
				default:
					break;
				}	
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("filteredMovies", filteredMovies);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		System.out.println(searchOptionBox);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
