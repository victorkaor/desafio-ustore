package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import daos.CadastroDAO;
import models.EnvelopeDeCadastros;
import models.Cadastro;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.*;

public class ApiController extends Controller {

	private static final List<String> ATRIBUTOS = Arrays.asList("id", "nome", "email", "senha", "telefone");
	@Inject
	private CadastroDAO CadastroDAO;
	@Inject
	private FormFactory formularios;
	
	public Result todos() {
		EnvelopeDeCadastros envelopeDeCadastros = new EnvelopeDeCadastros(CadastroDAO.todos());
		return ok(Json.toJson(envelopeDeCadastros));
	}
	
	public Result doTipo(String tipo) {
		EnvelopeDeCadastros envelope = new EnvelopeDeCadastros(CadastroDAO.doTipo(tipo));
		return ok(Json.toJson(envelope));
	}
	
	public Result comFiltros() {
		DynamicForm formulario = formularios.form().bindFromRequest();
		validaFormulario(formulario);
		if (formulario.hasErrors()) {
			JsonNode erros = Json.newObject().set("erros", formulario.errorsAsJson());
			return badRequest(erros);
		}
		Map<String, String> parametros = formulario.data();
		List<Cadastro> Cadastros = CadastroDAO.comFiltro(parametros);
		EnvelopeDeCadastros envelope = new EnvelopeDeCadastros(Cadastros);
		return ok(Json.toJson(envelope));
	}

	private void validaFormulario(DynamicForm formulario) {
		Map<String, String> parametros = formulario.data();
		parametros.keySet().forEach(chave -> {
			if(!ATRIBUTOS.contains(chave)) {
				formulario.reject(new ValidationError("Atributos inválidos", chave));
			}
		});
	}
}
