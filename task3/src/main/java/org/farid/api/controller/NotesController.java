package org.farid.api.controller;

import org.farid.api.service.NotesService;
import org.farid.libs.request.TokenReq;
import org.farid.libs.response.NoteResponse;
import org.farid.security.jwt.Const;
import org.farid.security.jwt.JwtTokenService;
import org.farid.security.userdetails.CustomUserDetails;
import org.farid.security.userdetails.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private final NotesService service;
    private final JwtTokenService tokenService;
    private final CustomUserDetailsService userDetailsService;

    public NotesController(NotesService service, JwtTokenService tokenService, CustomUserDetailsService userDetailsService) {
        this.service = service;
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<NoteResponse> getNote(@RequestHeader(name = Const.AUTH_HEADER) TokenReq tokenReq) {
        return Optional.of(tokenReq.getToken().split(" ")[1])
                .flatMap(tokenService::tokenToClaims)
                .map(tokenService::getIdFromClaims)
                .map(userDetailsService::loadUserById)
                .map(userDetails -> service.getNotes())
                .orElse(new LinkedList<>());
    }

    @PostMapping
    public NoteResponse postNote(@RequestParam("note") String note, @RequestHeader(name = Const.AUTH_HEADER) TokenReq tokenReq) {
        UserDetails currentUser = Optional.of(tokenReq.getToken().split(" ")[1])
                .flatMap(tokenService::tokenToClaims)
                .map(tokenService::getIdFromClaims)
                .map(userDetailsService::loadUserById)
                .orElse(new CustomUserDetails(-1, "", ""));

        return service.addNote(note, currentUser.getUsername())
                .map(oneOne -> new NoteResponse(oneOne.getId(), oneOne.getNote(), oneOne.getAuthors()))
                .orElse(new NoteResponse(-1L, "", new LinkedList<>()));
    }

    @PutMapping
    public NoteResponse putNote(@RequestParam("newNote") String newNote, @RequestParam("noteId") Long id, @RequestHeader(name = Const.AUTH_HEADER) TokenReq req) {

        UserDetails currentUser = Optional.of(req.getToken().split(" ")[1])
                .flatMap(tokenService::tokenToClaims)
                .map(tokenService::getIdFromClaims)
                .map(userDetailsService::loadUserById)
                .orElse(new CustomUserDetails(-1, "", ""));

        return service.putNote(currentUser.getUsername(), id, newNote)
                .map(oneNote -> new NoteResponse(oneNote.getId(), oneNote.getNote(), oneNote.getAuthors()))
                .orElse(new NoteResponse(-1L, "", new LinkedList<>()));
    }

    @DeleteMapping
    public void deleteNote(@RequestParam("id") long noteId, @RequestHeader(name = Const.AUTH_HEADER) TokenReq req) {
        Optional.of(req.getToken().split(" ")[1])
                .flatMap(tokenService::tokenToClaims)
                .map(tokenService::getIdFromClaims)
                .map(userDetailsService::loadUserById)
                .ifPresent(userDetails -> service.deleteNote(noteId));
    }

}
