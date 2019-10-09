package com.ec.sticket.services;

import com.ec.sticket.dto.request.auth.FindPasswordRequest;
import com.ec.sticket.dto.request.auth.UpdatePasswordRequest;
import com.ec.sticket.dto.request.user.SignupRequest;
import com.ec.sticket.dto.request.user.UserUpdateRequest;
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
import com.ec.sticket.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return user.orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ApiMessage save(SignupRequest request) {
        if (request != null) {
            User user = new User(User.SnsType.NONE, request.getEmail(), passwordEncoder.encode(request.getPassword()),
                    request.getName(), request.getDescription(), null);
            userRepository.save(user);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(int userId, UserUpdateRequest modified) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.update(modified);

            userRepository.save(user);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage(String.format("The user with id [%d] doesn't exist", userId));
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

    public ApiMessage findLike(int followerId) {
        List<UserLikeUser> userLikeUsers = userLikeUserRepository.findAllByFollowerId(followerId);
        List<User> followings = userLikeUsers.stream().map(UserLikeUser::getFollowing).collect(Collectors.toList());
        return ApiMessage.getSuccessMessage(followings);
    }

    public ApiMessage findLike(int followerId, int followingId) {
        return ApiMessage.getSuccessMessage(userLikeUserRepository.getOne(new UserLikeUserKey(followerId, followingId)));
    }

    @Transactional
    public ApiMessage likeUser(int followerId, int followingId) {
        if (followerId == followingId) {
            return ApiMessage.getFailMessage("Can't like myself");
        }

        User follower = findById(followerId);
        User following = findById(followingId);

        if (follower == null || following == null) {
            return ApiMessage.getFailMessage("follower/following doesn't exist");
        }

        UserLikeUserKey userLikeUserKey = new UserLikeUserKey(followerId, followingId);

        if (userLikeUserRepository.findById(userLikeUserKey).isPresent()) {
            return ApiMessage.getFailMessage(String.format("User with id [%d] already like User with id [%d]",
                    followerId, followingId));
        } else {
            userLikeUserRepository.save(new UserLikeUser(userLikeUserKey));
            follower.setFollowingCnt(follower.getFollowingCnt() + 1);
            following.setFollowerCnt(following.getFollowerCnt() + 1);
            return ApiMessage.getSuccessMessage();

        }
    }

    @Transactional
    public ApiMessage dislikeUser(int followerId, int followingId) {
        if (followerId == followingId) {
            return ApiMessage.getFailMessage("Can't dislike myself");
        }

        User follower = findById(followerId);
        User following = findById(followingId);

        if (follower == null || following == null) {
            return ApiMessage.getFailMessage("follower/following doesn't exist");
        }

        UserLikeUserKey userLikeUserKey = new UserLikeUserKey(followerId, followingId);

        if (!userLikeUserRepository.findById(userLikeUserKey).isPresent()) {
            return ApiMessage.getFailMessage(String.format("User with id [%d] doesn't like User with id [%d]",
                    followerId, followingId));
        } else {
            userLikeUserRepository.deleteById(userLikeUserKey);
            follower.setFollowingCnt(follower.getFollowingCnt() - 1);
            following.setFollowerCnt(following.getFollowerCnt() - 1);
            return ApiMessage.getSuccessMessage();

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

    public String findRandomPassword(FindPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            String randomPassword = RandomUtil.getRandomPassword();
            user.setPassword(passwordEncoder.encode(randomPassword));
            userRepository.save(user);
            return randomPassword;
        }else{
            return null;
        }
    }

    public String updatePassword(User user, UpdatePasswordRequest request) {
        if (user != null) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return request.getNewPassword();
        }else{
            return null;
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
