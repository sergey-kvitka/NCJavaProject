package com.example.ncjavaproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@CrossOrigin
@NoArgsConstructor
public class LinkValue implements DBValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "object_id")
    private Long objectId;

    @Column(nullable = false, name = "attribute_id")
    private Long attributeId;

    @Column(nullable = false, name = "value_object_id")
    private Long valueObjectId;

    public LinkValue(Long objectId, Long attributeId, Long valueObjectId) {
        this.objectId = objectId;
        this.attributeId = attributeId;
        this.valueObjectId = valueObjectId;
    }

    @Override
    public String toString() {
        return "LinkValue{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", attributeId=" + attributeId +
                ", valueObjectId=" + valueObjectId +
                '}';
    }

    @Override
    public AttributeType.Type valueType() {
        return AttributeType.Type.LINK;
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
