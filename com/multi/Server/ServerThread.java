package com.multi.Server;

import java.io.*;
import java.net.Socket;

/**
 * Server Thread Class
 * Each Client
 */
public class ServerThread extends Thread
{
    // Exceptions
    private static class ClientInputException extends Exception {}
    private static class ClientArgumentException extends Exception {}

    // Properties
    private static final String SERVER_INFO = "SERVER INFORMATION";

    private Socket socket;


    // Constructors
    /**
     *
     * @param socket
     */
    public ServerThread(Socket socket)
    {
        this.socket = socket;
    }

    // Methods
    @Override
    public void run()
    {
        try
        {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String text = "";

            while(!text.equals("end"))
            {
                // Gather input and then respond to said input
                text = reader.readLine();


            }
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
    private String respondToClientInput(String clientInput)
    {
        // Split the entire string
        String[] words = clientInput.split(" ");

        switch(words[0])
        {
            case "BATTLE":
                // Add user to the battle queue (To be added)

                // TEST
                return "BATTLE";

            case "CHECK":
                // 2 other arguments:
                // Check num arguments
                if(words.length != 2)
                {

                }

                // First is name of file to check
                // Second is the title to be checked
                String fileName = words[1];
                String check = words[2];

                // TEST
                return "CHECK";

            case "GET":
                // Retrieve data for the user
                // Check for what the user wants
                if(words[1].equalsIgnoreCase("ServerInfo"))
                    return SERVER_INFO;

                // TEST
                return "GET";

            case "SAVE":
                // Save the user's data to the server
                // Data includes level and party

                // TEST
                return "SAVE";

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
    private static String decryptMessage(String input)
    {
        return input;
    }

    /**
     * TODO
     * @param output
     * @return
     */
    private static String encryptMessage(String output)
    {
        return output;
    }



}