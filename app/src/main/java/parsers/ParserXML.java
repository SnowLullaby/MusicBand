package parsers;

import models.*;
import jakarta.xml.bind.*;
import java.io.*;
import java.util.AbstractMap;
import java.util.Vector;

/**
 * Parsed .xml file
 * @see ISaveLoad
 */
public class ParserXML implements ISaveLoad {
    /**path to file*/
    private final String fileName;
    /**pair of collection and maxID of the elements*/
    private AbstractMap.SimpleEntry<Vector<MusicBand>, Long> collectionAndID;
    /**reader*/
    private InputStreamReader reader;

    /**
     * set path to file
     * @param fileName path to file
     */
    public ParserXML(String fileName) {
        this.fileName = fileName;
    }

    /**
     * pars xml file
     * @return pair of collection and maxID of the elements
     */
    public AbstractMap.SimpleEntry<Vector<MusicBand>, Long> parse() {
        var file = new File(fileName);
        this.reader = new InputStreamReader(getFileStream(file));
        var result = parsing();
        closeFile(reader);
        return result;
    }

    /**
     * get FileInputStream
     * @param file - file that need to parse
     * @return File stream reader
     */
    private static FileInputStream getFileStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует или у Вас нет прав для доступа!");
            System.exit(0);
        }
        return null;
    }

    /**
     * get pair: collection and maxID of the elements
     * @return pair of collection and maxID of the elements
     */
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

    /**
     * try to close reader
     * @param reader - InputStreamReader
     */
    private void closeFile(InputStreamReader reader){
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу");
        }
    }

    /**
     * unmarshalling for JAXB
     * @return  MusicBandCollection - data for filling collection
     * @throws JAXBException - errors of library
     */
    private MusicBandCollectionStructureXML getMBCContext() throws JAXBException {
        var context = JAXBContext.newInstance(MusicBandCollectionStructureXML.class);
        return (MusicBandCollectionStructureXML) context.createUnmarshaller().unmarshal(reader);
    }

    /**
     * saving xml file
     * @param collectionAndID - Music band collection and MaxID
     */
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

    /**
     * getting writer
     * @throws JAXBException - errors of library
     * @throws FileNotFoundException - file not found exception
     */
    private void saveImp() throws JAXBException, FileNotFoundException {
        var writer = new PrintWriter(fileName);
        marshalling(writer);
        writer.close();
    }

    /**
     * Marshalling for JAXB
     * @param writer - PrintWriter
     * @throws JAXBException - errors of library
     */
    private void marshalling(PrintWriter writer) throws JAXBException {
        var jaxbMarshaller = JAXBContext.newInstance(MusicBandCollectionStructureXML.class)
                .createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(getBands(), writer);
    }

    /**
     * get collection for writing
     * @return bands - Music band collection and maxID
     */
    private MusicBandCollectionStructureXML getBands() {
        var bands = new MusicBandCollectionStructureXML();
        bands.collection = collectionAndID.getKey();
        bands.maxID = collectionAndID.getValue();
        return bands;
    }

}
