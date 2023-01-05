package com.example.wmii.confg;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("user_id".equals(username)) {
			return new User("user_id", "$2a$10$Dx1iyLHuszXpL.wUfPtXDOychXoB5BiEx0ALWdW4FUBznXVEKjhWi", new ArrayList<>());			
		}else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
}
