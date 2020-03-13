package src;

import java.util.*;

/**
 * Class which contains collection of Routes and methods to control that collection.
 */
public class Collection {
    private ArrayList<Route> route;
    private java.time.ZonedDateTime date;

    /**
     * In constructor parameter date generate automatically.
     */
    public Collection() {
        date = java.time.ZonedDateTime.now();
        route = new ArrayList<>();
    }

    /**
     * This method add's new element to collection.
     * @param element = element which we want to add in collection.
     */
    public void addElement(Route element) {
        route.add(element);
    }

    /**
     * This method updating fields for element from collection.
     * @param oldElement - element which we want to update.
     * @param newElement - element with new fields.
     * @throws OutOfBoundsException - throws exception, if fields in collection file with wrong arguments.
     */
    public void updateElement(Route oldElement, Route newElement) throws OutOfBoundsException {
        oldElement.setName(newElement.getName());
        oldElement.setCoordinates(newElement.getCoordinates());
        oldElement.setFrom(newElement.getFrom());
        oldElement.setTo(newElement.getTo());
        oldElement.setDistance(newElement.getDistance());
    }

    /**
     * This method returning element from collection by id.
     * @param id - field from element in collection.
     * @return - returning element.
     * @throws Exception - throws exception, if no elements with id from parameter.
     */
    public Route readElementById(int id) throws Exception {
        return route.get(getIndexById(id));
    }

    /**
     * This method returning element from collection by index in ArrayList.
     * @param index - element with this index from collection.
     * @return - returning element.
     */
    public Route readElementByIndex(int index) {
        return route.get(index);
    }

    /**
     * This method removes element from collection by id.
     * @param id - field from element in collection.
     * @throws Exception - throws exception, if no elements with id from parameter.
     */
    public void removeElementById(int id) throws Exception {
        route.remove(getIndexById(id));
    }

    /**
     * This method removes element from collection by index.
     * @param index - element with this index from collection.
     */
    public void removeElementByIndex(int index) {
        route.remove(index);
    }

    /**
     * This method removes all elements from collection and resets static id's.
     */
    public void clearCollection() {
        Route.resetId();
        route.clear();
    }

    /**
     * This method returns number of elements from collection.
     * @return - returns number of elements.
     */
    public int getCollectionSize() {
        return route.size();
    }

    /**
     * This method returns collection.
     * @return - returns collection.
     */
    public ArrayList<Route> getCollection() {
        return route;
    }

    /**
     * This method returns date of creating.
     * @return - returns ZonedDateTime.
     */
    public java.time.ZonedDateTime getDate() {
        return date;
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

    /**
     * This method returns number of matches with distance.
     * @param distance - field of element.
     * @return - returns number of matches.
     */
    public int countByDistance(float distance) {
        int counter = 0;
        for (Route value : route) {
            if (value.getDistance() == distance) counter++;
        }
        return counter;
    }

    /**
     * This method add's all elements from parameter's ArrayList to collection.
     * @param routeList - ArrayList with elements.
     */
    public void setArrayList(ArrayList<Route> routeList) {
        route.addAll(routeList);
    }

    /**
     * This method add check that element by maximal distance in collection.
     * @param distance - field of element.
     * @return - returns true if distance of element is maximal in collection.
     */
    public boolean isDistanceMax(float distance) {
        float maxDistance = 0;
        for (Route value : route) {
            if (value.getDistance() > maxDistance) maxDistance = value.getDistance();
        }
        return !(maxDistance > distance);
    }

    /**
     * This method add check that element by minimal distance in collection.
     * @param distance - field of element.
     * @return - returns true if distance of element is minimal in collection.
     */
    public boolean isDistanceMin(float distance) {
        float minDistance = route.get(0).getDistance();
        for (Route value : route) {
            if (value.getDistance() < minDistance) minDistance = value.getDistance();
        }
        return !(minDistance < distance);
    }

    /**
     * This method prints unique values of distances from collection.
     */
    public void printUniqueDistance() {
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
    public void printSortedAscendingDistance() {
        ArrayList<Route> sortedRoute = route;
        sortedRoute.sort(Route::compareTo);
        for (Route value : sortedRoute) {
            System.out.println(value.getDistance());
        }
    }
}
