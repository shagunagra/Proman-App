package com.upgrad.proman.service.business;

import com.upgrad.proman.service.dao.UserDao;
import com.upgrad.proman.service.entity.RoleEntity;
import com.upgrad.proman.service.entity.UserAuthTokenEntity;
import com.upgrad.proman.service.entity.UserEntity;
import com.upgrad.proman.service.exception.ResourceNotFoundException;
import com.upgrad.proman.service.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAdminBusinessService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    public UserEntity getUser(final String userUuid, final String authorizationToken) throws ResourceNotFoundException, UnauthorizedException {

        UserAuthTokenEntity userAuthTokenEntity = userDao.getUserAuthToken(authorizationToken);
        RoleEntity role= userAuthTokenEntity.getUser().getRole();

        if(role != null && role.getUuid() == 101){
            UserEntity user = userDao.getUser(userUuid);

            if(user == null){
                // My Exception handling
                throw new ResourceNotFoundException("USR-001", "User Not Found");

            }else{
                return user;
            }
        }
        throw new UnauthorizedException("ATH-002", "You are not authorized for this action!!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity createUser(final UserEntity userEntity){

        String password = userEntity.getPassword();
//        String password = "proman@123";
//        userEntity.setPassword(password);

        if(password == null){
            userEntity.setPassword("proman@123");
        }

        String[] encryptText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptText[0]);
        userEntity.setPassword(encryptText[1]);

        return userDao.createUser(userEntity);


    }
}
