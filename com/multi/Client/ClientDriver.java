package com.multi.Client;

// Imports
import javax.swing.*;
import java.io.*;
import java.net.Socket;


/**
 * The main class for the Client machine
 * Will essentially host 2 GUIs - Login and Main
 *
 * @author Lucentus
 * @version Nov 9th, 2020
 */
public class ClientDriver extends JFrame
{
    // Constants
    private static final int PORT_NUMBER = 5500;

    // Properties
    private String ipAddress;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    // Swing Properties


    // Constructors
    /**
     * Default Constructor
     */
    public ClientDriver() { }

    /**
     * Constructor that initializes the online properties
     *
     * @param socket is the socket object that is connected to the server
     * @param in is the DataInputStream that will send data to the server
     * @param out is the DataOutputStream that will receive data from the server
     */
    public ClientDriver(Socket socket, DataInputStream in, DataOutputStream out)
    {
        // Initialize variables
        this.socket = socket;
        this.in = in;
        this.out = out;
        
        // Create the GUI
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createAndRunLoginGUI();
            }
        });
    }


    // Methods
    /**
     * Main Method of Client Machine
     * Start of program logic
     * @param args will have one argument - the IP address of the server
     */
    public static void main(String[] args)
    {
        // Check command-line arguments
        if(args.length != 1)
        {
            System.err.println("Illegal number of command-line arguments.");
            return;
        }

        // Open the Socket and connect to the Server
        try(Socket socket = new Socket(args[0], PORT_NUMBER))
        {
            // Get the input and output streams
            InputStream inputStream = socket.getInputStream();
            DataInputStream in = new DataInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);

            /*
             * TESTING CONNECTIVITY TO SERVER
             */


            // Create new instance of ClientDriver that will create the GUI
            ClientDriver client = new ClientDriver(socket, in, out);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void createAndRunLoginGUI()
    {

    }

    /**
     *
     */
    private void createAndRunHomeGUI()
    {

    }
}
