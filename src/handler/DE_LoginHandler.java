package handler;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.User;

/**
 * Servlet implementation class LoginHandler
 */
@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher reqDis = null;
		
		request.setCharacterEncoding("utf-8");
		
		if(session.getAttribute("userID") != null) { // session 속에 userID 속성이 있다면 이미 로그인된 상태 -> 회원가입 불가
			String message = "이미 로그인된 상태입니다.";
			request.setAttribute("message", message);
			reqDis = request.getRequestDispatcher("/OpenProject/main/index.jsp");
		} else {
			User user = new User();
			UserDAO uDao = new UserDAO();
			
			String inID = request.getParameter("ID");
			String inPW = request.getParameter("PW");
			user.setId(inID);
			user.setPw(inPW);
			
			User result = uDao.selectByID(user.getId());

			if(inID.equals(result.getId()) && inPW.equals(result.getPw())) { // DB에 INSERT 성공한 경우 -> 회원가입 성공, 이제 로그인으로
				session.setAttribute("userID", inID);
				reqDis = request.getRequestDispatcher("/OpenProject/main/index.jsp");
			} else { // DB에 INSERT 실패한 경우 -> 다시 회원가입 양식으로
				String message = "로그인에 실패했습니다.";
				request.setAttribute("message", message);
				reqDis = request.getRequestDispatcher("/OpenProject/login/loginForm.jsp");
			}
		}
		
		reqDis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
