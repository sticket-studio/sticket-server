package com.ec.sticket.repositories;

import com.ec.sticket.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

}
