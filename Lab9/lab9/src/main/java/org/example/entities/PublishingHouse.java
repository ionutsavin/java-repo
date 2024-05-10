package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "publishing_houses")
@NamedQueries({
        @NamedQuery(name = "PublishingHouse.findAll", query = "SELECT p FROM PublishingHouse p ORDER BY p.name"),
        @NamedQuery(name = "PublishingHouse.findById", query = "SELECT p FROM PublishingHouse p WHERE p.id = :id"),
        @NamedQuery(name = "PublishingHouse.findByName", query = "SELECT p FROM PublishingHouse p WHERE p.name = :name"),
        @NamedQuery(name = "PublishingHouse.findByBookTitle", query = "SELECT p FROM PublishingHouse p JOIN p.books b WHERE b.title = :title")
})
public class PublishingHouse extends AbstractEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "publishingHouse")
    private Set<Book> books;

    public PublishingHouse() {
    }

    public PublishingHouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "PublishingHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}