package parsers;

import java.io.*;
import java.util.Vector;

/**
 * Parsed file with script
 */
public class ScriptParser {

    /**
     * parse script file
     * @param fileName - path to file
     * @return collection of commands from script file
     * @throws Exception
     */
    public static Vector<String> parse(String fileName) throws Exception {
        var file = new File(fileName);
        var reader = new BufferedReader(getFileStream(file));
        var commandsVector = fillCommandVector(reader);
        reader.close();
        return commandsVector;
    }

    /**
     * get file stream
     * @param file with data
     * @return InputStreamReader
     */
    private static InputStreamReader getFileStream(File file) {
        try {
            return new InputStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist or access is denied");
            System.exit(0);
        }
        return null;
    }

    /**
     * Collects all command from file into general structure
     * @param reader with script file
     * @return collection of commands from script file
     * @throws IOException - errors from reading lines
     */
    private static Vector<String> fillCommandVector(BufferedReader reader) throws IOException {
        var commandsVector = new Vector<String>();
        String commandLine;
        while((commandLine = reader.readLine()) != null) commandsVector.add(commandLine);
        return commandsVector;
    }
}
