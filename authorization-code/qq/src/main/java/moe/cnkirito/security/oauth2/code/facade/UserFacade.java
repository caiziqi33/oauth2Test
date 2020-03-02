package moe.cnkirito.security.oauth2.code.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import moe.cnkirito.security.oauth2.code.model.UserDemo;
import moe.cnkirito.security.oauth2.code.service.UserService;

public class UserFacade implements UserDetailsService{
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDemo userDemo =  userService.findByName(username);
		
		if(userDemo==null) {
			throw new UsernameNotFoundException("User:"+username+"not found");
		}
		
		User user = new User(userDemo.getUsername(),userDemo.getPassword(),AuthorityUtils.createAuthorityList("USER"));
		return user;
	}

}
