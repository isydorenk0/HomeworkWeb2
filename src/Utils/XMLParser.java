package Utils;

import Cooking.Candy;
import Cooking.Ingredients;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XMLParser {
    public static List<Candy> XMLParse(File file) throws XMLStreamException, IOException, SAXException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        validateXml(xmlInputFactory.createXMLEventReader(new FileInputStream(file)));

        XMLEventReader reader = xmlInputFactory.createXMLEventReader(
                new FileInputStream(file));

        List<Candy> candies = new ArrayList<>();
        Candy candy = new Candy();
        Ingredients ingredients = new Ingredients();

        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "candy":
                        candy = new Candy();
                        break;
                    case "name":
                        nextEvent = reader.nextEvent();
                        candy.setName(nextEvent.asCharacters().getData());
                        break;
                    case "energy":
                        nextEvent = reader.nextEvent();
                        candy.setEnergy(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "type":
                        nextEvent = reader.nextEvent();
                        candy.setType(nextEvent.asCharacters().getData());
                        break;
                    case "ingredients":
                        nextEvent = reader.nextEvent();
                        ingredients = new Ingredients();
                        break;
                    case "sugar":
                        nextEvent = reader.nextEvent();
                        ingredients.setSugar(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "fructose":
                        nextEvent = reader.nextEvent();
                        ingredients.setFructose(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "citric_acid":
                        nextEvent = reader.nextEvent();
                        ingredients.setCitric_acid(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "natural_flavor":
                        nextEvent = reader.nextEvent();
                        ingredients.setNatural_flavor(Boolean.parseBoolean(nextEvent.asCharacters().getData()));
                        break;
                    case "artificial_flavor":
                        nextEvent = reader.nextEvent();
                        ingredients.setArtificial_flavor(Boolean.parseBoolean(nextEvent.asCharacters().getData()));
                        break;
                    case "production":
                        nextEvent = reader.nextEvent();
                        candy.setProduction(nextEvent.asCharacters().getData());
                }
            }
            if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("candy")) {
                    candies.add(candy);
                } else if (endElement.getName().getLocalPart().equals("ingredients")) {
                    candy.setIngredients(ingredients);
                }
            }
        }
        return candies;
    }

    private static void validateXml(XMLEventReader reader) throws XMLStreamException, IOException, SAXException {
        Schema schema = null;
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(new File("src/Files/candies.xsd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.nonNull(schema)) {
            Validator validator = schema.newValidator();
            validator.validate(new StAXSource(reader));
        }
    }

}
