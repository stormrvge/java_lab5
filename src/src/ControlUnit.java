package src;

import src.commands.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Control Unit class realize execute methods from commands classes.
 */
public class ControlUnit {
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
         * @param collection - collection which you operate in program.
         */
    public void info(Collection collection) {
        try {
            Field arrayListField = Collection.class.getDeclaredField("route");
            String arrayListType = arrayListField.getGenericType().getTypeName();
            arrayListType = arrayListType.replace("<", " ");
            arrayListType = arrayListType.replace(">", " ");
            String[] className = arrayListType.split("[ .]");
            System.out.println("Type: " + className[4] +
                    ", initializing date: " + collection.getDate() +
                    ", collection size: " + collection.getCollection().size());
        } catch (NoSuchFieldException e) {
            System.err.println("Problem with general class. Cant find type of class!");
        }

    }

        /**
         * This method shows a elements in collection.
         * @param arrayList - ArrayList of collection which we operate.
         */
    public void show(ArrayList<Route> arrayList) {
        for (Route route : arrayList) {
            System.out.println(route.toString());
        }
    }

        /**
         * This method add's a new element to collection.
         * @param collection - collection which you operate in program.
         * bounds for coordinates and location class.
         */
    public void add(Collection collection) {
        Route route = readElement();
        if (route != null) collection.addElement(route);
    }

        /**
         * This method update's element fields by id.
         * @param id - unique id of element in collection.
         * @param collection - collection which you operate in program.
         */
    public void update_id(int id, Collection collection) {
        try {
            Route routeOldElement = collection.readElementById(id);
            Route routeNewElement = readElement();
            if (routeNewElement != null) collection.updateElement(routeOldElement, routeNewElement);
        } catch (Exception e) {
            System.err.println("No element with id: " + id);
        }
    }

        /**
         * This method remove's element from collection by id.
         * @param id - unique id of element in collection.
         * @param collection - collection which you operate in program.
         */
    public void remove_by_id(int id, Collection collection) {
        try {
            collection.removeElementById(id);
        } catch (Exception e) {
         System.err.println("No element with id: " + id);
        }
    }

        /**
         * This method clear's collection (deleting all elements).
         * @param collection - collection which you operate in program.
         */
    public void clear(Collection collection) {
        collection.clearCollection();
    }

        /**
         * This method save
         * @param collection - collection which you operate in program.
         */
    public void save(String path, Collection collection) {
        try {
            Parse.parseToJSON(path, collection);
        } catch (IOException | NoSuchFieldException e) {
            System.err.println("Permission denied or file not found.");
        }
    }

        /**
         *
         * @param path - path to file which need to load.
         * @param collection - collection which you operate in program.
         */
    public void load(String path, Collection collection) {
        try {
            collection.clearCollection();
            collection.setArrayList(Parse.parseFromJSON(path));
        } catch (IOException e) {
            System.err.println("Permission denied or file not found!");
        } catch (OutOfBoundsException e) {
            System.err.println("Element from collection file with incorrect fields!");
        }
    }


        /**
         * In method "exit" we have one boolean field, if field false program will stop.
         */
    public void exit() {
        Command_exit.isRunning = false;
    }

        /**
         *
         * @param index - index of element in collection.
         * @param collection - collection which you operate in program.
         */
    public void remove_at(int index, Collection collection) {
        try {
            collection.removeElementByIndex(index);
            System.out.println("Element with index " + index + " was deleted.");
        } catch (Exception e) {
            System.err.println("No element with index: " + index);
        }
    }

        /**
         *
         * @param collection - collection which you operate in program.
         */
    public void add_if_max(Collection collection)  {
            Route route = readElement();
            assert route != null;
            if(collection.isDistanceMax(route.getDistance())) {
                collection.addElement(route);
            }
            else System.err.println("This distance not maximal in collection");
    }

        /**
         * @param collection - collection which you operate in program.
         */
    public void add_if_min(Collection collection) {
        Route route = readElement();
        assert route != null;
        if(collection.isDistanceMin(route.getDistance())) {
            collection.addElement(route);
        }
        else System.err.println("This distance not minimal in collection");
    }

        /**
         *
         * @param distance - use this parameter to count number of coincidence.
         * @param collection - collection which you operate in program.
         */
    public void count_by_distance(float distance, Collection collection) {
        System.out.println("Количество совпадений: " + collection.countByDistance(distance));
    }

        /**
         *
         * @param collection - collection which you operate in program.
         */
    public void print_unique_distance(Collection collection) {
        collection.printUniqueDistance();
    }

        /**
         *
         * @param collection - collection which you operate in program.
         */
    public void print_field_ascending_distance(Collection collection) {
        collection.printSortedAscendingDistance();
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
}