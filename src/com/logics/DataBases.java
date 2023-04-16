package com.logics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DataBases {

    private static final String COMMA_DELIMITER = ",";

    private String database_Files = "src\\com\\databases\\data\\";
    private String paths_Files = "src\\com\\databases\\paths\\";
    private String userDBPath = "src\\com\\databases\\data\\UserData-";
    private String roomDBPath = "src\\com\\databases\\data\\RoomData-";
    private String bookingDBPath = "src\\com\\databases\\data\\BookingData-";
    private int numDays[];

    protected DataBases() {
    }

    // General Database methods:
    // -----------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------

    // This method returns the current date
    protected String getTheCurrentDate() {
        String currentDate = "";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        currentDate = formatter.format(date);
        return currentDate;
    }

    // This method returns the current time
    protected String getTheCurrentTime() {
        String currentTime = "";
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        currentTime = time.format(formatter);
        return currentTime;
    }

    // This method returns the path of (User, Room and Booking) Database files
    protected String getAllDatabaseNames() {
        String answer = "";
        File file = new File(database_Files);
        String[] fileList = file.list();
        for (String name : fileList) {
            answer += " " + name + "\n";
        }
        return answer;
    }

    // This method returns the paths of (User, Room and Booking) path Storage files
    protected String getAllPathStorage_DatabaseNames() {
        String answer = "";
        File file = new File(paths_Files);
        String[] fileList = file.list();
        for (String name : fileList) {
            answer += " " + name + "\n";
        }
        return answer;
    }

    // This method gets the database file path as String and if found deletes it
    // also returns a message; success or error
    protected String deleteDatabase(String deletePath) {
        String delMessage = "";
        try {
            Files.deleteIfExists(Paths.get(deletePath));
        } catch (NoSuchFileException e) {
            delMessage = "No such file/directory exists";
        } catch (DirectoryNotEmptyException e) {
            delMessage = "Directory is not empty.";
        } catch (IOException e) {
            delMessage = "Invalid permissions.";
        }
        delMessage = "File deletion successful.";
        return delMessage;
    }

    // User Database creating methods:
    // -----------------------------------------------------------------------------------------
    // User----------------User----------------User----------------User-------------------------
    // -----User------User------User------User------User------User------User--------------------
    // ----------User----------------User----------------User----------------User---------------

    // This method creates a unique path for new database file by using date and
    // time
    private String createPath_UserDB() {
        String answer = "";
        LocalDateTime date = LocalDateTime.now();
        String st1 = "" + date;
        String st2 = st1.replace(':', '-');
        String st3 = st2.substring(0, 19);
        answer = userDBPath + st3 + ".csv";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return answer;
    }

    // This method creates a CSV file with the given path for storing user data
    // the file will have one line/Header (ID,Job,FirstName,LastName,Email,Password)
    protected String createUser_EmptyDatabase() {
        String answer = "";
        String path = createPath_UserDB();
        LogicAdministrator admin = new LogicAdministrator();
        admin.saveUserDBpath(path);
        String inputDates = "ID,Job,FirstName,LastName,Email,Password";
        File csvFile = new File(path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(csvFile);
            fileWriter.write(inputDates);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            answer = "An IO Exception happened!";
        }
        answer = "An empty user database is created!";
        return answer;
    }

    // Room Database creating methods:
    // -----------------------------------------------------------------------------------------
    // Room----------------Room----------------Room----------------Room-------------------------
    // -----Room------Room------Room------Room------Room------Room------Room--------------------
    // ----------Room----------------Room----------------Room----------------Room---------------

    // This method creates a unique path for new room database file by using date
    // and time
    private String createPath_RoomDB() {
        String answer = "";
        LocalDateTime date = LocalDateTime.now();
        String st1 = "" + date;
        String st2 = st1.replace(':', '-');
        String st3 = st2.substring(0, 19);
        answer = roomDBPath + st3 + ".csv";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return answer;
    }

    // This method creates a CSV file with the given path
    // the file will have one column with Room number and ~365 days as column
    // headers
    protected String createRoom_EmptyDatabase() {
        String answer = "";
        String path = createPath_RoomDB();
        LogicAdministrator admin = new LogicAdministrator();
        admin.saveRoomDBpath(path);
        String inputDates = "Status,RoomNo,RoomName,Location,Capacity,Resources";
        File csvFile = new File(path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(csvFile);
            fileWriter.write(inputDates);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            answer = "An IO Exception happened!";
        }
        answer = "An empty room database is created!";
        return answer;
    }

    // Booking Database creating methods:
    // -----------------------------------------------------------------------------------------
    // Booking-------------------------Booking-------------------------Booking------------------
    // --------Booking---------Booking---------Booking---------Booking---------Booking----------
    // ----------------Booking-------------------------Booking-------------------------Booking--

    // This method creates a unique path for new Booking database file by using date
    // and time
    private String createPath_BookingDB() {
        String answer = "";
        LocalDateTime date = LocalDateTime.now();
        String st1 = "" + date;
        String st2 = st1.replace(':', '-');
        String st3 = st2.substring(0, 19);
        answer = bookingDBPath + st3 + ".csv";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return answer;
    }

    // This method returns the current year as an integer
    private int getYear() {
        int answer = 0;
        LocalDate date = LocalDate.now();
        String st1 = date + "";
        String dateYear = st1.substring(0, 4);
        answer = Integer.parseInt(dateYear);
        return answer;
    }

    // This method checks for leap year and returns the number of days in each month
    // as an Array
    private int[] getMonths() {
        int y = getYear();
        numDays = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if ((((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0))) {
            numDays[1] = 29;
        }
        return numDays;
    }

    // This method returns a full calender as a String for the Booking
    // database(1/1/2022,2/1/2022...)
    private String createDates() {
        String dates = "";
        int days[] = getMonths();
        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < days[i]; j++) {
                dates += COMMA_DELIMITER + (j + 1) + "/" + (i + 1) + "/" + getYear();
            }
        }
        return dates;
    }

    // This method returns the number of days in a year
    private int getDaySum() {
        int i = 0;
        int[] arr = getMonths();
        for (int j = 0; j < arr.length; j++) {
            i += arr[j];
        }
        return i;
    }

    // The method creates a String of empty Booking sets (set = "t1-t2-t3-t4-t5-t6")
    // for all existing rooms
    private String createAllBookingLine() {
        String answer = "";
        int d = getDaySum();
        ArrayList<String> rNum_String = new ArrayList<>();
        LogicAdministrator admin = new LogicAdministrator();
        rNum_String = admin.getRoomDataList(admin.ROOM_NUMBER_INDEX);
        // i starts from 1 because the element 0 is (RoomNo.) title
        for (int i = 1; i < rNum_String.size(); i++) {
            answer += "\n" + rNum_String.get(i);
            for (int j = 0; j < d; j++) {
                answer += COMMA_DELIMITER + "t1-t2-t3-t4-t5-t6";
            }
        }
        return answer;
    }

    // This method creates lines for each room with empty booking set for each day
    // (set = "t1-t2-t3-t4-t5-t6")
    private void createRoomsInCSV_Booking(String path) {
        String inputData = createAllBookingLine();
        try {
            BufferedWriter dataWriter = new BufferedWriter(new FileWriter(path, true));
            dataWriter.write(inputData);
            dataWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method creates a CSV file with the given path
    // the file will have only column with Room number and ~365 days as column
    // headers
    protected String createBooking_withRoomDatabase() {
        String answer = "";
        String path = createPath_BookingDB();
        LogicAdministrator admin = new LogicAdministrator();
        admin.saveBookingDBpath(path);
        String inputDates = "RoomNo" + createDates();
        File csvFile = new File(path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(csvFile);
            fileWriter.write(inputDates);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            answer = "An IO Exception happened!";
        }
        createRoomsInCSV_Booking(path);
        answer = "A Booking database with rooms and dates is created!";
        return answer;
    }

    protected String createBooking_EmptyDatabase() {
        String answer = "";
        String path = createPath_BookingDB();
        LogicAdministrator admin = new LogicAdministrator();
        admin.saveBookingDBpath(path);
        String inputDates = "RoomNo" + createDates();
        File csvFile = new File(path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(csvFile);
            fileWriter.write(inputDates);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            answer = "An IO Exception happened!";
        }
        answer = "An empty Booking database is created!";
        return answer;
    }

    protected String createOneNewBookingLine(String roomNo) {
        String answer = "";
        int d = getDaySum();
        answer += roomNo;
        for (int j = 0; j < d; j++) {
            answer += COMMA_DELIMITER + "t1-t2-t3-t4-t5-t6";
        }
        return answer;
    }
}