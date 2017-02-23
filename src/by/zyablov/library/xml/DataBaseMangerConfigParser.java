package by.zyablov.library.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;



/**
 * Class {@code DataBaseMangerConfigParser} has methods to parse a xml file with
 * a configurations for {@code DataBaseManager}.
 * 
 * @author Дмитрий
 *
 * @see DataBaseManager
 */
public class DataBaseMangerConfigParser {

	/**
	 * Atribute from XML file element
	 */
	private static final int ATRIBUTE_VALUE = 1;

	/**
	 * Atribute from XML file element
	 */
	private static final int ATRIBUTE_NAME = 0;

	/**
	 * A properties to configurate {@code DataBaseManager}.
	 */
	private HashMap<String, String> properties;

	/**
	 * XML file input stream.
	 */
	private XMLInputFactory inputFactory;

	/**
	 * Enums for tags of XML file.
	 * 
	 * @author Дмитрий
	 *
	 */
	public enum ConfigEnum {
		CONFIG, PROPERTY, LIMIT_CONFIG;
	}

	/**
	 * Parses and initializes a xml file with configurations.
	 * 
	 * @param fileName
	 *            - a path to xml file.
	 */
	private void buildConfig(String fileName) {
		InputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;

		try {

			inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
			reader = inputFactory.createXMLStreamReader(inputStream);

			// StaX parsing
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();

					if (ConfigEnum.valueOf(name.toUpperCase()) == ConfigEnum.CONFIG) {
						this.properties = buildProperties(reader);
					}
				}
			}

		} catch (XMLStreamException e) {
			System.err.println("StAX parsing error! " + e.getMessage());

		} finally {

			try {
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (IOException e) {
				System.err.println("Impossible close file " + fileName + ": " + e);
			}
		}

	}

	/**
	 * Builds and initializes properties from XML file.
	 * 
	 * @param reader
	 * @return Initialized properties
	 * 
	 * @throws XMLStreamException
	 */
	private HashMap<String, String> buildProperties(XMLStreamReader reader) throws XMLStreamException {
		HashMap<String, String> parsedProperties = new HashMap<>();
		String name;

		while (reader.hasNext()) {
			int type = reader.next();
			if (type == XMLStreamConstants.START_ELEMENT) {
				name = reader.getLocalName();

				if (ConfigEnum.valueOf(name.toUpperCase().replace('-', '_')) == ConfigEnum.PROPERTY) {
					addNewProperty(parsedProperties, reader);
				}
			}
		}
		return parsedProperties;
	}

	/**
	 * Parses a new property from XML file and sets it to global properties.
	 * 
	 * @param properties
	 *            Global properties
	 * @param reader
	 *            An input XML file stream
	 * 
	 * @throws XMLStreamException
	 */
	private void addNewProperty(HashMap<String, String> properties, XMLStreamReader reader) throws XMLStreamException {
		properties.put(reader.getAttributeValue(ATRIBUTE_NAME), reader.getAttributeValue(ATRIBUTE_VALUE));
	}

	/**
	 * Constructs a {@code ConfigParser} using name of path to XML file.
	 * 
	 * @param fileName
	 *            A name of path to XML file.
	 */
	public DataBaseMangerConfigParser(String fileName) {
		this.inputFactory = XMLInputFactory.newInstance();

		this.buildConfig(fileName);
	}

	/**
	 * @return the properties
	 */
	public HashMap<String, String> getProperties() {
		return properties;
	}

}
