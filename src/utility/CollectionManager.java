package utility;

import data.IDprovider;
import data.StudyGroup;
import exceptions.CollectionIsEmptyException;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Operates the collection itself.
 */

public class CollectionManager {

    private LinkedList<StudyGroup> groupsCollection = new LinkedList<>();

    private LocalDateTime lastInitTime;

    private LocalDateTime lastSaveTime;

    private FileManager fileManager;
    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
        loadCollection();
    }

    /**
     * @return The collection itself.
     */

    public LinkedList<StudyGroup> getCollection() {return groupsCollection;}

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */

    public LocalDateTime getLastInitTime() {return lastInitTime;}

    /**
     * @return Last save time or null if there wasn't saving.
     */

    public LocalDateTime getLastSaveTime() {return lastSaveTime;}

    /**
     * @return Name of the collection's type.
     */

    public String collectionType() {return groupsCollection.getClass().getName();}

    /**
     * @return Size of the collection.
     */

    public int collectionSize() {return  groupsCollection.size();}

    /**
     * @return The first element of the collection or null if collection is empty.
     */

    public StudyGroup getFirst() {
        if (groupsCollection.isEmpty()) return null;
        return groupsCollection.getFirst();
    }

    /**
     * @return The last element of the collection or null if collection is empty.
     */

    public StudyGroup getLast() {
        if (groupsCollection.isEmpty()) return null;
        return groupsCollection.getLast();
    }

    /**
     * @param id ID of the group.
     * @return A group by his ID or null if group isn't found.
     */

    public StudyGroup getById(Long id) {
        for (StudyGroup group: groupsCollection) {
            if (group.getId().equals(id)) return group;
        }
        return null;
    }

    /**
     * @return A group by his index or null if group isn't found.
     */

    public StudyGroup getByIndex(int index) {
        return groupsCollection.get(index);
    }

    /**
     * @param groupToFind A marine who's value will be found.
     * @return A group by his value or null if marine isn't found.
     */

    public StudyGroup getByValue(StudyGroup groupToFind) {
        for (StudyGroup group : groupsCollection) {
            if (group.equals(groupToFind)) return group;
        }
        return null;
    }

    /**
     * @return Sum of all transferred students or 0 if collection is empty.
     */

    public int getSumOfTransferredStudents() {
        int sumOfTransferredStudents = 0;
        for (StudyGroup group: groupsCollection) {
            sumOfTransferredStudents += group.getTransferredStudents();
        }
        return sumOfTransferredStudents;
    }

    /**
     * @return Group, whose semesterEnum field value is the minimum.
     * @throws CollectionIsEmptyException If collection is empty.
     */

    public String minBySemester() throws CollectionIsEmptyException {
        if (groupsCollection.isEmpty()) throw  new CollectionIsEmptyException();

        StudyGroup minGroup = getFirst();
        for (StudyGroup group: groupsCollection) {
            if(group.getSemesterEnum().compareTo(minGroup.getSemesterEnum()) < 0) {
                minGroup = group;
            }
        }
        return minGroup.toString();
    }

    /**
     *
     * @return a sorted collection.
     */

    public void sortByNameAscending() {
        Collections.sort(groupsCollection, new Comparator<StudyGroup>() {
            @Override
            public int compare(StudyGroup group1, StudyGroup group2) {
                return group1.getName().compareTo(group2.getName());
            }
        });
    }




    public void addToCollection(StudyGroup group) {
//        group.setId(IDprovider.getInstance().getID());
        groupsCollection.add(group);
    }

    /**
     * Removes a group from collection.
     * @param group A group to remove.
     */


    public void removeFromCollection(StudyGroup group) {
        groupsCollection.remove(group);
    }

    /**
     * Remove group given the index.
     */

    public void removeByIndex(int index) {
        groupsCollection.remove(index);
    }


    /**
     * Group of groups with the same coordinates.
     */

    public void groupCountingByCoordinates() {
        HashMap<String, Integer> groupCounts = new HashMap<>();
        for (StudyGroup group : groupsCollection) {
            String key = group.getCoordinatesString();
            if (groupCounts.containsKey(key)) {
                int count = groupCounts.get(key);
                groupCounts.put(key, count + 1 );
            } else {
                groupCounts.put(key, 1);
            }
        }
        for (String key : groupCounts.keySet()) {
            Console.println("Coordinate" + key + ": " + groupCounts.get(key) + " group");
        }
    }




    /**
     * Clears the collection.
     */

    public void clearCollection() {
        groupsCollection.clear();
    }

    /**
     * Generates next ID. It will be (the bigger one + 1).
     * @return Next ID.
     */

    public Long generateNextId() {
        if (groupsCollection.isEmpty()) return null;
        return groupsCollection.getLast().getId() + 1;
    }

    /**
     * Saves the collection to file.
     */

    public void saveCollection() {
        fileManager.writeCollection(groupsCollection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */

    public void loadCollection() {
        groupsCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }


    @Override
    public String toString() {
        if (groupsCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (StudyGroup group : groupsCollection) {
            info += group;
            if (group != groupsCollection.getLast()) info += "\n\n";
        }
        return info;
    }

}
