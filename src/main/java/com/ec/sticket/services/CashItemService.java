package com.ec.sticket.services;

import com.ec.sticket.models.CashItem;
import com.ec.sticket.repositories.CashItemRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashItemService {
    private final CashItemRepository cashItemRepository;

    public CashItemService(CashItemRepository cashItemRepository) {
        this.cashItemRepository = cashItemRepository;
    }

    public List<CashItem> findAll() {
        return cashItemRepository.findAll();
    }

    public CashItem findById(int cashItemId) {
        Optional<CashItem> cashItem = cashItemRepository.findById(cashItemId);
        return cashItem.orElseGet(CashItem::new);
    }

    public ApiMessage save(CashItem cashItem) {
        if (cashItem != null && cashItem.getCash() != 0 && cashItem.getStick() != 0) {
            cashItemRepository.save(cashItem);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(CashItem modified) {
        Optional<CashItem> cashItemOptional = cashItemRepository.findById(modified.getId());
        if (cashItemOptional.isPresent()) {
            CashItem cashItem = cashItemOptional.get();

            cashItem.setCash(modified.getCash());
            cashItem.setStick(modified.getStick());
            cashItem.setImgUrl(modified.getImgUrl());

            cashItemRepository.save(cashItem);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int cashItemId) {
        Optional<CashItem> cashItem = cashItemRepository.findById(cashItemId);
        if (cashItem.isPresent()) {
            cashItemRepository.deleteById(cashItemId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
}
