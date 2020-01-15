package org.farid.libs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NoteResponse {
    private Long noteId;
    private String note;
    private List<Long> authors;
}
