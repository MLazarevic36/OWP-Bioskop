package cinema.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.dao.UserDAO;
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
		doGet(request, response);
	}

}
