package moe.cnkirito.security.oauth2.code.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moe.cnkirito.security.oauth2.code.model.UserDemo;
import moe.cnkirito.security.oauth2.code.service.UserService;

import java.util.List;

/**
 * @author 徐靖峰[OF2938]
 * company qianmi.com
 * Date 2018-04-25
 */
@RestController
@RequestMapping("/qq")
public class QQRestfulApiProviderController {
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private UserService userServiceImple;
	
    @RequestMapping("/info/{qq}")
    public UserDemo info(@PathVariable("qq") String id){
    	return userServiceImple.findById(id);
        //return InMemoryQQDatabase.database.get(qq);
    }

    @RequestMapping("fans/{qq}")
    public List<QQAccount> fans(@PathVariable("qq") String qq){
    	return ((QQAccount) userService.loadUserByUsername(qq)).getFans();
        //return InMemoryQQDatabase.database.get(qq).getFans();
    }



}
