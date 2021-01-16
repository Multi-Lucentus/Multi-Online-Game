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
    // Properties
    private static int portNum;

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
        if(args.length != 1)
        {
            // Incorrect number of arguments, need to restart
            // TODO: If 0 zero arguments, automatic port finder?
            System.err.println("Illegal number of command-line arguments");
        }

        // Get the port number from the args
        portNum = Integer.parseInt(args[0]);

        try(ServerSocket serverSocket = new ServerSocket(portNum))
        {
            System.out.println("Server is listening on port " + portNum);

            while(true)
            {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ServerThread(socket).start();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
