package com.creatorshub.service;

import com.creatorshub.model.Gig;
import com.creatorshub.repository.GigRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GigService {
    private final GigRepository repo;
    public GigService(GigRepository repo) { this.repo = repo; }

    public Gig create(Gig gig) { return repo.save(gig); }
    public Optional<Gig> findById(Long id) { return repo.findById(id); }
    public List<Gig> findAll() { return repo.findAll(); }
    public List<Gig> findByOwner(Long ownerId) { return repo.findByOwnerId(ownerId); }
    public Gig update(Long id, Gig updated) {
        return repo.findById(id).map(g -> {
            g.setTitle(updated.getTitle());
            g.setDescription(updated.getDescription());
            g.setPrice(updated.getPrice());
            g.setCategory(updated.getCategory());
            return repo.save(g);
        }).orElseThrow(() -> new RuntimeException("Gig not found"));
    }
    public void delete(Long id) { repo.deleteById(id); }
}
