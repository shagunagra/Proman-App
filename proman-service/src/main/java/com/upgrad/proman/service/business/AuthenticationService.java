package com.upgrad.proman.service.business;

import com.upgrad.proman.service.business.JwtTokenProvider;
import com.upgrad.proman.service.dao.UserDao;
import com.upgrad.proman.service.entity.UserAuthTokenEntity;
import com.upgrad.proman.service.entity.UserEntity;
import com.upgrad.proman.service.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AuthenticationService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;


    @Transactional(propagation = Propagation.REQUIRED)
    public UserAuthTokenEntity authenticate(final String username, final String password) throws AuthenticationFailedException {

        UserEntity userEntity = userDao.getUserByEmail(username);

        if(userEntity == null){
            throw new AuthenticationFailedException("ATH-001", "User email is not found");
        }

        final String encryptedPassword = passwordCryptographyProvider.encrypt(password, userEntity.getSalt());
        // Base64 encode -  password - password -  p -   0101   0101   0101   0101 - DIXW - p
        //                                                D      I       X       W


        if(encryptedPassword.equals(userEntity.getPassword())){

            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
            UserAuthTokenEntity userAuthToken = new UserAuthTokenEntity();
            userAuthToken.setUser(userEntity);

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime expiresAt = now.plusHours(8);
            userAuthToken.setAccessToken(jwtTokenProvider.generateToken(userEntity.getUuid(), now, expiresAt));

            userAuthToken.setLoginAt(now);
            userAuthToken.setExpiresAt(expiresAt);
            userAuthToken.setCreatedAt(now);
            userAuthToken.setCreatedBy("backend-api");

            userDao.createAuthToken(userAuthToken);
            userDao.updateUser(userEntity);
            userEntity.setLastLoginAt(now);

            return userAuthToken;

        }
        else
        {
            throw new AuthenticationFailedException("ATH-002", "Password Incorrect!");
        }

    }
}
