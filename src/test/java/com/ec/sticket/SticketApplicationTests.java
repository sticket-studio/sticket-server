package com.ec.sticket;

import com.ec.sticket.repositories.AssetRepository;
import com.ec.sticket.repositories.StickerRepository;
import com.ec.sticket.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SticketApplicationTests {

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final StickerRepository stickerRepository;

    public SticketApplicationTests(UserRepository userRepository, AssetRepository assetRepository, StickerRepository stickerRepository) {
    this.userRepository = userRepository;
    this.assetRepository = assetRepository;
    this.stickerRepository = stickerRepository;
}

    @Test
    public void contextLoads() {
//        User user =
    }

}

