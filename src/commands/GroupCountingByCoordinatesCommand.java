package commands;


import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'group_counting_by_coordinates'. Group the elements of the collection by the value of the field coordinates, display the number of elements in each group.
 */
public class GroupCountingByCoordinatesCommand extends AbstractCommand{

    private CollectionManager collectionManager;


    public GroupCountingByCoordinatesCommand(CollectionManager collectionManager) {
        super("group_counting_by_coordinates", "Отображает группы, сгруппированные по координатам");
        this.collectionManager = collectionManager;
    }


    /**
     * Executes the command.
     * @return Command exit status.
     */

    @Override
    public boolean execute(String argument) {
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            collectionManager.groupCountingByCoordinates();
            Console.println("сгруппированные учебные группы по координатам");
            return true;
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }

}
