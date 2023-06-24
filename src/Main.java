import Cooking.Candy;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static Utils.XMLParser.XMLParse;


public class Main {
    public static void main(String[] args) throws IOException, XMLStreamException, SAXException {
        File file = new File("src/Files/candies.xml");

        List<Candy> candies = XMLParse(file);
        candies.forEach(System.out::println);
    }
}