package br.com.bluetask.app.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AppUser {

	@Id
	@GeneratedValue
	private Integer id;
	private String username;
	private String password;
	private String displayName;
	
	public AppUser(String username, String password, String displayName) {
		this.username = username;
		this.password = password;
		this.displayName = displayName;
	}
	
	
	
}
