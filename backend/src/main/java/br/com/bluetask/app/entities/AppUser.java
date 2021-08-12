package br.com.bluetask.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O nome de usuário é obrigatório")
	private String username;
	
	@NotEmpty(message = "A senha é obrigatória")
	private String password;
	
	@NotEmpty(message = "o nome de exibição é obrigatório")
	private String displayName;
		
	public AppUser(String username, String password, String displayName) {
		this.username = username;
		this.password = password;
		this.displayName = displayName;
	}
	
}
