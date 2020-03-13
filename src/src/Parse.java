package src;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class parsing from JSON files and to JSON files from collection.
 * In this class we have final parameters with numbers from  ASCII table.
 */
public class Parse {
    static private final int NEW_LINE = 10;
    static private final int CARRIAGE_RETURN = 13;
    static private final int SPACE = 32;
    static private final int QUOTES = 34;
    static private final int LEFT_SQUARE_BRACE = 91;
    static private final int RIGHT_SQUARE_BRACE = 93;
    static private final int EOF = -1;
    static private final int HORIZONTAL_TAB = 9;

    /**
     * This method parses from file to array of strings in our program.
     * Method works with additional private method which returns ArrayList.
     * @param pathToFile - path in our system to JSON file with collection.
     * @return - returns ArrayList with collection.
     * @throws IOException - throws exception, if we don't have permission to file or file not found.
     * @throws OutOfBoundsException - throws exception, if coordinates or location are have out of bounds values.
     */
    public static ArrayList<Route> parseFromJSON(String pathToFile) throws IOException, OutOfBoundsException {
        Path path = Paths.get(pathToFile);
        InputStreamReader in = new InputStreamReader(new FileInputStream(String.valueOf(path)));
        int line;
        String str = "";
        while ((line = in.read()) != EOF) {
            if (line != NEW_LINE && line != CARRIAGE_RETURN && line != SPACE
                    &&  line != QUOTES &&
                    line != LEFT_SQUARE_BRACE && line != RIGHT_SQUARE_BRACE && line != HORIZONTAL_TAB) {
                str += (char) line;
            }
        }
        return parser(str);
    }

    /**
     * This method parses array of strings into ArrayList of objects.
     * @param str - arrays of strings with fields and values.
     * @return - returns ArrayList.
     * @throws OutOfBoundsException - throws exception, if coordinates or location are have out of bounds values.
     * @throws NullPointerException - throws exception, if Location From fields equals null.
     */
    static private ArrayList<Route> parser(String str) throws OutOfBoundsException, NullPointerException {
        String[] arr = str.split("[:,{}]");
        List<String> removeSpaces = new ArrayList<>();
        for (String s : arr) {
            if ((s != null && s.length() > 0)) {
                removeSpaces.add(s);
            }
        }
        arr = removeSpaces.toArray(new String[0]);

        int num = 0;
        for (String s : arr) {
            if (s.equals("name")) num++;
        }
        ArrayList<Route> routeList = new ArrayList<>();
        Route[] route = new Route[num];

        for (int i = 0; num > i;) {
            route[i] = new Route();
            for (int j = 0; arr.length > j; j++) {
                try {
                    if (arr[j].equals("name") && !arr[j + 1].equals("coordinates")) {
                        route[i].setName("hello"); //arr[j + 1]
                    }
                    else if (arr[j].equals("name"))
                        route[i].setName(null);
                } catch (NullPointerException e) {
                    return new ArrayList<>();
                }

                try {
                    if (arr[j].equals("coordinates")) {
                        route[i].setCoordinates(new Coordinates(Double.parseDouble(arr[j + 2]),
                                Double.parseDouble(arr[j + 4])));
                    }
                    } catch (NullPointerException | NumberFormatException e) {
                        return new ArrayList<>();
                    }

                try {
                    if (arr[j].equals("creationDate")) {
                        ZoneId zoneId = ZoneId.systemDefault();
                        String[] date;
                        date = arr[j + 1].split("-");
                        java.time.ZonedDateTime time = ZonedDateTime.of(Integer.parseInt(date[0]),
                                Integer.parseInt(date[1]), Integer.parseInt(date[2]), 0, 0,
                                0, 0, zoneId);
                        route[i].setCreationDateForParse(time);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    route[i].setCreationDate(false);
                }

                if (arr[j].equals("from")) {
                    route[i].setFrom(new Location(Float.parseFloat(arr[j + 2]),
                            Integer.parseInt(arr[j + 4]), Integer.parseInt(arr[j + 6])));
                }
                if (arr[j].equals("to") && !arr[j + 1].equals("null")) {
                    route[i].setTo(new Location(Float.parseFloat(arr[j + 2]),
                            Integer.parseInt(arr[j + 4]), Integer.parseInt(arr[j + 6])));
                }
                else if ((arr[j].equals("to") && arr[j + 1].equals("null"))){
                    route[i].setTo(null);
                }
                if (arr[j].equals("distance")) {
                    route[i].setDistance(Float.parseFloat(arr[j + 1]));
                    route[i].setId();
                    if (route[i].getName() == null || route[i].getCoordinates() == null ||
                         route[i].getFrom() == null ||
                         route[i].getCreationDate() == null) {
                         System.err.println("The " + (i + 1) + " element incorrect!");
                    }
                    else {
                        routeList.add(route[i]);
                    }
                    i++;
                    if (num > i) {
                        route[i] = new Route();
                    }
                }
            }
        }
        return routeList;
    }

    /**
     * This method parses from collection in program to JSON file.
     * @param collection - collection which contains all elements from program.
     * @throws IOException - throws exception, if we don't have permission to create file.
     * @throws NoSuchFieldException - throws exception, if we don't have elements in collection,
     * needs for parse a class type.
     */
    static public void parseToJSON(String path, Collection collection) throws IOException, NoSuchFieldException {
        Path pathToFile = Paths.get(path);

        BufferedOutputStream bos;
        FileOutputStream fos;
        fos = new FileOutputStream(String.valueOf(pathToFile));
        bos = new BufferedOutputStream(fos);

        Route[] route = new Route[collection.getCollectionSize()];
        for (int i = 0; i < route.length; i++) {
            route[i] = collection.readElementByIndex(i);
        }
        String str;
        String additionalStr;


        Field arrayListField = Collection.class.getDeclaredField("route");
        String arrayListType = arrayListField.getGenericType().getTypeName();
        arrayListType = arrayListType.replace("<", " ");
        arrayListType = arrayListType.replace(">", " ");
        String[] className = arrayListType.split("[ .]");

        str = "{\n  \"" + className[4] + "\":[";
        bos.write(str.getBytes(), 0, str.length());
        for (int i = 0; i < route.length; i++)
        {
            Coordinates coordinates = route[i].getCoordinates();
            Location from = route[i].getFrom();
            Location to = route[i].getTo();
            ZonedDateTime creationDate = route[i].getCreationDate();
            int[] date = {creationDate.getYear(), creationDate.getMonthValue(), creationDate.getDayOfMonth()};
            str = "\n    {\n\t\"name\":\"" + route[i].getName() + "\", " +
                    "\n\t\"coordinates\":{" +
                    "\n\t  \"x\":" + coordinates.getX() + "," +
                    "\n\t  \"y\":" + coordinates.getY() +
                    "\n\t}," +
                    "\n\t\"creationDate\":\"" + date[0] + "-" + date[1] + "-" + date[2] +"\"," +
                    "\n\t\"from\":{" +
                    "\n\t  \"x\":" + from.getX() + "," +
                    "\n\t  \"y\":" + from.getY() +"," +
                    "\n\t  \"z\":" + from.getZ() +
                    "\n\t},";
            if (to != null)
            str += "\n\t\"to\":{" +
                    "\n\t  \"x\":" + to.getX() + "," +
                    "\n\t  \"y\":" + to.getY() + "," +
                    "\n\t  \"z\":" + to.getZ() +
                    "\n\t}," +
                    "\n\t\"distance\":" + route[i].getDistance();
            else {
                str += "\n\t\"to\":null," +
                        "\n\t\"distance\":" + route[i].getDistance();
            }
            if (i < route.length - 1) additionalStr = "\n    },";
            else additionalStr = "\n    }";

            bos.write(str.getBytes(), 0, str.length());
            bos.write(additionalStr.getBytes(), 0, additionalStr.length());
        }
        str = "\n  ]\n}";
        bos.write(str.getBytes(), 0, str.length());
        bos.flush();
        bos.close();
    }

    /**
     * This method reads file with commands (script) and parses into array strings.
     * @param path - path to file in system.
     * @return - returns array of strings with commands.
     * @throws IOException - throws exception, if we don't have permission to file or file not found.
     */
    static public String[] executeStrings(String path) throws IOException {
        InputStreamReader in = new InputStreamReader(new FileInputStream(String.valueOf(path)));
        int line;
        String str = "";
        while ((line = in.read()) != EOF) {
            if (line != CARRIAGE_RETURN &&  line != QUOTES && line != LEFT_SQUARE_BRACE
                    && line != RIGHT_SQUARE_BRACE && line != HORIZONTAL_TAB) {
                str += (char) line;
            }
        }
        return str.split("\n");
    }
}