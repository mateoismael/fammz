package com.example.fammz.Actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    // Obtener todos los actores
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    // Obtener un actor por su ID
    public Actor getActorById(Long id) {
        Optional<Actor> actor = actorRepository.findById(id);
        return actor.orElse(null);
    }

    // Crear un nuevo actor
    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    // Actualizar un actor existente
    public Actor updateActor(Long id, Actor actorDetails) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (optionalActor.isPresent()) {
            Actor actor = optionalActor.get();
            actor.setName(actorDetails.getName());
            actor.setBirthDate(actorDetails.getBirthDate());
            actor.setBiography(actorDetails.getBiography());
            actor.setProfileImageUrl(actorDetails.getProfileImageUrl());
            return actorRepository.save(actor);
        } else {
            return null;
        }
    }

    // Eliminar un actor
    public boolean deleteActor(Long id) {
        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
