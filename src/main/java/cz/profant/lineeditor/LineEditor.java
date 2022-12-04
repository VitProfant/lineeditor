package cz.profant.lineeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LineEditor {
    private static final String PROMPT = ">>";
    private Buffer buffer;
    private Path path;

    public static void main(String[] args) {
        new LineEditor(args);
    }

    public LineEditor(String[] args) {
        if (args.length > 1) {
            System.out.println(Message.TOO_MANY_ARGUMENTS);
            System.out.println(Message.USAGE);
            System.exit(1);
        } else if (args.length == 1) {
            try {
                buffer = loadFile(args[0]);
            } catch (IOException e) {
                buffer = new Buffer();
                System.out.printf(Message.COULD_NOT_LOAD_FILE, args[0]);
                System.exit(1);
            }
        } else {
            System.out.println(Message.NO_FILE_NAME_ARGUMENT);
            System.out.println(Message.USAGE);
            System.exit(1);
        }
        commandLoop();
    }

    private Buffer loadFile(String fileName) throws IOException {
        this.path = Paths.get(fileName);
        if (Files.exists(path)) {
            List<String> fileContent = Files.readAllLines(path);
            return new Buffer(fileContent);
        }
        return new Buffer();
    }

    private void commandLoop() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        do {
            System.out.printf("%s ", PROMPT);
            try {
                line = bufferedReader.readLine().trim();
            } catch (IOException e) {
                System.out.println(Message.ERRONEOUS_INPUT);
                line = "";
            }
            if (line.length() > 0) {
                try {
                    processInputLine(line);
                } catch (LineEditorException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.printf(Message.COULD_NOT_SAVE_FILE, path);
                }
            }
        } while (!line.equals("quit"));
    }

    private void processInputLine(String line) throws LineEditorException, IOException {
        if (line.equals("list")) {
            listBuffer();
        } else if (line.startsWith("del ")) {
            delete(line.substring(4));
        } else if (line.startsWith("ins ")) {
            insert(line.substring(4));
        } else if (line.equals("save")) {
            saveToFile();
        } else if (line.equals("help")) {
            printHelp();
        } else if (!line.equals("quit")) {
            throw new LineEditorException(Message.UNKNOWN_COMMAND);
        }
    }

    private void listBuffer() {
        System.out.print(buffer.list());
    }

    private void insert(String line) throws LineEditorException {
        buffer.insertLineAt(firstTokenToInt(line), afterFirstToken(line));
    }

    private void delete(String line) throws LineEditorException {
        buffer.deleteLineAt(firstTokenToInt(line));
    }

    private int firstTokenToInt(String line) throws LineEditorException {
        int spacePosition = line.indexOf(" ");
        String valueToInt = spacePosition > -1 ? line.substring(0, spacePosition) : line;
        try {
            return Integer.parseInt(valueToInt);
        } catch (NumberFormatException e) {
            throw new LineEditorException(String.format(Message.IS_NOT_INTEGER, valueToInt));
        }
    }

    private String afterFirstToken(String line) {
        int spacePosition = line.indexOf(" ");
        return spacePosition > -1 ? line.substring(spacePosition + 1) : "";
    }

    private void saveToFile() throws IOException {
        Files.write(path, buffer.getLinesList());
    }

    private void printHelp() {
        System.out.println(Message.HELP);
    }
}
