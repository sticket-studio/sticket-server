package com.ec.sticket.services;

import com.ec.sticket.models.Title;
import com.ec.sticket.repositories.TitleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {

    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    public Title findById(int themeId) {
        Optional<Title> titleOptional = titleRepository.findById(themeId);
        return titleOptional.orElseGet(Title::new);
    }
}
