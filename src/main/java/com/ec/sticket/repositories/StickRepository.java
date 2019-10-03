package com.ec.sticket.repositories;

import com.ec.sticket.models.Stick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StickRepository extends JpaRepository<Stick, Integer> {

}
