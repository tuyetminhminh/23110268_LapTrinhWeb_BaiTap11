package vn.org.com.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import vn.org.com.entity.UserInfo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Implements UserDetails để Spring Security có thể sử dụng thông tin user
public class UserInfoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    // Constructor để khởi tạo từ Entity UserInfo
    public UserInfoUserDetails(UserInfo userInfo) {
        name = userInfo.getName();
        password = userInfo.getPassword();
        // Chuyển chuỗi roles (VD: "ROLE_USER,ROLE_ADMIN") thành List of GrantedAuthority
        authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // --- Các phương thức của UserDetails ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name; // name trong entity tương ứng với username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}