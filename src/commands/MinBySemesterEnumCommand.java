package commands;


import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'min_by_semester_enum'. Prints the element of the collection with minimum semester.
 */

public class MinBySemesterEnumCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public MinBySemesterEnumCommand(CollectionManager collectionManager) {
        super("min_by_semester_enum", "вывести элемент, значение поля Semester которого минимально");
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
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println(collectionManager.minBySemester());
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return true;
    }
}
