package team5.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(unique=true)
	@NotNull
    private String userName;
	@NotNull
	@Size(min=2,max=30)
	private String password;
	@NotNull
	private RoleType role;
	
	@OneToMany(mappedBy = "user")
	private List<Annotation> annotations;
	
	public User() {
		super();
	}
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public User(String userName, String password, RoleType role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}
	
}

