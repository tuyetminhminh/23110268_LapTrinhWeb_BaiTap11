package vn.org.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.org.com.entity.UserInfo;
import vn.org.com.repository.UserInfoRepository;

@SpringBootApplication
public class SecurityDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemo2Application.class, args);
	}
	@Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Hàm chạy một lần khi ứng dụng khởi động để thêm user vào DB (Bước 7)
    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Kiểm tra nếu user "user" chưa tồn tại thì mới thêm
            if (repository.findByName("user").isEmpty()) {
                UserInfo user = new UserInfo();
                user.setName("user");
                user.setPassword(passwordEncoder.encode("123")); // Mã hóa mật khẩu
                user.setEmail("user@test.com");
                user.setRoles("ROLE_USER"); // Vai trò (Role)
                repository.save(user);
            }

            if (repository.findByName("admin").isEmpty()) {
                UserInfo admin = new UserInfo();
                admin.setName("admin");
                admin.setPassword(passwordEncoder.encode("admin")); // Mã hóa mật khẩu
                admin.setEmail("admin@test.com");
                admin.setRoles("ROLE_ADMIN,ROLE_USER"); 
                repository.save(admin);
            }
        };
    }

}
