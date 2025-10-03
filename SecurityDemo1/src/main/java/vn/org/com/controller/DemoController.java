package vn.org.com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.experimental.PackagePrivate;
import vn.org.com.entity.Customer;

@RestController
public class DemoController {

	final private List<Customer> customers = List.of(
			Customer.builder().id("001").name("Phạm Thị Tuyết Minh").email("phamthituyetminh2005@gmail.com").build(),
			Customer.builder().id("002").name("User One").email("userone@gmail.com").build());

	// Endpoint công khai, không cần đăng nhập
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Hello, Guest!!!");
	}

	@GetMapping("/admin/home")
	public ResponseEntity<String> adminHome() {
		return ResponseEntity.ok("ADMIN HOME - xem /customer/all");
	}

	@GetMapping("/user/home")
	public ResponseEntity<String> userHome() {
		return ResponseEntity.ok("USER HOME - xem /customer/{id}");
	}
}