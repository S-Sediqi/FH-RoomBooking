package com.logics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Pathfinder {

    public final String SET_USER_AS_STRING;
    public final String SET_ROOM_AS_STRING;
    public final String SET_BOOKING_AS_STRING;

    private String databasePaths = "src\\com\\databases\\paths\\";
    private String userPathStorage = "src\\com\\databases\\paths\\PathUser-";
    private String roomPathStorage = "src\\com\\databases\\paths\\PathRoom-";
    private String bookingPathStorage = "src\\com\\databases\\paths\\PathBooking-";

    protected Pathfinder() {
        SET_USER_AS_STRING = "user";
        SET_ROOM_AS_STRING = "room";
        SET_BOOKING_AS_STRING = "booking";
    }

    // -----------------------------------------------------------------------------------------
    // ---Content:------------------------------------------------------------------------------
    // ---1. Creating Path Database methods
    // ----------------------------------------------------
    // ---2. PathUser File methods
    // -------------------------------------------------------------
    // ---3. PathRoom File methods
    // -------------------------------------------------------------
    // ---4. PathBooking File methods
    // ----------------------------------------------------------
    // -----------------------------------------------------------------------------------------

    // ---1. Creating paths for (Path-Storages) methods
    // ----------------------------------------
    // This method creates a unique path for new (Path database) file by using date
    // and time
    // (Path Database): are used to store the paths of User, Room and Booking
    // database files
    private String createPathForPathStorageFiles(String dbName) {
        String answer = "";
        String addpath = "";
        if (dbName.equalsIgnoreCase(SET_USER_AS_STRING)) {
            addpath = userPathStorage;
        } else if (dbName.equalsIgnoreCase(SET_ROOM_AS_STRING)) {
            addpath = roomPathStorage;
        } else if (dbName.equalsIgnoreCase(SET_BOOKING_AS_STRING)) {
            addpath = bookingPathStorage;
        }
        LocalDateTime date = LocalDateTime.now();
        String st1 = "" + date;
        String st2 = st1.replace(':', '-');
        String st3 = st2.substring(0, 19);
        answer = addpath + st3 + ".csv";
        // the 1 second delay makes sure that the new files will get different name than
        // old ones
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return answer;
    }

    // This method creates a CSV file to act as a storage for other database paths
    // Should be used only to create new files, if the files storing the paths for
    // other
    // databases are damaged or for any reason is lost or deleted
    protected String createDatabaseforFilePaths(String databaseName) {
        String answer = "";
        String path = createPathForPathStorageFiles(databaseName);
        String inputDates = "Following are the saved paths of: " + databaseName + " Booking Database files";
        File csvFile = new File(path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(csvFile);
            fileWriter.write(inputDates);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        answer = "A path storage for " + databaseName + " database is created!";
        return answer;
    }
    /*
     * // This method returns the path of all files containing (Path) in their names
     * // These files are responsible for storing the paths of other databases
     * public ArrayList<String> getPathsOfFiles() {
     * String str1 = "";
     * File file = new File(databasePaths);
     * String[] fileList = file.list();
     * ArrayList<String> answers = new ArrayList<>();
     * for (int i = 0; i < fileList.length; i++) {
     * str1 = ""+fileList[i];
     * if (str1.contains("Path")) {
     * answers.add(fileList[i]);
     * }
     * }
     * return answers;
     * }
     */

    // ---2. PathUser File methods
    // -------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // (PathUser) file is used to store the path of (UserData) files
    // it finds the last (UserData) file path and returns it
    // it saves the given paths of new (UserData) files
    // -----------------------------------------------------------------------------------------

    // This method returns the path of file containing (PathUser) in their names
    // This file (PathUser) stores the paths of new USER_DATA file
    private String getPathOfUserPathFile() {
        String answer = "";
        String str1 = "";
        File file = new File(databasePaths);
        String[] fileList = file.list();
        for (int i = 0; i < fileList.length; i++) {
            str1 = "" + fileList[i];
            if (str1.contains("PathUser")) {
                answer = databasePaths + fileList[i];
            }
        }
        return answer;
    }

    // This method saves/add the user data file path into the (PathUser) file
    protected void addNewUserDBPath(String newUD_path) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(getPathOfUserPathFile(), true));
            bWriter.write("\n" + newUD_path);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method returns the last line from the (PathUser) file
    // the last line is the path of user database file, where all user data is
    // stored
    protected String getLastUserDBpath() {
        String answer = "";
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(getPathOfUserPathFile()));
            String csvLine = "";
            while ((csvLine = bReader.readLine()) != null) {
                answer = csvLine;
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    // ---3. PathRoom File methods
    // -------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // (PathRoom) file is used to store the path of (RoomData) files
    // it finds the last (RoomData) file path and returns it
    // it saves the given paths of new (RoomData) files
    // -----------------------------------------------------------------------------------------

    // This method returns the path of file containing (PathRoom) in their names
    // This file (PathRoom) stores the paths of new (RoomData) file
    private String getPathOfRoomPathFile() {
        String answer = "";
        String str1 = "";
        File file = new File(databasePaths);
        String[] fileList = file.list();
        for (int i = 0; i < fileList.length; i++) {
            str1 = "" + fileList[i];
            if (str1.contains("PathRoom")) {
                answer = databasePaths + fileList[i];
            }
        }
        return answer;
    }

    // This method saves/add the Room data file path into the (PathRoom) file
    protected void addNewRoomDBPath(String newRD_path) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(getPathOfRoomPathFile(), true));
            bWriter.write("\n" + newRD_path);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method returns the last line from the (PathRoom) file
    // the last line is the path of room database file, where all room data is
    // stored
    protected String getLastRoomDBpath() {
        String answer = "";
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(getPathOfRoomPathFile()));
            String csvLine = "";
            while ((csvLine = bReader.readLine()) != null) {
                answer = csvLine;
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    // ---4. PathBooking File methods
    // ----------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    // (PathBooking) file is used to store the path of (BookingData) files
    // it finds the last (BookingData) file path and returns it
    // it saves the given paths of new (BookingData) files
    // -----------------------------------------------------------------------------------------

    // This method returns the path of file containing (PathBooking) in their names
    // This file (PathBooking) stores the paths of new (BookingData) file
    private String getPathOfBookingPathFile() {
        String answer = "";
        String str1 = "";
        File file = new File(databasePaths);
        String[] fileList = file.list();
        for (int i = 0; i < fileList.length; i++) {
            str1 = "" + fileList[i];
            if (str1.contains("PathBooking")) {
                answer = databasePaths + fileList[i];
            }
        }
        return answer;
    }

    // This method saves/add the Booking data file path into the (PathBooking) file
    protected void addNewBookingDBPath(String newBD_path) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(getPathOfBookingPathFile(), true));
            bWriter.write("\n" + newBD_path);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method returns the last line from the (PathBooking) file
    protected String getLastBookingDBpath() {
        String answer = "";
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(getPathOfBookingPathFile()));
            String csvLine = "";
            while ((csvLine = bReader.readLine()) != null) {
                answer = csvLine;
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
