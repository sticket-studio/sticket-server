package com.ec.sticket;

import com.ec.sticket.models.*;
import com.ec.sticket.models.mapping.SticonAsset;
import com.ec.sticket.repositories.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SticketApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StickRepository stickRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private SticonRepository sticonRepository;
    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        // User - 유저
        User user1 = new User(User.SnsType.NAVER, "yhc94@naver.com", passwordEncoder.encode("test"), "양희찬1", null);
        User user2 = new User(User.SnsType.GOOGLE, "yhc944@gmail.com2", passwordEncoder.encode("test"), "양희찬2", null);

        userRepository.save(user1);
        userRepository.save(user2);

        // Stick - 캐시 아이템

        Stick stick1 = new Stick(10, 1000, null);
        Stick stick2 = new Stick(25, 2000, null);
        Stick stick3 = new Stick(50, 3000, null);
        Stick stick4 = new Stick(80, 5000, null);

        stickRepository.save(stick1);
        stickRepository.save(stick2);
        stickRepository.save(stick3);
        stickRepository.save(stick4);

        // Title - 칭호

        Title title1 = new Title("초보 디자이너");
        Title title2 = new Title("중급 디자이너");
        Title title3 = new Title("고오급 디자이너");
        Title title4 = new Title("초보 이용자");
        Title title5 = new Title("중급 이용자");
        Title title6 = new Title("지름신");

        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);
        titleRepository.save(title4);
        titleRepository.save(title5);
        titleRepository.save(title6);

        // Quest - 퀘스트

        Quest quest1 = new Quest("에셋을 등록해보자!", "에셋을 1개 등록하기", 4);
        Quest quest2 = new Quest("스티커를 등록해보자!", "스티커를 1개 등록하기", 5);
        Quest quest3 = new Quest("모션티콘을 등록해보자!", "모션티콘을 1개 등록하기", 5);
        Quest quest4 = new Quest("에셋을 사용해보자!", "에셋을 1개 사용하기", 4);
        Quest quest5 = new Quest("스티커를 사용해보자!", "스티커를 1개 사용하기", 5);
        Quest quest6 = new Quest("모션티콘을 사용해보자!", "모션티콘을 1개 등록하기", 6);

        questRepository.save(quest1);
        questRepository.save(quest2);
        questRepository.save(quest3);
        questRepository.save(quest4);
        questRepository.save(quest5);
        questRepository.save(quest6);

        // Theme - 테마

        Theme theme1 = new Theme("귀염뽀짝");
        Theme theme2 = new Theme("재밌는");
        Theme theme3 = new Theme("부드러운");
        Theme theme4 = new Theme("섹시한");
        Theme theme5 = new Theme("로맨틱");
        Theme theme6 = new Theme("연예인");

        themeRepository.save(theme1);
        themeRepository.save(theme2);
        themeRepository.save(theme3);
        themeRepository.save(theme4);
        themeRepository.save(theme5);
        themeRepository.save(theme6);

        // Asset - 에셋

        Asset asset1 = new Asset(user1, Asset.Landmark.EYE_LEFT, theme1, "궁예안대"
                , "https://aws.xxx.xxx/s3/xxxxx", 3, "궁예가 쓰던 안대를 써보자!");

        Asset asset2 = new Asset(user1, Asset.Landmark.EYE_LEFT, theme3, "하트 눈"
                , "https://aws.xxx.xxx/s3/xxxxx", 3, "하트 뿅뿅");

        Asset asset3 = new Asset(user1, Asset.Landmark.CHEEK_LEFT, theme2, "홍조"
                , "https://aws.xxx.xxx/s3/xxxx1", 4, "부끄부끄>.<");

        Asset asset4 = new Asset(user2, Asset.Landmark.NOSE, theme4, "돼지코"
                , "https://aws.xxx.xxx/s3/xxxx2", 3, "꿀꿀꿀!! 꿀..! 꿀꿀꿀!!");

        assetRepository.save(asset1);
        assetRepository.save(asset2);
        assetRepository.save(asset3);
        assetRepository.save(asset4);

        // Sticon - 스티커

        SticonAsset sticonAsset1 = new SticonAsset();
        sticonAsset1.setAsset(asset1);
        sticonAsset1.setOffsetX(-0.1f);
        sticonAsset1.setOffsetY(0f);
        sticonAsset1.setRotate(0);
        sticonAsset1.setFlip(false);
        SticonAsset sticonAsset2 = new SticonAsset();
        sticonAsset2.setAsset(asset1);
        sticonAsset2.setOffsetX(0.1f);
        sticonAsset2.setOffsetY(0f);
        sticonAsset2.setRotate(180);
        sticonAsset2.setFlip(true);
        SticonAsset sticonAsset3 = new SticonAsset();
        sticonAsset3.setAsset(asset2);
        sticonAsset3.setOffsetX(0f);
        sticonAsset3.setOffsetY(0f);
        sticonAsset3.setRotate(0);
        sticonAsset3.setFlip(false);

        List<SticonAsset> sticonAssets1 = new ArrayList<>();
        sticonAssets1.add(sticonAsset1);
        sticonAssets1.add(sticonAsset2);
        sticonAssets1.add(sticonAsset3);

        Sticon sticon1 = new Sticon(user1, sticonAssets1, theme1, "궁예","대표이미지 URL", 6, "안대 두개 한 궁예");

        sticonRepository.save(sticon1);

        // Motionticon - 모션티콘

//        Motionticon motionticon = new Motionticon(user1, );

        //
    }

}

