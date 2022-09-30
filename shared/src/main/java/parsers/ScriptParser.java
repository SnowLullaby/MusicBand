package parsers;

import java.io.*;
import java.util.Vector;

public class ScriptParser {
    public static Vector<String> parse(String fileName) throws Exception {
        var file = new File(fileName);
        var reader = new BufferedReader(getFileStream(file));
        var commandsVector = fillCommandVector(reader);
        reader.close();
        return commandsVector;
    }

    private static InputStreamReader getFileStream(File file) {
        try {
            return new InputStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует или у Вас нет прав для доступа!");
            System.exit(0);
        }
        return null;
    }

    private static Vector<String> fillCommandVector(BufferedReader reader) throws IOException {
        var commandsVector = new Vector<String>();
        String commandLine;
        while((commandLine = reader.readLine()) != null) commandsVector.add(commandLine);
        return commandsVector;
    }
}
