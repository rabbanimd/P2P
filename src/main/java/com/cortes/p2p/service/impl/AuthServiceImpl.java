package com.cortes.p2p.service.impl;

import com.cortes.p2p.Resolve.exceptions.InvalidUserDataException;
import com.cortes.p2p.Resolve.exceptions.ResourceAlreadyExistException;
import com.cortes.p2p.Resolve.exceptions.ResourceNotFoundException;
import com.cortes.p2p.data.DTO.SignupDTO;
import com.cortes.p2p.data.models.User;
import com.cortes.p2p.data.models.UserCredentials;
import com.cortes.p2p.data.payload.Author;
import com.cortes.p2p.repo.AuthRepository;
import com.cortes.p2p.repo.UserRepository;
import com.cortes.p2p.service.AuthService;
import com.cortes.p2p.service.helper.Mailer;
import com.cortes.p2p.utils.Generator;
import com.cortes.p2p.utils.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private Mailer mailer;

    @Override
    public Author registerUser(SignupDTO signupDTO) {
        User user;
        if (validate(signupDTO)) {
//            persist user
            user = new User();
            user.setName(signupDTO.getFullName());
            user.setUsername(signupDTO.getUsername());
            UserCredentials cred = new UserCredentials();
            cred.setUser(user);
            cred.setAuthToken(Generator.generateUserAuthToken());
            cred.setPassword(Generator.generateUserPasswordHash(signupDTO.getPassword()));
            cred.setExpireAt(TimeHelper.getUserAuthTokenExpireAt());
            user.setUserCredentials(cred); //!@@
            user.setEmail(signupDTO.getEmail());
            User persistedUser = userRepository.save(user);
            /**
             * impliment async api for email authentication
             */

            try {
                mailer.sendVerificationEmail(signupDTO.getEmail(),
                        signupDTO.getFullName(),
                        cred.getAuthToken());
            } catch (Exception e) {
                throw new InvalidUserDataException("email"/*field*/, signupDTO.getEmail()
                        /*FieldValue*/);
            }
            return Mapper.userToAuthor(persistedUser);
        }
        return null;
    }

    @Override
    public HttpStatus verifyToken(String userToken) {
        UserCredentials cred = authRepository.findByAuthToken(userToken);
        if (cred == null) {
            throw new ResourceNotFoundException("Token", "token", userToken);
        }
//        check if expired
        if (TimeHelper.isTokenValid(cred.getExpireAt())) {
//            set authorize true
            User user = cred.getUser();
            user.setAuthorized(true);
            userRepository.save(user);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    /**
     * loginUser(String username, String password)
     * username contains '@' => email
     * otherwise username.
     *
     * @return Author
     */
    @Override
    public Author loginUser(String username, String password) {
        User user;
        if (username.contains("@")) {
            user = userRepository.findByEmail(username);
            if (user == null) {
                return null;
            }
            return Mapper.userToAuthor(user);
        } else {
            user = userRepository.findByUsername(username);
        }
        if (user.getUserCredentials().equals(Generator.generateUserPasswordHash(password))) {
            return Mapper.userToAuthor(user);
        }
        return null;
    }

    @Override
    public void regenerateAuthToken(Long userId) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                        "user", "id", userId));
        UserCredentials cred = user.getUserCredentials();
        if (!TimeHelper.isTokenValid(user.getUserCredentials().getExpireAt())) {
            cred.setAuthToken(Generator.generateUserAuthToken());
            cred.setExpireAt(TimeHelper.getUserAuthTokenExpireAt());
            user.setUserCredentials(cred);
            userRepository.save(user);
        }

        try {
            mailer.sendVerificationEmail(user.getEmail(),
                    user.getName(),
                    cred.getAuthToken());
        } catch (Exception e) {
            throw new InvalidUserDataException("email"/*field*/, user.getEmail()
                    /*FieldValue*/);
        }

    }

    /**
     * protocol :
     * We will verify first email
     * if old email is verified : we keep old email
     *
     * @param userId
     * @param newEmail
     */
    @Override
    public void updateEmail(Long userId, String newEmail) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                        "user", "id", userId));
        user.setEmail(newEmail);
        if (user.isAuthorized()) {
            user.setAuthorized(false);
        }
        user.getUserCredentials().setExpireAt(TimeHelper.getUserAuthTokenExpireAt());
        user.getUserCredentials().setAuthToken(Generator.generateUserAuthToken());
        userRepository.save(user);
        try {
            mailer.sendVerificationEmail(user.getEmail(),
                    user.getName(),
                    user.getUserCredentials().getAuthToken());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidUserDataException("email"/*field*/, user.getEmail()
                    /*FieldValue*/);
        }
    }

    private boolean validate(SignupDTO signupDTO) {
        /**
         * check uniqueness of email and username
         */
        User user;
        user = userRepository.findByUsername(signupDTO.getEmail());
        if (user != null) {
            throw new ResourceAlreadyExistException("user", "username", signupDTO.getUsername());
        }
        user = userRepository.findByEmail(signupDTO.getEmail());
        if (user != null) {
            throw new ResourceAlreadyExistException("user", "email", signupDTO.getEmail());
        }
        return true;
    }
}
