package com.UI.user;

import com.logics.*;
import com.main.TeacherMenu_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoomListTeacher_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel datetime_Lb;
    // private JButton search_Bt;
    // private JTextField search_TF;
    // private JComboBox<String> filter_CmBx;
    private JButton goBack_Bt;
    private String userID;
    // private JTable table;
    // private JScrollPane sp;

    public RoomListTeacher_UI(String userID) {
        this.userID = userID;

        setTitle("Room List");
        // setBounds(300, 100, 1000, 600);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Creates a title for the input page
        title_Lb = new JLabel("Room List Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 24));
        title_Lb.setSize(400, 30);
        title_Lb.setLocation(400, 30);
        c.add(title_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 25);
        datetime_Lb.setLocation(770, 30);
        c.add(datetime_Lb);
        /*
         * // creates a search button, which will search the given parameter
         * search_Bt = new JButton("Search");
         * search_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
         * search_Bt.setSize(100, 22);
         * search_Bt.setLocation(100, 75);
         * search_Bt.setFocusable(false);
         * search_Bt.addActionListener(this);
         * c.add(search_Bt);
         */
        /*
         * // creates a field for the user to put the search term/text
         * search_TF = new JTextField();
         * search_TF.setFont(new Font("Arial", Font.PLAIN, 15));
         * search_TF.setSize(150, 22);
         * search_TF.setLocation(200, 75);
         * c.add(search_TF);
         */
        /*
         * // creates the filter option for the user
         * String filterOptions[] = {"Number", "Name", "Floor"};
         * filter_CmBx = new JComboBox<>(filterOptions);
         * filter_CmBx.addActionListener(this);
         * filter_CmBx.setFont(new Font("Arial", Font.PLAIN, 15));
         * filter_CmBx.setSize(100, 20);
         * filter_CmBx.setLocation(400, 75);
         * c.add(filter_CmBx);
         */
        // closes this specific input panel for user
        goBack_Bt = new JButton("Back");
        goBack_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        goBack_Bt.setSize(100, 22);
        goBack_Bt.setLocation(800, 500);
        goBack_Bt.setFocusable(false);
        goBack_Bt.addActionListener(this);
        c.add(goBack_Bt);

        String[][] rRowData = admin.getRoomFullTable();
        String[] rColumnData = admin.getHeaderRoomTable();

        JTable table = new JTable(rRowData, rColumnData);
        table.setBounds(50, 80, 880, 400);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(15);
        table.getColumnModel().getColumn(2).setPreferredWidth(45);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        table.getColumnModel().getColumn(4).setPreferredWidth(15);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);
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

        if (e.getSource() == goBack_Bt) {
            new TeacherMenu_UI(getuserID());
            dispose();
        }
    }
}