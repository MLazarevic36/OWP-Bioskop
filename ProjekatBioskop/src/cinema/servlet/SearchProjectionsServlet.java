package cinema.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cinema.dao.ProjectionDAO;
import cinema.entity.Projection;

public class SearchProjectionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieTitle = null;
		movieTitle = request.getParameter("projectionMovieFilter");
		String projectionTypeName = null;
		projectionTypeName = request.getParameter("projectionTypeFilter");
		String theaterName = null;
		theaterName = request.getParameter("projectionTheaterFilter");
		String startDate = null;
		startDate = request.getParameter("projectionStartDate");
		String endDate = null;
		endDate = request.getParameter("projectionEndDate");
		String minPrice = null;
		minPrice = request.getParameter("projectionMinPrice");
		String maxPrice = null;
		maxPrice = request.getParameter("projectionMaxPrice");
		
		List<Projection> filteredProjections = new ArrayList<>();
		filteredProjections = ProjectionDAO.searchProjections(movieTitle, projectionTypeName, theaterName, startDate, endDate, minPrice, maxPrice);
		
		Map<String, Object> data = new HashMap<>();
		data.put("filteredProjections", filteredProjections);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
