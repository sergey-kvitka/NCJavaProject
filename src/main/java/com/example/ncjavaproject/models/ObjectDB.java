package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity(name = "object")
@Setter
@Getter
@CrossOrigin
@NoArgsConstructor
public class ObjectDB {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "object_type_id")
    private Long objectTypeId;

    @Column(name = "parent_object_id")
    private Long parentObjectId = null;

    public ObjectDB(@NotNull String name, @NotNull ObjectType objectType) {
        this.name = name;
        objectTypeId = objectType.getId();
    }

    public ObjectDB(@NotNull String name, @NotNull Long objectTypeId) {
        this.name = name;
        this.objectTypeId = objectTypeId;
    }

    public ObjectDB(@NotNull String name, @NotNull ObjectType objectType, @NotNull ObjectDB parentObject) {
        this.name = name;
        objectTypeId = objectType.getId();
        parentObjectId = parentObject.getId();
    }

    public ObjectDB(@NotNull String name, @NotNull Long objectTypeId, @NotNull Long parentObjectId) {
        this.name = name;
        this.objectTypeId = objectTypeId;
        this.parentObjectId = parentObjectId;
    }

//    public ObjectType getObjectType() {
//        return DBComponent.objectTypeRepository().findById(objectTypeId).orElseThrow();
//
//    }

//    public List<Attribute> getAttributes() {
//        return getObjectType().getAttributes();
//    }
//
//    public List<Attribute> getAllInheritedAttributes() {
//        return getObjectType().getAllInheritedAttributes();
//    }
//
//    public void addAttributes(List<Attribute> attributes, LightAttribute...lightAttributes) {
//        getObjectType().addAttributes(attributes, lightAttributes);
//    }

//    public void addValue(Attribute attribute, String value) {
//        if (attribute.getAttributeType().equals(AttributeType.Type.LINK)) throw new IllegalArgumentException();
//        DBComponent().valueRepository.save(new Value(this, attribute, value));
//    }
//
//    public void addValue(Attribute attribute, Date dateValue) {
//        if (attribute.getAttributeType().equals(AttributeType.Type.LINK)) throw new IllegalArgumentException();
//        DBComponent().valueRepository.save(new Value(this, attribute, dateValue));
//    }
//
//    public void addValue(Attribute attribute, ObjectDB objectValue) {
//        if (attribute.getAttributeType().equals(AttributeType.Type.TEXT)) throw new IllegalArgumentException();
//        DBComponent().linkValueRepository.save(new LinkValue(this, attribute, objectValue));
//    }
//    public void addChildObject() {
//
//    }
}
