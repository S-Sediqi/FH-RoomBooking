package com.main;

import com.UI.user.*;
import com.logics.LogicAdministrator;
import com.logics.LoginCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TeacherMenu_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel datetime_Lb;
    private JButton roomList_Bt;
    private JButton bookedPage_Bt;
    private JComboBox<String> date_CmBx;
    private JButton goBack_Bt;
    private JButton closeProgram_Bt;
    private String userID;

    public TeacherMenu_UI(String userID) {
        this.userID = userID;

        setTitle("Teachers Panel");
        setSize(400, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        c = getContentPane();
        c.setLayout(null);

        // creates the title for the main Controlling panel
        title_Lb = new JLabel("Teachers Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 18));
        title_Lb.setSize(200, 25);
        title_Lb.setLocation(40, 20);
        c.add(title_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 25);
        datetime_Lb.setLocation(220, 20);
        c.add(datetime_Lb);

        // creates a button for initiating/opening login panel
        roomList_Bt = new JButton("Rooms");
        roomList_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        roomList_Bt.setSize(100, 30);
        roomList_Bt.setLocation(20, 80);
        roomList_Bt.setFocusable(false);
        roomList_Bt.addActionListener(this);
        c.add(roomList_Bt);

        // creates a button for initiating/opening the registration panel
        bookedPage_Bt = new JButton("Booking");
        bookedPage_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        bookedPage_Bt.setSize(100, 30);
        bookedPage_Bt.setLocation(140, 80);
        bookedPage_Bt.setFocusable(false);
        bookedPage_Bt.addActionListener(this);
        c.add(bookedPage_Bt);

        // gets all the dates list from Booking Database as a String Array
        String[] dateOptions = admin.getDateList();
        date_CmBx = new JComboBox<>(dateOptions);
        date_CmBx.addActionListener(this);
        date_CmBx.setFont(new Font("Arial", Font.PLAIN, 15));
        date_CmBx.setSize(100, 30);
        date_CmBx.setLocation(260, 80);
        c.add(date_CmBx);

        // creates a button for closing the whole program
        goBack_Bt = new JButton("Back");
        goBack_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        goBack_Bt.setSize(100, 30);
        goBack_Bt.setLocation(260, 130);
        goBack_Bt.setFocusable(false);
        goBack_Bt.addActionListener(this);
        c.add(goBack_Bt);

        // creates a button for closing the whole program
        closeProgram_Bt = new JButton("CLOSE PROGRAM");
        closeProgram_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        closeProgram_Bt.setSize(220, 30);
        closeProgram_Bt.setLocation(20, 130);
        closeProgram_Bt.setFocusable(false);
        closeProgram_Bt.addActionListener(this);
        c.add(closeProgram_Bt);
    }

    // returns the user ID for room booking
    private String getuserID() {
        String answer = this.userID;
        return answer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == roomList_Bt) {
            new RoomListTeacher_UI(getuserID());
            dispose();
        } else if (e.getSource() == goBack_Bt) {
            LoginCheck logCheck = new LoginCheck();
            logCheck.setLoginInfo();
            new Login_UI(logCheck.getLoginInfo());
            dispose();
        } else if (e.getSource() == bookedPage_Bt) {
            // opens the registration panel for the user
            String date = date_CmBx.getItemAt(date_CmBx.getSelectedIndex());
            new BookedRoomsTeacher_UI(date, getuserID());
            dispose();
        } else if (e.getSource() == closeProgram_Bt) {
            System.exit(0);
        }
    }
}