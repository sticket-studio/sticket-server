package com.ec.sticket.services;

import com.ec.sticket.dto.request.user.SignupRequest;
import com.ec.sticket.models.Asset;
import com.ec.sticket.models.Sticon;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserLikeUser;
import com.ec.sticket.models.mapping.compositekey.UserLikeUserKey;
import com.ec.sticket.repositories.AssetRepository;
import com.ec.sticket.repositories.SticonRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.repositories.mapping.like.UserLikeUserRepository;
import com.ec.sticket.util.ApiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final SticonRepository sticonRepository;
    private final UserLikeUserRepository userLikeUserRepository;
//    private final MotionticonRepository motionticonRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AssetRepository assetRepository
            , SticonRepository sticonRepository, UserLikeUserRepository userLikeUserRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.sticonRepository = sticonRepository;
        this.userLikeUserRepository = userLikeUserRepository;
        this.passwordEncoder = passwordEncoder;
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

    public ApiMessage findLike(int followerId, int followingId) {
        return ApiMessage.getSuccessMessage(userLikeUserRepository.getOne(new UserLikeUserKey(followerId, followingId)).getId());
    }

    public ApiMessage likeUser(int followerId, int followingId) {
        userLikeUserRepository.save(new UserLikeUser(new UserLikeUserKey(followerId, followingId)));
        return ApiMessage.getSuccessMessage();
    }

    public ApiMessage dislikeUser(int followerId, int followingId) {
        try {
            userLikeUserRepository.deleteById(new UserLikeUserKey(followerId, followingId));
            return ApiMessage.getSuccessMessage();
        } catch (Exception e) {
            log.error(e.toString());
            return ApiMessage.getFailMessage();
        }
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
