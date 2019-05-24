package com.ec.sticket.services;

import com.ec.sticket.dto.request.user.AssetLikeRequest;
import com.ec.sticket.dto.request.user.MotionticonLikeRequest;
import com.ec.sticket.dto.request.user.SignupRequest;
import com.ec.sticket.dto.request.user.SticonLikeRequest;
import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.User;
import com.ec.sticket.repositories.AssetRepository;
import com.ec.sticket.repositories.SticonRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final SticonRepository sticonRepository;
//    private final MotionticonRepository motionticonRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AssetRepository assetRepository
            , SticonRepository sticonRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.sticonRepository = sticonRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        String newEmail = "yhc944@gmail.com";
        User yhc944 = userRepository.findByEmail(newEmail);
        if (yhc944 == null) {
            SignupRequest request = SignupRequest.builder()
                    .email(newEmail)
                    .name("양희찬")
                    .password("password")
                    .build();
            ApiMessage apiMessage = this.save(request);
            System.out.println(apiMessage.getMessage());
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseGet(User::new);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ApiMessage save(SignupRequest request) {
        if (request != null) {
            User user = new User(User.SnsType.NONE, request.getEmail(), passwordEncoder.encode(request.getPassword()),
                    request.getName(), null);
            userRepository.save(user);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(User modified) {
        Optional<User> userOptional = userRepository.findById(modified.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.update(modified);

            userRepository.save(user);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    // 완전 삭제가 아닌 데이터 수정
    public ApiMessage delete(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setName("탈퇴한 유저");
            user.setEmail(String.valueOf(user.getId()));
            user.setPassword(BCrypt.hashpw("0000", BCrypt.gensalt()));
            // TODO: 이미지 파일도 삭제
            user.setImgUrl(null);
            user.setStick(0);

            userRepository.save(user);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage likeAsset(AssetLikeRequest request){
        userRepository.likeAsset(request.getAssetId(),request.getUserId());

        return ApiMessage.getSuccessMessage();
    }

    public ApiMessage likeSticon(SticonLikeRequest request){
        userRepository.likeSticon(request.getSticonId(), request.getUserId());

        return ApiMessage.getSuccessMessage();
    }

    public ApiMessage likeMotionticon(MotionticonLikeRequest request){
        userRepository.likeMotionticon(request.getMotionticonId(), request.getUserId());

        return ApiMessage.getSuccessMessage();
    }

    public ApiMessage addSellingAsset(int userId, Asset asset) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            asset.setAuthor(user);

            assetRepository.save(asset);

            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage addSellingSticon(int userId, Sticon sticon) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            sticon.setAuthor(user);

            sticonRepository.save(sticon);

            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userId);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid name or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
