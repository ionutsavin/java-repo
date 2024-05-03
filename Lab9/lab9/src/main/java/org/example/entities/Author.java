package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a ORDER BY a.name"),
        @NamedQuery(name = "Author.findById", query = "SELECT a FROM Author a WHERE a.id = :id"),
        @NamedQuery(name = "Author.findByName", query = "SELECT a FROM Author a WHERE a.name = :name")
})
public class Author extends AbstractEntity implements Serializable {

    @Column(name = "name")
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
