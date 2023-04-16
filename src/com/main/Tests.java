package com.main;

//import java.util.ArrayList;
//import java.util.ArrayList;

//import com.logics.*;
//import com.validation.*;

// Driver Code
public class Tests {

    public static void toWelcomePage() {
        // new Welcome_UI();
    }

    public static void csvUserTEST() {
        /*
         * ArrayList<String> arrU = new ArrayList<>();
         * User_Data_RW udTest = new User_Data_RW();
         * System.out.println(udTest.deleteUser("35847938"));
         * System.out.println(udTest.deleteUser("35847938"));
         * arrU = udTest.getUserData("id");
         * System.out.println(arrU);
         * 
         * String[][] newArray = udTest.getUserDataIn2DArray();
         * for (int i = 0; i < newArray.length; i++) {
         * for (int j = 0; j < newArray[i].length; j++) {
         * System.out.print(newArray[i][j]);
         * }
         * System.out.println("");
         * }
         */
    }

    public static void csvRoomTEST() {
        // Room_Data_RW rdTest = new Room_Data_RW();
        // rdTest.sortRoomsByNumber();
        // rdTest.readCSV_Room();
        // ArrayList<String> arrList = new ArrayList<>();
        // arrList = rdTest.getRoomData("number");

        /*
         * String[][] newArray = rdTest.getDataList();
         * for (int i = 0; i < newArray.length; i++) {
         * for (int j = 0; j < newArray[i].length; j++) {
         * System.out.print(newArray[i][j]);
         * }
         * System.out.println("");
         * }
         */
    }

    public static void csvBookingTEST() {
        /*
         * Booking_Data_RW bdTest = new Booking_Data_RW();
         * String[] st = bdTest.getBookingTimeSetOfARoom("101", "1/1/2022");
         * for (String string : st) {
         * System.out.print(string + ",");
         * }
         * 
         * LogicAdministrator admin = new LogicAdministrator();
         * String[] dateList = admin.getDateList();
         * String[] roomList = admin.getRoomNumbers();
         * for (int i = 0; i < dateList.length; i++) {
         * for (int j = 0; j < roomList.length; j++) {
         * String date = dateList[i];
         * String roomNo = roomList[j];
         * Booking_Data_RW bdTest = new Booking_Data_RW();
         * String[] st = bdTest.getBookingTimeSetOfARoom(roomNo, date);
         * for (String string : st) {
         * System.out.print(string+",");
         * }
         * }
         * System.out.println();
         * }
         * 
         * 
         * for (int i = 0; i < arr.length; i++) {
         * System.out.println(arr[i]);
         * }
         * 
         * Booking_Data_RW bdTest = new Booking_Data_RW();
         * bdTest.toReadCSV_Booking();
         * 
         * String[][] newArray = bdTest.getDataList();
         * for (int i = 0; i < newArray.length; i++) {
         * for (int j = 0; j < newArray[i].length; j++) {
         * System.out.print(newArray[i][j]);
         * }
         * System.out.println("");
         * }
         */
    }

    public static void csvTableTEST() {
        /*
         * Booking_Table tdTest = new Booking_Table(2022);
         * System.out.println(tdTest.addRoomInCSV_Booking(201));
         * tdTest.createCSV_Booking();
         * tdTest.createRoomsInCSV_Booking();
         * 
         * for (int i = 100; i < 500; i++) {
         * Booking_Table tdTest = new Booking_Table(2022);
         * if (tdTest.isRoomListed(i)) {
         * System.out.println("true for "+i);
         * }
         * }
         */
        // System.out.println(""+tdTest.addRoomInCSV_Booking(210));
    }

    public static void roomValidationTEST() {
        /*
         * String p = "com\\Database\\BOOKING_2022.csv";
         * RoomDataValidation rmVal = new RoomDataValidation();
         * 
         * for (int i = 100; i < 500; i++) {
         * if (rmVal.isRoomListed(i, p)) {
         * System.out.println("True for "+i);
         * }
         * }
         * 
         * for (int i = 100; i < 500; i++) {
         * if (rmVal.doesRoomExist(i)) {
         * System.out.println("True for "+i);
         * }
         * }
         */
    }

    public static void userValidationTEST() {
        /*
         * UserDataValidation usVal = new UserDataValidation();
         * int i = 65874895;
         * if (usVal.doesUserExist(i)) {
         * System.out.println("User "+i+" Exist");
         * }
         */
    }

    public static void inputValidationTEST() {
        /*
         * UserDataValidation inVal = new UserDataValidation();
         * String[] arr = {"65#874895", "rtr54321", "65465 ", "2 54633", "5146jhh",
         * "634.656", "5'685465"};
         * for (int j = 0; j < arr.length; j++) {
         * if (inVal.isID_Valid(arr[j])) {
         * System.out.println("ID: "+arr[j]+" is valid");
         * }
         * if (!inVal.isID_Valid(arr[j])) {
         * System.out.println("ID: "+arr[j]+" is invalid");
         * }
         * }
         */
        /*
         * String[] arr = {"65#874895", "rtr54321", "6546,5", "2 54633", "5146jhh",
         * "634.656", "5'685465"};
         * for (int j = 0; j < arr.length; j++) {
         * if (inVal.isPass_Valid(arr[j])) {
         * System.out.println("Password: "+arr[j]+" is valid");
         * }
         * if (!inVal.isPass_Valid(arr[j])) {
         * System.out.println("Password: "+arr[j]+" is invalid");
         * }
         * }
         */
        /*
         * String[] arr = {"ht#874895", "rtr54321", "kjt lkr", "634.656", "5'685465"};
         * for (int j = 0; j < arr.length; j++) {
         * if (inVal.isFname_Valid(arr[j])) {
         * System.out.println("name: "+arr[j]+" is valid");
         * }
         * if (!inVal.isFname_Valid(arr[j])) {
         * System.out.println("name: "+arr[j]+" is invalid");
         * }
         * }
         * 
         * String[] arr = { " max@gmail.com", "finn@gmx.de", "tom@yahoo.com",
         * "tim@yahoo.com",
         * "sam@gmail.com", "meier.janis@yahoo.com", "florick@gmx.org ",
         * "filip.@gmx.org", "bauer@yahoo.com", "j,meier@gmx.de" };
         * for (int j = 0; j < arr.length; j++) {
         * if (inVal.isEmail_Valid(arr[j])) {
         * System.out.println("Email: " + arr[j] + " is valid");
         * }
         * if (!inVal.isEmail_Valid(arr[j])) {
         * System.out.println("Email: " + arr[j] + " is invalid");
         * }
         * }
         */
    }

    public static void smallTEST() {
        /*
         * for (int i = 100; i <= 999; i++) {
         * System.out.print('\u0022'+""+i+""+'\u0022'+", ");
         * if (i%10 == 0) {
         * System.out.println("");
         * }
         * }
         * 
         * for (int i = 101; i <= 999; i++) {
         * System.out.print('\u0022' + "" + i + "" + '\u0022' + ", ");
         * if (i % 10 == 0) {
         * System.out.println("");
         * }
         * }
         */
    }

    public static void databasesTEST() {
        /*
         * DataBases databases = new DataBases();
         * System.out.println();
         * System.out.println();
         * System.out.println();
         * System.out.println();
         * System.out.println(databases.getYear());
         * 
         * databases.createUser_EmptyDatabase();
         * databases.createRoom_EmptyDatabase();
         * databases.createBooking_EmptyDatabase();
         * Pathfinder pf = new Pathfinder();
         * databases.createRoomsInCSV_Booking(pf.getLastBookingDBpath());
         * System.out.println(databases.createAllBookingLine());
         * 
         * for (int i = 0; i < 5; i++) {
         * System.out.println(databases.getPath_UserDB());
         * }
         * 
         * System.out.println();
         * for (int i = 0; i < 5; i++) {
         * System.out.println(databases.getPath_BookingDB());
         * }
         * System.out.println();
         * 
         * for (int i = 0; i < 5; i++) {
         * System.out.println(databases.getPath_RoomDB());
         * }
         */
    }

    public static void pathfinderTEST() {
        /*
         * Pathfinder pathfinder = new Pathfinder();
         * pathfinder.createDatabaseforFilePaths("user");
         * //pathfinder.createDatabaseforFilePaths("room");
         * //pathfinder.createDatabaseforFilePaths("booking");
         * 
         * System.out.println();
         * System.out.println( pathfinder.getFileUserPaths() );
         * System.out.println( pathfinder.getFileRoomPaths() );
         * System.out.println( pathfinder.getFileBookingPaths() );
         */
    }

    public static void roomSortingTEST() {
        /*
         * RoomSorting rSorting = new RoomSorting();
         * ArrayList<String> element = rSorting.getRoomSortedByNumber();
         * System.out.println(element);
         * for (String s : element) {
         * System.out.println(s);
         * }
         */
    }

    public static void administratorTEST() {
        /*
         * String answer = "";
         * LogicAdministrator admin = new LogicAdministrator();
         * admin.deleteNonExistingRooms();
         * String[][] arr = admin.getBookingFullTable();
         * for (int i = 0; i < arr.length; i++) {
         * for (int j = 0; j < arr[0].length; j++) {
         * answer += "" + arr[i][j] + ", ";
         * }
         * System.out.println(answer);
         * answer = "";
         * }
         * answer += admin.getCurrentDate();
         * answer += " " + admin.getCurrentTime();
         * System.out.println(answer);
         */
    }

    public static void automaticBookingTEST() {
        /*
         * LogicAdministrator admin = new LogicAdministrator();
         * String[] dateList = admin.getDateList();
         * String[] roomList = admin.getRoomNumbers();
         * ArrayList<String> uIdList = new ArrayList<String>();
         * uIdList = admin.getUserDataList(admin.USER_ID_INDEX);
         * 
         * for (int i = 0; i < dateList.length; i++) {
         * for (int j = 0; j < roomList.length; j++) {
         * String date = dateList[i];
         * String roomNo = roomList[j];
         * int a = (int) (Math.random() * 11) + 1;
         * String userId = uIdList.get(a);
         * int bookingTimeIndex = (int) (Math.random() * 6);
         * if (bookingTimeIndex < 6) {
         * String answer = admin.bookARoom(userId, roomNo, date, bookingTimeIndex);
         * System.out.println(answer);
         * System.out.println(userId + "  " + roomNo + "  " + date + "  " +
         * bookingTimeIndex);
         * }
         * }
         * }
         * System.out.println(roomList.length);
         * for (String string : roomList) {
         * System.out.println(string);
         * }
         */
    }

    public static void main(String[] args) throws Exception {
        // toWelcomePage();
        // csvUserTEST();
        // csvRoomTEST();
        // csvBookingTEST();
        // csvTableTEST();
        // roomValidationTEST();
        // userValidationTEST();
        // inputValidationTEST();
        // smallTEST();
        // databasesTEST();
        // pathfinderTEST();
        // roomSortingTEST();
        // administratorTEST();
        // automaticBookingTEST();
    }
}