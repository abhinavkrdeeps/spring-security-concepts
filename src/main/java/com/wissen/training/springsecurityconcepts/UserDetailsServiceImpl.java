package com.wissen.training.springsecurityconcepts;

import com.wissen.training.springsecurityconcepts.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    @Lazy
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(s);

        if(!user.isPresent()){
            throw new UsernameNotFoundException(String.format("User %s Not Found",s));
        }
        logger.info("User: {}",user);
        return user.map(UserDetailsImpl::new).get();
    }
}
