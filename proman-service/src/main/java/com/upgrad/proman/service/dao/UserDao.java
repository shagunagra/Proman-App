package com.upgrad.proman.service.dao;

import com.upgrad.proman.service.entity.UserAuthTokenEntity;
import com.upgrad.proman.service.entity.UserEntity;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


@Repository
public class UserDao {

    // Container Managed Entity Manager.
    // Two types of Entity Manager
    // 1. Container Managed
    // 2. Application Managed
    @PersistenceContext
    EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity){
      entityManager.persist(userEntity);
      return userEntity;
    }

    public UserEntity getUser(final String userUuid){
        // Select * from user where user.id = <user_id> and role.id = <role_id>
        // As query parameters /user?id=123&roleId=100
        // Save it in a String parameter
        try{
            return entityManager.createNamedQuery("userByUuid", UserEntity.class)
                    .setParameter("uuid", userUuid)
                    .getSingleResult();
        } catch(NoResultException nre){
            return null;
        }
    }

    public UserEntity getUserByEmail(final String email){

        try{
            return entityManager.createNamedQuery("userByEmail", UserEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

    public UserAuthTokenEntity createAuthToken(final UserAuthTokenEntity userAuthTokenEntity){
        entityManager.persist(userAuthTokenEntity);
        return userAuthTokenEntity;
    }

    public void updateUser(final UserEntity updatedUserEntity){
        entityManager.merge(updatedUserEntity);
    }

    public UserAuthTokenEntity getUserAuthToken(final String accessToken){
        try{
            return entityManager.createNamedQuery("userAuthTokenByAccessToken", UserAuthTokenEntity.class)
                    .setParameter("accessToken", accessToken).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }
}
