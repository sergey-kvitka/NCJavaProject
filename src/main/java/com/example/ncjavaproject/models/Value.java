package com.example.ncjavaproject.models;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@CrossOrigin
public class Value implements DBValue {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "object_id")
    private Long objectId;

    @Column(nullable = false, name = "attribute_id")
    private Long attributeId;

    @Column
    private String value;

    @Column(name = "date_value")
    private Date dateValue;

    public Value(Long objectId, Long attributeId, String value, Date dateValue) {
        this.objectId = objectId;
        this.attributeId = attributeId;
        if (value != null)
            this.value = value;
        else
            this.dateValue = dateValue;
    }

    public Value(@NotNull ObjectDB object, @NotNull Attribute attribute, String value) {
        objectId = object.getId();
        attributeId = attribute.getId();
        this.value = value;
    }

    public Value(@NotNull ObjectDB object, @NotNull Attribute attribute, @NotNull Date dateValue) {
        objectId = object.getId();
        attributeId = attribute.getId();
        this.dateValue = dateValue;
    }

    @Override
    public AttributeType.Type valueType() {
        return AttributeType.Type.TEXT;
    }
}
