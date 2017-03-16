package validadores;

import javax.inject.Inject;

import daos.CadastroDAO;
import models.Cadastro;
import play.data.Form;
import play.data.validation.ValidationError;

public class ValidadorDeCadastro {

	@Inject 
	private CadastroDAO CadastroDAO;

	public boolean temErros(Form<Cadastro> formulario) {
		Cadastro Cadastro = formulario.get();
		if (Cadastro.getEmail() == 0.0) {
			formulario.reject(new ValidationError("Email", "email inválido"));
		}
		return formulario.hasErrors();
	}
}
