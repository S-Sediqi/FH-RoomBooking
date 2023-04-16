package com.main;

import com.logics.LoginCheck;
import com.UI.user.UserRegSelf_UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Welcome_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JButton login_Bt;
    private JButton register_Bt;
    private JButton closeProgram_Bt;

    public Welcome_UI() {

        setTitle("Welcome");
        setSize(400, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        c = getContentPane();
        c.setLayout(null);

        // creates the title for the main Controlling panel
        title_Lb = new JLabel("Welcome to FH Room Booking Panel");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 18));
        title_Lb.setSize(300, 25);
        title_Lb.setLocation(40, 20);
        c.add(title_Lb);

        // creates a button for initiating/opening login panel
        login_Bt = new JButton("Login");
        login_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        login_Bt.setSize(120, 30);
        login_Bt.setLocation(50, 80);
        login_Bt.setFocusable(false);
        login_Bt.addActionListener(this);
        c.add(login_Bt);

        // creates a button for initiating/opening the registration panel
        register_Bt = new JButton("Register");
        register_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        register_Bt.setSize(120, 30);
        register_Bt.setLocation(200, 80);
        register_Bt.setFocusable(false);
        register_Bt.addActionListener(this);
        c.add(register_Bt);

        // creates a button for closing the whole program
        closeProgram_Bt = new JButton("CLOSE PROGRAM");
        closeProgram_Bt.setFont(new Font("Arial", Font.PLAIN, 18));
        closeProgram_Bt.setSize(270, 30);
        closeProgram_Bt.setLocation(50, 130);
        closeProgram_Bt.setFocusable(false);
        closeProgram_Bt.addActionListener(this);
        c.add(closeProgram_Bt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == login_Bt) {
            // opens the login panel for user
            LoginCheck logCheck = new LoginCheck();
            logCheck.setLoginInfo();
            new Login_UI(logCheck.getLoginInfo());
            dispose();
        } else if (e.getSource() == register_Bt) {
            // opens the registration panel for user
            new UserRegSelf_UI();
            dispose();
        } else if (e.getSource() == closeProgram_Bt) {
            // closes the program
            System.exit(0);
        }
    }
}
