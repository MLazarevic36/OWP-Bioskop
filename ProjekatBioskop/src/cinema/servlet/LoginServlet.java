package cinema.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import cinema.dao.UserDAO;
import cinema.entity.User;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user;
		try {
			user = UserDAO.get(username, password);
			if (user == null) {
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}
			
			HttpSession httpSession = request.getSession();
			if(user.getUsername() == null) {
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}
			httpSession.setAttribute("loggedInUsername", user.getUsername());
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}

}
