package controllers;

import java.util.List;

import models.Login;
import models.Nivel;
import play.modules.paginate.ValuePaginator;
import play.mvc.Controller;
import play.mvc.With;

/** 
 * Funcionalidade: Essa classe tem como função listar os usuarios do sistema
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 * 
 * */
@With(Seguranca.class)
public class Usuarios extends Controller {
	
	/**
	 * Listando usuarios, também possui filtro de pesquisa
	 * */
	public static void gerenciar() {
		String busca = params.get("busca");
		List<Login> l = null;
		if (busca == null || busca.isEmpty()) {
			l = Login.findAll();
		} else {
			l = Login.find("lower(nome) like ?1 or lower(email) like ?1 or lower(nivel) like ?1 order by id",
					"%" + busca.toLowerCase() + "%").fetch();
		}
		ValuePaginator listaPaginada = new ValuePaginator(l); 
		listaPaginada.setPageSize(20);
		render(listaPaginada);

	}
	/**
	 * Editar nível no sistema, sendo estes: Administrador ou
	 * usuário comum.
	 * obs: somente os administradores podem utilizar dessa função.
	 * */
	public static void editarNivel(Long id, Nivel n) {
		Login login = Login.findById(id);
		login.nivel = n;
		login.save();
		gerenciar();
	}
}
