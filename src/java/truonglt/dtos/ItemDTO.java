/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package truonglt.dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author datnlm
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemDTO", propOrder = {
    "id",
    "name",
    "description",
    "point",
    "type",
    "status"
})
@XmlRootElement(name = "item")
public class ItemDTO implements Serializable {

    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "point")
    private String point;
    @XmlAttribute(name = "type")
    private String type;
    @XmlElement(name = "status")
    private String status;

    public ItemDTO() {
    }

    public ItemDTO(String id, String name, String description, String point, String type, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.point = point;
        this.type = type;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
