package co.empresa.test.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.banco.modelo.User;
import co.empresa.banco.util.Conexion;


public class UserDAO {

	private Conexion conexion;
	private static final String INSERT_USUARIO_SQL="INSERT INTO users (username, pass, email) VALUES (?, ?, ?);";
	private static final String DELETE_USUARIO_SQL="DELETE FROM users WHERE id= ?;";
	private static final String UPDATE_USUARIO_SQL="UPDATE users SET username= ?, pass= ?, email= ? WHERE id = ? ;";
	private static final String SELECT_USUARIO_BY_ID="SELECT * FROM users WHERE id = ?;";
	private static final String SELECT_ALL_USUARIOS="SELECT * FROM users ;";
	private static final String SELECT_USUARIO_BY_email_pass="SELECT * FROM users WHERE email = ? AND pass = ? ;";


	public UserDAO() {
		this.conexion = conexion.getConexion();
	}
	
	
	public void insert (User usuario) throws SQLException{
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getPass());
			preparedStatement.setString(3, usuario.getEmail());
			conexion.execute();
		}catch (SQLException e) {
			
		}
	}
	
	public void delete(int id)  throws SQLException{
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_USUARIO_SQL);
			preparedStatement.setInt(1,id);

			conexion.execute();
		}catch (SQLException e) {
			
		}
	}
	
	public void update (User usuario) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getPass());
			preparedStatement.setString(3, usuario.getEmail());
			preparedStatement.setInt(4, usuario.getId());
			conexion.execute();
		}catch (SQLException e) {
			
		}
	}
	
	public List<User> selectAll() {
		List<User> usuarios = new ArrayList<>();
		
		try {
		PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_USUARIOS);

			ResultSet rs = conexion.query();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String email = rs.getString("email");
				usuarios.add(new User(id,username,pass,email));
			}
		}catch(SQLException e) {
			
		}
		
		
		return usuarios;
		
	}
	
	public User select(int id) {
		User usuario = null;
		
		try {
		PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
        preparedStatement.setInt(1, id);
		ResultSet rs = conexion.query();
		
			while(rs.next()) {
	
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String email = rs.getString("email");
	
				usuario= new User(id,username,pass,email);
			}
		}catch(SQLException e) {
			
		}
		
		
		return usuario;
		
	}
	
	
	
}
