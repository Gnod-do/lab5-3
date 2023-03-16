package commands;

import data.*;

import exceptions.CollectionIsEmptyException;
import exceptions.GroupNotFoundException;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;

import java.util.Date;
import utility.CollectionManager;
import utility.Console;
import utility.GroupAsker;


/**
 * Command 'update'. Updates the information about selected group.
 */

public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private GroupAsker groupAsker;

    public UpdateCommand(CollectionManager collectionManager, GroupAsker groupAsker) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
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
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Long id = Long.parseLong(argument);
            StudyGroup oldGroup= collectionManager.getById(id);
            if (oldGroup == null) throw new GroupNotFoundException();

            String name = oldGroup.getName();
            Coordinates coordinates = oldGroup.getCoordinates();
            Date creationDate = oldGroup.getCreationDate();
            Long studentsCount = oldGroup.getStudentsCount();
            long transferredStudents = oldGroup.getTransferredStudents();
            FormOfEducation formOfEducation  = oldGroup.getFormOfEducation();
            Semester semesterEnum = oldGroup.getSemesterEnum();
            Person groupAdmin = oldGroup.getGroupAdmin();

            collectionManager.removeFromCollection(oldGroup);

            if (groupAsker.askQuestion("Хотите изменить имя солдата?")) name = groupAsker.askName();
            if (groupAsker.askQuestion("Хотите изменить координаты солдата?")) coordinates = groupAsker.askCoordinates();
            if (groupAsker.askQuestion("Хотите изменить количество студентов в группе?")) studentsCount = groupAsker.askStudentsCount();
            if (groupAsker.askQuestion("Хотите изменить количество переводных студентов в группе?")) transferredStudents = groupAsker.askTransferredStudents();
            if (groupAsker.askQuestion("Хотите изменить форму группового обучения?")) formOfEducation = groupAsker.askFormOfEducation();
            if (groupAsker.askQuestion("Хотите изменить семестр группы?")) semesterEnum = groupAsker.askSemester();
            if (groupAsker.askQuestion("Хотите сменить администратора группы?")) groupAdmin = groupAsker.askGroupAdmin();

            collectionManager.addToCollection(new StudyGroup(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    studentsCount,
                    transferredStudents,
                    formOfEducation,
                    semesterEnum,
                    groupAdmin
            ));
            Console.println("Солдат успешно изменен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            Console.printerror("Солдата с таким ID в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
