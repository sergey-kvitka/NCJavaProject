package com.example.ncjavaproject.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Objects;

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

    public ObjectType(String name, Long parentObjectTypeId) {
        this.name = name;
        this.parentObjectTypeId = parentObjectTypeId;
    }

    public ObjectType(@NotNull String name, @NotNull ObjectType parentObjectType) {
        this.name = name;
        parentObjectTypeId = parentObjectType.getId();
    }

    @Override
    public String toString() {
        return "ObjectType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentObjectTypeId=" + parentObjectTypeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectType that = (ObjectType) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
