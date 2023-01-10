package com.example.wmii.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "auth")
	private String auth;
	
	@Builder
	public User(String name, String password, String auth) {
		this.name = name;
		this.password = password;
		this.auth = auth;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		for(String role : auth.split(",")) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		return roles;
	}

	//사용자의 이름 반환
	@Override
	public String getUsername() {
		return name;
	}

	//사용자의 password를 반환
	@Override
	public String getPassword() {
		return password;
	}

	//계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정 잠금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//패스워드의 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
		return true;
	}

}
