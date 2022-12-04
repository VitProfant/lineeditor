package cz.profant.lineeditor;

public class Message {
    public static final String COULD_NOT_LOAD_FILE = "The file %s could not be loaded.%n";
    public static final String COULD_NOT_SAVE_FILE = "The file %s could not be saved.%n";
    public static final String ERRONEOUS_INPUT = "Reading input ended with an error.";
    public static final String FROM_1_TO = "from 1 to ";
    public static final String IS_NOT_INTEGER = "%s is not an integer value.";
    public static final String LINES_RANGE_TO_DEL = "Only a line with a number %s%d can be deleted now.";
    public static final String LINES_RANGE_TO_INS = "Only a line with a number %s%d can be inserted now.";
    public static final String NO_DELETABLE_LINE = "No line can be deleted now.";
    public static final String TOO_MANY_ARGUMENTS = "Too many arguments! No file was loaded.";
    public static final String UNKNOWN_COMMAND = "Unknown command.";
    public static final String NO_FILE_NAME_ARGUMENT = "No path to the file was entered as an argument.";
    public static final String USAGE = "Usage:\n" +
            "lineeditor [path_to_the_file]\n" +
            "(displays a >> prompt; you can then type 'help').";
    public static final String HELP = "Commands:\n" +
            "list - list each line in n:xxx format, e.g.\n" +
            "                1: first line\n" +
            "                2: second line\n" +
            "                3: last line\n" +
            "del n - delete line at n\n" +
            "ins n - insert line at n\n" +
            "save - saves to disk\n" +
            "help - display this help\n" +
            "quit - quits the editor and returns to the command line";
}
