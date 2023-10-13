package com.book.store.app.bookstoreapplication.configuration;

import com.book.store.app.bookstoreapplication.model.AuthenticationAuthorizationInfo;
import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.repository.AuthenticationAuthorizationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AuthenticationAuthorizationInfoRepository authenticationAuthorizationInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<GrantedAuthority>authorities;
        String emailId=authentication.getName();
        String pwd=authentication.getCredentials().toString();
        AuthenticationAuthorizationInfo authenticationAuthorizationInfo=authenticationAuthorizationInfoRepository.findByuserName(emailId);
        if(authenticationAuthorizationInfo!=null){
            NewUser user=authenticationAuthorizationInfo.getNewUser();
            if(user!=null){
                if(user.getIsVerified().equals("false"))
                    throw new BadCredentialsException("Please Verify your mail by clicking the link send to your mail at registration time!!");
                if(passwordEncoder.matches(pwd,authenticationAuthorizationInfo.getPassword())){
                    authorities=new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(authenticationAuthorizationInfo.getRoles().getRoleName()));
                    return new
                            UsernamePasswordAuthenticationToken(user.getFirstName(),pwd,authorities);
                }else{
                    throw new BadCredentialsException("Invalid password!!");
                }
            }
        }
        throw new BadCredentialsException("Account Not Found!!!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
