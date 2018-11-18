package botPackage;
import org.jibble.pircbot.*;

/**
 * @author Brad Mickow
 * The MyBot class is the communication between the channel and the bot.
 */

public class MyBot extends PircBot {
	
    public MyBot() {
        this.setName("TravelBot");
    }
    
    // Listening to the channel and sending messages all happens in the onMessage method.
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
    	
    	 message = message.toLowerCase();
    	 String[] words = message.split(" ");
    	 
         	// Listen for the keyword "travelbot"
         if (message.toLowerCase().startsWith("travelbot"))
         {
        	 	// Each word is an array index separated by a space. Index 0 is the keyword,
        	 	// index 1 is the start city and index 2 is the end city.
          		String startCity = words[1];
        		String endCity = words[2];
        		
        		String weatherApiKey = "Your api.openweathermap.org api Key here";
        		String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + endCity + "&appid=" + weatherApiKey + "&units=imperial";
        		
        		String distanceApiKey = "Your developers.google.com/maps/documentation/distance-matrix/get-api-keyapi key here";
        		String distanceUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + startCity +  "%20&destinations=%20" + endCity + "%20&key=" + distanceApiKey;

        		String resultWeather = TravelBot.getWeather(weatherUrl);
           		String resultDistance = TravelBot.getDistance(distanceUrl);
           		
           		sendMessage(channel, sender + "\n");
        		sendMessage(channel, sender + " The forecast for " + endCity + " is " + resultWeather);
        		sendMessage(channel, sender + "\n");
        		sendMessage(channel, sender + " The travel distance to " + endCity + " is " + resultDistance);

	            String longlat_api_key= "your googleapis.com api key here";
	            String longLatUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + endCity + "&key=" + longlat_api_key;
	            
	            Double[] resultLongLat = TravelBot.getLongLat(longLatUrl);
	            
			
				String restaurantsUrl = "https://developers.zomato.com/api/v2.1/search?count=10&lat=" + resultLongLat[0] + "&lon=" 
											+ resultLongLat[1] + "&collection_id=1&sort=rating&order=asc";
					
				String[] resultRestaurants = TravelBot.getRestaurants(restaurantsUrl);
				
				sendMessage(channel, sender + "\n");
				sendMessage(channel, sender + " Here are 5 top rated Restaurants I recommend you dine at while visiting " + endCity);
				sendMessage(channel, sender + "\n");
				
				// Read in Array of Restaurants and skip a line every three lines for spacing between Restaurants
				for (int i=0; i < resultRestaurants.length; i++) {
					sendMessage(channel, sender + " " + resultRestaurants[i]);
				    if ((i + 1) % 3 == 0) {
				    	sendMessage(channel, sender + "\n");
				    }
				}
				sendMessage(channel, sender + " Have a safe Trip!");
					
        }
    }
 }
