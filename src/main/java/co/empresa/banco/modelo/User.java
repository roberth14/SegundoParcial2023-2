package co.empresa.banco.modelo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	
	private Integer id;
	private String username;
	private String email;
	private String pass;
	
	
	
	public User(String username, String email, String pass) {
		super();
		this.username = username;
		this.email = email;
		this.pass = pass;
	}
	public User(Integer id, String username, String email, String pass) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.pass = pass;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	

	
}
