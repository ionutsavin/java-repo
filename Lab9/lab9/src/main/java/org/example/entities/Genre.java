package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g ORDER BY g.name"),
        @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = :id"),
        @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name = :name"),
        @NamedQuery(name = "Genre.findByBookTitle", query = "SELECT g FROM Genre g JOIN g.books b WHERE b.title = :title")
})
public class Genre extends AbstractEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;

    public Genre() {
    }

    public Genre(String name) {
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
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

