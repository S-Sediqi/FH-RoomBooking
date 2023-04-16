package com.UI.admin;

import com.logics.LogicAdministrator;
import com.main.AdminMenu_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoomSetting_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel datetime_Lb;
    private JLabel message_Lb;
    private JTextArea infoArea_TA;
    private JButton searchRoom_Bt;
    private JComboBox<String> roomList_CmBx;
    private JButton deleteRoom_Bt;
    private JButton closeRoom_Bt;
    private JButton openRoom_Bt;
    private JButton adminPage_Bt;
    private JButton closeProgram_Bt;

    public RoomSetting_UI() {

        setTitle("Room Setting");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        c = getContentPane();
        c.setLayout(null);

        // creates the title for the main Controlling panel
        title_Lb = new JLabel("Room Setting Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 18));
        title_Lb.setSize(200, 25);
        title_Lb.setLocation(100, 10);
        c.add(title_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 25);
        datetime_Lb.setLocation(490, 10);
        c.add(datetime_Lb);

        // shows the requested room data and the errors
        infoArea_TA = new JTextArea();
        infoArea_TA.setFont(new Font("Arial", Font.PLAIN, 15));
        infoArea_TA.setSize(300, 230);
        infoArea_TA.setLocation(320, 60);
        infoArea_TA.setLineWrap(true);
        infoArea_TA.setEditable(false);
        c.add(infoArea_TA);

        // creates a button for initiating/opening the registration panel
        searchRoom_Bt = new JButton("Search");
        searchRoom_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        searchRoom_Bt.setSize(110, 30);
        searchRoom_Bt.setLocation(50, 60);
        searchRoom_Bt.setFocusable(false);
        searchRoom_Bt.addActionListener(this);
        c.add(searchRoom_Bt);

        // creates a list of room numbers for search option
        String[] roomNum = admin.getRoomNumbers();
        roomList_CmBx = new JComboBox<>(roomNum);
        roomList_CmBx.addActionListener(this);
        roomList_CmBx.setFont(new Font("Arial", Font.PLAIN, 15));
        roomList_CmBx.setSize(70, 30);
        roomList_CmBx.setLocation(180, 60);
        c.add(roomList_CmBx);

        // creates a button for admin to see the user data - all user list
        deleteRoom_Bt = new JButton("Delete Room");
        deleteRoom_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteRoom_Bt.setSize(200, 30);
        deleteRoom_Bt.setLocation(50, 100);
        deleteRoom_Bt.setFocusable(false);
        deleteRoom_Bt.addActionListener(this);
        c.add(deleteRoom_Bt);

        // creates a button for admin to change room status to close (not available for
        // booking)
        closeRoom_Bt = new JButton("Close Room");
        closeRoom_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        closeRoom_Bt.setSize(200, 30);
        closeRoom_Bt.setLocation(50, 140);
        closeRoom_Bt.setFocusable(false);
        closeRoom_Bt.addActionListener(this);
        c.add(closeRoom_Bt);

        // creates a button for admin to change room status to open (available for
        // booking)
        openRoom_Bt = new JButton("Open Room");
        openRoom_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        openRoom_Bt.setSize(200, 30);
        openRoom_Bt.setLocation(50, 180);
        openRoom_Bt.setFocusable(false);
        openRoom_Bt.addActionListener(this);
        c.add(openRoom_Bt);

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
        message_Lb.setSize(350, 25);
        message_Lb.setLocation(50, 300);
        c.add(message_Lb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchRoom_Bt) {
            // takes the room number and shoes all info, if room exists
            String searchRoomNum = roomList_CmBx.getItemAt(roomList_CmBx.getSelectedIndex());
            LogicAdministrator admin = new LogicAdministrator();
            String rInfo = admin.getARoomData(searchRoomNum);
            message_Lb.setText("searching for room No. " + searchRoomNum);
            infoArea_TA.setText(rInfo);
            infoArea_TA.setEditable(false);
        } else if (e.getSource() == deleteRoom_Bt) {
            String deleteRoomNum = roomList_CmBx.getItemAt(roomList_CmBx.getSelectedIndex());

            // shows a warning panel and asks for approval before deleting the room
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to delete Room " + deleteRoomNum + " ?", "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (warning == JOptionPane.YES_OPTION) {
                LogicAdministrator admin = new LogicAdministrator();
                String msg = admin.deleteARoom(deleteRoomNum);
                message_Lb.setText(msg);
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("Room deletion CANCELED !");
            }
        } else if (e.getSource() == closeRoom_Bt) {
            String roomNo = roomList_CmBx.getItemAt(roomList_CmBx.getSelectedIndex());
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to CLOSE room " + roomNo + " for booking?", "Confirm closing",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (warning == JOptionPane.YES_OPTION) {
                LogicAdministrator admin = new LogicAdministrator();
                String msg = admin.closeARoom(roomNo);
                message_Lb.setText(msg);
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("closing Room for booking is CANCELED!");
            }
        } else if (e.getSource() == openRoom_Bt) {
            String roomNo = roomList_CmBx.getItemAt(roomList_CmBx.getSelectedIndex());
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to OPEN room " + roomNo + " for booking?", "Confirm opening",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (warning == JOptionPane.YES_OPTION) {
                LogicAdministrator admin = new LogicAdministrator();
                String msg = admin.openARoom(roomNo);
                message_Lb.setText(msg);
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("opening Room for booking is CANCELED!");
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
