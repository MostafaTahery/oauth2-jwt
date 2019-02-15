package co.nilin.security.oauth2jwt.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@PropertySource({"classpath:db.properties"})
@Repository
public class OAuthDao {


   // @Autowired
    //private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.class.name"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;

    }


    public UserEntity getUserDetails(String username){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        Collection<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        String userSQLQuery= "SELECT * FROM USERS WHERE USERNAME=?";
        List<UserEntity> list=jdbcTemplate.query(userSQLQuery,new String[]{username},(ResultSet rs,int rowNum)->{
            UserEntity userEntity=new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(rs.getString("PASSWORD"));
            return userEntity;
        });
        if(list.size()>0){
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            ((ArrayList<GrantedAuthority>) grantedAuthorities).add(grantedAuthority);
            list.get(0).setGrantedAuthorities(grantedAuthorities);
            return list.get(0);
        }
        return null;
    }

}
