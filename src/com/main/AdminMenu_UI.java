package com.main;

import com.UI.admin.*;
import com.UI.user.BookedRoomsStudent_UI;
//import com.UI.user.BookedRoomsTeacher_UI;
import com.logics.LogicAdministrator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminMenu_UI extends JFrame implements ActionListener {

    private final static String MASTER_PASSWORD = "Password";
    private final static String ADMIN_ID = "33333333";

    private Container c;
    private JLabel title_Lb;
    private JLabel datetime_Lb;
    private JButton regUser_Bt;
    private JButton regRoom_Bt;
    private JButton userList_Bt;
    private JButton roomList_Bt;
    private JButton roomSetting_Bt;
    private JButton userSetting_Bt;
    private JButton bookingPage_Bt;
    private JButton databaseSetting_Bt;
    private JButton welcomePage_Bt;
    private JButton closeProgram_Bt;

    public AdminMenu_UI() {

        setTitle("Admin Menu Panel");
        setSize(520, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        c = getContentPane();
        c.setLayout(null);

        // creates the title for the main Controlling panel
        title_Lb = new JLabel("Welcome to Administrator Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 18));
        title_Lb.setSize(300, 25);
        title_Lb.setLocation(50, 15);
        c.add(title_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 25);
        datetime_Lb.setLocation(340, 15);
        c.add(datetime_Lb);

        // creates a button for initiating/opening the registration panel
        regUser_Bt = new JButton("User Register");
        regUser_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        regUser_Bt.setSize(200, 30);
        regUser_Bt.setLocation(50, 60);
        regUser_Bt.setFocusable(false);
        regUser_Bt.addActionListener(this);
        c.add(regUser_Bt);

        // creates a button for admin to create/enter room data
        regRoom_Bt = new JButton("Room Register");
        regRoom_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        regRoom_Bt.setSize(200, 30);
        regRoom_Bt.setLocation(270, 60);
        regRoom_Bt.setFocusable(false);
        regRoom_Bt.addActionListener(this);
        c.add(regRoom_Bt);

        // creates a button for admin to see the user data - all user list
        userList_Bt = new JButton("User List");
        userList_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        userList_Bt.setSize(200, 30);
        userList_Bt.setLocation(50, 100);
        userList_Bt.setFocusable(false);
        userList_Bt.addActionListener(this);
        c.add(userList_Bt);

        // creates a button for admin to see the user data - all user list
        roomList_Bt = new JButton("Room List");
        roomList_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        roomList_Bt.setSize(200, 30);
        roomList_Bt.setLocation(270, 100);
        roomList_Bt.setFocusable(false);
        roomList_Bt.addActionListener(this);
        c.add(roomList_Bt);

        // creates a button for closing the whole program
        userSetting_Bt = new JButton("User Setting");
        userSetting_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        userSetting_Bt.setSize(200, 30);
        userSetting_Bt.setLocation(50, 140);
        userSetting_Bt.setFocusable(false);
        userSetting_Bt.addActionListener(this);
        c.add(userSetting_Bt);

        // creates a button for opening the room booking panel
        roomSetting_Bt = new JButton("Room Setting");
        roomSetting_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        roomSetting_Bt.setSize(200, 30);
        roomSetting_Bt.setLocation(270, 140);
        roomSetting_Bt.setFocusable(false);
        roomSetting_Bt.addActionListener(this);
        c.add(roomSetting_Bt);

        // creates a button for opening the room booking panel
        bookingPage_Bt = new JButton("Room Booking");
        bookingPage_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        bookingPage_Bt.setSize(200, 30);
        bookingPage_Bt.setLocation(270, 180);
        bookingPage_Bt.setFocusable(false);
        bookingPage_Bt.addActionListener(this);
        c.add(bookingPage_Bt);

        // creates a button for opening database setting panel
        databaseSetting_Bt = new JButton("Database Setting");
        databaseSetting_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        databaseSetting_Bt.setSize(200, 30);
        databaseSetting_Bt.setLocation(50, 180);
        databaseSetting_Bt.setFocusable(false);
        databaseSetting_Bt.addActionListener(this);
        c.add(databaseSetting_Bt);

        // creates a button for opening database setting panel
        welcomePage_Bt = new JButton("Welcome Panel");
        welcomePage_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomePage_Bt.setSize(200, 30);
        welcomePage_Bt.setLocation(270, 220);
        welcomePage_Bt.setFocusable(false);
        welcomePage_Bt.addActionListener(this);
        c.add(welcomePage_Bt);

        // creates a button for closing the whole program
        closeProgram_Bt = new JButton("CLOSE PROGRAM");
        closeProgram_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        closeProgram_Bt.setSize(200, 30);
        closeProgram_Bt.setLocation(50, 220);
        closeProgram_Bt.setFocusable(false);
        closeProgram_Bt.addActionListener(this);
        c.add(closeProgram_Bt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == regUser_Bt) {
            // opens the registration panel for the user
            new UserRegAdmin_UI();
            dispose();
        } else if (e.getSource() == userList_Bt) {
            // opens a page with a list of all users and their data
            new UserList_UI();
            dispose();
        } else if (e.getSource() == userSetting_Bt) {
            // opens the room setting page
            new UserSetting_UI();
            dispose();
        } else if (e.getSource() == regRoom_Bt) {
            // opens the panel for admin to create a room
            new RoomReg_UI();
            dispose();
        } else if (e.getSource() == roomList_Bt) {
            // opens a page with a list of all users and their data
            new RoomListAdmin_UI();
            dispose();
        } else if (e.getSource() == roomSetting_Bt) {
            // opens the room setting page
            new RoomSetting_UI();
            dispose();
        } else if (e.getSource() == bookingPage_Bt) {
            // opens the room booking page
            new BookedRoomsStudent_UI(ADMIN_ID);
            dispose();
        } else if (e.getSource() == databaseSetting_Bt) {
            // opens the database setting page
            String warning = JOptionPane.showInputDialog(null,
                    "Enter Master Password", "Database access protection", JOptionPane.WARNING_MESSAGE);
            if (warning != null) {
                if (warning.equals(MASTER_PASSWORD)) {
                    new DatabaseSetting_UI();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "!ACCESS DENIED TO DATABASE SETTING!", "WRONG PASSWORD", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == welcomePage_Bt) {
            // opens the welcome page
            new Welcome_UI();
            dispose();
        } else if (e.getSource() == closeProgram_Bt) {
            System.exit(0);
        }
    }
}