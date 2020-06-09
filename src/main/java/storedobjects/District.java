package storedobjects;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;


public class District {
	
	private Car car;
	private String district;
	
	public District(Car car){
		this.car = car;
	}
	
	
	
	//find district of car
	private String getDistrict() {
		
		//get geo-location of car
		double lng = car.getLongitude();
		double lat = car.getLatitude();
		
		//transfer coordinates to district address
		JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(API_KEY);

		JOpenCageReverseRequest request = new JOpenCageReverseRequest(lat, lng);
		
		request.setNoAnnotations(true);

		JOpenCageResponse response = jOpenCageGeocoder.reverse(request);
		
		//handle the json response to get the district of car
		JSONObject obj = new JSONObject(response);
        
        JSONArray arr = obj.getJSONArray("results");
        
        for (int i = 0; i < arr.length(); i++) {
        	
            district = arr.getJSONObject(i).getJSONObject("components").getString("city_district");
            
        }
		
		return district;
		 
	}
	
	//calculate #cars in the district
	private int getCarAmount() {
		
		return 0;
		
	}
		
	//determine price of this district by the #cars in it
	private double getPrice() {
		
		return 1.0;
		
	}
	
	//caching high requested data into redis
	
	
	//store data necessary into mongodb
	
	
	public static void main(String[] args) {
		
	}
	
}