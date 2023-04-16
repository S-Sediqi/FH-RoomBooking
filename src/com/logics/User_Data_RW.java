package com.logics;

import java.io.*;
import java.util.*; 

import com.validation.UserDataValidation;

public class User_Data_RW {

    private static final String COMMA_DELIMITER = ",";
    private static final int USER_ID_INDEX = 0;
    private static final int USER_JOB_INDEX = 1;
    private static final int USER_FIRSTNAME_INDEX = 2;
    private static final int USER_LASTNAME_INDEX = 3;
    private static final int USER_EMAIL_INDEX = 4;
    private static final int USER_PASS_INDEX = 5;

    private ArrayList<ArrayList<String>> userList = new ArrayList<>();
    private ArrayList<String> uID_List = new ArrayList<>();
    private ArrayList<String> uJob_List = new ArrayList<>();
    private ArrayList<String> uFName_List = new ArrayList<>();
    private ArrayList<String> uLName_List = new ArrayList<>();
    private ArrayList<String> uEmail_List = new ArrayList<>();
    private ArrayList<String> uPass_List = new ArrayList<>();

    protected User_Data_RW() {
    }

    // This method gets the String data from user input and stores it in the user database file 
    protected void writeToUserDataCSV(String user_Input) {
        Pathfinder pf_User  = new Pathfinder();
        String path = pf_User.getLastUserDBpath();
        try {
            BufferedWriter file_in = new BufferedWriter(new FileWriter(path, true));
            file_in.write("\n"+user_Input);
            file_in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method reads the data from the UserData CSV file
    // it creates Arraylists for each user data and adds each data to its corresponding ArrayList
    // then it adds all ArrayLists to one 2D ArrayList (userList)
    private void readUserDataFile() {
        Pathfinder pf_User  = new Pathfinder();
        String path = pf_User.getLastUserDBpath();
        try {
            BufferedReader file_out = new BufferedReader(new FileReader(path));

            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);

                // saving each data of User into a separate ArrayList
                uID_List.add(dataLine[0]);
                uJob_List.add(dataLine[1]);
                uFName_List.add(dataLine[2]);
                uLName_List.add(dataLine[3]);
                uEmail_List.add(dataLine[4]);
                uPass_List.add(dataLine[5]);
            }
            // adding all ArrayLists to the one ArrayList 
            // the new/big ArrayList will save other ArrayLists as its elements
            userList.add(uID_List);
            userList.add(uJob_List);
            userList.add(uFName_List);
            userList.add(uLName_List);
            userList.add(uEmail_List);
            userList.add(uPass_List);
            
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The method gets requested user data as String (id, job, fname, lname, email, pass)
    // it looks through each data list
    // then returns the requested data as an ArrayList 
    protected ArrayList<String> getOneDataListOfUsers(int req_UserDataIndex) {
        ArrayList<String> userData = new ArrayList<>();
        readUserDataFile();
        switch (req_UserDataIndex) {
            case (USER_ID_INDEX): userData = userList.get(USER_ID_INDEX);
                break;
            case (USER_JOB_INDEX): userData = userList.get(USER_JOB_INDEX);
                break;    
            case (USER_FIRSTNAME_INDEX): userData = userList.get(USER_FIRSTNAME_INDEX);
                break;
            case (USER_LASTNAME_INDEX): userData = userList.get(USER_LASTNAME_INDEX);
                break;
            case (USER_EMAIL_INDEX): userData = userList.get(USER_EMAIL_INDEX);
                break;
            case (USER_PASS_INDEX): userData = userList.get(USER_PASS_INDEX);
                break;
            default:
                break;
        }
        return userData;
    }

    // This method returns a 2D array of user data for the JTable in the UI
    protected String[][] getUserDataIn2DArray(){
        readUserDataFile();
        int colSize = userList.size();
        int rowSize = userList.get(0).size();
        String[][] arrData = new String[rowSize][colSize];

        for (int i = 0; i < arrData.length; i++) {
            for (int j = 0; j < arrData[i].length; j++) {
                arrData[i][j] = userList.get(j).get(i);
            }
        }
        return arrData;
    }

    // This method returns an array of column headers from user data file for the UI
    protected String[] getUserDataHeaderList() {
        readUserDataFile();
        int arrSize = userList.size();
        String[] arrHeader = new String[arrSize];
        for (int i = 0; i < arrHeader.length; i++) {
            arrHeader[i] = userList.get(i).get(0);
        }
        return arrHeader;
    }

    // The method returns the data of a requested user as String
    protected String getOneUserData(String userId) {
        UserDataValidation uDataVal = new UserDataValidation();
        boolean b = uDataVal.doesUserExist(userId);
        
        Pathfinder pf_User  = new Pathfinder();
        String path = pf_User.getLastUserDBpath();
        String userInfo = "";
        if (!b) {
            userInfo = "User "+userId+" does not Exit !!!";
        } else {
            try {
                BufferedReader bReader = new BufferedReader(new FileReader(path));
                String csvLine = "";
                while ( (csvLine = bReader.readLine()) != null ) {
                    String[] dataLine = csvLine.split(COMMA_DELIMITER);
                    if (userId.equals(dataLine[0])) {
                        userInfo =  "Requested user data:" + "\n" + "\n" +
                                    "ID: " + dataLine[USER_ID_INDEX] + "\n" +
                                    "Job: " + dataLine[USER_JOB_INDEX] + "\n" +
                                    "First Name: " + dataLine[USER_FIRSTNAME_INDEX] + "\n" +
                                    "Last Name: " + dataLine[USER_LASTNAME_INDEX] + "\n" +
                                    "Email: " + dataLine[USER_EMAIL_INDEX] + "\n" +
                                    "Password: " + dataLine[USER_PASS_INDEX] + "\n";
                    }
                }
                bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }

    // This method returns the user Job
    protected String getUserJob(String userId) {
        String answer = "";
        ArrayList<String> uID = new ArrayList<>();
        ArrayList<String> uJob = new ArrayList<>();

        uID = getOneDataListOfUsers(USER_ID_INDEX);
        uJob = getOneDataListOfUsers(USER_JOB_INDEX);
            for (int i = 1; i < uID.size(); i++) {
                if (userId.equals(uID.get(i))) {
                    answer = uJob.get(i);
                }
            }
        return answer;
    }

    // This method first gets the (userId) as input
    // it reads the current existing (UserData) file and copies the data to new (UserData) file
    // when it comes to given user id it ignores the line and thus in the new file the user is no longer exist
    // afterwards the old file is deleted by (deleteDatabase) method 
    protected String deleteUser(String userId) {
        String answer = "";
        Pathfinder pf_User  = new Pathfinder();
        String currentPath = pf_User.getLastUserDBpath();

        DataBases userDB = new DataBases();
        userDB.createUser_EmptyDatabase();

        try {
            BufferedReader file_out = new BufferedReader(new FileReader(currentPath));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                // ignores the first line (column headers) and the line with the given user ID number
                if (!dataLine[USER_ID_INDEX].equals(userId) && !dataLine[USER_ID_INDEX].equals("ID")) {
                    String user_Input = csvLine;
                    writeToUserDataCSV(user_Input);
                }
            }
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        answer += userDB.deleteDatabase(currentPath);
        answer += "\n"+"User "+userId+" Successfully deleted !";
        return answer;
    }
}