package happybit;

import happybit.command.Command;
import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.exception.HaBitParserException;
import happybit.goal.GoalList;
import happybit.parser.Parser;
import happybit.storage.Storage;
import happybit.ui.Ui;

import java.util.Scanner;

public class HappyBit {

    private Storage storage;
    private GoalList goalList;
    private Ui ui;

    /**
     * Duke class constructor that also loads in tasks data from an external save file.
     *
     * @param filePath File path of the external save file
     */
    public HappyBit(String filePath, String fileDir) {
        ui = new Ui();
        storage = new Storage(filePath, fileDir);
        goalList = new GoalList();
        loadData();
    }

    /**
     * Main method of HappyBit.
     * Creates a HappyBit class and runs it.
     *
     * @param args Not applicable.
     */
    public static void main(String[] args) {
        new HappyBit("data/habits.txt", "data").run();
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Loads in data from an external storage.
     */
    private void loadData() {
        try {
            goalList = storage.load();
        } catch (HaBitStorageException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Executes the main body of HappyBit.
     */
    private void run() {
        ui.showWelcome();
        handleUserInput();
        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            ui.showError(e.getMessage());
        }
        ui.showGoodbye();
    }

    /**
     * Takes in the user input and performs the relevant commands.
     */
    private void handleUserInput() {
        String userInput;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            userInput = in.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.runCommand(goalList, ui, storage);
                isExit = command.isExit();
            } catch (HaBitParserException | HaBitCommandException e) {
                ui.showError(e.getMessage());
            }
        }
    }

}
