package com.devsuperior.bds04.repository;

import com.devsuperior.bds04.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
