package org.farid.libs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    @Transient
    private final String AUTH_DELIMITER = ",";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note;
    private String authors;

    public Note(String note, String authors) {
        this.note = note;
        this.authors = authors;
    }

    public Note(String note, Long authId) {
        this.note = note;
        this.authors = String.join(",", authId.toString());
    }

    public List<Long> getAuthors() {
        return Arrays.stream(authors.split(AUTH_DELIMITER))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public void setAuthors(List<Long> authors) {
        this.authors = authors.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public void addAuthor(Long authId) {
        List<Long> authors = getAuthors();
        if (!authors.contains(authId))
            authors.add(authId);
        setAuthors(authors);
    }
}
