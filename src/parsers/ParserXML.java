package parsers;

import models.*;
import javax.xml.bind.*;
import java.io.*;
import java.util.Vector;

public class Parser implements ISaveLoad {
    private final String fileName;

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public Vector<MusicBand> parse() {
        var file = new File(fileName);
        var reader = new InputStreamReader(getFileStream(file));
        var result = parsing(reader);
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибика доступа к файлу");
        }
        return result;
    }

    private static Vector<MusicBand> parsing(InputStreamReader reader) {
        try {
            var context = JAXBContext.newInstance(MusicBandCollectionStructureXML.class);
            return ((MusicBandCollectionStructureXML) context.createUnmarshaller().unmarshal(reader)).collection;
        } catch (JAXBException e) {
            System.out.println("Ошибка при парсинге XML файла. Текст системной ошибки:");
            System.out.println(e.getMessage());
            System.out.println("Возможные способы исправления: проверьте расширение файла и правильность XML-разметки");
            System.exit(0);
        }
        return null;
    }

    private static FileInputStream getFileStream(File file) {
        try {
            System.out.println(file);
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует или у Вас нет прав для доступа!");
            System.exit(0);
        }
        return null;
    }

    public void save(Vector<MusicBand> collection) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MusicBandCollectionStructureXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            System.out.println("я тут был");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            var bands = new MusicBandCollectionStructureXML();
            bands.collection = collection;
            PrintWriter writer = new PrintWriter(fileName);
            jaxbMarshaller.marshal(bands, writer);
            writer.close();
        } catch (PropertyException e) {
            System.out.println("Ошибка доступа к файлу!");
        } catch (JAXBException e) {
            System.out.println("Ошибка при записи коллекции в файл! Текст системной ошибки:");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Невозможно открыть файл на чение");
        }
    }
}
