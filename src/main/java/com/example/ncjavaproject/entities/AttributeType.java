package com.example.ncjavaproject.entities;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity(name = "attribute_type")
@NoArgsConstructor
@CrossOrigin
public class AttributeType {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    public AttributeType(@NotNull Type attributeTypeName) {
        name = attributeTypeName.name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AttributeType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public enum Type {
        TEXT, LINK
    }
}
