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
import cinema.dao.UserDAO;
import cinema.entity.Projection;
import cinema.entity.User;

public class SingleUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String request_id = request.getParameter("user_id");
		Integer id = Integer.parseInt(request_id);
		User user;
		try {
			user = UserDAO.get(id);
			Map<String, Object> data = new HashMap<>();
			data.put("user", user);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
