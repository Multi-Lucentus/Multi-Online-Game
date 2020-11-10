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
public class ServerDriver implements Runnable
{
    // Properties
    private int portNum;
    private Thread thread;
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
     *
     */
    public void start()
    {
        // Initialize the thread and start it
        thread = new Thread(this);
        thread.start();
    }

    /**
     *
     */
    @Override
    public void run()
    {
        // Create the socket and get the data streams
        try
        {
            // Create the socket
            Socket server = serverSocket.accept();

            // Get input and output streams
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

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
            server.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
