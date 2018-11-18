# TravelBot

# Description
A Java IRC pircbot that listens in on channel(chat room) and responds to a keyword followed by two city names with the
forecast and temperature of the second city, the drive distance and drive time from the first city to the second city, and the top 5 rated restaurants in the second city.

# Requirements
Api keys (free):

Google maps distance matrix api:
https://developers.google.com/maps/documentation/distance-matrix/get-api-key

Zomato restaurant api:
https://developers.zomato.com/api

Open Weather Map current weather api:
https://api.openweathermap.org/

MyBot class has labels for where to put your weather and Google api keys and getRestaurants method in the TravelBot class has label for your Zomato api key.

Libraries:

pircbot.jar
https://mvnrepository.com/artifact/pircbot/pircbot/1.5.0

json-20140107.jar
https://mvnrepository.com/artifact/org.json/json/20140107  

# Deployment
1)	Go to https://freenode.net/
2)	Click on chat
3)	Create nickname
4)	Channel is #pircbot
5)	Connect
6)	Run MyBotMain and you will see TravelBot has joined the chat room
7)	Type in "travelbot startcity endcity", hit enter.  
•	Travelbot (not case sensitive) is the keyword  
•	Startcity is city you’re in or traveling from (no spaces)  
•	Endcity is the city you’re traveling to (no spaces)  
•	Use major cities and no spaces in city names.  
Example:  
travelbot chicago dallas  
The travel bot will respond in chat with the forecast and temperature of your end city (Dallas), 
the drive distance and drive time from start city to end city (from Chicago to Dallas), 
and 5 top rated restaurants in the end city (Dallas).

# Built With
•	Eclipse

# Author

•	Brad Mickow
