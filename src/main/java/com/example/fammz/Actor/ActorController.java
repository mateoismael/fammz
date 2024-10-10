package com.example.fammz.Actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    // Obtener todos los actores
    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    // Obtener un actor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        Actor actor = actorService.getActorById(id);
        return actor != null ? ResponseEntity.ok(actor) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo actor
    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    // Actualizar un actor existente
    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor actorDetails) {
        Actor updatedActor = actorService.updateActor(id, actorDetails);
        return updatedActor != null ? ResponseEntity.ok(updatedActor) : ResponseEntity.notFound().build();
    }

    // Eliminar un actor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        boolean deleted = actorService.deleteActor(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
