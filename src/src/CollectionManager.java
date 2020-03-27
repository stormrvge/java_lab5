package src;

import src.commands.*;
import src.io.Parse;
import src.labclasses.Coordinates;
import src.labclasses.Location;
import src.labclasses.Route;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * This class realizing methods for commands.
 */
public class CollectionManager {
    private final int ARGUMENT = 1;
    private final int REQUIRED_NUM_OF_ARG = 2;
    private ArrayList<Route> route;
    private java.time.ZonedDateTime date;

    public CollectionManager() {
        date = java.time.ZonedDateTime.now();
        route = new ArrayList<>();
    }

        /**
         * Method "info" which displays short instruction of every command program.
         */
    public void help() {
        System.out.println("info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)" +
                "\nshow: вывести в стандартный поток вывода все элементы коллекции в строковом представлении" +
                "\nadd {element}: добавить новый элемент в коллекцию" +
                "\nupdate_id {element}: обновить значение элемента коллекции, id которого равен заданному" +
                "\nremove_by_id id: удалить элемент из коллекции по его id" +
                "\nclear: очистить коллекцию" +
                "\nsave: сохранить коллекцию в файл" +
                "\nload: загрузить коллекцию из файла" +
                "\nexecute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. + " +
                "\nexit: завершить программу (без сохранения в файл)" +
                "\nremove_at index: удалить элемент, находящийся в заданной позиции коллекции (index)" +
                "\nadd_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции" +
                "\nadd_if_min {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции" +
                "\ncount_by_distance distance: вывести количество элементов, значение поля distance которых равно заданному" +
                "\nprint_unique_distance distance: вывести уникальные значения поля distance" +
                "\nprint_field_ascending_distance distance: вывести значение поля distance в порядке возрастания");
    }

        /**
         * This method print info about collection.
         */
    public void info() {
        try {
            Field arrayListField = CollectionManager.class.getDeclaredField("route");
            String arrayListType = arrayListField.getGenericType().getTypeName();
            String[] className = arrayListType.replace("<", " ").
                    replace(">", " ").split("[ .]");
            System.out.println("Type: "  + className[5] +
                    ", initializing date: " + date +
                    ", collection size: " + route.size());
        } catch (NoSuchFieldException e) {
            System.err.println("Problem with general class. Cant find type of class!");
        }

    }

        /**
         * This method shows a elements in collection.
         */
    public void show() {
        for (Route value : route) {
            System.out.println(value.toString());
        }
    }

        /**
         * This method add's a new element to collection.
         * bounds for coordinates and location class.
         */
    public void add() {
        Route newRoute = readElement();
        if (newRoute != null) {
            route.add(newRoute);
            System.out.println("Element was added!");
        }
    }

    /**
     * This method update's an element in collection by id.
     * @param args - arguments from console.
     */
    public void update_id(String ... args) {
        int id;
        if (args.length != REQUIRED_NUM_OF_ARG)
            System.err.println("Incorrect number of arguments!");
        else {
            try {
                id = Integer.parseInt(args[ARGUMENT]);
                Route oldElement = route.get(getIndexById(id));
                Route newElement = readElement();
                if (newElement != null) {
                    oldElement.setName(newElement.getName());
                    oldElement.setCoordinates(newElement.getCoordinates());
                    oldElement.setFrom(newElement.getFrom());
                    oldElement.setTo(newElement.getTo());
                    oldElement.setDistance(newElement.getDistance());
                    System.out.println("Element with " + id + " was updated!");
                }
            } catch (Exception e) {
                System.err.println("No element with such id!");
            }
        }

    }

        /**
         * This method remove's element from collection by id.
         * @param args - arguments from console.
         */
    public void remove_by_id(String ... args) {
        int id;
        if (args.length != REQUIRED_NUM_OF_ARG)
            System.err.println("Incorrect number of arguments!");
        else {
            try {
                id = Integer.parseInt(args[ARGUMENT]);
                route.remove(getIndexById(id));
                System.out.println("Element with " + id + " was removed!");
            } catch (Exception e) {
                System.err.println("No element with such id!");
            }
        }
    }

        /**
         * This method clear's collection (deleting all elements).
         */
    public void clear() {
        setInitDate(java.time.ZonedDateTime.now());
        route.clear();
        System.out.println("Collection was cleared!");
    }

        /**
         * This method save collection to file.
         */
    public void save(String ... args) {
        String path;
        if (args.length != REQUIRED_NUM_OF_ARG)
            System.err.println("Incorrect number of arguments!");
        else {
            try {
                path = args[ARGUMENT];
                try {
                    Parse.parseToJSON(path, route, date);
                    System.out.println("Collection was saved!");
                } catch (IOException | NoSuchFieldException e) {
                    System.err.println("Permission denied or file not found.");
                } catch (InvalidPathException e) {
                    System.err.println("Invalid path!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Bad argument type!");
            }
        }
    }

        /**
         *
         * @param args - arguments from console.
         */
    public void load(String ... args) {
        route.clear();
        Route.resetId();
        String path;
        if (args.length != REQUIRED_NUM_OF_ARG)
                System.err.println("Incorrect number of arguments!");
        else {
            try {
                path = args[ARGUMENT];
                route.addAll(Parse.parseFromJSON(path));
                setInitDate(Parse.getInitDate(path));
                System.out.println("Collection loaded!");
            } catch (IOException e) {
                System.err.println("Permission denied or file not found!");
            } catch (OutOfBoundsException e) {
                System.err.println("Element from collection file with incorrect fields!");
            } catch (NullPointerException e) {
                System.err.println("No initialization date in JSON collection file!");
            } catch (NumberFormatException e) {
                System.err.println("Bad argument type!");
            } catch (InvalidPathException e) {
                System.err.println("Invalid path!");
            }
        }
    }

        /**
         * In method "exit" we have one boolean field, if field false program will stop.
         */
    public void exit() {
        CommandExit.isRunning = false;
    }

        /**
         * This method delete element from collection by index.
         * @param args - arguments from console.
         */
    public void remove_at(String ... args) {
        int index;
        if (args.length != REQUIRED_NUM_OF_ARG)
            System.err.println("Incorrect number of arguments!");
        else {
            try {
                index = Integer.parseInt(args[ARGUMENT]);
                route.remove(index);
                System.out.println("Element with index " + index + " was deleted.");
                } catch (Exception e) {
                    System.err.println("No element with such index!");
            }
        }
    }

            /**
     * This method will add new element, if distance of new element is maximal in collection.
     */
    public void add_if_max()  {
        Route newRoute = readElement();
        float maxDistance = 0;
        for (Route value : route) {
            if (value.getDistance() > maxDistance) maxDistance = value.getDistance();
        }

        if((newRoute != null) && (newRoute.getDistance() > maxDistance)) {
            route.add(newRoute);
            System.out.println("New element was added");
        }
        else if (newRoute != null) System.err.println("This distance not maximal in collection");
    }

    /**
     * This method will add new element, if distance of new element is minimal in collection.
     */
    public void add_if_min() {
        Route newRoute = readElement();
        float minDistance = route.get(0).getDistance();
        for (Route value : route) {
            if (value.getDistance() < minDistance) minDistance = value.getDistance();
        }

        if ((newRoute != null) && (newRoute.getDistance() < minDistance)) {
            route.add(newRoute);
            System.out.println("New element was added");
        }
        else if (newRoute != null) System.err.println("This distance not minimal in collection");
    }

    /**
     * This method returns number of matches with distance.
     * @param args - arguments from console.
     */
    public void count_by_distance(String ... args) {
        float distance;
        if (args.length != REQUIRED_NUM_OF_ARG)
            System.err.println("Incorrect number of arguments!");
        else {
            try {
                distance = Float.parseFloat(args[ARGUMENT]);
                int counter = 0;
                for (Route value : route) {
                    if (value.getDistance() == distance) counter++;
                }
                System.out.println("Количество совпадений: " + counter);
            } catch (NumberFormatException e) {
                System.err.println("Bad type of argument!");
            }
        }
    }

    /**
     * This method prints unique values of distances from collection.
     */
    public void print_unique_distance() {
        List<Float> floatList = new ArrayList<>();
        for (Route value : route) {
            floatList.add(value.getDistance());
        }
        HashSet<Float> floatHashSet = new HashSet<>(floatList);
        System.out.println("Unique distance: " + floatHashSet.toString());
    }

    /**
     * This method prints sorted collection in ascending by distance field.
     */
    public void print_field_ascending_distance() {
        ArrayList<Route> sortedRoute = route;
        sortedRoute.sort(Route::compareTo);
        System.out.print("Sorted by distance: ");
        for (int i = 0; i < sortedRoute.size(); i++) {
            Route value = sortedRoute.get(i);
            System.out.print(value.getDistance());
            if (i + 1 < sortedRoute.size()) System.out.print(", ");
        }
        System.out.println();
    }

        /**
         *
         * @return - if method will successful (without exception) he will return object,
         * else method will return null;
         */
    private Route readElement() {
        Scanner input = new Scanner(System.in);
        Route route = new Route();

        try {
            System.out.println("Введите имя: ");
            route.setName(input.nextLine());

            System.out.println("Введите координату x (Double): ");
            double x = Double.parseDouble(input.nextLine());
            System.out.println("Введите координату у (double): ");
            double y = Double.parseDouble(input.nextLine());
            route.setCoordinates(new Coordinates(x, y));

            System.out.println("Введите координату x (Float, Location from): ");
            Float x_From = Float.parseFloat(input.nextLine());
            System.out.println("Введите координату y (Integer, Location from): ");
            Integer y_From = Integer.parseInt(input.nextLine());
            System.out.println("Введите координату z (int, Location from): ");
            int z_From = Integer.parseInt(input.nextLine());
            route.setFrom(new Location(x_From, y_From, z_From));

            System.out.println("Введите координату x (Float, Location to): ");
            try {
                Float x_To = Float.parseFloat(input.nextLine());
                System.out.println("Введите координату y (Integer, Location to): ");
                Integer y_To = Integer.parseInt(input.nextLine());
                System.out.println("Введите координату z (int, Location to): ");
                int z_To = Integer.parseInt(input.nextLine());
                route.setTo(new Location(x_To, y_To, z_To));
            } catch (NumberFormatException e) {
                route.setTo(null);
                System.err.println("Be care! Location_To is null.");
            }

            System.out.println("Введите дистанцию (float): ");
            route.setDistance(Float.parseFloat(input.nextLine()));
            route.setId();
            route.setCreationDate();
            return route;
        } catch (NullPointerException | NumberFormatException e) {
            System.err.println("Field cant be null!");
        } catch (OutOfBoundsException e) {
            System.err.println("Out of bounds for this fields!");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid type for this fields!");
        }
        return null;
    }


    /**
     * This method returning index of element in collection with id as parameter.
     * @param id - field of element.
     * @return - returns index.
     * @throws Exception - throws exception, if no elements with id from parameter.
     */
    private int getIndexById(int id) throws Exception {
        for (int i = 0; i < route.size(); i++) {
            if (route.get(i).getId() == id) {
                return i;
            }
        } throw new Exception("No such id");
    }

    private void setInitDate(java.time.ZonedDateTime date) {
        this.date = date;
    }
}