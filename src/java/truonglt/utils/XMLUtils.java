/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package truonglt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.helpers.DefaultHandler;
import truonglt.dtos.ItemDTO;

public class XMLUtils {

    public static Document parseFileToDOM(String filePath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File file = new File(filePath);
        Document doc = db.parse(file);
        return doc;
    }

    public static boolean writeXML(Node node, String filePath) throws Exception {
        Source src = new DOMSource(node);
        File file = new File(filePath);
        Result result = new StreamResult(file);
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer trans = tff.newTransformer();
        trans.transform(src, result);
        return true;
    }

    public static void parseFileWithSAX(String filePath, DefaultHandler handler) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sax = spf.newSAXParser();
        File file = new File(filePath);
        sax.parse(file, handler);
    }

    public static void updateNodeinStAX(String updateId, String filePath, XMLStreamReader reader) throws Exception {
        InputStream is = null;
        OutputStream os = null;

        XMLEventWriter writerEvent = null;
        XMLEventReader readerEvent = null;

        try {
            XMLInputFactory xif = XMLInputFactory.newInstance();
            is = new FileInputStream(filePath);
            readerEvent = xif.createXMLEventReader(is);

            XMLOutputFactory xof = XMLOutputFactory.newInstance();
            os = new FileOutputStream(filePath + ".new");
            writerEvent = xof.createXMLEventWriter(os);

            JAXBContext jaxb = JAXBContext.newInstance(ItemDTO.class);
            Unmarshaller unmarshall = jaxb.createUnmarshaller();
            Marshaller marshall = jaxb.createMarshaller();
            marshall.setProperty(Marshaller.JAXB_FRAGMENT, true);

            while (readerEvent.hasNext()) {

                if (readerEvent.peek().isStartElement()
                        && readerEvent.peek().asStartElement().getName().getLocalPart().equals("item")) {
                    ItemDTO item = (ItemDTO) unmarshall.unmarshal(readerEvent);
                    if (item.getId().equals(updateId)) {
                        item.setStatus("disable");
                    }
                    marshall.marshal(item, writerEvent);

                } else {
                    writerEvent.add(readerEvent.nextEvent());
                }
            }
            writerEvent.flush();

            File f = new File(filePath);
            f.delete();

            f = null;
            f = new File(filePath + ".new");
            f.renameTo(new File(filePath));

            reader.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            is.close();
            writerEvent.close();
            reader.close();

            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static XMLStreamReader parseStAX(String filePath) throws Exception {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        File file = new File(filePath);
        InputStream is = new FileInputStream(file);
        XMLStreamReader reader = xif.createXMLStreamReader(is);
        return reader;
    }
}
