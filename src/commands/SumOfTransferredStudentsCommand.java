package commands;


import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'sum_of_TransferredStudents'. Prints the sum of transferred students of all group.
 */

public class SumOfTransferredStudentsCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public SumOfTransferredStudentsCommand(CollectionManager collectionManager) {
        super("sum_of_transferred_students", "вывести сумму значений поля transferred_students для всех элементов коллекции");
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
            int sum_of_transferred_students = collectionManager.getSumOfTransferredStudents();
            if (sum_of_transferred_students == 0) throw new CollectionIsEmptyException();
            Console.println("Сумма количества переведенных студентов: " + sum_of_transferred_students);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return false;
    }

}
