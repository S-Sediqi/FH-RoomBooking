package com.UI.admin;

import com.logics.*;
import com.main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoomReg_UI extends JFrame implements ActionListener {

    // Elements of the UI for Room Registration
    private Container c;
    private JLabel title_Lb;
    private JLabel datetime_Lb;
    private JLabel rNum_Lb;
    private JComboBox<String> rNum_CmBx;
    private JLabel rCapacity_Lb;
    private JComboBox<String> rCapacity_CmBx;
    private JLabel rName_Lb;
    private JTextField rName_TF;
    private JLabel rLocation_Lb;
    private JTextField rLocation_TF;
    private JLabel rResource_Lb;
    private JTextField rResources_TF;
    private JLabel res_PC_Lb;
    private JCheckBox res_WBoard_mounted_CB;
    private JCheckBox res_WBoard_portable_CB;
    private JCheckBox res_CBoard_mounted_CB;
    private JCheckBox res_CBoard_portable_CB;
    private JCheckBox res_TouchTV_CB;
    private JCheckBox res_TV_CB;
    private JCheckBox res_SBoard_CB;
    private JCheckBox res_Projector_CB;
    private JCheckBox res_Speaker_CB;
    private JCheckBox res_Sink_CB;
    private JLabel rStatus_Lb;
    private JRadioButton rOpen_Bt;
    private JRadioButton rClose_Bt;
    private ButtonGroup rStatus_BG;
    private JCheckBox info_CB;
    private JTextArea info_TA;
    private JLabel message_Lb;
    private JButton save_Bt;
    private JButton reset_Bt;
    private JButton goBack_Bt;
    private JButton roomSetting_Bt;

    // The constructor creates each elements of the Room Registration UI
    public RoomReg_UI() {
        setTitle("Registration");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Creates a title for the input page
        title_Lb = new JLabel("Please Enter Room Information");
        title_Lb.setFont(new Font("Arial", Font.PLAIN, 28));
        title_Lb.setSize(400, 30);
        title_Lb.setLocation(50, 20);
        c.add(title_Lb);

        // creates a label to display current date and time
        LogicAdministrator admin = new LogicAdministrator();
        String datetime = admin.getCurrentDate() + "  " + admin.getCurrentTime();
        datetime_Lb = new JLabel(datetime);
        datetime_Lb.setFont(new Font("Arial", Font.PLAIN, 15));
        datetime_Lb.setSize(150, 25);
        datetime_Lb.setLocation(690, 23);
        c.add(datetime_Lb);

        // creates a label for the room number list
        rNum_Lb = new JLabel("Room No.");
        rNum_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        rNum_Lb.setSize(90, 20);
        rNum_Lb.setLocation(40, 80);
        c.add(rNum_Lb);

        // creates a list for user to select room number between (101-999)
        String num[] = {
                "101", "102", "103", "104", "105", "106", "107", "108", "109", "110",
                "111", "112", "113", "114", "115", "116", "117", "118", "119", "120",
                "121", "122", "123", "124", "125", "126", "127", "128", "129", "130",
                "131", "132", "133", "134", "135", "136", "137", "138", "139", "140",
                "141", "142", "143", "144", "145", "146", "147", "148", "149", "150",
                "151", "152", "153", "154", "155", "156", "157", "158", "159", "160",
                "161", "162", "163", "164", "165", "166", "167", "168", "169", "170",
                "171", "172", "173", "174", "175", "176", "177", "178", "179", "180",
                "181", "182", "183", "184", "185", "186", "187", "188", "189", "190",
                "191", "192", "193", "194", "195", "196", "197", "198", "199", "200",
                "201", "202", "203", "204", "205", "206", "207", "208", "209", "210",
                "211", "212", "213", "214", "215", "216", "217", "218", "219", "220",
                "221", "222", "223", "224", "225", "226", "227", "228", "229", "230",
                "231", "232", "233", "234", "235", "236", "237", "238", "239", "240",
                "241", "242", "243", "244", "245", "246", "247", "248", "249", "250",
                "251", "252", "253", "254", "255", "256", "257", "258", "259", "260",
                "261", "262", "263", "264", "265", "266", "267", "268", "269", "270",
                "271", "272", "273", "274", "275", "276", "277", "278", "279", "280",
                "281", "282", "283", "284", "285", "286", "287", "288", "289", "290",
                "291", "292", "293", "294", "295", "296", "297", "298", "299", "300",
                "301", "302", "303", "304", "305", "306", "307", "308", "309", "310",
                "311", "312", "313", "314", "315", "316", "317", "318", "319", "320",
                "321", "322", "323", "324", "325", "326", "327", "328", "329", "330",
                "331", "332", "333", "334", "335", "336", "337", "338", "339", "340",
                "341", "342", "343", "344", "345", "346", "347", "348", "349", "350",
                "351", "352", "353", "354", "355", "356", "357", "358", "359", "360",
                "361", "362", "363", "364", "365", "366", "367", "368", "369", "370",
                "371", "372", "373", "374", "375", "376", "377", "378", "379", "380",
                "381", "382", "383", "384", "385", "386", "387", "388", "389", "390",
                "391", "392", "393", "394", "395", "396", "397", "398", "399", "400",
                "401", "402", "403", "404", "405", "406", "407", "408", "409", "410",
                "411", "412", "413", "414", "415", "416", "417", "418", "419", "420",
                "421", "422", "423", "424", "425", "426", "427", "428", "429", "430",
                "431", "432", "433", "434", "435", "436", "437", "438", "439", "440",
                "441", "442", "443", "444", "445", "446", "447", "448", "449", "450",
                "451", "452", "453", "454", "455", "456", "457", "458", "459", "460",
                "461", "462", "463", "464", "465", "466", "467", "468", "469", "470",
                "471", "472", "473", "474", "475", "476", "477", "478", "479", "480",
                "481", "482", "483", "484", "485", "486", "487", "488", "489", "490",
                "491", "492", "493", "494", "495", "496", "497", "498", "499", "500",
                "501", "502", "503", "504", "505", "506", "507", "508", "509", "510",
                "511", "512", "513", "514", "515", "516", "517", "518", "519", "520",
                "521", "522", "523", "524", "525", "526", "527", "528", "529", "530",
                "531", "532", "533", "534", "535", "536", "537", "538", "539", "540",
                "541", "542", "543", "544", "545", "546", "547", "548", "549", "550",
                "551", "552", "553", "554", "555", "556", "557", "558", "559", "560",
                "561", "562", "563", "564", "565", "566", "567", "568", "569", "570",
                "571", "572", "573", "574", "575", "576", "577", "578", "579", "580",
                "581", "582", "583", "584", "585", "586", "587", "588", "589", "590",
                "591", "592", "593", "594", "595", "596", "597", "598", "599", "600",
                "601", "602", "603", "604", "605", "606", "607", "608", "609", "610",
                "611", "612", "613", "614", "615", "616", "617", "618", "619", "620",
                "621", "622", "623", "624", "625", "626", "627", "628", "629", "630",
                "631", "632", "633", "634", "635", "636", "637", "638", "639", "640",
                "641", "642", "643", "644", "645", "646", "647", "648", "649", "650",
                "651", "652", "653", "654", "655", "656", "657", "658", "659", "660",
                "661", "662", "663", "664", "665", "666", "667", "668", "669", "670",
                "671", "672", "673", "674", "675", "676", "677", "678", "679", "680",
                "681", "682", "683", "684", "685", "686", "687", "688", "689", "690",
                "691", "692", "693", "694", "695", "696", "697", "698", "699", "700",
                "701", "702", "703", "704", "705", "706", "707", "708", "709", "710",
                "711", "712", "713", "714", "715", "716", "717", "718", "719", "720",
                "721", "722", "723", "724", "725", "726", "727", "728", "729", "730",
                "731", "732", "733", "734", "735", "736", "737", "738", "739", "740",
                "741", "742", "743", "744", "745", "746", "747", "748", "749", "750",
                "751", "752", "753", "754", "755", "756", "757", "758", "759", "760",
                "761", "762", "763", "764", "765", "766", "767", "768", "769", "770",
                "771", "772", "773", "774", "775", "776", "777", "778", "779", "780",
                "781", "782", "783", "784", "785", "786", "787", "788", "789", "790",
                "791", "792", "793", "794", "795", "796", "797", "798", "799", "800",
                "801", "802", "803", "804", "805", "806", "807", "808", "809", "810",
                "811", "812", "813", "814", "815", "816", "817", "818", "819", "820",
                "821", "822", "823", "824", "825", "826", "827", "828", "829", "830",
                "831", "832", "833", "834", "835", "836", "837", "838", "839", "840",
                "841", "842", "843", "844", "845", "846", "847", "848", "849", "850",
                "851", "852", "853", "854", "855", "856", "857", "858", "859", "860",
                "861", "862", "863", "864", "865", "866", "867", "868", "869", "870",
                "871", "872", "873", "874", "875", "876", "877", "878", "879", "880",
                "881", "882", "883", "884", "885", "886", "887", "888", "889", "890",
                "891", "892", "893", "894", "895", "896", "897", "898", "899", "900",
                "901", "902", "903", "904", "905", "906", "907", "908", "909", "910",
                "911", "912", "913", "914", "915", "916", "917", "918", "919", "920",
                "921", "922", "923", "924", "925", "926", "927", "928", "929", "930",
                "931", "932", "933", "934", "935", "936", "937", "938", "939", "940",
                "941", "942", "943", "944", "945", "946", "947", "948", "949", "950",
                "951", "952", "953", "954", "955", "956", "957", "958", "959", "960",
                "961", "962", "963", "964", "965", "966", "967", "968", "969", "970",
                "971", "972", "973", "974", "975", "976", "977", "978", "979", "980",
                "981", "982", "983", "984", "985", "986", "987", "988", "989", "990",
                "991", "992", "993", "994", "995", "996", "997", "998", "999", };
        rNum_CmBx = new JComboBox<>(num);
        rNum_CmBx.addActionListener(this);
        rNum_CmBx.setFont(new Font("Arial", Font.PLAIN, 15));
        rNum_CmBx.setSize(80, 22);
        rNum_CmBx.setLocation(140, 80);
        c.add(rNum_CmBx);

        // creates a label for the room capacity list
        rCapacity_Lb = new JLabel("Room Cap.");
        rCapacity_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        rCapacity_Lb.setSize(100, 22);
        rCapacity_Lb.setLocation(250, 80);
        c.add(rCapacity_Lb);

        // creates a list for user to select room capacity between (1-400) person
        String cap[] = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
                "51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
                "61", "62", "63", "64", "65", "66", "67", "68", "69", "70",
                "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
                "81", "82", "83", "84", "85", "86", "87", "88", "89", "90",
                "91", "92", "93", "94", "95", "96", "97", "98", "99", "100",
                "101", "102", "103", "104", "105", "106", "107", "108", "109", "110",
                "111", "112", "113", "114", "115", "116", "117", "118", "119", "120",
                "121", "122", "123", "124", "125", "126", "127", "128", "129", "130",
                "131", "132", "133", "134", "135", "136", "137", "138", "139", "140",
                "141", "142", "143", "144", "145", "146", "147", "148", "149", "150",
                "151", "152", "153", "154", "155", "156", "157", "158", "159", "160",
                "161", "162", "163", "164", "165", "166", "167", "168", "169", "170",
                "171", "172", "173", "174", "175", "176", "177", "178", "179", "180",
                "181", "182", "183", "184", "185", "186", "187", "188", "189", "190",
                "191", "192", "193", "194", "195", "196", "197", "198", "199", "200",
                "201", "202", "203", "204", "205", "206", "207", "208", "209", "210",
                "211", "212", "213", "214", "215", "216", "217", "218", "219", "220",
                "221", "222", "223", "224", "225", "226", "227", "228", "229", "230",
                "231", "232", "233", "234", "235", "236", "237", "238", "239", "240",
                "241", "242", "243", "244", "245", "246", "247", "248", "249", "250",
                "251", "252", "253", "254", "255", "256", "257", "258", "259", "260",
                "261", "262", "263", "264", "265", "266", "267", "268", "269", "270",
                "271", "272", "273", "274", "275", "276", "277", "278", "279", "280",
                "281", "282", "283", "284", "285", "286", "287", "288", "289", "290",
                "291", "292", "293", "294", "295", "296", "297", "298", "299", "300",
                "301", "302", "303", "304", "305", "306", "307", "308", "309", "310",
                "311", "312", "313", "314", "315", "316", "317", "318", "319", "320",
                "321", "322", "323", "324", "325", "326", "327", "328", "329", "330",
                "331", "332", "333", "334", "335", "336", "337", "338", "339", "340",
                "341", "342", "343", "344", "345", "346", "347", "348", "349", "350",
                "351", "352", "353", "354", "355", "356", "357", "358", "359", "360",
                "361", "362", "363", "364", "365", "366", "367", "368", "369", "370",
                "371", "372", "373", "374", "375", "376", "377", "378", "379", "380",
                "381", "382", "383", "384", "385", "386", "387", "388", "389", "390",
                "391", "392", "393", "394", "395", "396", "397", "398", "399", "400", };
        rCapacity_CmBx = new JComboBox<>(cap);
        rCapacity_CmBx.addActionListener(this);
        rCapacity_CmBx.setFont(new Font("Arial", Font.PLAIN, 15));
        rCapacity_CmBx.setSize(80, 22);
        rCapacity_CmBx.setLocation(370, 80);
        c.add(rCapacity_CmBx);

        // creates a label for the room name input box
        rName_Lb = new JLabel("Room Name");
        rName_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        rName_Lb.setSize(120, 20);
        rName_Lb.setLocation(40, 120);
        c.add(rName_Lb);

        // creates a field for user to enter the room name
        rName_TF = new JTextField();
        rName_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        rName_TF.setSize(250, 20);
        rName_TF.setLocation(200, 120);
        c.add(rName_TF);

        // creates a label for the room location input box
        rLocation_Lb = new JLabel("Location");
        rLocation_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        rLocation_Lb.setSize(100, 20);
        rLocation_Lb.setLocation(40, 160);
        c.add(rLocation_Lb);

        // creates a field for the user to enter the room location
        rLocation_TF = new JTextField();
        rLocation_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        rLocation_TF.setSize(250, 20);
        rLocation_TF.setLocation(200, 160);
        c.add(rLocation_TF);

        // creates a label for the room resource input box
        rResource_Lb = new JLabel("Resources");
        rResource_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        rResource_Lb.setSize(100, 20);
        rResource_Lb.setLocation(40, 200);
        c.add(rResource_Lb);

        // creates a field for the user to enter room resources
        rResources_TF = new JTextField();
        rResources_TF.setFont(new Font("Arial", Font.PLAIN, 15));
        rResources_TF.setSize(250, 20);
        rResources_TF.setLocation(200, 200);
        c.add(rResources_TF);

        // info for user, how to enter a data in resource field
        res_PC_Lb = new JLabel("For Computers enter: PC<amount>  e.g., PC25 or PC18...");
        res_PC_Lb.setFont(new Font("Arial", Font.ITALIC, 15));
        res_PC_Lb.setSize(450, 20);
        res_PC_Lb.setLocation(40, 240);
        c.add(res_PC_Lb);

        // checkbox for resource selection for a white board mounted on the wall
        res_WBoard_mounted_CB = new JCheckBox("mounted white board");
        res_WBoard_mounted_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_WBoard_mounted_CB.setSize(200, 20);
        res_WBoard_mounted_CB.setLocation(40, 280);
        c.add(res_WBoard_mounted_CB);

        // checkbox for resource selection for a portable white board
        res_WBoard_portable_CB = new JCheckBox("portable white board");
        res_WBoard_portable_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_WBoard_portable_CB.setSize(200, 20);
        res_WBoard_portable_CB.setLocation(250, 280);
        c.add(res_WBoard_portable_CB);

        // checkbox for resource selection for a chalk (black/green) board mounted on
        // the wall
        res_CBoard_mounted_CB = new JCheckBox("mounted chalk board");
        res_CBoard_mounted_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_CBoard_mounted_CB.setSize(200, 20);
        res_CBoard_mounted_CB.setLocation(40, 320);
        c.add(res_CBoard_mounted_CB);

        // checkbox for resource selection for a portable chalk (black7green) board
        res_CBoard_portable_CB = new JCheckBox("portable chalk board");
        res_CBoard_portable_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_CBoard_portable_CB.setSize(200, 20);
        res_CBoard_portable_CB.setLocation(250, 320);
        c.add(res_CBoard_portable_CB);

        // checkbox for resource selection for touch screen smart TV
        res_TouchTV_CB = new JCheckBox("touchable TV");
        res_TouchTV_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_TouchTV_CB.setSize(150, 20);
        res_TouchTV_CB.setLocation(40, 360);
        c.add(res_TouchTV_CB);

        // checkbox for resource selection for a TV
        res_TV_CB = new JCheckBox("TV");
        res_TV_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_TV_CB.setSize(80, 20);
        res_TV_CB.setLocation(200, 360);
        c.add(res_TV_CB);

        // checkbox for resource selection for a smart/touchable board
        res_SBoard_CB = new JCheckBox("smart board");
        res_SBoard_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_SBoard_CB.setSize(150, 20);
        res_SBoard_CB.setLocation(300, 360);
        c.add(res_SBoard_CB);

        // checkbox for resource selection for a projector on the ceilling
        res_Projector_CB = new JCheckBox("projector");
        res_Projector_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_Projector_CB.setSize(100, 20);
        res_Projector_CB.setLocation(40, 400);
        c.add(res_Projector_CB);

        // checkbox for resource selection for a speaker/loudspeaker
        res_Speaker_CB = new JCheckBox("speaker");
        res_Speaker_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_Speaker_CB.setSize(100, 20);
        res_Speaker_CB.setLocation(200, 400);
        c.add(res_Speaker_CB);

        // checkbox for resource selection for a sink in the room
        res_Sink_CB = new JCheckBox("sink");
        res_Sink_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        res_Sink_CB.setSize(100, 20);
        res_Sink_CB.setLocation(300, 400);
        c.add(res_Sink_CB);

        // creates a label for the room status (open/close)
        rStatus_Lb = new JLabel("Room Status");
        rStatus_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        rStatus_Lb.setSize(120, 20);
        rStatus_Lb.setLocation(40, 440);
        c.add(rStatus_Lb);

        // creates a selection for a room to register as open
        rOpen_Bt = new JRadioButton("OPEN");
        rOpen_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        rOpen_Bt.setSelected(true);
        rOpen_Bt.setSize(90, 20);
        rOpen_Bt.setLocation(200, 440);
        c.add(rOpen_Bt);

        // creates a selection for a room to register as close
        rClose_Bt = new JRadioButton("CLOSE");
        rClose_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        rClose_Bt.setSelected(false);
        rClose_Bt.setSize(80, 20);
        rClose_Bt.setLocation(290, 440);
        c.add(rClose_Bt);

        // creates group for the (open/close) options
        rStatus_BG = new ButtonGroup();
        rStatus_BG.add(rOpen_Bt);
        rStatus_BG.add(rClose_Bt);

        // reminds the user to recheck the given data
        info_CB = new JCheckBox("All information is correct.");
        info_CB.setFont(new Font("Arial", Font.PLAIN, 15));
        info_CB.setSize(250, 20);
        info_CB.setLocation(150, 480);
        c.add(info_CB);

        // registers the room in database, if all conditions are meet
        save_Bt = new JButton("Save");
        save_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        save_Bt.setSize(90, 20);
        save_Bt.setLocation(100, 520);
        save_Bt.setFocusable(false);
        save_Bt.addActionListener(this);
        c.add(save_Bt);

        // resets or clears all the boxes for user to enter new data
        reset_Bt = new JButton("Reset");
        reset_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        reset_Bt.setSize(90, 20);
        reset_Bt.setLocation(200, 520);
        reset_Bt.addActionListener(this);
        c.add(reset_Bt);

        // closes this specific input panel for user
        goBack_Bt = new JButton("Back");
        goBack_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        goBack_Bt.setSize(120, 20);
        goBack_Bt.setLocation(300, 520);
        goBack_Bt.setFocusable(false);
        goBack_Bt.addActionListener(this);
        c.add(goBack_Bt);

        // after the user registered, this button can open the login panel
        roomSetting_Bt = new JButton("Room Setting");
        roomSetting_Bt.setFont(new Font("Arial", Font.PLAIN, 15));
        roomSetting_Bt.setSize(300, 20);
        roomSetting_Bt.setLocation(500, 520);
        roomSetting_Bt.setFocusable(false);
        roomSetting_Bt.addActionListener(this);
        c.add(roomSetting_Bt);

        // shows the entered data and the errors
        info_TA = new JTextArea();
        info_TA.setFont(new Font("Arial", Font.PLAIN, 15));
        info_TA.setSize(320, 400);
        info_TA.setLocation(500, 80);
        info_TA.setLineWrap(true);
        info_TA.setEditable(false);
        c.add(info_TA);

        // warns if the check box in not checked
        message_Lb = new JLabel("");
        message_Lb.setFont(new Font("Arial", Font.PLAIN, 20));
        message_Lb.setSize(500, 25);
        message_Lb.setLocation(100, 560);
        c.add(message_Lb);

        setVisible(true);
    }

    // Receives the user command through buttons and performs the operations
    // specified to each button
    @Override
    public void actionPerformed(ActionEvent e) {
        // saves the input data into the database after data validation is correct.
        if (e.getSource() == save_Bt) {
            boolean resourceSelection = false;
            String status = "";
            String resCheckBox = "WiFi";
            String infoOutput = "";
            String messages = "";

            // The box should be first checked by the user
            if (info_CB.isSelected()) {

                // stores each entered data into a String for further use
                String number = rNum_CmBx.getItemAt(rNum_CmBx.getSelectedIndex());
                String capacity = rCapacity_CmBx.getItemAt(rCapacity_CmBx.getSelectedIndex());
                String name = rName_TF.getText();
                String location = rLocation_TF.getText();
                String resource = "";
                // first the resources input by user is checked for hyphen sign(-)
                // if any hyphen sign exist it is replaced by the underline sign(_)
                // this is because in (RoomValidation) class each resource is separated
                // according to hyphen sign for further applications
                String res_notChecked = rResources_TF.getText();
                if (res_notChecked.contains("-")) {
                    resource += res_notChecked.replace('-', '_');

                } else {
                    resource = res_notChecked;
                }
                // specifies if the room status (open/close)
                // later could be changed
                if (rOpen_Bt.isSelected()) {
                    status = "OPEN";
                }
                if (rClose_Bt.isSelected()) {
                    status = "CLOSE";
                }

                // checks if minmum one resource is selected for the room from checkboxes
                if (!res_WBoard_mounted_CB.isSelected() && !res_WBoard_portable_CB.isSelected() &&
                        !res_CBoard_mounted_CB.isSelected() && !res_CBoard_portable_CB.isSelected() &&
                        !res_TouchTV_CB.isSelected() && !res_TV_CB.isSelected() && !res_SBoard_CB.isSelected() &&
                        !res_Projector_CB.isSelected() && !res_Speaker_CB.isSelected() && !res_Sink_CB.isSelected()) {
                    resourceSelection = true;
                }
                if (res_WBoard_mounted_CB.isSelected()) {
                    resCheckBox += "-wbm"; // wbm = white board mounted
                }
                if (res_WBoard_portable_CB.isSelected()) {
                    resCheckBox += "-wbp"; // wbp = white board portable
                }
                if (res_CBoard_mounted_CB.isSelected()) {
                    resCheckBox += "-cbm"; // cbm = chalk board mounted
                }
                if (res_CBoard_portable_CB.isSelected()) {
                    resCheckBox += "-cbp"; // cbp = chalk board portable
                }
                if (res_TouchTV_CB.isSelected()) {
                    resCheckBox += "-ttv"; // tsTV = touch screen TV
                }
                if (res_TV_CB.isSelected()) {
                    resCheckBox += "-tv"; // tv = TV
                }
                if (res_SBoard_CB.isSelected()) {
                    resCheckBox += "-sb"; // sm = smart board
                }
                if (res_Projector_CB.isSelected()) {
                    resCheckBox += "-pj"; // pj = projector
                }
                if (res_Speaker_CB.isSelected()) {
                    resCheckBox += "-sp"; // sp = speaker
                }
                if (res_Sink_CB.isSelected()) {
                    resCheckBox += "-sk"; // sk = sink
                }
                // if there is input in resource text field, adds the data with other selected
                // ones
                if (!resource.isEmpty()) {
                    resource = resource + "-" + resCheckBox;
                    // if there is no data entered in resource text field, return only selected
                    // checkboxes
                } else if (resource.isEmpty()) {
                    resource = resCheckBox;
                }

                // stores all input data in one String to show in (infoBox)
                infoOutput = "Status: " + status + "\n" +
                        "Number: " + number + "\n" +
                        "Name: " + name + "\n" +
                        "Location: " + location + "\n" +
                        "Capacity: " + capacity + "\n" +
                        "Resources: " + resource + "\n";

                // A constructor of the class (Room_Input_Check) is created
                // The method (checkRoomInputData) is used by passing all input data into it
                // All input data are checked by the method:
                // if input field is empty
                // if input data type is correct (only integer, only letters, mixed with
                // symbols)
                // if forbidden data (space or comma) is used
                // Then, if all conditions are correct, the method stores the data into Room
                // database
                // else does not store the data and corresponds with a specified error message
                LogicAdministrator admin = new LogicAdministrator();
                messages = admin.checkAndAddToRoomDB(status, number, name, location, capacity, resource,
                        resourceSelection);
                info_TA.setText(infoOutput + messages);
                info_TA.setEditable(false);
            }

            // if the box is not checked, prints the alert message
            if (!info_CB.isSelected()) {
                info_TA.setText("");
                // infoTxtArea.setText("");
                message_Lb.setText("Please check if all information is correct");
            }
        }

        // resets and clear all the data from their fields
        else if (e.getSource() == reset_Bt) {
            String def = "";
            rName_TF.setText(def);
            rLocation_TF.setText(def);
            rResources_TF.setText(def);
            message_Lb.setText(def);
            info_TA.setText(def);
            rNum_CmBx.setSelectedIndex(0);
            rCapacity_CmBx.setSelectedIndex(0);
            res_WBoard_mounted_CB.setSelected(false);
            res_WBoard_portable_CB.setSelected(false);
            res_CBoard_mounted_CB.setSelected(false);
            res_CBoard_portable_CB.setSelected(false);
            res_TouchTV_CB.setSelected(false);
            res_TV_CB.setSelected(false);
            res_SBoard_CB.setSelected(false);
            res_Projector_CB.setSelected(false);
            res_Speaker_CB.setSelected(false);
            res_Sink_CB.setSelected(false);
            info_CB.setSelected(false);
        }

        // opens the room setting UI
        else if (e.getSource() == roomSetting_Bt) {
            new RoomSetting_UI();
            dispose();
        }

        // gets the user out of the panel
        else if (e.getSource() == goBack_Bt) {
            new AdminMenu_UI();
            dispose();
        }
    }
}