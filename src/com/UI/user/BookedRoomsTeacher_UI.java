package com.UI.user;

import com.logics.*;
import com.main.TeacherMenu_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookedRoomsTeacher_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel user_Lb;
    private JLabel datetime_Lb;
    private JLabel reservationDate_Lb;
    private JButton reserve_Bt;
    private JButton cancelReserve_Bt;
    private JComboBox<String> time_CmBx;
    private JComboBox<String> roomNum_CmBx;
    private JLabel message_Lb;
    private JButton exit_Bt;
    private String date;
    private String userID;

    public BookedRoomsTeacher_UI(String date, String userID) {
        this.date = date;
        this.userID = userID;

        setTitle("Teachers Booking Panel");
        // setBounds(300, 90, 1000, 600);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Creates a title for the input page
        title_Lb = new JLabel("Welcome to Room Booking Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 24));
        title_Lb.setSize(400, 30);
        title_Lb.setLocation(100, 30);
        c.add(title_Lb);

        user_Lb = new JLabel("USER: " + this.userID);
        user_Lb.setFont(new Font("Arial", Font.BOLD, 15));
        user_Lb.setSize(400, 30);
        user_Lb.setLocation(820, 30);
        c.add(user_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 30);
        datetime_Lb.setLocation(650, 30);
        c.add(datetime_Lb);

        reservationDate_Lb = new JLabel("For: " + this.date);
        reservationDate_Lb.setFont(new Font("Arial", Font.BOLD, 15));
        reservationDate_Lb.setSize(400, 30);
        reservationDate_Lb.setLocation(830, 80);
        c.add(reservationDate_Lb);

        // creates a list of time sets in which a room can be reserved/booked
        String[] timeOptions = { "08:00", "09:45", "12:15", "14:00", "15:45", "17:30" };
        time_CmBx = new JComboBox<>(timeOptions);
        time_CmBx.addActionListener(this);
        time_CmBx.setFont(new Font("Arial", Font.PLAIN, 15));
        time_CmBx.setSize(100, 25);
        time_CmBx.setLocation(830, 120);
        c.add(time_CmBx);

        // creates a list of time sets in which a room can be reserved/booked

        String[] roomOptions = admin.getRoomNumbers();
        roomNum_CmBx = new JComboBox<>(roomOptions);
        roomNum_CmBx.addActionListener(this);
        roomNum_CmBx.setFont(new Font("Arial", Font.PLAIN, 15));
        roomNum_CmBx.setSize(100, 25);
        roomNum_CmBx.setLocation(830, 160);
        c.add(roomNum_CmBx);

        // creates a room Booking/reservation button, which will take the given
        // parameters
        // and if room is available reserves the room for the user
        reserve_Bt = new JButton("Reserve");
        reserve_Bt.setFont(new Font("Arial", Font.BOLD, 15));
        reserve_Bt.setSize(100, 24);
        reserve_Bt.setLocation(830, 200);
        reserve_Bt.setBackground(new Color(60, 179, 113));
        reserve_Bt.setFocusable(false);
        reserve_Bt.addActionListener(this);
        c.add(reserve_Bt);

        cancelReserve_Bt = new JButton("Cancel");
        cancelReserve_Bt.setFont(new Font("Arial", Font.BOLD, 15));
        cancelReserve_Bt.setSize(100, 24);
        cancelReserve_Bt.setLocation(830, 240);
        cancelReserve_Bt.setBackground(new Color(205, 92, 92));
        cancelReserve_Bt.setFocusable(false);
        cancelReserve_Bt.addActionListener(this);
        c.add(cancelReserve_Bt);

        // closes this specific input panel for user
        exit_Bt = new JButton("Exit");
        exit_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        exit_Bt.setSize(100, 20);
        exit_Bt.setLocation(800, 500);
        exit_Bt.setFocusable(false);
        exit_Bt.addActionListener(this);
        c.add(exit_Bt);

        // the room numbers and the booking sets are stored in 2D Arrays and presented
        // in the JTable
        // String currentDate = admin.getCurrentDate();
        String[][] rRowData = admin.getBookingFullTable(this.date);
        String[] rColumnData = admin.getHeaderBookingTable();

        JTable table = new JTable(rRowData, rColumnData);
        table.setBounds(100, 80, 700, 400);
        table.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(table);
        c.add(table);
        c.add(sp);

        // warns if the check box in not checked
        message_Lb = new JLabel("waiting...");
        message_Lb.setFont(new Font("Arial", Font.ITALIC, 16));
        message_Lb.setSize(500, 25);
        message_Lb.setLocation(100, 500);
        c.add(message_Lb);

        setVisible(true);
    }
    /*
     * protected String[][] updateRow(String date) {
     * LogicAdministrator admin = new LogicAdministrator();
     * String[][] rRowData = admin.getBookingFullTable(date);
     * return rRowData;
     * }
     * protected String[] updateColumn() {
     * LogicAdministrator admin = new LogicAdministrator();
     * String[] rColumnData = admin.getHeaderBookingTable();
     * return rColumnData;
     * }
     */

    // returns the user ID for room booking
    private String getuserID() {
        String answer = this.userID;
        return answer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reserve_Bt) {
            LogicAdministrator admin = new LogicAdministrator();

            String userId = getuserID();
            String roomNo = roomNum_CmBx.getItemAt(roomNum_CmBx.getSelectedIndex());
            String date = this.date;
            String timeString = time_CmBx.getItemAt(time_CmBx.getSelectedIndex());
            int bookingTimeIndex = admin.getTimeIndex(timeString);

            String msg = admin.bookARoom(userId, roomNo, date, bookingTimeIndex);
            message_Lb.setText(msg);
        }

        if (e.getSource() == cancelReserve_Bt) {
            LogicAdministrator admin = new LogicAdministrator();

            String userId = getuserID();
            String roomNo = roomNum_CmBx.getItemAt(roomNum_CmBx.getSelectedIndex());
            String date = this.date;
            String timeString = time_CmBx.getItemAt(time_CmBx.getSelectedIndex());
            int cancelingTimeIndex = admin.getTimeIndex(timeString);

            String msg = admin.cancelABooking(userId, roomNo, date, cancelingTimeIndex);
            message_Lb.setText(msg);
        }

        if (e.getSource() == exit_Bt) {
            new TeacherMenu_UI(getuserID());
            /*
             * LoginCheck logCheck = new LoginCheck();
             * logCheck.setLoginInfo();
             * new Login_UI(logCheck.getLoginInfo());
             */
            dispose();
        }
    }
}