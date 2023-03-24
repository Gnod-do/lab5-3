package commands;


import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'sort'. Returns a sorted collection.
 */
public class SortCommand extends AbstractCommand{

    private CollectionManager collectionManager;
    public SortCommand(CollectionManager collectionManager) {
        super("sort","Отображение коллекций, отсортированных по имени");
        this.collectionManager = collectionManager;
    }

    @Override
    public String getDescription() {
        return null;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */

    @Override
    public boolean execute(String argument) {
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            collectionManager.sortByNameAscending();
            Console.println(collectionManager);
            return true;
        }   catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return false;
    }
}
