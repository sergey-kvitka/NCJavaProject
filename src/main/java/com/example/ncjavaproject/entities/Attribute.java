package com.example.ncjavaproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@CrossOrigin
public class Attribute {

    public static final Long ATTRIBUTE_TYPE_ID_TEXT = 1L;
    public static final Long ATTRIBUTE_TYPE_ID_LINK = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "attribute_type_id")
    private Long attributeTypeId;

    @Column(nullable = false, name = "object_type_id")
    private Long objectTypeId;

    public Attribute(String name, Long attributeTypeId, Long objectTypeId) {
        this.name = name;
        this.attributeTypeId = attributeTypeId;
        this.objectTypeId = objectTypeId;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attributeTypeId=" + attributeTypeId +
                ", objectTypeId=" + objectTypeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return Objects.equals(name, attribute.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}