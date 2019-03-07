package com.ec.sticket.services;

import com.ec.sticket.models.Title;
import com.ec.sticket.repositories.TitleRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {

    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public ApiMessage save(Title title) {
        if (title != null && title.getName() != null) {
            titleRepository.save(title);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    public Title findById(int titleId) {
        Optional<Title> titleOptional = titleRepository.findById(titleId);
        return titleOptional.orElseGet(Title::new);
    }

    public ApiMessage update(Title modified) {
        Optional<Title> titleOptional = titleRepository.findById(modified.getId());

        if (titleOptional.isPresent()) {
            Title title = titleOptional.get();

            title.setName(modified.getName());

            titleRepository.save(title);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
    
    public ApiMessage deleteById(int titleId){
        Optional<Title> titleOptional = titleRepository.findById(titleId);

        if (titleOptional.isPresent()) {
            titleRepository.deleteById(titleId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }
}
