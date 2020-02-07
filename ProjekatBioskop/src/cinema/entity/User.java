package cinema.entity;
import java.util.Date;



public class User {
	
	public enum Role {USER, ADMIN};
	
	private Integer id;
	private String username;
	private String password;
	private Date registrationDate;
	private Role role;
	private String dateOutput;
	private Integer deleted;
	
	public User() {
		
	}
	
	public User(Integer id, String username, String password, Date registrationDate, Role role, 
			String dateOutput, Integer deleted) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		this.dateOutput = dateOutput;
		this.deleted = deleted;
		
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
	
	public void setId(Integer id) {
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getDateOutput() {
		return dateOutput;
	}
	
	public void setDateOutput(String dateOutput) {
		this.dateOutput = dateOutput;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
	
	
	
	

}
