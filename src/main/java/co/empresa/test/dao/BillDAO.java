package co.empresa.test.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.banco.modelo.Bill;
import co.empresa.banco.modelo.User;
import co.empresa.banco.util.Conexion;


public class BillDAO {

	private Conexion conexion;
	private static final String INSERT_BILL_SQL="INSERT INTO bill (date_bill, user_id, value, type, observation) VALUES (?, ?, ?, ?, ?);";
	private static final String DELETE_BILL_SQL="DELETE FROM bill WHERE id= ?;";
	private static final String SELECT_BILL_BY_ID="SELECT * FROM bill WHERE id = ?;";
	private static final String SELECT_ALL_BILL= "SELECT * FROM bill ;";


	public BillDAO() {
		this.conexion = conexion.getConexion();
	}
	
	
	public void insert (Bill bill) throws SQLException{
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_BILL_SQL);
			preparedStatement.setString(1, bill.getDate_bill());
			preparedStatement.setInt(2, bill.getId());
			preparedStatement.setFloat(3, bill.getValue());
			preparedStatement.setInt(4, bill.getType());
			preparedStatement.setString(5, bill.getObservation());
			
			conexion.execute();
		}catch (SQLException e) {
			
		}
	}
	
	public void delete(int id)  throws SQLException{
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_BILL_SQL);
			preparedStatement.setInt(1,id);

			conexion.execute();
		}catch (SQLException e) {
			
		}
	}
	
	
	public List<Bill> selectAll() {
		List<Bill> usuarios = new ArrayList<>();
		
		try {
		PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_BILL);

			ResultSet rs = conexion.query();
			while(rs.next()) {
				int id = rs.getInt("serial");
				String date = rs.getString("date_bill");
				String user = rs.getString("user_id");
				String value = rs.getString("value");
				String type = rs.getString("type");
				String observation = rs.getString("observation");
//				usuarios.add(new User(id,date,user,value,type,observation));
			}
		}catch(SQLException e) {
			
		}
		
		
		return usuarios;
		
	}
	
	public User select(int id) {
		User usuario = null;
		
		try {
		PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_BILL_BY_ID);
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
