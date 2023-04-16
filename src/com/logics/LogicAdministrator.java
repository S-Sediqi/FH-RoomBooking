package com.logics;

import java.util.ArrayList;

public class LogicAdministrator {

    public final int USER_ID_INDEX;
    public final int USER_JOB_INDEX;
    public final int USER_FIRSTNAME_INDEX;
    public final int USER_LASTNAME_INDEX;
    public final int USER_EMAIL_INDEX;
    public final int USER_PASS_INDEX;

    public final String SET_USER_AS_STRING;
    public final String SET_ROOM_AS_STRING;
    public final String SET_BOOKING_AS_STRING;

    public final int ROOM_STATUS_INDEX;
    public final int ROOM_NUMBER_INDEX;
    public final int ROOM_NAME_INDEX;
    public final int ROOM_LOCATION_INDEX;
    public final int ROOM_CAPACITY_INDEX;
    public final int ROOM_RESOURCES_INDEX;

    public final String STATUS_ROOM_OPEN;
    public final String STATUS_ROOM_CLOSE;

    public final int TIME1_08_00_INDEX;
    public final int TIME2_09_45_INDEX;
    public final int TIME3_12_15_INDEX;
    public final int TIME4_14_00_INDEX;
    public final int TIME5_15_45_INDEX;
    public final int TIME6_17_30_INDEX;

    public final String TIME1_08_00_SET;
    public final String TIME2_09_45_SET;
    public final String TIME3_12_15_SET;
    public final String TIME4_14_00_SET;
    public final String TIME5_15_45_SET;
    public final String TIME6_17_30_SET;

    // This class contains all methods that is available for Admin
    // The methods here are as shorten as possible.
    // The reasons are to secure some methods and functions by declaring them  (private and protection)
    // and to make it easy for UI input and out put 
    public LogicAdministrator() {
        USER_ID_INDEX = 0;
        USER_JOB_INDEX = 1;
        USER_FIRSTNAME_INDEX = 2;
        USER_LASTNAME_INDEX = 3;
        USER_EMAIL_INDEX = 4;
        USER_PASS_INDEX = 5;

        SET_USER_AS_STRING = "user";
        SET_ROOM_AS_STRING = "room";
        SET_BOOKING_AS_STRING = "booking";

        ROOM_STATUS_INDEX = 0;
        ROOM_NUMBER_INDEX = 1;
        ROOM_NAME_INDEX = 2;
        ROOM_LOCATION_INDEX = 3;
        ROOM_CAPACITY_INDEX = 4;
        ROOM_RESOURCES_INDEX = 5;

        STATUS_ROOM_OPEN = "OPEN";
        STATUS_ROOM_CLOSE = "CLOSE";

        TIME1_08_00_INDEX = 0;
        TIME2_09_45_INDEX = 1;
        TIME3_12_15_INDEX = 2;
        TIME4_14_00_INDEX = 3;
        TIME5_15_45_INDEX = 4;
        TIME6_17_30_INDEX = 5;

        TIME1_08_00_SET = "t1";
        TIME2_09_45_SET = "t2";
        TIME3_12_15_SET = "t3";
        TIME4_14_00_SET = "t4";
        TIME5_15_45_SET = "t5";
        TIME6_17_30_SET = "t6";
    }

    //------------------------------------------------------------
    //---Content:-------------------------------------------------
    //---1. Database methods -------------------------------------
    //---2. User methods -----------------------------------------
    //---3. Room methods -----------------------------------------
    //---4. Booking methods --------------------------------------
    //---5. Pathfinder methods -----------------------------------
    //---6. Utility methods --------------------------------------
    //------------------------------------------------------------

    //------------------------------------------------------------
    //---1.---------------Datbase methods-------------------------
    //------------------------------------------------------------

    // This method returns the databases names
    public String getDatabaseNames() {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.getAllDatabaseNames();
        return answer;
    }

    // This method returns the path storage names
    public String getPathStorageNames() {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.getAllPathStorage_DatabaseNames();
        return answer;
    }

    // This method returns a message after deleting the given database
    public String deleteDatabase(String path) {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.deleteDatabase(path);
        return answer;
    }

    // This method returns a message after it creates an empty (UserData) CSV file 
    // with only one line/header: (ID,Job,FirstName,LastName,Email,Password) 
    public String createEmptyUserDatabase() {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.createUser_EmptyDatabase();
        return answer;
    }

    // This method returns a message after it creates an empty (RoomData) CSV file 
    // with only line/header: (Status,RoomNo,RoomName,Location,Capacity,Resources) 
    public String createEmptyRoomDatabase() {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.createRoom_EmptyDatabase();
        return answer;
    }

    // This method returns a message after it creates an empty (BookingData) CSV file 
    // first line/header contains: (RoomNo,1/1/2022,2/1/2022,3/1/2022,4/1/2022,.....)
    // and other lines: (101,t1-t2-t3-t4-t5-t6,t1-t2-t3-t4-t5-t6,...)
    //                  (102,t1-t2-t3-t4-t5-t6,t1-t2-t3-t4-t5-t6,...)
    public String createBookingDatabaseWithRooms() {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.createBooking_withRoomDatabase();
        return answer;
    }

    public String createEmptyBookingDatabase() {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.createBooking_EmptyDatabase();
        return answer;
    }

    // This method creates one booking line for a given new room
    // ex: (101,t1-t2-t3-t4-t5-t6,t1-t2-t3-t4-t5-t6...)
    public String createOneEmptyBookingLine(String roomNo) {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.createOneNewBookingLine(roomNo);
        return answer;
    }

    //------------------------------------------------------------
    //---2.---------------User methods----------------------------
    //------------------------------------------------------------

    public String checkAndAddToUserDB(String id, String job, String fName, 
                                    String lName, String email, String pass) {
        String answer = "";
        User_Input_Check uInputCheck = new User_Input_Check();
        answer = uInputCheck.checkUserInputData(id, job, fName, lName, email, pass);
        return answer;
    }
    // This method returns an ArrayList of a requested data of all users
    public String getAUserData(String userId) {
        String answer = "";
        User_Data_RW uData_RW = new User_Data_RW();
        answer = uData_RW.getOneUserData(userId);
        return answer;
    }

    // This method returns the information of a user after getting the user ID
    public ArrayList<String> getUserDataList(int dataIndex) {
        ArrayList<String> answer = new ArrayList<>();
        User_Data_RW uData_RW = new User_Data_RW();
        answer = uData_RW.getOneDataListOfUsers(dataIndex);
        return answer;
    }

    // This method returns all users data in a 2D Array
    public String[][] getUserFullTable() {
        User_Data_RW uData_RW = new User_Data_RW();
        String[][] answer = uData_RW.getUserDataIn2DArray();
        return answer;
    }

    // This method returns titles/headers of (UserData) file in an Array
    public String[] getHeaderUserTable() {
        User_Data_RW uData_RW = new User_Data_RW();
        String[] answer = uData_RW.getUserDataHeaderList();
        return answer;
    }

    // This method returns the user Job
    public String getUserJob(String userId) {
        String answer = "";
        User_Data_RW uData_RW = new User_Data_RW();
        answer = uData_RW.getUserJob(userId);
        return answer;
    }
    // This method returns a message after deleting a user from the (UserData) file
    public String deleteAUser(String userId) {
        String answer = "";
        User_Data_RW uData_RW = new User_Data_RW();
        answer = uData_RW.deleteUser(userId);
        return answer;
    }

    // This method gets the String data from user input and stores it in the user database file 
    public void writeToUserDB(String userInput) {
        User_Data_RW uData_RW = new User_Data_RW();
        uData_RW.writeToUserDataCSV(userInput);
    }

    //------------------------------------------------------------
    //---3.---------------Room methods----------------------------
    //------------------------------------------------------------

    public String checkAndAddToRoomDB(String status, String number, String name,
                                    String location, String capacity, String resource,
                                    boolean resourceSelection) {
        String answer = "";
        Room_Input_Check rInputCheck = new Room_Input_Check();
        answer = rInputCheck.checkRoomInputData(status, number, name, location,
                                                capacity, resource, resourceSelection);
        return answer;
    }
    // This method returns the information of a room after getting the room number
    public String getARoomData(String roomNum) {
        String answer = "";
        Room_Data_RW rData_RW = new Room_Data_RW();
        answer = rData_RW.getOneRoomData(roomNum);
        return answer;
    }

    // This method returns an ArrayList of a requested data of all rooms
    public ArrayList<String> getRoomDataList(int dataIndex) {
        ArrayList<String> answer = new ArrayList<>();
        Room_Data_RW rData_RW = new Room_Data_RW();
        answer = rData_RW.getOneDataListOfRooms(dataIndex);
        return answer;
    }

    // This method returns all room data in a 2D Array
    public String[][] getRoomFullTable() {
        Room_Data_RW rData_RW = new Room_Data_RW();
        String[][] answer = rData_RW.getRoomDataIn2DArray();
        return answer;
    }

    // This method returns titles/headers of (RoomData) file in an Array
    public String[] getHeaderRoomTable() {
        Room_Data_RW rData_RW = new Room_Data_RW();
        String[] answer = rData_RW.getRoomDataHeaderList();
        return answer;
    }

    // This method returns all room numbers in an Array
    public String[] getRoomNumbers() {
        Room_Data_RW rData_RW = new Room_Data_RW();
        String[] answer = rData_RW.getRoomNumbersArray();
        return answer;
    }

    // This method returns a message after sorting rooms ascending by number in (RoomData) file
    public String sortRoomsByNumber() {
        String answer = "";
        Room_Data_RW rData_RW = new Room_Data_RW();
        answer = rData_RW.sortRoomDataByNumberAscending();
        return answer;
    }

    // This method opens a room for booking
    public String openARoom(String roomNo) {
        String answer = "";
        Room_Data_RW rData_RW = new Room_Data_RW();
        answer = rData_RW.changeRoomStatus(roomNo, STATUS_ROOM_OPEN);
        return answer;
    }

    // This method closes a room for booking
    public String closeARoom(String roomNo) {
        String answer = "";
        Room_Data_RW rData_RW = new Room_Data_RW();
        answer = rData_RW.changeRoomStatus(roomNo, STATUS_ROOM_CLOSE);
        return answer;
    }

    // This method returns a message after deleting a room from the (RoomData) file
    public String deleteARoom(String roomNum) {
        String answer = "";
        Room_Data_RW rData_RW = new Room_Data_RW();
        answer = rData_RW.deleteRoom(roomNum);
        return answer;
    }

    // This method returns the status(open/close) of a given room (number)
    public String getRoomStatus(String roomNum) {
        String answer = "";
        Room_Data_RW rData_RW = new Room_Data_RW();
        answer = rData_RW.getStatusOfARoom(roomNum);
        return answer;
    }

    // This method gets the String data from user input and stores it in the room database file 
    public void writeToRoomDB(String roomInput) {
        Room_Data_RW rData_RW = new Room_Data_RW();
        rData_RW.writeToRoomDataCSV(roomInput);
    }

    //------------------------------------------------------------
    //---4.---------------Booking methods-------------------------
    //------------------------------------------------------------
    
    // This method returns a mesasage after booking a room for the user
    public String bookARoom(String userId, String roomNo, String date, int bookingTimeIndex) {
        String answer = "";
        Booking_Data_RW bData_RW = new Booking_Data_RW();
        answer = bData_RW.bookARoomForATime(userId, roomNo, date, bookingTimeIndex);
        return answer;
    }

    // This method returns a message after it cancells a booking for the user
    public String cancelABooking(String userId, String roomNo, String date, int cancelingTimeIndex) {
        String answer = "";
        Booking_Data_RW bData_RW = new Booking_Data_RW();
        answer = bData_RW.cancelARoomBooking(userId, roomNo, date, cancelingTimeIndex);
        return answer;
    }

    // This method returns all room data in a 2D Array
    public String[][] getBookingFullTable(String date) {
        Booking_Data_RW bData_RW = new Booking_Data_RW();
        String[][] answer = bData_RW.getDataIn2DArray(date);
        return answer;
    }

    // This method returns titles/headers of (RoomData) file in an Array
    public String[] getHeaderBookingTable() {
        Booking_Data_RW bData_RW = new Booking_Data_RW();
        String[] answer = bData_RW.getHeaderList();
        return answer;
    }

    // This method returns all dates from (BookingData) file as an Array
    public String[] getDateList() {
        Booking_Data_RW bData_RW = new Booking_Data_RW();
        String[] answer = bData_RW.getListOfDates();
        return answer;
    }

    // This method returns the index of a given time (in: 08:00,09:45...) (out: 0,1...)
    public int getTimeIndex(String timeString) {
        Booking_Data_RW bData_RW = new Booking_Data_RW();
        int answer = bData_RW.getIndexOfTime(timeString);
        return answer;
    }

    // This method returns a message after it adds a new room to the (BookingData) file
    public String addNewRoomInBooking(String roomNo) {
        String answer = "";
        Booking_Data_RW bData_RW = new Booking_Data_RW();
        answer = bData_RW.addOneRoomIn_BookingDB(roomNo);
        return answer;
    }

    // This method deletes any room from the booking database if the room is (Non-existing)
    // the room does not exist/deleted from the (RoomData) file 
    public void deleteNonExistingRooms() {
        Booking_Data_RW bData_RW = new Booking_Data_RW();
        bData_RW.deleteNonExistingRoomsFromBookingList();
    }

    //------------------------------------------------------------
    //---5.---------------Pathfinder methods----------------------
    //------------------------------------------------------------

    // This method creates a file to save the paths of other database
    // pathOwnerNames:(user, room, booking)
    public String createPathStorageFile(String pathOwnerName) {
        String answer = "";
        Pathfinder pf = new Pathfinder();
        answer = pf.createDatabaseforFilePaths(pathOwnerName);
        return answer;
    }

    // This method saves the (UserData) file path in the (PathUser) file
    public void saveUserDBpath(String databasePath) {
        Pathfinder pf = new Pathfinder();
        pf.addNewUserDBPath(databasePath);
    }

    // This method returns the last user database path from (PathUser) file
    public String getUserDBpath() {
        String answer = "";
        Pathfinder pf = new Pathfinder();
        answer = pf.getLastUserDBpath();
        return answer;
    }

    // This method saves the (RoomData) file path in the (PathRoom) file
    public void saveRoomDBpath(String databasePath) {
        Pathfinder pf = new Pathfinder();
        pf.addNewRoomDBPath(databasePath);
    }

    // This method returns the last room database path from (PathRoom) file
    public String getRoomDBpath() {
        String answer = "";
        Pathfinder pf = new Pathfinder();
        answer = pf.getLastRoomDBpath();
        return answer;
    }

    // This method saves the (BookingData) file path in the (PathBooking) file
    public void saveBookingDBpath(String databasePath) {
        Pathfinder pf = new Pathfinder();
        pf.addNewBookingDBPath(databasePath);
    }

    // This method returns the last booking database path from (PathBooking) file
    public String getBookingDBpath() {
        String answer = "";
        Pathfinder pf = new Pathfinder();
        answer = pf.getLastBookingDBpath();
        return answer;
    }

    //------------------------------------------------------------
    //---6.---------------Utility methods-------------------------
    //------------------------------------------------------------

    // This method returns the current date
    public String getCurrentDate() {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.getTheCurrentDate();
        return answer;
    }

    // This method returns the current time
    public String getCurrentTime() {
        String answer = "";
        DataBases db = new DataBases();
        answer = db.getTheCurrentTime();
        return answer;
    }
}