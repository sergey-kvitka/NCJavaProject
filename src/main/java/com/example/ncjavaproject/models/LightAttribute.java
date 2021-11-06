package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LightAttribute {
    private final String name;
    private final Long attributeTypeId;

    public LightAttribute(@NotNull Attribute attribute) {
        name = attribute.getName();
        attributeTypeId = attribute.getObjectTypeId();
    }

    public String getName() {
        return name;
    }

    public Long getAttributeTypeId() {
        return attributeTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LightAttribute that = (LightAttribute) o;
        return Objects.equals(this.name, that.name) && Objects.equals(this.attributeTypeId, that.attributeTypeId);
    }

    public static List<LightAttribute> attributesToLight(List<Attribute> attributes, boolean onlyUnique) {
        List<LightAttribute> lightAttributes = new ArrayList<>();
        LightAttribute newLightAttribute;
        for (Attribute attribute : attributes) {
            newLightAttribute = new LightAttribute(attribute);
            if (onlyUnique && lightAttributes.contains(newLightAttribute)) continue;
            lightAttributes.add(newLightAttribute);
        }
        return lightAttributes;
    }

    public static List<LightAttribute> attributesToLight(List<Attribute> attributes) {
        return attributesToLight(attributes, true);
    }

}
