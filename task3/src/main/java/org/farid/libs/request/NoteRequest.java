package org.farid.libs.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NoteRequest {
    private Long noteId;
    private String note;
}
