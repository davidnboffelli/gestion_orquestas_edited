package com.gruposei.gestion_orquestas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gruposei.gestion_orquestas.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {

    // Optional<Role> findByName(String name);

    // void deleteById(Long id);
}
