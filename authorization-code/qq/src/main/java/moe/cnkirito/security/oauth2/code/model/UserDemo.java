package moe.cnkirito.security.oauth2.code.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class UserDemo {

	 	@Id
	    private String id;

	    @Column(name = "username") //这是和数据表对应的一个列
	    private String username;

	    @Column(name = "password") //这是和数据表对应的一个列
	    private String password;

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

}
