package com.multi.Server;

// Imports
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * The main class for the Server machine
 *
 * @author Lucentus
 * @version Nov 9th, 2020
 */
public class ServerDriver
{
    // Constants
    private static final String SERVER_INFO = "The Official Online Multi Game of 2020";

    // Properties
    private int portNum;
    private ServerSocket serverSocket;

    private DataInputStream in;
    private DataOutputStream out;


    // Constructors
    /**
     * Default Constructor
     */
    public ServerDriver() { }

    /**
     * The constructor that
     * @param portNum is the integer port number that the server will use
     */
    public ServerDriver(int portNum) throws IOException
    {
        // Initialize variables
        this.portNum = portNum;

        // Create the Server Socket
        serverSocket = new ServerSocket(portNum);
    }

    // Methods
    /**
     * Main method of Server machine
     * Start of program logic
     *
     * @param args will be implemented in the future where a GUI might be available to control
     */
    public static void main(String[] args)
    {
        // TODO: Check command-line arguments

        // Get the port number from the args
        int port_number = Integer.parseInt(args[0]);

        // Create a new instance of the server
        try
        {
            ServerDriver server = new ServerDriver(port_number);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Much of client input will be in string format to make my life easier hopefully
     * (Watch me come back in like 2 weeks hating myself for this)
     * Input will be made up of a string with multiple words so we can split
     *
     * TODO: Could totally encrypt and decrypt data from client to server for fun
     *
     * @param clientInput is the string that is sent from the client
     * @return A string is returned that answers the
     */
    private String ListenToClient(String clientInput)
    {
        // Split the entire string
        String[] words = clientInput.split(" ");

        switch(words[0])
        {
            case "CHECK":
                return "";

            case "GET":
                // Retrieve data for the user
                // Check for what the user wants
                if(words[1].equalsIgnoreCase("ServerInfo"))
                    return SERVER_INFO;

            default:
                return "ERROR: No matching command.";

        }
    }

    /**
     *
     * @param input
     * @return
     */
    private String decryptMessage(String input)
    {
       return "";
    }

    /**
     *
     * @param output
     * @return
     */
    private String encryptMessage(String output)
    {


        return "";
    }
}
