package com.ec.sticket.controllers.normal;

import com.ec.sticket.services.MotionticonService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotionticionController {

    private  final MotionticonService motionticonService;

    public MotionticionController(MotionticonService motionticonService) {
        this.motionticonService = motionticonService;
    }


}
