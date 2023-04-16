package com.logics;

import com.validation.UserDataValidation;

public class User_Input_Check {

    private int idAlreadyExist = 0,
                idDataCheck = 0,
                idConditionCheck = 0,
                idEmptyCheck = 0,
                fNameCheck = 0,
                fNameEmptyCheck = 0,
                lNameCheck = 0,
                lNameEmptyCheck = 0,
                emailCheck = 0,
                emailEmptyCheck = 0,
                passCheck = 0,
                passEmptyCheck = 0;
    private String  idAlreadyExistMsg = "",
                    idDataMsg = "",
                    idConditionMsg = "",
                    idEmptyMsg = "",
                    fNameMsg = "",
                    fNameEmptyMsg = "",
                    lNameMsg = "",
                    lNameEmptyMsg = "",
                    emailMsg = "",
                    emailEmptyMsg = "",
                    passMsg = "",
                    passEmptyMsg = "";
    private String userData = "";

    protected User_Input_Check () {
    }

    protected String checkUserInputData (String id, String job, String fName, String lName, String email, String pass) {
        String answer = "";
        // checks if first name entry is empty or not
        if (fName.isEmpty()) {
            fNameEmptyCheck = 1;
            fNameEmptyMsg = "First name field can't be Empty";
        }
        // checks if last name entry is empty or not
        if (lName.isEmpty()) {
            lNameEmptyCheck = 1;
            lNameEmptyMsg = "Last name field can't be Empty";
        }
        // checks if email entry is empty or not
        if (email.isEmpty()) {
            emailEmptyCheck = 1;
            lNameEmptyMsg = "Email field can't be Empty";
        }
        // checks if password entry is empty or not
        if (pass.isEmpty()) {
            passEmptyCheck = 1;
            passEmptyMsg = "Password field can't be Empty";
        }

        // creates a constructor of class (RoomRegValidation) to use the functions for validating the input data
        UserDataValidation uDataVal = new UserDataValidation();

        // checks if id entry is empty or not
        if (id.isEmpty()) {
            idEmptyCheck = 1;
            idEmptyMsg = "ID field can't be Empty";
        }
        if (!id.isEmpty()) {
            // checks if ID contains only integer formate
            if (!uDataVal.isID_Type_Valid(id)) {
                idDataCheck = 1;
                idDataMsg = "ID can only contain numbers";
            }
            if (uDataVal.isID_Type_Valid(id)) {
                // checks if ID is 8 digit and starts with 3
                if (!uDataVal.isID_Condition_Valid(id)) {
                    idConditionCheck = 1;
                    idConditionMsg = "ID must be 8 digits & stars with 3";
                }
                if (uDataVal.isID_Condition_Valid(id)) {
                    // checks if user ID already exist in database
                    if (uDataVal.doesUserExist(id)) {
                    idAlreadyExist = 1;
                    idAlreadyExistMsg = "User ID already exist !";
                    }
                }
            }
        }
        if (!uDataVal.isFname_Valid(fName)) {
            fNameCheck = 1;
            fNameMsg = "First name can only contain letters !";
        }
        if (!uDataVal.isLname_Valid(lName)) {
            lNameCheck = 1;
            lNameMsg = "Last name can only contain letters !";
        }
        if (!uDataVal.isEmail_Valid(email)) {
            emailCheck = 1;
            emailMsg = "Email can't contain space or comma !";
        }
        if (!uDataVal.isPass_Valid(pass)) {
            passCheck = 1;
            passMsg = "Password can't contain space or comma !";
        }
        // stores all the user data into a Sting separated with comma for CSV file
        userData = id+","+job+","+fName+","+lName+ ","+email+","+pass;

        // adds all int after running all data validation functions for input fields 
        // if all data is correct (sumOfValidation = 0)
        // if any data is wrong/empty (sumOfValidation > 0 )            
        int sumOfValidation = idAlreadyExist + idDataCheck + idConditionCheck + 
                            idEmptyCheck + fNameCheck + fNameEmptyCheck +
                            lNameCheck + lNameEmptyCheck + emailCheck + 
                            emailEmptyCheck + passCheck + passEmptyCheck;
        
        if ( sumOfValidation == 0 ) {
            // creates a constructor of user data entry class and saves the given data into the CSV file
            LogicAdministrator admin = new LogicAdministrator();
            admin.writeToUserDB(userData);
            answer += "\n" + "\n" + "------ !!! Congrats !!! ------";
            answer += "\n" + "User Registred Successfully!";
        }

        // if entered data is wrong, prints the corresponding statement
        else if ( sumOfValidation != 0 ) {
            answer += "\n" + "------ !!! ERROR ! ------";
            if (idEmptyCheck != 0) { // if ID field is empty
                answer += "\n" + idEmptyMsg;
            }
            if (idAlreadyExist != 0) { // if user id already exist in the database
                answer += "\n" + idAlreadyExistMsg;
            }
            if (idDataCheck != 0) { // checks if ID is correct (only numbers)
                answer += "\n" + idDataMsg;
            }
            if (idConditionCheck != 0) { // checks if ID is 8 digit and starts with 3
                answer += "\n" + idConditionMsg;
            }
            if (fNameCheck != 0) { // if entered first name is invalid (contains: number, symbol, space)
                answer += "\n" + fNameMsg;
            }
            if (fNameEmptyCheck != 0) { // if first name field is empty
                answer += "\n" + fNameEmptyMsg;
            }
            if (lNameCheck != 0) { // if entered last name is invalid (contains: number, symbol, space)
                answer += "\n" + lNameMsg;
            }
            if (lNameEmptyCheck != 0) { // if last name field is empty
                answer += "\n" + lNameEmptyMsg;
            }
            if (emailCheck != 0) { // if entered Email is invalid (contains: space, comma)
                answer += "\n" + emailMsg;
            }
            if (emailEmptyCheck != 0) { // if Email field is empty
                answer += "\n" + emailEmptyMsg;
            }
            if (passCheck != 0) { // if entered password is invalid (contains: space, comma)
                answer += "\n" + passMsg;
            }
            if (passEmptyCheck != 0) { // if password field is empty
                answer += "\n" + passEmptyMsg;
            }
        }
        return answer;
    }
}
