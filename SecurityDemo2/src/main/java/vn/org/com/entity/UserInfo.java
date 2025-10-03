package vn.org.com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Tạo getters, setters, toString, equals, hashCode (Lombok)
@AllArgsConstructor // Tạo constructor với tất cả tham số (Lombok)
@NoArgsConstructor // Tạo constructor không tham số (Lombok)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String email;
    private String password; // Lưu trữ mật khẩu đã mã hóa
    private String roles;    // Chuỗi chứa các role (VD: "ROLE_USER,ROLE_ADMIN")
}