package com.example.ncjavaproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "object")
@Setter
@Getter
@CrossOrigin
@NoArgsConstructor
public class ObjectDB implements Comparable<ObjectDB> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "object_type_id")
    private Long objectTypeId;

    @Column(name = "parent_object_id")
    private Long parentObjectId = null;

    public ObjectDB(String name, Long objectTypeId) {
        this.name = name;
        this.objectTypeId = objectTypeId;
    }

    public ObjectDB(String name, Long objectTypeId, Long parentObjectId) {
        this.name = name;
        this.objectTypeId = objectTypeId;
        this.parentObjectId = parentObjectId;
    }

    @Override
    public String toString() {
        return "ObjectDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", objectTypeId=" + objectTypeId +
                ", parentObjectId=" + parentObjectId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectDB objectDB = (ObjectDB) o;
        return Objects.equals(name, objectDB.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(ObjectDB object) {
        return this.name.compareTo(object.getName());
    }
}
