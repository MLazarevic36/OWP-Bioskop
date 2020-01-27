package cinema.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cinema.dao.ProjectionDAO;
import cinema.entity.Projection;

public class ProjectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		Projection projection;
		try {
			projection = ProjectionDAO.get(id);
			Map<String, Object> data = new HashMap<>();
			data.put("projection", projection);
			ObjectMapper mapper = new ObjectMapper();
			String jsonDataProjection = mapper.writeValueAsString(data);
			
			response.setContentType("application/json");
			response.getWriter().write(jsonDataProjection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
