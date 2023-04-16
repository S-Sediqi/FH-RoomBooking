package com.UI.admin;

import com.main.AdminMenu_UI;
import com.logics.LogicAdministrator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DatabaseSetting_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel datetime_Lb;
    private JLabel message_Lb;
    private JTextArea info_TA;
    private JButton databaseList_Bt;
    private JButton databasePaths_Bt;
    private JButton createPathStorage_Bt;
    private JComboBox<String> pathOptions_CmBx;
    private JButton createUserDB_Bt;
    private JButton createRoomDB_Bt;
    private JButton createBookingDB_Bt;
    private JButton goBack_Bt;
    private JButton closeProgram_Bt;

    public DatabaseSetting_UI() {

        setTitle("Database Setting");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        c = getContentPane();
        c.setLayout(null);

        // creates the title for the main Controlling panel
        title_Lb = new JLabel("Database Setting Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        title_Lb.setSize(300, 25);
        title_Lb.setLocation(100, 15);
        c.add(title_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 25);
        datetime_Lb.setLocation(590, 15);
        c.add(datetime_Lb);

        // shows the requested room data and the errors
        info_TA = new JTextArea();
        info_TA.setFont(new Font("Arial", Font.PLAIN, 15));
        info_TA.setSize(300, 230);
        info_TA.setLocation(440, 60);
        info_TA.setLineWrap(true);
        info_TA.setEditable(false);
        c.add(info_TA);

        // creates a button for initiating/opening the registration panel
        databaseList_Bt = new JButton("Database List");
        databaseList_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        databaseList_Bt.setSize(200, 30);
        databaseList_Bt.setLocation(20, 60);
        databaseList_Bt.setFocusable(false);
        databaseList_Bt.addActionListener(this);
        c.add(databaseList_Bt);

        // creates a button for admin to see the user data - all user list
        databasePaths_Bt = new JButton("Path Storages");
        databasePaths_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        databasePaths_Bt.setSize(200, 30);
        databasePaths_Bt.setLocation(230, 60);
        databasePaths_Bt.setFocusable(false);
        databasePaths_Bt.addActionListener(this);
        c.add(databasePaths_Bt);

        createPathStorage_Bt = new JButton("Create Path Storage Files");
        createPathStorage_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        createPathStorage_Bt.setSize(300, 30);
        createPathStorage_Bt.setLocation(20, 100);
        createPathStorage_Bt.setFocusable(false);
        createPathStorage_Bt.addActionListener(this);
        c.add(createPathStorage_Bt);

        String[] pathfor = { "user", "room", "booking" };
        pathOptions_CmBx = new JComboBox<>(pathfor);
        pathOptions_CmBx.addActionListener(this);
        pathOptions_CmBx.setFont(new Font("Arial", Font.PLAIN, 18));
        pathOptions_CmBx.setSize(100, 30);
        pathOptions_CmBx.setLocation(330, 100);
        c.add(pathOptions_CmBx);

        createUserDB_Bt = new JButton("Create Empty User Database File");
        createUserDB_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        createUserDB_Bt.setSize(410, 30);
        createUserDB_Bt.setLocation(20, 140);
        createUserDB_Bt.setFocusable(false);
        createUserDB_Bt.addActionListener(this);
        c.add(createUserDB_Bt);

        createRoomDB_Bt = new JButton("Create Empty Room Database File");
        createRoomDB_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        createRoomDB_Bt.setSize(410, 30);
        createRoomDB_Bt.setLocation(20, 180);
        createRoomDB_Bt.setFocusable(false);
        createRoomDB_Bt.addActionListener(this);
        c.add(createRoomDB_Bt);

        createBookingDB_Bt = new JButton("Create Empty Booking Database File");
        createBookingDB_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        createBookingDB_Bt.setSize(410, 30);
        createBookingDB_Bt.setLocation(20, 220);
        createBookingDB_Bt.setFocusable(false);
        createBookingDB_Bt.addActionListener(this);
        c.add(createBookingDB_Bt);

        // creates a button for closing the whole program
        goBack_Bt = new JButton("Back");
        goBack_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        goBack_Bt.setSize(200, 30);
        goBack_Bt.setLocation(230, 260);
        goBack_Bt.setFocusable(false);
        goBack_Bt.addActionListener(this);
        c.add(goBack_Bt);

        // creates a button for closing the whole program
        closeProgram_Bt = new JButton("CLOSE PROGRAM");
        closeProgram_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        closeProgram_Bt.setSize(200, 30);
        closeProgram_Bt.setLocation(20, 260);
        closeProgram_Bt.setFocusable(false);
        closeProgram_Bt.addActionListener(this);
        c.add(closeProgram_Bt);

        // comments the selected button's action
        message_Lb = new JLabel("waiting...");
        message_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        message_Lb.setSize(350, 25);
        message_Lb.setLocation(30, 300);
        c.add(message_Lb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LogicAdministrator admin = new LogicAdministrator();
        if (e.getSource() == databaseList_Bt) {
            String dbList = admin.getDatabaseNames();
            info_TA.setText(dbList);
            info_TA.setEditable(false);
        } else if (e.getSource() == databasePaths_Bt) {
            String dbPaths = admin.getPathStorageNames();
            info_TA.setText(dbPaths);
            info_TA.setEditable(false);
        } else if (e.getSource() == createPathStorage_Bt) {
            String pathfor = pathOptions_CmBx.getItemAt(pathOptions_CmBx.getSelectedIndex());
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to create a file to store " + pathfor + " database paths?" +
                            "\n" + "\nNOTE: create new path storage file ONLY if old files are" +
                            "(DELETED or RELOCATED) and there is no file in the folder!" + "\n",
                    "Confirm path creation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (warning == JOptionPane.YES_OPTION) {
                String msg = admin.createPathStorageFile(pathfor);
                message_Lb.setText(msg);
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("creating new path storage is CANCELED!");
            }
        } else if (e.getSource() == createUserDB_Bt) {
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to create an empty file to store USERS data?" +
                            "\n" + "\nNOTE: create new user database file ONLY if old files are " +
                            "(DELETED or RELOCATED) and there is no user file in the folder!" + "\n",
                    "Confirm file creation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (warning == JOptionPane.YES_OPTION) {
                String msg = admin.createEmptyUserDatabase();
                message_Lb.setText(msg);
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("creating new USER database is CANCELED!");
            }
        } else if (e.getSource() == createRoomDB_Bt) {
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to create an empty file to store ROOM data?" +
                            "\n" + "\nNOTE: create new room database file ONLY if old files are " +
                            "(DELETED or RELOCATED) and there is no room file in the folder!" + "\n",
                    "Confirm file creation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (warning == JOptionPane.YES_OPTION) {
                String msg = admin.createEmptyRoomDatabase();
                message_Lb.setText(msg);
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("creating new ROOM database is CANCELED!");
            }
        } else if (e.getSource() == createBookingDB_Bt) {
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to create a new BOOKING file? (with room No.)" +
                            "\n" + "\nNOTE: create new booking database file ONLY if old files are " +
                            "(DELETED or RELOCATED) and there is no booking file in the folder!" + "\n",
                    "Confirm file creation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (warning == JOptionPane.YES_OPTION) {
                String msg = admin.createBookingDatabaseWithRooms();
                message_Lb.setText(msg);
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("creating new BOOKING database is CANCELED!");
            }
        } else if (e.getSource() == goBack_Bt) {
            // opens the admin control panel
            new AdminMenu_UI();
            dispose();
        } else if (e.getSource() == closeProgram_Bt) {
            System.exit(0);
        }
    }

}
