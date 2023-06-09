package utility;

import commands.Command;
import exceptions.HistoryIsEmptyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Operates the commands.
 */

public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 11;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];

    private List<Command> commands = new ArrayList<>();

    private Command helpCommand;

    private Command infoCommand;

    private Command showCommand;

    private Command addCommand;

    private Command updateCommand;

    private Command removeByIdCommand;

    private Command clearCommand;

    private Command saveCommand;

    private Command exitCommand;

    private Command executeScriptCommand;

    private Command historyCommand;

    private Command sumOfTransferredStudentsCommand;

    private Command sortCommand;

    private Command removeAtIndexCommand;

    private Command minBySemesterEnumCommand;

    private Command groupCountingByCoordinatesCommand;

    public CommandManager(Command helpCommand, Command infoCommand, Command showCommand, Command addCommand, Command updateCommand,
                          Command removeByIdCommand, Command clearCommand, Command saveCommand, Command exitCommand, Command executeScriptCommand,
                          Command removeAtIndexCommand, Command sortCommand, Command historyCommand, Command sumOfTransferredStudentsCommand,
                          Command minBySemesterEnumCommand, Command groupCountingByCoordinatesCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.exitCommand = exitCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.removeAtIndexCommand = removeAtIndexCommand;
        this.sortCommand = sortCommand;
        this.historyCommand = historyCommand;
        this.sumOfTransferredStudentsCommand = sumOfTransferredStudentsCommand;
        this.minBySemesterEnumCommand = minBySemesterEnumCommand;
        this.groupCountingByCoordinatesCommand = groupCountingByCoordinatesCommand;

        // commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(exitCommand);
        commands.add(executeScriptCommand);
        commands.add(removeAtIndexCommand);
        commands.add(sortCommand);
        commands.add(historyCommand);
        commands.add(sumOfTransferredStudentsCommand);
        commands.add(minBySemesterEnumCommand);
        commands.add(groupCountingByCoordinatesCommand);

    }

    /**
     * @return The command history.
     */

    public String[] getCommandHistory() {return commandHistory;}

    /**
     * @return List of manager's commands.
     */

    public List<Command> getCommands() {return commands;}

    /**
     * Adds command to command history.
     * @param commandToStore Command to add.
     */

    public void addToHistory(String commandToStore) {

        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }

    /**
     * Prints that command is not found.
     * @param command Command, which is not found.
     * @return Command exit status.
     */

    public boolean noSuchCommand(String command) {
        Console.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }

    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */

    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                Console.printtable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */

    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    public boolean add(String argument) {
        return addCommand.execute(argument);
    }

    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }

    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    public boolean sumOfTransferredStudents(String argument) {return sumOfTransferredStudentsCommand.execute(argument);}

    public boolean sort(String argument) {return sortCommand.execute(argument);}

    /**
     * Prints the history of used commands.
     * @param argument Its argument.
     * @return Command exit status.
     */

    public boolean history(String argument) {
        if (historyCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new HistoryIsEmptyException();

                Console.println("Последние использованные команды:");
                for (int i=0; i<commandHistory.length; i++) {
                    if (commandHistory[i] != null) Console.println(" " + commandHistory[i]);
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                Console.println("Ни одной команды еще не было использовано!");
            }
        }
        return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */

    public boolean removeAtIndex(String argument){return removeAtIndexCommand.execute(argument);}

    public boolean minBySemesterEnum(String argument) {return minBySemesterEnumCommand.execute(argument);}

    public boolean groupCountingByCoordinates(String argument) {return groupCountingByCoordinatesCommand.execute(argument);}

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }

}
