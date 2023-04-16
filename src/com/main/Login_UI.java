package com.main;

import com.UI.user.BookedRoomsStudent_UI;
import com.UI.user.UserRegSelf_UI;
import com.logics.LogicAdministrator;
import com.logics.LoginCheck;
import com.validation.UserDataValidation;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class Login_UI extends JFrame implements ActionListener {

    private Container c;
    private JLabel title_Lb;
    private JLabel userID_Lb;
    private JLabel userPass_Lb;
    private JTextField userID_TF;
    private JPasswordField userPass_PF;
    private JRadioButton admin_RBt;
    private JRadioButton teacher_RBt;
    private JRadioButton student_RBt;
    private ButtonGroup userType_BG;
    private JLabel alert_Lb;
    private JButton login_Bt;
    private JButton reset_Bt;
    private JButton registration_Bt;
    private JButton closeProgram_Bt;

    HashMap<String, String> loginInfo = new HashMap<String, String>();

    public Login_UI(HashMap<String, String> userLogInfo) {

        loginInfo = userLogInfo;

        setTitle("Login Panel");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Creates a title for the login page
        title_Lb = new JLabel("Please Enter Your Information");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 18));
        title_Lb.setSize(250, 25);
        title_Lb.setLocation(80, 10);
        c.add(title_Lb);

        // creates a label for the user name
        userID_Lb = new JLabel("User ID: ");
        userID_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        userID_Lb.setSize(100, 20);
        userID_Lb.setLocation(50, 50);
        c.add(userID_Lb);

        // creates a field for user to enter (username)
        userID_TF = new JTextField();
        userID_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        userID_TF.setSize(190, 20);
        userID_TF.setLocation(150, 50);
        c.add(userID_TF);

        // creates a label for the user Password
        userPass_Lb = new JLabel("Password: ");
        userPass_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        userPass_Lb.setSize(100, 20);
        userPass_Lb.setLocation(50, 100);
        c.add(userPass_Lb);

        // creates a field for user to enter Password
        userPass_PF = new JPasswordField();
        userPass_PF.setFont(new Font("Arial", Font.PLAIN, 15));
        userPass_PF.setSize(190, 20);
        userPass_PF.setLocation(150, 100);
        c.add(userPass_PF);

        // creates a label for the user to register as a student
        admin_RBt = new JRadioButton("Admin");
        admin_RBt.setFont(new Font("Arial", Font.PLAIN, 15));
        admin_RBt.setSelected(false);
        admin_RBt.setSize(80, 20);
        admin_RBt.setLocation(50, 140);
        c.add(admin_RBt);

        // creates a button for the user to register as a teacher
        teacher_RBt = new JRadioButton("Teacher");
        teacher_RBt.setFont(new Font("Arial", Font.PLAIN, 15));
        teacher_RBt.setSelected(true);
        teacher_RBt.setSize(80, 20);
        teacher_RBt.setLocation(150, 140);
        c.add(teacher_RBt);

        // creates a label for the user to register as a student
        student_RBt = new JRadioButton("Student");
        student_RBt.setFont(new Font("Arial", Font.PLAIN, 15));
        student_RBt.setSelected(false);
        student_RBt.setSize(80, 20);
        student_RBt.setLocation(250, 140);
        c.add(student_RBt);

        // creates group for the (teacher/student) buttons
        userType_BG = new ButtonGroup();
        userType_BG.add(admin_RBt);
        userType_BG.add(teacher_RBt);
        userType_BG.add(student_RBt);

        alert_Lb = new JLabel("waiting...");
        alert_Lb.setFont(new Font("Arial", Font.ITALIC, 15));
        alert_Lb.setSize(250, 25);
        alert_Lb.setLocation(50, 300);
        c.add(alert_Lb);

        // creates a button for login command input
        login_Bt = new JButton("Login");
        login_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        login_Bt.setSize(100, 25);
        login_Bt.setLocation(90, 180);
        login_Bt.setFocusable(false);
        login_Bt.addActionListener(this);
        c.add(login_Bt);

        // cancels the login process and exit the panel
        reset_Bt = new JButton("Reset");
        reset_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        reset_Bt.setSize(100, 25);
        reset_Bt.setLocation(210, 180);
        reset_Bt.setFocusable(false);
        reset_Bt.addActionListener(this);
        c.add(reset_Bt);

        // after the login a validated this button allows the user to Room Booking page
        registration_Bt = new JButton("Registration");
        registration_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        registration_Bt.setSize(220, 25);
        registration_Bt.setLocation(90, 220);
        registration_Bt.setFocusable(false);
        registration_Bt.addActionListener(this);
        c.add(registration_Bt);

        // after the login a validated this button allows the user to Room Booking page
        closeProgram_Bt = new JButton("CLOSE PROGRAM");
        closeProgram_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        closeProgram_Bt.setSize(220, 25);
        closeProgram_Bt.setLocation(90, 260);
        closeProgram_Bt.setFocusable(false);
        closeProgram_Bt.addActionListener(this);
        c.add(closeProgram_Bt);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login_Bt) {

            Boolean b = false;
            LoginCheck logCheck = new LoginCheck();
            logCheck.setLoginInfo();
            String uID = userID_TF.getText();
            String uPAss = String.valueOf(userPass_PF.getPassword());
            LogicAdministrator admin = new LogicAdministrator();
            UserDataValidation uDataVal = new UserDataValidation();
            String uJob = admin.getUserJob(uID);

            if (admin_RBt.isSelected()) {
                // if admin is selected then it checks if master ID and Password is correct
                if (uDataVal.isUserAdmin(uID, uPAss)) {
                    new AdminMenu_UI();
                    dispose();
                } else {
                    alert_Lb.setText("User is not Admin !");
                }
            } else {
                // checks if the user id and password maches
                if (loginInfo.containsKey(uID)) {
                    if (loginInfo.get(uID).equals(uPAss)) {
                        b = true;
                    } else {
                        alert_Lb.setText("User ID or Password is Wrong !");
                    }
                } else {
                    alert_Lb.setText("User ID or Password is Wrong !");
                }
                // checks if the the user type is correct (Teacher or Student)
                if (b) {
                    if (teacher_RBt.isSelected() && uJob.equals("Teacher")) {
                        new TeacherMenu_UI(uID);
                        dispose();
                    } else if (student_RBt.isSelected() && uJob.equals("Student")) {
                        new BookedRoomsStudent_UI(uID);
                        dispose();
                    } else {
                        alert_Lb.setText("Wrong user type selected !");
                    }
                }
            }
        }

        // clears the user Id and password fields
        if (e.getSource() == reset_Bt) {
            userID_TF.setText("");
            userPass_PF.setText("");
        }
        // opens the registration panel and closes the login panel
        if (e.getSource() == registration_Bt) {
            new UserRegSelf_UI();
            dispose();
        }
        // closes the program
        if (e.getSource() == closeProgram_Bt) {
            System.exit(0);
        }
    }
}
