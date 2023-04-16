package com.validation;

import java.util.ArrayList;

import com.logics.LogicAdministrator;

public class UserDataValidation {
    
    private final static String ADMIN_ID = "33333333";
    private final static String ADMIN_PASS = "123";

    public UserDataValidation () {
    }

    // The method gets the room number as input
    // it runs the readCSV_Room() then calls the room number list (rNo_List)
    // then compares the input to existing room numbers, if it exist or not
    public boolean doesUserExist(String userID) {
        boolean b = false;
        ArrayList<String> uID = new ArrayList<>();

        LogicAdministrator admin = new LogicAdministrator();
        uID = admin.getUserDataList(admin.USER_ID_INDEX);
        for (int i = 1; i < uID.size(); i++) {
            //int a = Integer.parseInt(uID.get(i));
            if (userID.equals(uID.get(i))) {
                b = true;
            }
        }
        return b;
    }
    
    // checks if the user id input has correct datatype
    // ID: only numbers are allowed (no letter, no space, no symbol) 
    public boolean isID_Type_Valid (String userID) {
        boolean b = false;
        int count = 0;
        for (int i = 0; i < userID.length(); i++) {
            // checks if the user ID contains only numbers
            if ( !Character.isDigit(userID.charAt(i)) ) {
                count++;
            }
        }
        if (count == 0) {
            b = true;
        }
        if (count > 0) {
            b = false;
        }
        return b;
    }

    // checks if the user id input has meet the conditions
    // ID: numbers starts with 3 and can only have 8 digits
    public boolean isID_Condition_Valid (String userID) {
        boolean b = false;
        int idLength = userID.length();
        char firstChar = userID.charAt(0);
            //checks if id length has 8 digits and starts with 3
            if (  (idLength == 8) && (firstChar == '3') ) {
                b = true;
            }
        return b;
    }

    // checks if the user first name input has correct datatype
    // First Name: only letters are allowed (no number, no space, no symbol) 
    public boolean isFname_Valid (String userFname) {
        boolean b = false;
        int count = 0;
        for (int i = 0; i < userFname.length(); i++) {
            // checks if the user ID contains only numbers
            if ( !Character.isLetter(userFname.charAt(i)) ) {
                count++;
            }
        }
        if (count == 0) {
            b = true;
        }
        if (count > 0) {
            b = false;
        }
        return b;
    }

    // checks if the user last name input has correct datatype
    // Last Name: only letters are allowed (no number, no space, no symbol) 
    public boolean isLname_Valid (String userLname) {
        boolean b = false;
        int count = 0;
        for (int i = 0; i < userLname.length(); i++) {
            // checks if the user ID contains only numbers
            if ( !Character.isLetter(userLname.charAt(i)) ) {
                count++;
            }
        }
        if (count == 0) {
            b = true;
        }
        if (count > 0) {
            b = false;
        }
        return b;
    }

    // checks if the user Email input has correct datatype
    // Email: space and comma (,) is not allowed 
    public boolean isEmail_Valid (String userEmail) {
        boolean b = false;
        int count = 0;
        for (int i = 0; i < userEmail.length(); i++) {
            // checks if the user ID contains only numbers
            if ( Character.isWhitespace(userEmail.charAt(i)) || userEmail.contains(",") ) {
                count++;
            }
        }
        if (count == 0) {
            b = true;
        }
        if (count > 0) {
            b = false;
        }
        return b;
    }

    // checks if the user password input has correct datatype
    // Password: only letters and numbers are allowed
    public boolean isPass_Valid (String userPass) {
        boolean b = false;
        int count = 0;
        for (int i = 0; i < userPass.length(); i++) {
            // checks if the user ID contains only numbers
            if ( Character.isWhitespace(userPass.charAt(i)) || userPass.contains(",") ) {
                count++;
            }
        }
        if (count == 0) {
            b = true;
        }
        if (count > 0) {
            b = false;
        }
        return b;
    }

    // checks if the user is admin
    public boolean isUserAdmin(String adminID, String adminPass) {
        boolean b = false;
        if (adminID.equals(ADMIN_ID) && adminPass.equals(ADMIN_PASS)) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }
}