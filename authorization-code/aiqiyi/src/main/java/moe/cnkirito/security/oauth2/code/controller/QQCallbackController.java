package moe.cnkirito.security.oauth2.code.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 徐靖峰[OF2938]
 * company qianmi.com
 * Date 2018-04-25
 */
@RestController
@Slf4j
public class QQCallbackController {

//    withClient("aiqiyi")
//    .authorizedGrantTypes("authorization_code","refresh_token", "implicit")
//    .authorities("ROLE_CLIENT")
//    .scopes("get_user_info","get_fanslist")
//    .secret("secret")
//    .redirectUris("http://localhost:8081/aiqiyi/qq/redirect")
//    .autoApprove(true)
//    .autoApprove("get_user_info")
	
	private Logger log = LoggerFactory.getLogger(QQCallbackController.class);
	
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="/aiqiyi/qq/redirect",params="code")
    public String getToken(@RequestParam String code){
        log.info("receive code {}",code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("code",code);
        params.add("client_id","aiqiyi");
        params.add("client_secret","secret");
        params.add("redirect_uri","http://localhost:8881/aiqiyi/qq/redirect");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8882/oauth/token", requestEntity, String.class);
        String token = response.getBody();
        log.info("token => {}",token);
        return token;
    }
    
    @RequestMapping(value="/aiqiyi/qq/redirect",params={"error","error_description"})
    public String getToken(@RequestParam String error,@RequestParam String error_description){
        log.info("receive code {}",error);
        return error_description;
    }
    
    @RequestMapping("/youku/qq/redirect")
    public void getToken1(@RequestParam String code,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        log.info("receive code {}",code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("code",code);
        params.add("client_id","youku");
        params.add("client_secret","secret");
        params.add("redirect_uri","http://localhost:8881/youku/qq/redirect");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8882/oauth/token", requestEntity, String.class);
        String tokenJson = responseEntity.getBody();
        JSONObject jsonObj = JSONObject.fromObject(tokenJson);
        String token = jsonObj.getString("access_token");
        log.info("token => {}",tokenJson);
        response.sendRedirect("http://localhost:8882/qq/info/250577914?access_token="+token);
        //return token;
    }
	
	@RequestMapping(value="/aiqiyi/qq/getValue")
    public String getValue(){
        String aa = "paul";
        return aa;
    }

}
