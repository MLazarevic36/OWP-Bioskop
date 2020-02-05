package cinema.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cinema.dao.MovieDAO;
import cinema.dao.UserDAO;
import cinema.entity.Movie;
import cinema.entity.User;

public class UserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedInUsername = (String) request.getSession().getAttribute("loggedInUsername");
		
		if(loggedInUsername == null) {
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
			return;
		}
		
		User loggedInUser;
		try {
			loggedInUser = UserDAO.get(loggedInUsername);
			if (loggedInUser == null) {
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
				return;
			}
			Map<String, Object> data = new LinkedHashMap<>();
			
			String action = request.getParameter("action");
			switch(action) {
				case "loggedInUserRole": {
					data.put("loggedInUserRole", loggedInUser.getRole());
					break;
				}
			}
			
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			switch(action) {
			case "updateUserPassword": {
				String id = request.getParameter("user-id");
				String pass = request.getParameter("password");
				Integer user_id = Integer.parseInt(id);
				UserDAO.updatePassword(pass, user_id);
				break;
			}
			case "updateUserRole": {
				String id = request.getParameter("user-id");
				String user_role = request.getParameter("role");
				Integer user_id = Integer.parseInt(id);
				UserDAO.updateRole(user_role, user_id);
				break;
			}
			case "deleteUser": {
				String id = request.getParameter("user-id");
				Integer user_id = Integer.parseInt(id);
				UserDAO.delete(user_id);
				break;
			}
			case "registerUser": {
				String register_username = request.getParameter("registerUsername");
				String register_password = request.getParameter("registerPassword");
				UserDAO.addUser(register_username, register_password);
				break;
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
