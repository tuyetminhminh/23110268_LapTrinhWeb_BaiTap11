package vn.org.com.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.org.com.entity.UserInfo;
import vn.org.com.repository.UserInfoRepository;

public class UserInfoService implements UserDetailsService{
	
	@Autowired
	UserInfoRepository repository;
	public void UserInforService(UserInfoRepository uir) {
		this.repository = uir;
	}
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repository.findByName(username);
		return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
	}

}
