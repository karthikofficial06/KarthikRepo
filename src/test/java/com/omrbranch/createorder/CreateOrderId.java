package com.omrbranch.createorder;
	
	import java.util.ArrayList;
import java.util.List;

import com.omrbranch.address.Address;
import com.omrbranch.pojo.addtocart.AddToCart_Input_Pojo;
import com.omrbranch.pojo.cancelorder.CancelOrder_Input_Pojo;
import com.omrbranch.pojo.cancelorder.CancelOrder_Output_Pojo;
import com.omrbranch.pojo.createorder.CreateOrder_Input_Pojo;
import com.omrbranch.pojo.createorder.CreateOrder_Output_Pojo;
import com.omrbranch.pojo.getallorders.GetAllOrders_Output_Pojo;
import com.omrbranch.pojo.getcartitems.GetCartItems_Output_Pojo;
import com.omrbranch.pojo.getsearchresult.Datum;
import com.omrbranch.pojo.getsearchresult.GetSearchResult_Input_pojo;
import com.omrbranch.pojo.getsearchresult.GetSearchResult_Output_Pojo;
import com.omrbranch.pojo.getsearchresult.Option;
import com.omrbranch.pojo.getsearchresult.Variation;
import com.omrbranch.pojo.searchproduct.ProductList;
import com.omrbranch.pojo.searchproduct.SearchProduct_Input_Pojo;
import com.omrbranch.pojo.searchproduct.SearchProduct_Output_Pojo;
import com.omrbranch.pojo.setaddress.SetAddress_Input_Pojo;
import com.omrbranch.pojo.setaddress.SetAddress_Output_Pojo;
import com.omrbranch.postmanbasicauthlogin.Login;
import com.omrbranch.utility.BaseClass;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
	
	public class CreateOrderId extends BaseClass {
		int category_id;
		int product_id;
		int variation_Id;
		int cancelOrderId;
		String categoryId;
		String productId;
		String variationId;
		String cart_id;
		String address_id;
		String orderNo;
	
		public void searchProduct() {
	        initRestAssured();
	        
			List<Header> lstHeader = new ArrayList<Header>();
			Header h1 = new Header("accept", "application/json");
			Header h2 = new Header("Content-Type", "application/json");
			lstHeader.add(h1);
			lstHeader.add(h2);
			
			Headers headers = new Headers(lstHeader);
			addHeaders(headers);
			addBasicAuthentication("Happiestperson11062001@gmail.com", "Karthi@1106");
			
			SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo("nuts");
			addPayload(searchProduct_Input_Pojo);
			Response response = sendRequest("POST", "https://omrbranch.com/api/searchProduct");
			
			SearchProduct_Output_Pojo searchProduct_Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
			ArrayList<ProductList> productList = searchProduct_Output_Pojo.getData();
			for (ProductList eachProduct : productList) {
				String productName = eachProduct.getText();
				if (productName.equals("Tata Sampann 100% Iranian Pistachios Roasted & Salted in Fruit & Nuts")) {
					category_id = eachProduct.getCategory_id();
					categoryId = String.valueOf(category_id);
					System.out.println(categoryId);
					product_id = eachProduct.getId();
					productId = String.valueOf(product_id);
					System.out.println(productId);
					break;
				}
			}
		}
public void getSearchResult()  {
	initRestAssured();
	//Headers	
	List<Header> lstHeader = new ArrayList<Header>();

	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Content-Type", "application/json");
	Header h3 = new Header("Authorization", "Bearer " + Login.logtoken);
	
	lstHeader.add(h1);
	lstHeader.add(h2);
	lstHeader.add(h3);
	Headers headers = new Headers(lstHeader);
	addHeaders(headers);

	GetSearchResult_Input_pojo getSearchResult_Input_Pojo = new GetSearchResult_Input_pojo(categoryId, productId,"category");
    addPayload(getSearchResult_Input_Pojo);
    Response response = sendRequest("POST","https://omrbranch.com/api/getSearchResult");
   System.out.println(getStatusCode(response)); 
   
   GetSearchResult_Output_Pojo getSearchResult_Output_Pojo = response.as(GetSearchResult_Output_Pojo.class);
    ArrayList<Datum> data = getSearchResult_Output_Pojo.getData();
    for (Datum datum : data) {
        ArrayList<Variation> variations= datum.getVariations();
        for(Variation eachVariation : variations) {
            String specification = eachVariation.getSpecifications();
            if( specification.equals("1 kg")) {
                        ArrayList<Option> options = eachVariation.getOptions();
                    variation_Id = options.get(0).getVariation_id();
                    variationId = String.valueOf(variation_Id);
                    System.out.println(variationId);
                    break;
            }}
        }}

public void addToCart() {
	initRestAssured();
	
	List<Header> lstHeader = new ArrayList<Header>();
	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Content-Type", "application/json");
	Header h3 = new Header("Authorization", "Bearer " + Login.logtoken);
	lstHeader.add(h1);
	lstHeader.add(h2);
	lstHeader.add(h3);
	
	Headers headers = new Headers(lstHeader);
	addHeaders(headers);
	
	AddToCart_Input_Pojo AddToCart_Input_Pojo = new AddToCart_Input_Pojo(
			productId, variationId, "plus");
	addPayload(AddToCart_Input_Pojo);
	Response response = sendRequest("POST", "https://omrbranch.com/api/addToCart");
	System.out.println(getStatusCode(response));
}

public void getCartItems() {
	initRestAssured();
	
	List<Header> lstHeader = new ArrayList<Header>();
	Header h1 = new Header("accept", "application/json");
	Header h2 = new Header("Authorization", "Bearer " + Login.logtoken);
	lstHeader.add(h1);
	lstHeader.add(h2);
	
	Headers headers = new Headers(lstHeader);
	addHeaders(headers);
	
	Response response = sendRequest("GET", "https://omrbranch.com/api/getCartItems");
	GetCartItems_Output_Pojo output_Pojo = response.as(GetCartItems_Output_Pojo.class);
	ArrayList<com.omrbranch.pojo.getcartitems.Datum> data = output_Pojo.getData();
	for(com.omrbranch.pojo.getcartitems.Datum eachdata :data) {
		int CartId = eachdata.getCart_id();
		cart_id = String.valueOf(CartId);
		System.out.println("Cart Id : "+CartId);
	}	
}

public void setAddress() {

    initRestAssured();

    List<Header> lstHeader = new ArrayList<Header>();

    Header h1 = new Header("accept", "application/json");
    Header h2 = new Header("Content-Type", "application/json");
    Header h3 = new Header("Authorization", "Bearer " + Login.logtoken);

    lstHeader.add(h1);
    lstHeader.add(h2);
    lstHeader.add(h3);

    Headers headers = new Headers(lstHeader);
    addHeaders(headers);
    
    System.out.println("Address Id : " + Address.address_id);
    System.out.println("Cart Id : " + cart_id);
    SetAddress_Input_Pojo inputPojo =
            new SetAddress_Input_Pojo(
            String.valueOf(Address.address_id),
            cart_id);
    addPayload(inputPojo);
    Response response = sendRequest("POST","https://omrbranch.com/api/setAddress");
    System.out.println(response.asPrettyString());
    
    SetAddress_Output_Pojo outputPojo = response.as(SetAddress_Output_Pojo.class);
    System.out.println(outputPojo.getStatus());
    System.out.println(outputPojo.getMessage());
}

public void createOrder() {
	    initRestAssured();

	    List<Header> lstHeader = new ArrayList<Header>();
	    Header h1 = new Header("accept", "application/json");
	    Header h2 = new Header("Content-Type", "application/json");
	    Header h3 = new Header("Authorization", "Bearer " + Login.logtoken);

	    lstHeader.add(h1);
	    lstHeader.add(h2);
	    lstHeader.add(h3);
	    Headers headers = new Headers(lstHeader);
	    addHeaders(headers);

	    CreateOrder_Input_Pojo inputPojo = new CreateOrder_Input_Pojo(
	                    "debit_card",
	                    "5555555555552222",
	                    "visa",
	                    "2027",
	                    "06",
	                    "567");
	    addPayload(inputPojo);

	    Response response = sendRequest("POST","https://omrbranch.com/api/createOrder");
	    System.out.println(getStatusCode(response));
	    System.out.println(response.asPrettyString());

	    CreateOrder_Output_Pojo outputPojo = response.as(CreateOrder_Output_Pojo.class);
	    System.out.println(outputPojo.getStatus());
	    System.out.println(outputPojo.getMessage());
	    System.out.println(outputPojo.getData().getOrder_no());
	            
	    orderNo = String.valueOf(outputPojo.getData().getOrder_no());	            
	    System.out.println("Stored Order No : " + orderNo);
	           
	    System.out.println(outputPojo.getData()	            
	                      .getUser_address()
	                      .getFirst_name());

	    System.out.println(outputPojo.getData()
	                      .getUser_address()
	                      .getAcity()
	                      .getName());
	}

public void getAllOrders() {
    initRestAssured();

    List<Header> lstHeader = new ArrayList<Header>();
    Header h1 = new Header("accept", "application/json");
    Header h2 = new Header("Authorization",
            "Bearer " + Login.logtoken);

    lstHeader.add(h1);
    lstHeader.add(h2);
    Headers headers = new Headers(lstHeader);
    addHeaders(headers);
    
    Response response = sendRequest("GET",
            "https://omrbranch.com/api/getAllOrders");
    System.out.println(getStatusCode(response));
    System.out.println(response.asPrettyString());
    GetAllOrders_Output_Pojo outputPojo = response.as(GetAllOrders_Output_Pojo.class);
    System.out.println(outputPojo.getStatus());
    System.out.println(outputPojo.getMessage());

    ArrayList<com.omrbranch.pojo.getallorders.Datum> data = outputPojo.getData();           
    for (com.omrbranch.pojo.getallorders.Datum eachData : data) {
        String order_Number = eachData.getOrder_no();
        System.out.println("Stored OrderNo : " + orderNo);
        System.out.println("API OrderNo : " + order_Number);
        if(order_Number.equals(orderNo)) {
            cancelOrderId = eachData.getId();
            System.out.println("Order Id : " + cancelOrderId);                
            break;
        }
    }
}

public void cancelOrder() {
    initRestAssured();

    List<Header> lstHeader = new ArrayList<Header>();
    Header h1 = new Header("accept", "application/json");
    Header h2 = new Header("Content-Type", "application/json");
    Header h3 = new Header("Authorization",
            "Bearer " + Login.logtoken);

    lstHeader.add(h1);
    lstHeader.add(h2);
    lstHeader.add(h3);
    
    Headers headers = new Headers(lstHeader);
    addHeaders(headers);
    System.out.println( "Cancel Order Id : "+ cancelOrderId);
    
    CancelOrder_Input_Pojo inputPojo = new CancelOrder_Input_Pojo(
                    String.valueOf(cancelOrderId));
    addPayload(inputPojo);

    Response response = sendRequest("POST","https://omrbranch.com/api/cancelOrder");
    System.out.println(getStatusCode(response));
    System.out.println(response.asPrettyString());
    
    CancelOrder_Output_Pojo outputPojo =  response.as(CancelOrder_Output_Pojo.class);          
    System.out.println(outputPojo.getStatus());
    System.out.println(outputPojo.getMessage());
}

public static void main(String[] args) {
	Login login = new Login();
    login.login();
    Address ad = new Address();
    ad.selectState();
    ad.cityList();
    ad.addUserAddress();
    CreateOrderId id = new CreateOrderId();
    id.searchProduct();
    id.getSearchResult();
    id.addToCart();
    id.getCartItems();
    id.setAddress();
    id.createOrder();
    id.getAllOrders();
    id.cancelOrder();
}
//Git Practice
}