package parsers;

import models.*;
import javax.xml.bind.*;
import java.io.*;
import java.util.AbstractMap;
import java.util.Vector;

public class ParserXML implements ISaveLoad {
    private final String fileName;
    private AbstractMap.SimpleEntry<Vector<MusicBand>, Long> collectionAndID;
    private InputStreamReader reader;

    public ParserXML(String fileName) {
        this.fileName = fileName;
    }

    public AbstractMap.SimpleEntry<Vector<MusicBand>, Long> parse() {
        var file = new File(fileName);
        this.reader = new InputStreamReader(getFileStream(file));
        var result = parsing();
        closeFile(reader);
        return result;
    }

    private static FileInputStream getFileStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует или у Вас нет прав для доступа!");
            System.exit(0);
        }
        return null;
    }

    private AbstractMap.SimpleEntry<Vector<MusicBand>, Long> parsing() {
        try {
            var mbcContext = getMBCContext();
            return new AbstractMap.SimpleEntry<>(mbcContext.collection, mbcContext.maxID);
        } catch (JAXBException e) {
            System.out.println("Ошибка при парсинге XML файла. Текст системной ошибки:");
            System.out.println(e.getMessage());
            System.out.println("Возможные способы исправления: проверьте расширение файла и правильность XML-разметки");
            System.exit(0);
        }
        return null;
    }

    private void closeFile(InputStreamReader reader){
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу");
        }
    }

    private MusicBandCollectionStructureXML getMBCContext() throws JAXBException {
        var context = JAXBContext.newInstance(MusicBandCollectionStructureXML.class);
        return (MusicBandCollectionStructureXML) context.createUnmarshaller().unmarshal(reader);
    }

    public void save(AbstractMap.SimpleEntry<Vector<MusicBand>, Long> collectionAndID) {
        try {
            this.collectionAndID = collectionAndID;
            saveImp();
        } catch (PropertyException e) {
            System.out.println("Ошибка доступа к файлу!");
        } catch (JAXBException e) {
            System.out.println("Ошибка при записи коллекции в файл! Текст системной ошибки:");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Невозможно открыть файл на чение");
        }
    }

    private void saveImp() throws JAXBException, FileNotFoundException {
        var writer = new PrintWriter(fileName);
        marshalling(writer);
        writer.close();
    }

    private void marshalling(PrintWriter writer) throws JAXBException {
        var jaxbMarshaller = JAXBContext.newInstance(MusicBandCollectionStructureXML.class)
                .createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(getBands(), writer);
    }

    private MusicBandCollectionStructureXML getBands() {
        var bands = new MusicBandCollectionStructureXML();
        bands.collection = collectionAndID.getKey();
        bands.maxID = collectionAndID.getValue();
        return bands;
    }

}
