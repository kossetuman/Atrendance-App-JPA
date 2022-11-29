package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //設定クラスであることを明示
public class SecurityConfig {

	@Bean //Spring(IoCコンテナ)に管理されるBeanの生成を示します
	public PasswordEncoder passwordEncoder() {
		// パスワードの暗号化用に、BCrypt(ビー・クリプト)を使用します
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// 認証リクエストの設定
				.authorizeHttpRequests(auth -> auth
						// 認証の必要があるように設定
						.anyRequest().authenticated())
				// フォームベースの認証の設定
				.formLogin();
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		//Userを作成
		UserDetails user = User.withUsername("user")
				//passwordをBCryptで暗号化
				.password(passwordEncoder().encode("password"))
				//権限を設定
				.authorities("USER")
				.build();
		//メモリ内認証を使用
		return new InMemoryUserDetailsManager(user);
	}

}
