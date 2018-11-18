package botPackage;

/**
 * Creates an instance of the bot.
 * Connects the bot to the IRC server.
 * Joins channel(chat room) specifed. This
 * channel is used for testing purposes.
 */
public class MyBotMain {

    public static void main(String[] args) throws Exception {

        // Now start our bot up.
        MyBot bot = new MyBot();
        
        // Enable debugging output.
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect("irc.freenode.net");

        // Join the #pircbot channel.
        bot.joinChannel("#pircbot");

    }
 }