package vn.org.com.repository;

import vn.org.com.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Extends JpaRepository để thừa hưởng các hàm CRUD cơ bản
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    
    // Khai báo method custom để tìm kiếm user bằng username (name)
    // Theo quy tắc đặt tên của Spring Data JPA, hàm này sẽ tự động tạo query
    Optional<UserInfo> findByName(String name);
}
