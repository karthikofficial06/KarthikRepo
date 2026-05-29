package com.omrbranch.postmanbasicauthlogin;

import com.omrbranch.address.Address;
import com.omrbranch.pojo.postmanbasicauthlogin.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.restassured.response.Response;

public class Login extends BaseClass {
	
	public static String logtoken;
	public void login() {
		initRestAssured();
	
		addHeader("accept", "application/json");
		addBasicAuthentication("Happiestperson11062001@gmail.com", "Karthi@1106");                             
		Response response = sendRequest("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		
		PostmanBasicAuthLogin_Output_Pojo postmanBasicAuthLogin_Output_Pojo = response.as(PostmanBasicAuthLogin_Output_Pojo.class);
		String first_name = postmanBasicAuthLogin_Output_Pojo.getData().getFirst_name();
	    System.out.println(first_name);
	    logtoken = postmanBasicAuthLogin_Output_Pojo.getData().getLogtoken();
	    System.out.println(logtoken);
	}	
	
	public static void main(String[] args) {
		Login log = new Login();
		log.login();
	}
}

