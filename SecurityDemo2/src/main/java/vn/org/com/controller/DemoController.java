package vn.org.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // THÊM HOẶC CHỈNH SỬA ENDPOINT NÀY
    @GetMapping("/") 
    public String home() { 
        // Đây là trang mặc định sau khi đăng nhập thành công
        return "<h1>Welcome to Home Page (Protected)</h1> <p>Sau khi đăng nhập, bạn được chuyển hướng về đây. Thử truy cập <a href='/hello'>/hello</a> (Public) hoặc <a href='/customers'>/customers</a> (Protected).</p>";
    }

    // Endpoint công khai
    @GetMapping("/hello") 
    public String hello() { 
        return "<h1>Hello (public)</h1> <p>Bạn đã truy cập trang công khai. Thử truy cập trang bảo vệ: <a href='/customers'>/customers</a></p>"; 
    }

    // Endpoint được bảo vệ
    @GetMapping("/customers") 
    public String customers() { 
        return "<h1>Customers (protected)</h1> <p>Đây là trang bảo vệ, chỉ có người dùng đã đăng nhập mới xem được.</p>"; 
    }
}