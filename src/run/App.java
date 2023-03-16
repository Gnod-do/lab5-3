package run;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import commands.*;
import data.StudyGroup;
import utility.*;
import utility.Console;

import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main application class. Creates all instances and runs the program.
 * @author Gnod do До Вань Донг
 */


public class App {
    public static final String PS1 = "$ ";

    public static final String PS2 = "> ";

    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)){
            final String envVariable = "LABA";

            GroupAsker groupAsker = new GroupAsker(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddCommand(collectionManager, groupAsker),
                    new UpdateCommand(collectionManager, groupAsker),
                    new RemoveByIdCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new SaveCommand(collectionManager),
                    new ExitCommand(),
                    new ExecuteScriptCommand(),
                    new RemoveAtIndexCommand(collectionManager),
                    new SortCommand(collectionManager),
                    new HistoryCommand(),
                    new SumOfTransferredStudentsCommand(collectionManager),
                    new MinBySemesterEnumCommand(collectionManager),
                    new GroupCountingByCoordinatesCommand(collectionManager)
            );
            Console console = new Console(commandManager,userScanner, groupAsker);

            console.interactiveMode();

        }
    }
}
