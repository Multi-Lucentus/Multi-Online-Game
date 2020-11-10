package com.multi.Client;

// Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static final int WIDTH = 550;
    private static final int HEIGHT = 350;

    // Properties
    private String ipAddress;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    // Swing Properties
    private JPanel loginMainPanel;
    private final JPanel imagePanel = new JPanel();
    private final JPanel serverInfoPanel = new JPanel();
    private final JPanel loginPanel = new JPanel();
    private final JPanel signupPanel = new JPanel();

    private final JTextField usernameField = new JTextField(10);
    private final JTextField passwordField = new JTextField(10);
    private final JButton loginButton = new JButton("Log In");
    private final JButton signupButton = new JButton("Sign Up");

    private JPanel homeMainPanel;


    // Constructors
    /**
     * Default Constructor
     * Used when a connection cannot be made
     * TODO: Create error page and then refresh when a connection can be made
     */
    public ClientDriver()
    {
        // Create the Error GUI
        // and check for internet connection every 5 seconds
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }

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
                /*
                 * TODO: Test GUI
                 */

                // Get data about the screen
                int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

                // Initialize basic properties of the frame
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBackground(Color.DARK_GRAY);
                setLocation(screenWidth / 6, screenHeight / 6);
                setSize(WIDTH, HEIGHT);

                // Run the Login GUI
                createAndRunLoginGUI();
            }
        });
    }


    // Methods
    /**
     * Main Method of Client Machine
     * Start of program logic
     *
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
             * Works as of 5:12 PM on 11/10/2020
             */
            // System.out.println("Connected to Server!");


            // Create new instance of ClientDriver that will create the GUI
            ClientDriver client = new ClientDriver(socket, in, out);
        }
        catch(IOException e)
        {
            /*
             * TESTING Server Connectivity
             */
            // System.out.println("Server Did Not Connect :(");

            /*
             * TODO: Make it so that GUI still opens, but just don't connect
             *  Can use default constructor and just display an error page until we can connect
             */
            ClientDriver client = new ClientDriver();

            e.printStackTrace();
        }
    }

    /**
     * The login GUI will
     */
    private void createAndRunLoginGUI()
    {
        // Set properties of the main panel
        // TODO: Look into content pane from frame
        loginMainPanel = new JPanel();
        loginMainPanel.setBackground(Color.DARK_GRAY);

        // Set layout - use GridBag layout for the most flexibility
        loginMainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 1, 5, 1);

        // Set borders - if needed/wanted

        // Image Panel - will display an image in the upper left hand corner
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel imageLabel = new JLabel();
        imageLabel.setOpaque(true);
        ImageIcon imageIcon = new ImageIcon("resources/images/icon.png");
        imageLabel.setIcon(imageIcon);
        imagePanel.add(imageLabel);

        loginMainPanel.add(imagePanel, constraints);

        // Server Info Panel - Will have a banner with a message from the server in the middle of the screen
        constraints.gridy = 2;
        constraints.gridwidth = 3;

        loginMainPanel.add(serverInfoPanel, constraints);

        // Log In Panel
        constraints.gridy = 3;
        constraints.gridwidth = 1;

        // Add button functionality
        // Will send data to server to check if user exists, if they do not, then provide error message
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                /*
                 * TESTING BUTTON
                 * Works as of 5:13 PM on 11/10/2020
                 */
                // System.out.println("Log In Button Works!");
            }
        });

        loginPanel.add(loginButton);
        loginMainPanel.add(loginPanel, constraints);


        // Sign Up Panel
        constraints.gridx = 3;

        // Add button functionality
        signupButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                /*
                 * TESTING BUTTON
                 * Works as of 5:13 PM on 11/10/2020
                 */
                // System.out.println("Sign Up Button Works!");

            }
        });

        signupPanel.add(signupButton);
        loginMainPanel.add(signupPanel, constraints);



        // Make the window visible
        add(loginMainPanel);
        setVisible(true);
    }

    /**
     *
     */
    private void createAndRunHomeGUI()
    {

    }
}
