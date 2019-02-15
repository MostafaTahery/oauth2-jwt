package co.nilin.security.oauth2jwt.data;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

    private static final long serialVersionUID=1L;
    public CustomUser(UserEntity userEntity){
        super(userEntity.getUsername(),userEntity.getPassword(),userEntity.getGrantedAuthorities());
    }

}
