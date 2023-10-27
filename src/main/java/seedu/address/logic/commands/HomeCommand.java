package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.StageManager;
import seedu.address.logic.parser.Stages;
import seedu.address.model.Model;

public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": returns back to the home page\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Returned to home page";

    public static final String MESSAGE_HOME_ALREADY = "You are already at the home page";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StageManager currStageManager = StageManager.getCurrent();

        if (StageManager.getStage() == Stages.HOME) {
            return new CommandResult(MESSAGE_HOME_ALREADY);
        }

        currStageManager.setHomeStage();
        
        return new CommandResult(MESSAGE_SUCCESS, Stages.HOME);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof HomeCommand;
    }
}
