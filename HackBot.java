package botPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
/**
 * This code is provided by freenode.net
 * to connect to their server and join
 * the channel.
 */
public class HackBot {
	
	public static void main(String[] args) throws Exception {
		
		String server = "irc.freenode.net";
		String Brad = "simple_bot";
		String login = "simple_bot";
		String channel = "#irchacks";
		
		@SuppressWarnings("resource")
		Socket socket = new Socket(server, 6667);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		writer.write("Brad " + Brad + "\r\n");
		writer.write("USER " + login + " 8 * : Java IRC Hacks Bot\r\n");
		writer.flush();
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.indexOf("004") >= 0) {
				break;
			}
			else if (line.indexOf("433") >= 0) {
				System.out.println("Nickname is already in use.");
				return;
			}
		}
		
		writer.write("Join " + channel + "\r\n");
		writer.flush();
		
		while((line = reader.readLine()) != null) {
			if(line.toLowerCase().startsWith("PING ")) {
				writer.write("PONG " + line.substring(5) + "\r\n");
				writer.write("PRIVMSG " + channel + " :I got pinged!\r\n");
				writer.flush();
			}
			else {
				System.out.println(line);
			}
		}	
	}
}
