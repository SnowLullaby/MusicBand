package models;

import javax.xml.bind.*;
import java.io.*;

public class MusicBandCollectionParser {
    public static MusicBandCollectionStructure parseXML(String fileName) {
        var file = new File(fileName);
        var reader = new InputStreamReader(chekIfFileExist(file));
        return parsing(reader);
    }

    private static MusicBandCollectionStructure parsing(InputStreamReader reader) {
        try {
            var context = JAXBContext.newInstance(MusicBandCollectionStructure.class);
            return (MusicBandCollectionStructure) context.createUnmarshaller().unmarshal(reader);
        } catch (JAXBException e) {
            System.out.println("Ошибка при парсинге XML файла. Текст системной ошибки:");
            System.out.println(e.getMessage());
            System.out.println("Возможные способы исправления: проверьте расширение файла и правильность XML-разметки");
            System.exit(0);
        }
        return null;
    }

    private static FileInputStream chekIfFileExist(File file) {
        try{
            System.out.println(file);
            return new FileInputStream(file);
        }catch (FileNotFoundException e) {
            System.out.println("Файл не существует или у Вас нет прав для доступа!");
            System.exit(0);
        }
        return null;
    }

    public static void saveXML(MusicBandCollectionStructure data) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MusicBandCollection.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Marshal the employees list in console
            jaxbMarshaller.marshal(bands, System.out);

        } catch (PropertyException e) {
            System.out.println("Ошибка доступа к файлу!");
        } catch (JAXBException e) {
            System.out.println("Ошибка при записи коллекции в файл! Текст системной ошибки:");
            System.out.println(e.getMessage());
        }
    }

    /*private void parseXML(String fileName) {



        for(MusicBand emp : bands.getMusicBandCollection())
        {
            System.out.println(emp.name);
        }


    }

    */
}
