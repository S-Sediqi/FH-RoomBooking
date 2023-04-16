package com.UI.user;

import com.logics.*;
import com.main.Login_UI;
import com.main.Welcome_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookedRoomsStudent_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel user_Lb;
    private JLabel datetime_Lb;
    private JButton deleteAccount_Bt;
    private JLabel message_Lb;
    private JButton exit_Bt;
    private String userID;

    public BookedRoomsStudent_UI(String userID) {
        this.userID = userID;

        setTitle("Students Room Schedule Panel");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Creates a title for the input page
        title_Lb = new JLabel("Welcome to Room Schedule Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 24));
        title_Lb.setSize(400, 30);
        title_Lb.setLocation(100, 30);
        c.add(title_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 25);
        datetime_Lb.setLocation(580, 40);
        c.add(datetime_Lb);

        user_Lb = new JLabel("USER: " + this.userID);
        user_Lb.setFont(new Font("Arial", Font.BOLD, 15));
        user_Lb.setSize(400, 30);
        user_Lb.setLocation(820, 15);
        c.add(user_Lb);

        deleteAccount_Bt = new JButton("DELETE ACCOUNT");
        deleteAccount_Bt.setFont(new Font("Arial", Font.BOLD, 16));
        deleteAccount_Bt.setSize(230, 30);
        deleteAccount_Bt.setLocation(730, 40);
        deleteAccount_Bt.setBackground(new Color(205, 133, 63));
        deleteAccount_Bt.setFocusable(false);
        deleteAccount_Bt.addActionListener(this);
        c.add(deleteAccount_Bt);

        // comments the selected button's action
        message_Lb = new JLabel("...");
        message_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        message_Lb.setSize(350, 25);
        message_Lb.setLocation(100, 500);
        c.add(message_Lb);

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
        String currentDate = admin.getCurrentDate();
        String[][] rRowData = admin.getBookingFullTable(currentDate);
        String[] rColumnData = admin.getHeaderBookingTable();

        JTable table = new JTable(rRowData, rColumnData);
        table.setBounds(100, 80, 700, 400);
        table.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(table);
        c.add(table);
        c.add(sp);

        setVisible(true);
    }

    // returns the user ID for room booking
    private String getuserID() {
        String answer = this.userID;
        return answer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteAccount_Bt) {
            String uID = getuserID();
            LogicAdministrator admin = new LogicAdministrator();
            int warning = JOptionPane.showConfirmDialog(null,
                    "Do you want to DELETE your account?" +
                            "\n" + "\nNOTE: if you DELETE your account you will not be able to Login anymore" +
                            "\n" + "    You will have to register again!" + "\n",
                    "Confirm Account Deletion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (warning == JOptionPane.YES_OPTION) {
                admin.deleteAUser(uID);
                new Welcome_UI();
                dispose();
            }
            if (warning == JOptionPane.NO_OPTION) {
                message_Lb.setText("User deletion is CANCELED!");
            }
        }
        if (e.getSource() == exit_Bt) {
            LoginCheck logCheck = new LoginCheck();
            logCheck.setLoginInfo();
            new Login_UI(logCheck.getLoginInfo());
            dispose();
        }
    }
}