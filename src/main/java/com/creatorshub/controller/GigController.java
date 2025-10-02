package com.creatorshub.controller;

import com.creatorshub.model.Gig;
import com.creatorshub.service.GigService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/gigs")
public class GigController {
    private final GigService gigService;
    public GigController(GigService gigService) { this.gigService = gigService; }

    @PostMapping
    public ResponseEntity<Gig> create(@RequestBody Gig gig, @AuthenticationPrincipal UserDetails user) {
        // set ownerId to authenticated user's id is skipped for demo - client can pass ownerId
        Gig created = gigService.create(gig);
        return ResponseEntity.created(URI.create("/api/gigs/" + created.getId())).body(created);
    }

    @GetMapping
    public List<Gig> list() { return gigService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Gig> get(@PathVariable Long id) {
        return gigService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gig> update(@PathVariable Long id, @RequestBody Gig gig) {
        return ResponseEntity.ok(gigService.update(id, gig));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        gigService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
