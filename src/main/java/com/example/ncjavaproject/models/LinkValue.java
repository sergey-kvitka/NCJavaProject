package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class LinkValue implements DBValue {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "object_id")
    private Long objectId;

    @Column(nullable = false, name = "attribute_id")
    private Long attributeId;

    @Column(nullable = false, name = "value_object_id")
    private Long valueObjectId;

    public LinkValue(@NotNull Long objectId, @NotNull Long attributeId, @NotNull Long valueObjectId) {
        this.objectId = objectId;
        this.attributeId = attributeId;
        this.valueObjectId = valueObjectId;
    }

    public LinkValue(@NotNull ObjectDB object, @NotNull Attribute attribute, @NotNull ObjectDB valueObject) {
        objectId = object.getId();
        attributeId = attribute.getId();
        valueObjectId = valueObject.getId();
    }

    @Override
    public AttributeType.Type valueType() {
        return AttributeType.Type.LINK;
    }
}
