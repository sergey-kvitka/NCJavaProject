package com.example.ncjavaproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@CrossOrigin
public class Value implements DBValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "object_id")
    private Long objectId;

    @Column(nullable = false, name = "attribute_id")
    private Long attributeId;

    @Column
    private String value;

    @Column(name = "date_value")
    private Date dateValue;

    public Value(Long objectId, Long attributeId, String value) {
        this(objectId, attributeId, value, null);
    }

    public Value(Long objectId, Long attributeId, String value, Date dateValue) {
        this.objectId = objectId;
        this.attributeId = attributeId;
        if (value != null)
            this.value = value;
        else
            this.dateValue = dateValue;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", attributeId=" + attributeId +
                ", value='" + value + '\'' +
                ", dateValue=" + dateValue +
                '}';
    }

    @Override
    public AttributeType.Type valueType() {
        return AttributeType.Type.TEXT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DBValue)) return false;
        DBValue value = (DBValue) o;
        return  Objects.equals(this.getObjectId(), value.getObjectId()) &&
                Objects.equals(this.getAttributeId(), value.getAttributeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId, attributeId);
    }
}
