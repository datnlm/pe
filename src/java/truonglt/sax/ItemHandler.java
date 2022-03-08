/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package truonglt.sax;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import truonglt.dtos.ItemDTO;

/**
 *
 * @author datnlm
 */
public class ItemHandler extends DefaultHandler {

    private String currentTagName, name, point, type, status, fromPoint, toPoint;
    private boolean found, foundItem;
    private ItemDTO item;
    private List<ItemDTO> listItem;

    public List<ItemDTO> getListItem() {
        return listItem;
    }

    public ItemHandler(String fromPoint, String toPoint) {
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        found = false;
        foundItem = false;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
        currentTagName = qName;
        if (!found) {

            if (qName.equals("item")) {
                type = attributes.getValue("type");

            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        if (!found) {
            String str = new String(ch, start, length);
            if (currentTagName.equals("name")) {
                name = str.trim();
            } else if (currentTagName.equals("point")) {
                point = str.trim();
                if (Integer.parseInt(point) >= Integer.parseInt(fromPoint) && Integer.parseInt(point) <= Integer.parseInt(toPoint)) {
                    foundItem = true;
                }
            } else if (currentTagName.equals("status")) {
                status = str.trim();
                if(status.equals("enable") && foundItem){
                    found = true;
                }
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
        currentTagName = "";
        if (qName.equals("item")) {
            if (found) {
                if (listItem == null) {
                    listItem = new ArrayList<>();
                }
                item = new ItemDTO();
                item.setName(name);
                item.setPoint(point);
                item.setType(type);
                listItem.add(item);
            }
            found = false;
            foundItem = false;
        }

    }
}
