package models;

import java.util.List;

public class EnvelopeDeCadastro {

	private List<Produto> Cadastro;
	public EnvelopeDeCadastro(List<Produto> Cadastro) {
		this.Cadastro = Cadastro;
	}
	public List<Produto> getCadastro() {
		return Cadastro;
	}
}
