package botPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author Brad Mickow
 * TravelBot class conatins the methods that retrieve the 
 * weather, distance, longitude/latitude, and restaurants.
 */
public class TravelBot {
	/**
	 * Read and parse api.openweathermap.org url json response.
	 * @param weatherUrl
	 * @return Forecast and temperature of end city.
	 */
	public static String getWeather(String weatherUrl) {
		try {
			
			/////read weather JSON in as string
			StringBuilder weatherResult = new StringBuilder();
			URL weatherUrlString = new URL(weatherUrl);
			URLConnection weatherConn = weatherUrlString.openConnection();
			BufferedReader rdWeather = new BufferedReader(new InputStreamReader(weatherConn.getInputStream()));
			String weatherLine;
			while((weatherLine = rdWeather.readLine()) != null) {
				weatherResult.append(weatherLine);
			}
			rdWeather.close();
			
			/////parse JSON for forecast and temperature
			JSONObject weatherObject = new JSONObject(weatherResult.toString());
			JSONObject weatherObject1 = weatherObject.getJSONObject("main");
			@SuppressWarnings("unused")
			Double resultTemp = weatherObject1.getDouble("temp");
			JSONArray weatherArray = weatherObject.getJSONArray("weather");
			JSONObject weatherObject2 = weatherArray.getJSONObject(0);
		
			/////return weather results as string
			final String DEGREE = "\u00B0";
			String weatherString = weatherObject2.getString("description") + " with a Temperature of " + weatherObject1.getDouble("temp") + DEGREE + "F";
			return weatherString;
			
			} catch (IOException e) {
		
				System.out.println(e.getMessage());
				
			}
			return "error";
	}
	/**
	 * Read and parse maps.googleapis.com url json response.
	 * @param distanceUrl
	 * @return Driving distance and duration from start city to end city.
	 */
	public static String getDistance(String distanceUrl) {
		try {
			
			/////read distance JSON in as string
			StringBuilder distanceResult = new StringBuilder();
			URL theDistanceUrl = new URL(distanceUrl);
			URLConnection distanceConn = theDistanceUrl.openConnection();
			BufferedReader rd_distance = new BufferedReader(new InputStreamReader(distanceConn.getInputStream()));
			String distanceLine;
			while((distanceLine = rd_distance.readLine()) != null) {
				distanceResult.append(distanceLine);
			}
			rd_distance.close();
			
			/////parse JSON for distance and duration
			JSONObject distanceObject = new JSONObject(distanceResult.toString());
			JSONArray objJSONArray = distanceObject.getJSONArray("rows");
			JSONObject distanceObject2 = objJSONArray.getJSONObject(0);
			JSONArray distanceArray = distanceObject2.getJSONArray("elements");
			JSONObject distanceObject3 = distanceArray.getJSONObject(0);
			JSONObject distanceObject4 = distanceObject3.getJSONObject("distance");
			JSONObject distanceObject5 = distanceObject3.getJSONObject("duration");
	
			/////return distance results as string
			String distanceString = distanceObject4.getString("text") +
					" and the estimated travel time(driving) is " + distanceObject5.getString("text");
			
			return distanceString;
	
			} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
			return "error";
			}
	}
	/**
	 * Read and parse maps.googleapis.com url json response.
	 * @param longLatUrl
	 * @return Longitude and latitude of end city for zomato api url location details.
	 */
	public static Double[] getLongLat(String longLatUrl) {
		try {
			
			/////read longitude and latitude JSON in as string
			StringBuilder result2 = new StringBuilder();
			URL theLongLatUrl = new URL(longLatUrl);
			URLConnection longLatConn = theLongLatUrl.openConnection();
			BufferedReader longLatRead = new BufferedReader(new InputStreamReader(longLatConn.getInputStream()));
			String longLatLine;
			
			while((longLatLine = longLatRead.readLine()) != null) {
				result2.append(longLatLine);
			}
			longLatRead.close();
			
			/////parse JSON for longitude and latitude
	        JSONObject longLatObject = new JSONObject(result2.toString());
	        JSONArray longLatArray = longLatObject.getJSONArray("results");
	        JSONObject longLatObject2 = longLatArray.getJSONObject(0);
	        JSONObject longlatObject3 = longLatObject2.getJSONObject("geometry");
	        JSONObject longLatObject4 = longlatObject3.getJSONObject("location");
	        
			Double latitude = (longLatObject4.getDouble("lat"));
			Double longitude = (longLatObject4.getDouble("lng"));
			
			/////return longitude and latitude results as array
			Double longlat[] = new Double[2];
			longlat[0] = latitude;
			longlat[1] = longitude;
			return longlat;
	        
		} catch (Exception e) {
			return null;
			}
		}

	/**
	 *  Read and parse developers.zomato.com url json response.
	 * @param restaurantsUrl
	 * @return Top five restaurants of a given city.
	 */
	public static String[] getRestaurants(String restaurantsUrl) {
		try {
			
			/////read restaurants JSON in as string
			URL theRestaurantsUrl = new URL(restaurantsUrl);
			HttpURLConnection restaurantsConn =(HttpURLConnection) theRestaurantsUrl.openConnection();
			restaurantsConn.setRequestMethod("GET");
			restaurantsConn.setRequestProperty("Accept", "application/json");
			restaurantsConn.setRequestProperty("user-key", "Your developers.zomato.com/api key here");
			BufferedReader in = new BufferedReader (new InputStreamReader(restaurantsConn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			} in.close();
			
			/////parse JSON for name, addresss and cuisine of 5 restaurants
			JSONObject restaurantsObject = new JSONObject(response.toString());
			JSONArray restaurantsArray = restaurantsObject.getJSONArray("restaurants");
			
			JSONObject restaurantsObject1 = restaurantsArray.getJSONObject(0);
			JSONObject restaurantsObject2 = restaurantsObject1.getJSONObject("restaurant");
			JSONObject restaurantsObject3 = restaurantsObject2.getJSONObject("location");
			String restaurant1 = restaurantsObject2.getString("name");
			String address1 = restaurantsObject3.getString("address");
			String cuisine1 = restaurantsObject2.getString("cuisines");

			JSONObject restaurantsObject4 = restaurantsArray.getJSONObject(1);
			JSONObject restaurantsObject5 = restaurantsObject4.getJSONObject("restaurant");
			JSONObject restaurantsObject6 = restaurantsObject5.getJSONObject("location");
			String restaurant2 = restaurantsObject5.getString("name");
			String address2 = restaurantsObject6.getString("address");
			String cuisine2 = restaurantsObject5.getString("cuisines");
			
			JSONObject restaurantsObject7 = restaurantsArray.getJSONObject(2);
			JSONObject restaurantsObject8 = restaurantsObject7.getJSONObject("restaurant");
			JSONObject restaurantsObject9 = restaurantsObject8.getJSONObject("location");
			String restaurant3 = restaurantsObject8.getString("name");
			String address3 = restaurantsObject9.getString("address");
			String cuisine3 = restaurantsObject8.getString("cuisines");
			
			JSONObject restaurantsObject10 = restaurantsArray.getJSONObject(3);
			JSONObject restaurantsObject11 = restaurantsObject10.getJSONObject("restaurant");
			JSONObject restaurantsObject12 = restaurantsObject11.getJSONObject("location");
			String restaurant4 = restaurantsObject11.getString("name");
			String address4 = restaurantsObject12.getString("address");
			String cuisine4 = restaurantsObject11.getString("cuisines");
			
			JSONObject restaurantsObject13 = restaurantsArray.getJSONObject(4);
			JSONObject restaurantsObject14 = restaurantsObject13.getJSONObject("restaurant");
			JSONObject restaurantsObject15 = restaurantsObject14.getJSONObject("location");
			String restaurant5 = restaurantsObject14.getString("name");
			String address5 = restaurantsObject15.getString("address");
			String cuisine5 = restaurantsObject14.getString("cuisines");
			
			/////return restaurant results as array
			String rest[] = {restaurant1, address1, cuisine1, restaurant2, address2, cuisine2, 
					restaurant3, address3, cuisine3, restaurant4, address4, cuisine4,
					 restaurant5, address5, cuisine5};
			return rest;

			
		} catch (Exception e) {
			return null;
		}
	}
}

