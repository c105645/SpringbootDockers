package com.stackroute.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stackroute.user.dao.User;
import com.stackroute.user.dto.MyUserDetails;
import com.stackroute.user.repository.UserAuthRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private final UserAuthRepository userAuthRepo;

	public MyUserDetailsService(UserAuthRepository userAuthRepo) {
		this.userAuthRepo = userAuthRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userAuthRepo.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with Id" + username + "dont exist"));
		System.out.println(user);
		MyUserDetails desired=new MyUserDetails(user);
		System.out.println("desired : " + desired.getUsername() + " " + desired.getPassword() + " " + desired.getAuthorities() );
		return desired;
	}
}
