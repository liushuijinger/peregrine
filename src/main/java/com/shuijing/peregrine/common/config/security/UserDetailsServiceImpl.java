package com.shuijing.peregrine.common.config.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author liushuijing (shuijing@shanshu.ai)
 * @date 2021-08-09
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return User.builder().username("admin").password("$2a$10$zgh1tBntUwtv0TOh/PwR4OJ4cJ9oASLOScujrhv1/AAcPBpzHvC/2")
                        .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admins")).build();
    }

}
