package com.multi.Server;

// Imports
import java.io.*;
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
     * TODO: Determine a suitable port number
     */
    public ServerDriver() throws IOException
    {

    }

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

        // Get input and output streams
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        in = new DataInputStream(inputStream);

        OutputStream outputStream = socket.getOutputStream();
        out = new DataOutputStream(outputStream);

        // Retrieve messages from the server
        messageLoop();
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
        // Check command-line arguments
        if(args.length == 1)
        {
            // Port number is provided
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
        else if(args.length == 0)
        {
            // Port number is not provided
            try
            {
                ServerDriver server = new ServerDriver();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            // Too many arguments - throw error
            System.err.println("Too many command-line arguments");
        }
    }

    /**
     *
     */
    private void messageLoop()
    {
        // Variables
        String clientString, outputString;

        try
        {
            do
            {
                clientString = decryptMessage(in.readUTF());

                outputString = respondToClientInput(clientString);

                out.writeUTF(encryptMessage(outputString));

            } while(true);
        }
        catch(IOException e)
        {
            // Issue either reading or writing a message from/to client
            e.printStackTrace();

            // TODO: Test
            messageLoop();
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
    private String respondToClientInput(String clientInput)
    {
        // Split the entire string
        String[] words = clientInput.split(" ");

        switch(words[0])
        {
            case "CHECK":
                // 2 other arguments:
                // First is name of file to check
                // Second is the title to be checked

                return "";

            case "GET":
                // Retrieve data for the user
                // Check for what the user wants
                if(words[1].equalsIgnoreCase("ServerInfo"))
                    return SERVER_INFO;

            default:
                // TODO: Make ClientInputException
                return "ERROR: No matching command.";

        }
    }

    /**
     * TODO
     * @param input
     * @return
     */
    private String decryptMessage(String input)
    {
       return input;
    }

    /**
     * TODO
     * @param output
     * @return
     */
    private String encryptMessage(String output)
    {
        return output;
    }
}
