package controllers;

import javax.inject.Inject;

import models.Cadastro;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import validadores.ValidadorDeCadastro;
import views.html.*;

public class CadastroController extends Controller {

	@Inject
	private FormFactory formularios;
	@Inject
	private ValidadorDeCadastro validadorDeCadastro;
	
	public Result salvaNovoCadastro() {
		Form<Cadastro> formulario = formularios.form(Cadastro.class).bindFromRequest();
		Cadastro Cadastro = formulario.get();
		if (validadorDeCadastro.temErros(formulario)) {
			flash("danger", "Existem erros no seu formulário!");
			return badRequest(formularioDeNovoCadastro.render(formulario));
		}
		Cadastro.save();
		flash("success", "Seu Cadastro '"+Cadastro.getTitulo()+"' foi cadastrado com sucesso!");
		return redirect(routes.CadastroController.formularioDeNovoCadastro());
	}
	
	public Result formularioDeNovoCadastro() {
		Cadastro Cadastro = new Cadastro();
		Cadastro.setTipo("e-book");
		Form<Cadastro> formulario = formularios.form(Cadastro.class).fill(Cadastro);
		return ok(formularioDeNovoCadastro.render(formulario));
	}
}
