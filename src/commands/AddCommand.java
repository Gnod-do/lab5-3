package commands;

import data.StudyGroup;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;

import java.time.LocalDateTime;
import utility.CollectionManager;
import utility.Console;
import utility.GroupAsker;

/**
 * Command 'add'. Adds a new element to collection.
 */

public class AddCommand extends AbstractCommand {

    private CollectionManager collectionManager;

    private GroupAsker groupAsker;

    public  AddCommand(CollectionManager collectionManager, GroupAsker groupAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.groupAsker = groupAsker;
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
            collectionManager.addToCollection(new StudyGroup(
                    groupAsker.askName(),
                    groupAsker.askCoordinates(),
                    groupAsker.askStudentsCount(),
                    groupAsker.askTransferredStudents(),
                    groupAsker.askFormOfEducation(),
                    groupAsker.askSemester(),
                    groupAsker.askGroupAdmin()
            ));
            Console.println("Солдат успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
