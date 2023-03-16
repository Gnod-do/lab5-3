package commands;

import data.StudyGroup;
import exceptions.CollectionIsEmptyException;
import exceptions.GroupNotFoundException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;


/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */

public class RemoveByIdCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
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
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong(argument);
            StudyGroup groupToRemove = collectionManager.getById(id);
            if (groupToRemove == null) throw new GroupNotFoundException();
            collectionManager.removeFromCollection(groupToRemove);
            Console.println("Солдат успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            Console.printerror("Солдата с таким ID в коллекции нет!");
        }
        return false;
    }
}
