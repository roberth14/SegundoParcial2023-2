package co.empresa.banco.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.banco.modelo.User;
import co.empresa.test.dao.UserDAO;


import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;

	/**
	 * Default constructor.
	 */
	public UserServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.userDao = new UserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertarUsuario(request, response);
				break;
			case "/delete":
				eliminarUsuario(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				actualizarUsuario(request, response);
				break;
			default:
				listUsuarios(request,response);
				break;

			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void listUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException,ServletException  {
		List<User>listUsuarios = userDao.selectAll();
		request.setAttribute("listUsuarios",listUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
		dispatcher.forward(request, response);
		
	}

	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)throws IOException, SQLException,ServletException  {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");

		User usuario = new User(id,nombre, email, pais);

		userDao.update(usuario);
		response.sendRedirect("list");

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		User usuarioActual = userDao.select(id);  
		request.setAttribute("usuario", usuarioActual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);

	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException,ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.delete(id);
		response.sendRedirect("list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");

		User usuario = new User(nombre, email, pais);

		userDao.insert(usuario);
		response.sendRedirect("list");
	}

}
