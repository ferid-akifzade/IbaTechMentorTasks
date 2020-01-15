package org.farid.api.service;

import org.farid.libs.Note;
import org.farid.libs.response.NoteResponse;
import org.farid.repositories.NoteRepository;
import org.farid.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotesService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NotesService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<NoteResponse> getNotes() {
        return ((List<Note>) noteRepository.findAll())
                .stream()
                .map(oneNote -> new NoteResponse(oneNote.getId(), oneNote.getNote(), oneNote.getAuthors()))
                .collect(Collectors.toList());
    }

    public Optional<Note> addNote(String note, String currEmail) {
        return userRepository.findByEmail(currEmail).map(oneUser -> noteRepository.save(new Note(note, oneUser.getId())));
    }

    public Optional<Note> putNote(String currEmail, Long noteId, String note) {

        return noteRepository.findById(noteId).flatMap(oneNote ->
                userRepository.findByEmail(currEmail).map(oneUser -> {
                    oneNote.addAuthor(oneNote.getId());
                    oneNote.setNote(note);
                    return noteRepository.save(oneNote);
                }));
    }

    public void deleteNote(Long noteId) {
        noteRepository.findById(noteId)
                .ifPresent(noteRepository::delete);
    }

}
