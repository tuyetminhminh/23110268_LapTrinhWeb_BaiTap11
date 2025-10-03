package vn.org.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.org.com.config.UserInfoUserDetails;
import vn.org.com.entity.UserInfo;
import vn.org.com.repository.UserInfoRepository;

import java.util.Optional;

// Implements UserDetailsService để thay thế In-memory UserDetailsManager
@Service // Đánh dấu là Spring Service
public class UserInfoDetailService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    // Phương thức chính: Tìm user theo tên và trả về UserDetails
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Lấy UserInfo từ Database
        Optional<UserInfo> userInfo = repository.findByName(username);
        
        // Nếu không tìm thấy, ném ngoại lệ
        return userInfo.map(UserInfoUserDetails::new) // Nếu tìm thấy, convert sang UserInfoUserDetails
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}