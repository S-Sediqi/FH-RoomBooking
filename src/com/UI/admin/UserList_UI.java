package com.UI.admin;

import com.logics.*;
import com.main.AdminMenu_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserList_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel datetime_Lb;
    private JButton goBack_Bt;

    public UserList_UI() {
        setTitle("User List");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Creates a title for the input page
        title_Lb = new JLabel("User List Panel");
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

        // closes this specific input panel for user
        goBack_Bt = new JButton("Back");
        goBack_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        goBack_Bt.setSize(100, 22);
        goBack_Bt.setLocation(800, 500);
        goBack_Bt.setFocusable(false);
        goBack_Bt.addActionListener(this);
        c.add(goBack_Bt);

        String[][] rRowData = admin.getUserFullTable();
        String[] rColumnData = admin.getHeaderUserTable();

        JTable table = new JTable(rRowData, rColumnData);
        table.setBounds(50, 80, 880, 400);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        JScrollPane sp = new JScrollPane(table);
        c.add(table);
        c.add(sp);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBack_Bt) {
            new AdminMenu_UI();
            dispose();
        }
    }
}
