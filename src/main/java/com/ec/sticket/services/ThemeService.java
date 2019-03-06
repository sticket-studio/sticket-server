package com.ec.sticket.services;

import com.ec.sticket.models.Theme;
import com.ec.sticket.repositories.ThemeRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public ApiMessage save(Theme theme){
        if(theme!=null && theme.getName()!=null) {
            themeRepository.save(theme);
            return ApiMessage.getSuccessMessage();
        }else{
            return ApiMessage.getFailMessage();
        }
    }

    public List<Theme> findAll(){
        return themeRepository.findAll();
    }

    public Theme findThemeById(int themeId){
        Optional<Theme> theme = themeRepository.findById(themeId);
        return theme.orElseGet(Theme::new);
    }
}
