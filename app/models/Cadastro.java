package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Cadastro extends Model {

	@Id
	@GeneratedValue
	private Long id;
	@Required(message = "Campo nome é obrigatório")
	private String nome;
	@Required(message = "Campo email é obrigatório")
	private String email;
	@Required(message = "Campo senha é obrigatório")
	private String senha;
	@Required(message = "Campo telefone é obrigatório")
	private int telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int gettelefone() {
		return telefone;
	}

	public void settelefone(int telefone) {
		this.telefone = telefone;
	}

}
