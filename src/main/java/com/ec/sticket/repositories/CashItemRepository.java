package com.ec.sticket.repositories;

import com.ec.sticket.models.CashItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashItemRepository extends JpaRepository<CashItem, Integer> {

}
