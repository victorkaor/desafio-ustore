package daos;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model.Finder;

import models.Cadastro;

public class CadastroDAO {

	private Finder<Long, Cadastro> Cadastros = new Finder<>(Cadastro.class);

	public Optional<Cadastro> comCodigo(String codigo) {
		Cadastro Cadastro = Cadastros
				.where()
				.eq("codigo", codigo)
				.findUnique();
		return Optional.of(Cadastro);
	}

	public List<Cadastro> todos() {
		return Cadastros.all();
	}

	public List<Cadastro> doNome(String Nome) {
		return Cadastros.where().eq("Nome", Nome).findList();
	}

	public List<Cadastro> comFiltro(Map<String, String> parametros) {
		ExpressionList<Cadastro> consulta = Cadastros.where();
		parametros.entrySet().forEach(entrada -> {
			consulta.eq(entrada.getKey(), entrada.getValue());
		});
		return consulta.findList();
	}
}
