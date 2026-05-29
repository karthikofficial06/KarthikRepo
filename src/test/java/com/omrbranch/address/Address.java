package com.omrbranch.address;

import java.util.ArrayList;
import java.util.List;

import com.omrbranch.pojo.addUserAddress.AddUserAddress_Input_Pojo;
import com.omrbranch.pojo.addUserAddress.AddUserAddress_Output_Pojo;
import com.omrbranch.pojo.citylist.CityList;
import com.omrbranch.pojo.citylist.CityList_Input_Pojo;
import com.omrbranch.pojo.citylist.CityList_Output_Pojo;
import com.omrbranch.pojo.deleteaddress.DeleteAddress_Input_Pojo;
import com.omrbranch.pojo.deleteaddress.DeleteAddress_Output_Pojo;
import com.omrbranch.pojo.getUserAddress.GetUserAddress_Output_Pojo;
import com.omrbranch.pojo.statelist.Datum;
import com.omrbranch.pojo.statelist.stateList_Output_Pojo;
import com.omrbranch.pojo.updateuseraddress.UpdateUserAddress_Input_Pojo;
import com.omrbranch.pojo.updateuseraddress.UpdateUserAddress_Output_Pojo;
import com.omrbranch.postmanbasicauthlogin.Login;
import com.omrbranch.utility.BaseClass;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Address extends BaseClass {
	String stateIdText;
	public static int address_id;

	
	public void cityList() {
	initRestAssured();

	// Header
	List<Header> lstHeader = new ArrayList<Header>();

	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Content-Type", "application/json");

	lstHeader.add(h1);
	lstHeader.add(h2);

	Headers headers = new Headers(lstHeader);

	addHeaders(headers);

	// Basic Auth
	addBasicAuthentication("Happiestperson11062001@gmail.com", "Karthi@1106");

	CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(stateIdText);

	addPayload(cityList_Input_Pojo);

	Response response = sendRequest("POST", "https://www.omrbranch.com/api/cityList");

	CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);

	ArrayList<CityList> cityList = cityList_Output_Pojo.getData();

	for (CityList eachCity : cityList) {
	String cityName = eachCity.getName();
	      if (cityName.equals("Yercaud")) {
	      int cityID = eachCity.getId();

	System.out.println(cityID);
	}
	}
	}
	
	public void selectState() {
	initRestAssured();
	
	addHeader("accept", "application/json");
	addBasicAuthentication("Happiestperson11062001@gmail.com", "Karthi@1106");                             
	Response response = sendRequest("GET", "https://omrbranch.com/api/stateList");
	
	stateList_Output_Pojo stateList_Output_Pojo = response.as(stateList_Output_Pojo.class);
	ArrayList<Datum> data = stateList_Output_Pojo.getData();
	for (Datum eachStateList : data) {
    String name = eachStateList.getName();
    if(name.equals("Tamil Nadu")) {
    	int id = eachStateList.getId();
    	
    	stateIdText = String.valueOf(id);
		System.out.println(id);
		break;
    }
    }
  }


public void addUserAddress(){
	initRestAssured();
//Headers	
List<Header> lstHeader = new ArrayList<Header>();

Header h1 = new Header("accept", "application/json");
Header h2 = new Header("Content-Type", "application/json");

// Bearer Token
Header h3 = new Header("Authorization", "Bearer " + Login.logtoken);
lstHeader.add(h1);
lstHeader.add(h2);
lstHeader.add(h3);

Headers headers = new Headers(lstHeader);
addHeaders(headers);

// Payload
AddUserAddress_Input_Pojo address_Input_Pojo = new AddUserAddress_Input_Pojo(
				"Karthikeyan",
				"Srinivasan",
				"1234567898",
				"apartment",
				33,
				3378,
				101,
				"202020",
				"64/63 partap nagar",
				"home");
addPayload(address_Input_Pojo);

Response response = sendRequest("POST","https://www.omrbranch.com/api/addUserAddress");
System.out.println(getStatusCode(response));
System.out.println(response.asPrettyString());

AddUserAddress_Output_Pojo output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
address_id = output_Pojo.getAddress_id();
System.out.println(address_id);
}

public void updateUserAddress(){
	initRestAssured();
//Headers	
List<Header> lstHeader = new ArrayList<Header>();

Header h1 = new Header("accept", "application/json");
Header h2 = new Header("Content-Type", "application/json");

// Bearer Token
Header h3 = new Header("Authorization", "Bearer " + Login.logtoken);
lstHeader.add(h1);
lstHeader.add(h2);
lstHeader.add(h3);

Headers headers = new Headers(lstHeader);
addHeaders(headers);

// Payload
UpdateUserAddress_Input_Pojo address_Input_Pojo = new UpdateUserAddress_Input_Pojo(
		         address_id,
				"Karthikeyan",
				"Srinivasan",
				"9789789789",
				"apartment",
				33,
				3378,
				101,
				"202020",
				"64/63 partap nagar",
				"home");

addPayload(address_Input_Pojo);
Response response = sendRequest("PUT","https://www.omrbranch.com/api/updateUserAddress");
System.out.println(getStatusCode(response));
System.out.println(response.asPrettyString());

UpdateUserAddress_Output_Pojo output = response.as(UpdateUserAddress_Output_Pojo.class);
System.out.println(output.getStatus());
System.out.println(output.getMessage());
}

public void getUserAddress() {
	initRestAssured();
	//Headers	
	List<Header> lstHeader = new ArrayList<Header>();

	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Authorization", "Bearer " + Login.logtoken);
	lstHeader.add(h1);
	lstHeader.add(h2);

	Headers headers = new Headers(lstHeader);
	addHeaders(headers);

	Response response = sendRequest("GET","https://www.omrbranch.com/api/getUserAddress");
	System.out.println(getStatusCode(response));
//	System.out.println(response.asPrettyString());

	GetUserAddress_Output_Pojo output1 = response.as(GetUserAddress_Output_Pojo.class);
	System.out.println(output1.getStatus());
	System.out.println(output1.getMessage());
	}

public void deleteAddress(){
	initRestAssured();
	
List<Header> lstHeader = new ArrayList<Header>();

Header h1 = new Header("accept", "application/json");
Header h2 = new Header("Content-Type", "application/json");

// Bearer Token
Header h3 = new Header("Authorization", "Bearer " + Login.logtoken);
lstHeader.add(h1);
lstHeader.add(h2);
lstHeader.add(h3);

Headers headers = new Headers(lstHeader);

addHeaders(headers);

// Payload
DeleteAddress_Input_Pojo address_Input_Pojo = new DeleteAddress_Input_Pojo(address_id);
addPayload(address_Input_Pojo);

Response response = sendRequest("DELETE","https://www.omrbranch.com/api/deleteAddress");
System.out.println(getStatusCode(response));
System.out.println(response.asPrettyString());

DeleteAddress_Output_Pojo output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
System.out.println(output_Pojo.getStatus());
System.out.println(output_Pojo.getMessage());
}

public static void main(String[] args) {
    // Login API
    Login login = new Login();
    login.login();
    // Address APIs
    Address ad = new Address();
    ad.selectState();
    ad.cityList();
    ad.addUserAddress();
    ad.updateUserAddress();
    ad.getUserAddress();
    ad.deleteAddress();
 }
}