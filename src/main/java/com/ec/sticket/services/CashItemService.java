package com.ec.sticket.services;

import com.ec.sticket.models.CashItem;
import com.ec.sticket.repositories.CashItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashItemService {
    private final CashItemRepository cashItemRepository;

    public CashItemService(CashItemRepository cashItemRepository) {
        this.cashItemRepository = cashItemRepository;
    }

    public List<CashItem> findAll(){
        return cashItemRepository.findAll();
    }
}
