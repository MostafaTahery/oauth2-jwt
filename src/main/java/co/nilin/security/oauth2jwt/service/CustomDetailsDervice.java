package co.nilin.security.oauth2jwt.service;

import co.nilin.security.oauth2jwt.data.CustomUser;
import co.nilin.security.oauth2jwt.data.OAuthDao;
import co.nilin.security.oauth2jwt.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsDervice implements UserDetailsService {

    @Autowired
    OAuthDao oAuthDao;

    @Override
    public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserEntity userEntity=null;
        try{
            userEntity=oAuthDao.getUserDetails(username);
            CustomUser customUser=new CustomUser(userEntity);
            return customUser;
           }
        catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

    }
}
