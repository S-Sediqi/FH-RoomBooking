package com.UI.user;

import com.logics.*;
import com.main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserRegSelf_UI extends JFrame implements ActionListener {

    // Elements of the UI for User Registration
    private Container c;
    private JLabel title_Lb;
    private JLabel fName_Lb;
    private JTextField fName_TF;
    private JLabel lName_Lb;
    private JTextField lName_TF;
    private JLabel id_LB;
    private JTextField id_TF;
    private JLabel userType_Lb;
    private JRadioButton teacher_RB;
    private JRadioButton student_RB;
    private ButtonGroup userType_BG;
    private JLabel email_Lb;
    private JTextField email_TF;
    private JLabel pass_Lb;
    private JTextField pass_TF;
    private JCheckBox info_CB;
    private JTextArea info_TA;
    private JLabel message_Lb;
    private JButton signUp_Bt;
    private JButton reset_Bt;
    private JButton loginPage_Bt;
    private JButton goBack_Bt;

    // the constructor creates each elements of the User Registration UI
    public UserRegSelf_UI() {

        setTitle("Registration");
        // setBounds(300, 90, 900, 600);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Creates a title for the input page
        title_Lb = new JLabel("Please Enter Your Information");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 28));
        title_Lb.setSize(400, 30);
        title_Lb.setLocation(300, 30);
        c.add(title_Lb);

        // creates a label for the user first name input box
        fName_Lb = new JLabel("First Name");
        fName_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        fName_Lb.setSize(100, 20);
        fName_Lb.setLocation(100, 100);
        c.add(fName_Lb);

        // creates a field for user to enter the first name
        fName_TF = new JTextField();
        fName_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        fName_TF.setSize(190, 20);
        fName_TF.setLocation(200, 100);
        c.add(fName_TF);

        // creates a label for the user last name input box
        lName_Lb = new JLabel("Last Name");
        lName_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        lName_Lb.setSize(100, 20);
        lName_Lb.setLocation(100, 150);
        c.add(lName_Lb);

        // creates a field for user to enter the last name
        lName_TF = new JTextField();
        lName_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        lName_TF.setSize(190, 20);
        lName_TF.setLocation(200, 150);
        c.add(lName_TF);

        // creates a label for the user ID input box
        id_LB = new JLabel("ID No.");
        id_LB.setFont(new Font("Arial", Font.PLAIN, 20));
        id_LB.setSize(100, 20);
        id_LB.setLocation(100, 200);
        c.add(id_LB);

        // creates a field for the user to enter the ID/matriculation number
        id_TF = new JTextField();
        id_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        id_TF.setSize(150, 20);
        id_TF.setLocation(200, 200);
        c.add(id_TF);

        // creates a label for the user type/role area
        userType_Lb = new JLabel("User Type");
        userType_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        userType_Lb.setSize(100, 20);
        userType_Lb.setLocation(100, 250);
        c.add(userType_Lb);

        // creates a button for the user to register as a teacher
        teacher_RB = new JRadioButton("Teacher");
        teacher_RB.setFont(new Font("Arial", Font.PLAIN, 15));
        teacher_RB.setSelected(true);
        teacher_RB.setSize(90, 20);
        teacher_RB.setLocation(200, 250);
        c.add(teacher_RB);

        // creates a label for the user to register as a student
        student_RB = new JRadioButton("Student");
        student_RB.setFont(new Font("Arial", Font.PLAIN, 15));
        student_RB.setSelected(false);
        student_RB.setSize(80, 20);
        student_RB.setLocation(290, 250);
        c.add(student_RB);

        // creates group for the (teacher/student) buttons
        userType_BG = new ButtonGroup();
        userType_BG.add(teacher_RB);
        userType_BG.add(student_RB);

        // creates a label for the user Email input box
        email_Lb = new JLabel("Email");
        email_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        email_Lb.setSize(100, 20);
        email_Lb.setLocation(100, 300);
        c.add(email_Lb);

        // creates a field for the user to enter Email Address
        email_TF = new JTextField();
        email_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        email_TF.setSize(250, 20);
        email_TF.setLocation(200, 300);
        c.add(email_TF);

        // creates a label for the user Password input box
        pass_Lb = new JLabel("Password");
        pass_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        pass_Lb.setSize(100, 20);
        pass_Lb.setLocation(100, 350);
        c.add(pass_Lb);

        // creates a field for the user to enter Password
        pass_TF = new JTextField();
        pass_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        pass_TF.setSize(250, 20);
        pass_TF.setLocation(200, 350);
        c.add(pass_TF);

        // reminds the user to recheck the given data
        info_CB = new JCheckBox("All information is correct.");
        info_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        info_CB.setSize(250, 20);
        info_CB.setLocation(150, 400);
        c.add(info_CB);

        // sign up the user: the user data gets stored in a (.txt) file as binary data
        signUp_Bt = new JButton("Sign up");
        signUp_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        signUp_Bt.setSize(90, 20);
        signUp_Bt.setLocation(100, 450);
        signUp_Bt.setFocusable(false);
        signUp_Bt.addActionListener(this);
        c.add(signUp_Bt);

        // resets or clears all the boxes for user to put new data
        reset_Bt = new JButton("Reset");
        reset_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        reset_Bt.setSize(90, 20);
        reset_Bt.setLocation(200, 450);
        reset_Bt.addActionListener(this);
        c.add(reset_Bt);

        // closes this specific input panel for user
        goBack_Bt = new JButton("Welcome Panel");
        goBack_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        goBack_Bt.setSize(150, 20);
        goBack_Bt.setLocation(300, 450);
        goBack_Bt.setFocusable(false);
        goBack_Bt.addActionListener(this);
        c.add(goBack_Bt);

        // after the user registered, this button can open the login panel
        loginPage_Bt = new JButton("Login Panel");
        loginPage_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        loginPage_Bt.setSize(300, 20);
        loginPage_Bt.setLocation(500, 450);
        loginPage_Bt.setFocusable(false);
        loginPage_Bt.addActionListener(this);
        c.add(loginPage_Bt);

        // the area where user sees what data is stored
        info_TA = new JTextArea();
        info_TA.setFont(new Font("Arial", Font.PLAIN, 15));
        info_TA.setSize(300, 300);
        info_TA.setLocation(500, 100);
        info_TA.setLineWrap(true);
        info_TA.setEditable(false);
        c.add(info_TA);

        // warns if the check box in not checked
        message_Lb = new JLabel("");
        message_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        message_Lb.setSize(500, 25);
        message_Lb.setLocation(100, 500);
        c.add(message_Lb);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUp_Bt) {
            String job = "";
            String messages = "";
            // the terms should be first accepted by the user
            if (info_CB.isSelected()) {
                message_Lb.setText("");
                // shows if the user is a teacher or a student
                if (teacher_RB.isSelected()) {
                    job = "Teacher";
                }
                if (student_RB.isSelected()) {
                    job = "Student";
                }
                // stores each entered data from text fields into its corresponding String
                String id = id_TF.getText();
                String fName = fName_TF.getText();
                String lName = lName_TF.getText();
                String email = email_TF.getText();
                String pass = pass_TF.getText();

                // stores all input data in one String to show in (infoBox)
                String infoOutput = "ID : " + id + "\n" +
                        "First Name : " + fName + "\n" +
                        "Last Name :  " + lName + "\n" +
                        "Occupation : " + job + "\n" +
                        "Email :  " + email + "\n" +
                        "Pass :  " + pass + "\n";

                // A constructor of the class (User_Input_Check) is created
                // The method (checkUserInputData) is used by passing all input data into it
                // All input data are checked by the method:
                // if input field is empty
                // if input data type is correct (only integer, only letters, mixed with
                // symbols)
                // if forbidden data (space or comma) is used
                // Then, if all conditions are correct, the method stores the data into User
                // database
                // else does not store the data and corresponds with a specified error message
                LogicAdministrator admin = new LogicAdministrator();
                messages = admin.checkAndAddToUserDB(id, job, fName, lName, email, pass);
                info_TA.setText(infoOutput + messages);
                info_TA.setEditable(false);
            }
            if (!info_CB.isSelected()) {
                info_TA.setText("");
                message_Lb.setText("Please check the box (All information is correct).");
            }
        }

        // it will reset and clear all the data from their fields
        else if (e.getSource() == reset_Bt) {
            String def = "";
            fName_TF.setText(def);
            lName_TF.setText(def);
            id_TF.setText(def);
            email_TF.setText(def);
            pass_TF.setText(def);
            message_Lb.setText(def);
            info_TA.setText(def);
            info_CB.setSelected(false);
        } else if (e.getSource() == loginPage_Bt) {
            LoginCheck logCheck = new LoginCheck();
            logCheck.setLoginInfo();
            new Login_UI(logCheck.getLoginInfo());
            dispose();
        }
        // gets the user out of the panel
        else if (e.getSource() == goBack_Bt) {
            new Welcome_UI();
            dispose();
        }
    }
}