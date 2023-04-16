package com.logics;

import com.validation.*;

import java.io.*;
import java.util.*; 

public class Booking_Data_RW {

    private static final String COMMA_DELIMITER = ",";
    private static final String HYPHEN_DELIMITER = "-";
    private static final String SLASH_DELIMITER = "/";
    private static final int TOTAL_TIME_SET = 6;
    
    private static final String TIME1_08_00_SET = "t1";
    private static final String TIME2_09_45_SET = "t2";
    private static final String TIME3_12_15_SET = "t3";
    private static final String TIME4_14_00_SET = "t4";
    private static final String TIME5_15_45_SET = "t5";
    private static final String TIME6_17_30_SET = "t6";
    
    private static final String TIME1_08_00_HEADER = "08:00-09:30";
    private static final String TIME2_09_45_HEADER = "09:45-11:15";
    private static final String TIME3_12_15_HEADER = "12:15-13:45";
    private static final String TIME4_14_00_HEADER = "14:00-15:30";
    private static final String TIME5_15_45_HEADER = "15:45-17:15";
    private static final String TIME6_17_30_HEADER = "17:30-19:00";

    private static final int TIME1_08_00_INDEX = 0;
    private static final int TIME2_09_45_INDEX = 1;
    private static final int TIME3_12_15_INDEX = 2;
    private static final int TIME4_14_00_INDEX = 3;
    private static final int TIME5_15_45_INDEX = 4;
    private static final int TIME6_17_30_INDEX = 5;

    private ArrayList<ArrayList<String>> bookingList = new ArrayList<>();
    private ArrayList<String> roomList = new ArrayList<>();
    private ArrayList<String> time_0800 = new ArrayList<>();
    private ArrayList<String> time_0945 = new ArrayList<>();
    private ArrayList<String> time_1215 = new ArrayList<>();
    private ArrayList<String> time_1400 = new ArrayList<>();
    private ArrayList<String> time_1545 = new ArrayList<>();
    private ArrayList<String> time_1730 = new ArrayList<>();

    public Booking_Data_RW() {   
    }

    // ------------------------------ Read and Write ------------------------------------------------
    // ----------------------------------------------------------------------------------------------
    // This method saves the booking data into (BookingData) CSV file
    private void writeToBookingDataCSV(String booking_Input) {
        LogicAdministrator admin = new LogicAdministrator();
        String path = admin.getBookingDBpath();
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(path, true));
            bWriter.write("\n"+booking_Input);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method reads the data from the (BookingData) CSV file
    // 1. it stores the room number list in the ArrayList (roomList)
    // 2. it stores the each booking time("t1-t2-t3-t4-t5-t6") for the given day into separate ArrayLists
    // 3. it stores all the booking time sets in one ArrayList (bookingList)
    private void readBookingDataFile(String date) {
        int dateIndex = getDateIndex(date);

        LogicAdministrator admin = new LogicAdministrator();
        String path = admin.getBookingDBpath();
            
        try {
            BufferedReader file_out = new BufferedReader(new FileReader(path));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                
                    roomList.add(dataLine[0]);
                    String[] bookingSet = changeBookingStringToArray(dataLine[dateIndex]);
                    time_0800.add(bookingSet[TIME1_08_00_INDEX]);
                    time_0945.add(bookingSet[TIME2_09_45_INDEX]);
                    time_1215.add(bookingSet[TIME3_12_15_INDEX]);
                    time_1400.add(bookingSet[TIME4_14_00_INDEX]);
                    time_1545.add(bookingSet[TIME5_15_45_INDEX]);
                    time_1730.add(bookingSet[TIME6_17_30_INDEX]);
            }
            bookingList.add(roomList);
            bookingList.add(time_0800);
            bookingList.add(time_0945);
            bookingList.add(time_1215);
            bookingList.add(time_1400);
            bookingList.add(time_1545);
            bookingList.add(time_1730);

            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method receives a string and returns an Array of time sets
    // receives: a date (dd/mm/yyy) or a time set (t1-t2-t3-t4-t5-t6) 
    // for date it returns: bookingSet[] = {"08:00-09:30", "09:45-11:15", ....} 
    // for time set it returns: bookingSet[] = {"t1", "t2", "t3", "t4", "t5", "t6"}
    private String[] changeBookingStringToArray(String inputTimeSet) {
        String[] bookingSet = new String[TOTAL_TIME_SET];
        if (inputTimeSet.contains(SLASH_DELIMITER)) {
            bookingSet[TIME1_08_00_INDEX] = TIME1_08_00_HEADER;
            bookingSet[TIME2_09_45_INDEX] = TIME2_09_45_HEADER;
            bookingSet[TIME3_12_15_INDEX] = TIME3_12_15_HEADER;
            bookingSet[TIME4_14_00_INDEX] = TIME4_14_00_HEADER;
            bookingSet[TIME5_15_45_INDEX] = TIME5_15_45_HEADER;
            bookingSet[TIME6_17_30_INDEX] = TIME6_17_30_HEADER;
        }else if (inputTimeSet.contains(HYPHEN_DELIMITER)) {
            bookingSet = inputTimeSet.split(HYPHEN_DELIMITER);
        }
        return bookingSet;
    }

    // ------------------------------ Booking and cancelling ----------------------------------------
    // ----------------------------------------------------------------------------------------------
    // This method returns a mesasage after booking a room for the user
    // the room booking process happens through calling (fifth) methods 
    protected String bookARoomForATime(String userId, String roomNo, String date, int bookingTimeIndex) {
        String answer = "";
        boolean canReserve;
        boolean isNotClosed;
        int target = getDateIndex(date);
        // checks if the room is not already reserved(true = room is not reserved) (false = room is reserved)
        canReserve = checkIfRoomIsAvailable(roomNo, date, bookingTimeIndex);
        isNotClosed = checkRoomStatus(roomNo);
        if (canReserve && isNotClosed) {
            String bookedTimeString = getBookedtimeSet(userId, roomNo, date, bookingTimeIndex);
            answer = saveChangesIntoBookingDataFile(bookedTimeString, roomNo, date, target);
        } else if(!canReserve) {
            answer = "Sorry! Room is already reserved!";
        } else if(!isNotClosed) {
            answer = "Sorry! Room is not available for reservation!";
        }
        return answer;
    }

    // This method returns a message after it cancells a booking for the user
    // the booking cancellation process happens through calling (~seven) methods
    protected String cancelARoomBooking(String userId, String roomNo, String date, int cancelingTimeIndex) {
        String answer = "";
        boolean canCancel;
        int target = getDateIndex(date);
        canCancel = checkIfUserBookedTheRoom(userId, roomNo, date, cancelingTimeIndex);

        if (canCancel) {
            switch (cancelingTimeIndex) {
                case (TIME1_08_00_INDEX): userId = TIME1_08_00_SET;
                    break;
                case (TIME2_09_45_INDEX): userId = TIME2_09_45_SET;
                    break;    
                case (TIME3_12_15_INDEX): userId = TIME3_12_15_SET;
                    break;
                case (TIME4_14_00_INDEX): userId = TIME4_14_00_SET;
                    break;
                case (TIME5_15_45_INDEX): userId = TIME5_15_45_SET;
                    break;
                case (TIME6_17_30_INDEX): userId = TIME6_17_30_SET;
                    break;
                default:
                    break;
            }
            String bookedTimeString = getBookedtimeSet(userId, roomNo, date, cancelingTimeIndex);
            saveChangesIntoBookingDataFile(bookedTimeString, roomNo, date, target);
            answer = "Reservation of room "+roomNo+" is cancelled!";
        } else if (!canCancel) {
            answer = "Room "+roomNo+" is not booked by: user "+
                    userId+"  for:  "+getTimeSet(cancelingTimeIndex);
        }
        return answer;
    }

    // --- Booking and cancelling: First step ------------------------------------------
    // ---------------------------------------------------------------------------------
    // This method returns the index of a given date(dd/mm/yyyy) from Booking CSV file
    private int getDateIndex(String date) {
        int dateIndex = 0;
        LogicAdministrator admin = new LogicAdministrator();
        String path = admin.getBookingDBpath();

        try {
            BufferedReader file_out = new BufferedReader(new FileReader(path));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                // if the first element of Array is equal to (RoomNo) it searches for the dates
                if (dataLine[0].equals("RoomNo")) {
                    for (int i = 1; i < dataLine.length; i++) {
                        // if the element of array is the same as requested date then it returns the elements index
                        if (dataLine[i].equals(date)) {
                            // with this index the booking set (set = "t1-t2-t3-t4-t5-t6") of a room for the given date can be found
                            dateIndex = i;
                        }
                    }
                }
            }
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dateIndex;
    }

    // --- Booking and cancelling: Second step -----------------------------------------
    // ---------------------------------------------------------------------------------
    // This method returns a String of Booking time set after it books the room for the chosen time and date 
    private String getBookedtimeSet(String userId, String roomNo, String date, int bookingTimeIndex) {
        String answer = "";
        // input: room number and date of booking
        // output: the booking time set of that day in an Array ["t1", "t2", "t3", "t4", "t5", "t6"]
        String[] timeSetArray = getBookingTimeSetOfARoom(roomNo, date);
        // input: user ID, Array of booking time set, the index of time the room should be booked
        // output: An Array of booking time sets, which the specified time is now booked by the user (user Id)
        String[] bookedSetArray = putUserIdInTimeIndex(userId, timeSetArray, bookingTimeIndex);
        answer = String.join(HYPHEN_DELIMITER, bookedSetArray);
        return answer;
    }

    // --- Booking and cancelling: Third step ------------------------------------------
    // ---------------------------------------------------------------------------------
    // This method returns an Array of booking time set("t1-t2-t3-t4-t5-t6") from the (BookingData) file
    // the booking time set is selected according to the given room number and date
    public String[] getBookingTimeSetOfARoom(String roomNo, String date) {
        String str = "";
        readBookingDataFile(date);
        for (int i = 1; i < bookingList.get(0).size(); i++) {
            if (bookingList.get(0).get(i).equals(roomNo)) {
                str  =  time_0800.get(i)+HYPHEN_DELIMITER+
                        time_0945.get(i)+HYPHEN_DELIMITER+
                        time_1215.get(i)+HYPHEN_DELIMITER+
                        time_1400.get(i)+HYPHEN_DELIMITER+
                        time_1545.get(i)+HYPHEN_DELIMITER+
                        time_1730.get(i);
            }
        }
        String[] answer = str.split(HYPHEN_DELIMITER);
        return answer;
    }

    // --- Booking and cancelling: Fourth step -----------------------------------------
    // ---------------------------------------------------------------------------------
    // This method returns an Array of Booking time set after it replaces the given time index with the users Id   
    private String[] putUserIdInTimeIndex(String userId, String[] bookingSetArray, int bookingTimeIndex) {
        switch (bookingTimeIndex) {
            case (TIME1_08_00_INDEX): bookingSetArray[TIME1_08_00_INDEX] = userId;
                break;
            case (TIME2_09_45_INDEX): bookingSetArray[TIME2_09_45_INDEX] = userId;
                break;    
            case (TIME3_12_15_INDEX): bookingSetArray[TIME3_12_15_INDEX] = userId;
                break;
            case (TIME4_14_00_INDEX): bookingSetArray[TIME4_14_00_INDEX] = userId;
                break;
            case (TIME5_15_45_INDEX): bookingSetArray[TIME5_15_45_INDEX] = userId;
                break;
            case (TIME6_17_30_INDEX): bookingSetArray[TIME6_17_30_INDEX] = userId;
                break;
            default:
                break;
        }
        String[] answer = bookingSetArray;
        return answer;
    }

    // --- Booking and cancelling: Fifth step ------------------------------------------
    // ---------------------------------------------------------------------------------
    // This method returns a message after:
    // 1. it finds the path of (BookingData) file 
    // 2. it creates new empty Database for Booking and it reads the current/old data
    // 3. if the room number is not the chosen one, it copies the data/lines to the new file
    // 4. if the room number is a match then instead of the copying it saves the new line with the booked room 
    // 5. after the data is copied and a room is booked for a user in a specific time of a day, old file is removed
    private String saveChangesIntoBookingDataFile(String bookedString, String roomNo, String date, int dateIndex) {
        String answer = "";
        LogicAdministrator admin = new LogicAdministrator();
        String currentPath = admin.getBookingDBpath();
        admin.createEmptyBookingDatabase();

        try {
            BufferedReader file_out = new BufferedReader(new FileReader(currentPath));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                // ignores the first line (column headers) and the line with the given room number
                if (!dataLine[0].equals("RoomNo")) {
                    if (!dataLine[0].equals(roomNo)) {
                        String booking_Input = csvLine;
                        writeToBookingDataCSV(booking_Input);
                    }
                    else if (dataLine[0].equals(roomNo)) {
                        String booking_Input = putBookedTimeSetIntoBookingLine(bookedString, csvLine, dateIndex);
                        writeToBookingDataCSV(booking_Input);
                    }
                }
            }
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        admin.deleteDatabase(currentPath);
        answer = "\n"+"Room booked for "+date+" Successfully!";
        return answer;
    }

    // --- Booking and cancelling: middle step between 1st and 2nd steps of cancelling -
    // ---------------------------------------------------------------------------------
    // This method checks if the user that has requested a booking cancellation is the one who has booked the room
    private boolean checkIfUserBookedTheRoom(String userId, String roomNo, String date, int cancelingTimeIndex) {
        boolean b = false;
        String[] bookingSetArray = getBookingTimeSetOfARoom(roomNo, date);
        if (bookingSetArray[cancelingTimeIndex].equals(userId)) {
            b = true;
        }
        return b;
    }

    // This method returns a boolean for the availability of the room
    // Input: room number, date of booking, time of booking
    // Output:(true = room is not booked); (false = room is already booked by a user) 
    private boolean checkIfRoomIsAvailable(String roomNo, String date, int bookingTimeIndex) {
        boolean b = false;
        String[] bookingSetArray = getBookingTimeSetOfARoom(roomNo, date);
        if (bookingSetArray[bookingTimeIndex].equals(TIME1_08_00_SET) ||
            bookingSetArray[bookingTimeIndex].equals(TIME2_09_45_SET) ||
            bookingSetArray[bookingTimeIndex].equals(TIME3_12_15_SET) ||
            bookingSetArray[bookingTimeIndex].equals(TIME4_14_00_SET) ||
            bookingSetArray[bookingTimeIndex].equals(TIME5_15_45_SET) ||
            bookingSetArray[bookingTimeIndex].equals(TIME6_17_30_SET) ) {
            b = true;
        }
        return b;
    }
    // This method returns a boolean after it checks is the room is open or closed for booking 
    private boolean checkRoomStatus(String roomNo) {
        boolean b = false;
        LogicAdministrator admin = new LogicAdministrator();
        String status = admin.getRoomStatus(roomNo);
        if (status.equalsIgnoreCase("open")) {
            b = true;
        } else if(status.equalsIgnoreCase("close")){
            b = false;
        }
        return b;
    }
    // --- Booking and cancelling: middle step in the Fifth step of booking ------------
    // ---------------------------------------------------------------------------------
    // This method returns a full booking String of a room for all day
    // after it split the String into an Array, finds the index  of booking day and replaces
    // the old booking time set with the new updated one ex: (t1-t2-t3-t4-t5-t6) --> (t1-36857491-t3-t4-t5-t6)
    private String putBookedTimeSetIntoBookingLine(String bookedTimeSetString, String bookingLine, int dateIndex) {
        String answer = "";
        // puts each booking time set into an Array element
        String[] bookingArray = bookingLine.split(COMMA_DELIMITER);
        // find the target index(day) where room should be booked
        int target = dateIndex;
        // replaces the 
        bookingArray[target] = bookedTimeSetString;
        answer += bookingArray[0]+"";
            for (int i = 1; i < bookingArray.length; i++) {
                answer += COMMA_DELIMITER+bookingArray[i];
            }
        return answer;
    }

    // --- methods for the JTable input ------------------------------------------------
    // ---------------------------------------------------------------------------------
    // This method returns a 2D Array of booking data for the JTable in the UI
    protected String[][] getDataIn2DArray(String date){
        readBookingDataFile(date);
        int colSize = bookingList.size();
        int rowSize = bookingList.get(0).size();
        String[][] arrData = new String[rowSize][colSize];

        for (int i = 0; i < arrData.length; i++) {
            for (int j = 0; j < arrData[i].length; j++) {
                arrData[i][j] = bookingList.get(j).get(i);
            }
        }
        return arrData;
    }

    // This method returns an array of column headers from booking data file for the UI
    protected String[] getHeaderList() {
        String[] arrHeader = {"RoomNo", "08:00-09:30", "09:45-11:15", "12:15-13:45", 
                                        "14:00-15:30", "15:45-17:15", "17:30-19:00"};
        return arrHeader;
    }

    // --- Extra methods: --------------------------------------------------------------
    // ---------------------------------------------------------------------------------
    // This method gets the room number that is going to be added in the Booking list
    // it checks if the room already exists in the (RoomData) file 
    // if yes, then checks if it is listed in the (BookingData) file, if not it adds the room to Booking list  
    protected String addOneRoomIn_BookingDB(String roomNo) {
        String addMessage = "";
        LogicAdministrator admin = new LogicAdministrator();
        String path = admin.getBookingDBpath();
        String inputData = admin.createOneEmptyBookingLine(roomNo);
        // checks if the room is already listed or not
        RoomDataValidation roomVal = new RoomDataValidation();
        // checks if the room exist or need to be first created
        if (roomVal.doesRoomExist(roomNo)) {
            if ( (roomVal.isRoomListed(roomNo, path) == false) ) {
                try {
                    BufferedWriter dataWriter = new BufferedWriter(new FileWriter(path, true));
                    dataWriter.write("\n"+inputData);
                    dataWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addMessage = "Room successfully added in Booking List!" ;
            }
            else if (roomVal.isRoomListed(roomNo, path) == true) {
                addMessage = "Room already exists in Booking List!" ;
            }
        } else {
            addMessage = "First you have to create the room!"; 
        }
        return addMessage;
    }

    // This method returns all dates from (BookingData) file as an Array
    protected String[] getListOfDates() {
        LogicAdministrator admin = new LogicAdministrator();
        String path = admin.getBookingDBpath();

        ArrayList<String> dateList = new ArrayList<>();
        try {
            BufferedReader file_out = new BufferedReader(new FileReader(path));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                if (dataLine[0].equals("RoomNo")) {
                    for (int i = 1; i < dataLine.length; i++) {
                        dateList.add(dataLine[i]);
                    }
                }
            }
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] dateArray = new String[dateList.size()];
        for (int i = 0; i < dateList.size(); i++) {
            dateArray[i] = dateList.get(i);
        }
        return dateArray;
    }

    // This method returns the time that a room can be booked
    // input: (1, 2, 3, 4, 5, 6); output: (08:00-09:30, 09:45-11:15...) 
    private String getTimeSet(int timeIndex) {
        String answer = "";
        switch (timeIndex) {
            case (TIME1_08_00_INDEX): answer = TIME1_08_00_HEADER;
                break;
            case (TIME2_09_45_INDEX): answer = TIME2_09_45_HEADER;
                break;    
            case (TIME3_12_15_INDEX): answer = TIME3_12_15_HEADER;
                break;
            case (TIME4_14_00_INDEX): answer = TIME4_14_00_HEADER;
                break;
            case (TIME5_15_45_INDEX): answer = TIME5_15_45_HEADER;
                break;
            case (TIME6_17_30_INDEX): answer = TIME6_17_30_HEADER;
                break;
            default:
                break;
        }
        return answer;
    }

    protected int getIndexOfTime(String timeString) {
        int answer = 0;
        switch (timeString) {
            case ("08:00"): answer = TIME1_08_00_INDEX;
                break;
            case ("09:45"): answer = TIME2_09_45_INDEX;
                break;    
            case ("12:15"): answer = TIME3_12_15_INDEX;
                break;
            case ("14:00"): answer = TIME4_14_00_INDEX;
                break;
            case ("15:45"): answer = TIME5_15_45_INDEX;
                break;
            case ("17:30"): answer = TIME6_17_30_INDEX;
                break;
            default:
                break;
        }
        return answer;
    }

    // This method copies all booking data from old to a new file, while deleting
    // any rooms that are not existing anymore (rooms that are deleted from RoomData file)    
    protected void deleteNonExistingRoomsFromBookingList() {
        LogicAdministrator admin = new LogicAdministrator();
        String currentPath = admin.getBookingDBpath();
        admin.createEmptyBookingDatabase();

        ArrayList<String> rNum_String = new ArrayList<>();
        rNum_String = admin.getRoomDataList(admin.ROOM_NUMBER_INDEX);

        try {
            BufferedReader file_out = new BufferedReader(new FileReader(currentPath));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                // ignores the first line (column headers) and the line with the given room number
                if (!dataLine[0].equals("RoomNo")) {
                    if ( rNum_String.contains(dataLine[0]) ) {
                        String booking_Input = csvLine;
                        writeToBookingDataCSV(booking_Input);
                    }
                }
            }
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        admin.deleteDatabase(currentPath);
    }
}