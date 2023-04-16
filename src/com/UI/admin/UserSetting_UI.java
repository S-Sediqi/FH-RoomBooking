package com.UI.admin;

import com.logics.LogicAdministrator;
import com.main.AdminMenu_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserSetting_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel datetime_Lb;
    private JLabel message_Lb;
    private JLabel id_Lb;
    private JTextArea infoArea_TA;
    private JButton searchUser_Bt;
    private JTextField userID_TF;
    private JButton deleteUser_Bt;
    private JButton adminPage_Bt;
    private JButton closeProgram_Bt;

    public UserSetting_UI() {

        setTitle("User Setting");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        c = getContentPane();
        c.setLayout(null);

        // creates the title for the main Controlling panel
        title_Lb = new JLabel("User Setting Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        title_Lb.setSize(200, 25);
        title_Lb.setLocation(70, 10);
        c.add(title_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 25);
        datetime_Lb.setLocation(490, 10);
        c.add(datetime_Lb);

        // creates a label for the user ID input box
        id_Lb = new JLabel("ID: ");
        id_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        id_Lb.setSize(40, 20);
        id_Lb.setLocation(50, 60);
        c.add(id_Lb);

        // creates a list of room numbers for search option
        userID_TF = new JTextField();
        userID_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        userID_TF.setSize(150, 20);
        userID_TF.setLocation(100, 60);
        c.add(userID_TF);

        // shows the requested room data and the errors
        infoArea_TA = new JTextArea();
        infoArea_TA.setFont(new Font("Arial", Font.PLAIN, 15));
        infoArea_TA.setSize(300, 230);
        infoArea_TA.setLocation(320, 60);
        infoArea_TA.setLineWrap(true);
        infoArea_TA.setEditable(false);
        c.add(infoArea_TA);

        // creates a button for initiating/opening the registration panel
        searchUser_Bt = new JButton("Search User");
        searchUser_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        searchUser_Bt.setSize(200, 30);
        searchUser_Bt.setLocation(50, 100);
        searchUser_Bt.setFocusable(false);
        searchUser_Bt.addActionListener(this);
        c.add(searchUser_Bt);

        // creates a button for admin to see the user data - all user list
        deleteUser_Bt = new JButton("Delete User");
        deleteUser_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteUser_Bt.setSize(200, 30);
        deleteUser_Bt.setLocation(50, 140);
        deleteUser_Bt.setFocusable(false);
        deleteUser_Bt.addActionListener(this);
        c.add(deleteUser_Bt);

        // creates a button for closing the whole program
        adminPage_Bt = new JButton("Back");
        adminPage_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        adminPage_Bt.setSize(200, 30);
        adminPage_Bt.setLocation(50, 220);
        adminPage_Bt.setFocusable(false);
        adminPage_Bt.addActionListener(this);
        c.add(adminPage_Bt);

        // creates a button for closing the whole program
        closeProgram_Bt = new JButton("CLOSE PROGRAM");
        closeProgram_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        closeProgram_Bt.setSize(200, 30);
        closeProgram_Bt.setLocation(50, 260);
        closeProgram_Bt.setFocusable(false);
        closeProgram_Bt.addActionListener(this);
        c.add(closeProgram_Bt);

        // comments the selected button's action
        message_Lb = new JLabel("waiting...");
        message_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        message_Lb.setSize(260, 25);
        message_Lb.setLocation(50, 300);
        c.add(message_Lb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchUser_Bt) {
            // takes the room number and shoes all info, if room exists
            String searchUserId = userID_TF.getText();
            LogicAdministrator admin = new LogicAdministrator();
            String rInfo = admin.getAUserData(searchUserId);
            message_Lb.setText("searching for user with ID " + searchUserId);
            infoArea_TA.setText(rInfo);
            infoArea_TA.setEditable(false);
        } else if (e.getSource() == deleteUser_Bt) {
            String deleteUserId = userID_TF.getText();

            // shows a warning panel and asks for approval before deleting the user
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to delete user " + deleteUserId + " ?", "Confirm deletion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (warning == JOptionPane.YES_OPTION) {
                LogicAdministrator admin = new LogicAdministrator();
                String msg = admin.deleteAUser(deleteUserId);
                message_Lb.setText(msg);
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("User deletion CANCELED !");
            }
        } else if (e.getSource() == adminPage_Bt) {
            // opens the admin control panel
            new AdminMenu_UI();
            dispose();
        } else if (e.getSource() == closeProgram_Bt) {
            System.exit(0);
        }
    }

}