package cinema.entity;

import java.sql.Timestamp;



public class User {
	
	
	
	public enum Role {USER, ADMIN};
	
	private Integer id;
	private String username;
	private String password;
	private Timestamp registrationDate;
	private Role role;
	
	public User(Integer id, String username, String password, Timestamp registrationDate, Role role) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return username.equals(other.username);
	}

	
	public Integer getId() {
		return id;
	}

	public void setUsername(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
	
	
	
	
	

}
