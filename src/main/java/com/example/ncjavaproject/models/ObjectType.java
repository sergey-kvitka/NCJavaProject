package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity(name = "object_type")
@Getter
@Setter
@CrossOrigin
@NoArgsConstructor
public class ObjectType {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "parent_object_type_id")
    private Long parentObjectTypeId = null;

    public ObjectType(String name) {
        this.name = name;
    }

    public ObjectType(@NotNull String name, @NotNull ObjectType parentObjectType) {
        this.name = name;
        parentObjectTypeId = parentObjectType.getId();
    }
//    public List<Attribute> getAttributes() {
//        return DBComponent().attributeRepository.findAllByObjectTypeId(id);
//    }
//
//    public List<Attribute> getAllInheritedAttributes() {
//        List<Attribute> attributes = this.getAttributes();
//        DBComponent().objectTypeRepository.findById(parentObjectTypeId).ifPresent(
//                 parentObjectType -> attributes.addAll(parentObjectType.getAllInheritedAttributes())
//        );
//        return attributes;
//    }
//
//    public void addAttributes(List<Attribute> attributes, LightAttribute...lightAttributes) {
//        List<LightAttribute> currentAttributes = LightAttribute.attributesToLight(this.getAttributes());
//        List<LightAttribute> newAttributes = (attributes == null
//                ? new ArrayList<>()
//                : LightAttribute.attributesToLight(attributes));
//        if (lightAttributes.length != 0) {
//            newAttributes.addAll(Arrays.asList(lightAttributes));
//        }
//
//        List<ObjectType> children;
//
//        for (LightAttribute newLightAttribute : newAttributes) {
//            if (!currentAttributes.contains(newLightAttribute)) {
//                DBComponent().attributeRepository.save(new Attribute(newLightAttribute, this.id));
//                children = DBComponent().objectTypeRepository.findAllByParentObjectTypeId(this.id);
//                for (ObjectType child : children) {
//                    child.addAttributes(null, newLightAttribute);
//                }
//            }
//        }
//    }
//
//    public void addChildObjectType(String childObjectTypeName) {
//        ObjectType child = DBComponent().objectTypeRepository.save(
//                new ObjectType(childObjectTypeName, this));
//
//    }
 }
