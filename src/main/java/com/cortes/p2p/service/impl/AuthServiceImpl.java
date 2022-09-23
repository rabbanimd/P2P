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

            Mailer mailer = new Mailer();
            try{
                mailer.sendVerificationEmail(signupDTO.getEmail(),
                        signupDTO.getFullName(),
                        cred.getAuthToken());
            }catch (Exception e) {
                throw new InvalidUserDataException("email"/*field*/, signupDTO.getEmail());
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
