package controllers;

import java.util.ArrayList;
import java.util.List;

import groovy.transform.stc.FirstParam.FirstGenericType;
import models.Curso;
import models.CursoLogin;
import models.Login;
import models.Status;
import models.StatusPadrao;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Funcionalidade: Essa classe tem como função salvar, editar, remover e mostrar
 * os cursos que estão sendo disponibilizados pelo NUARTE
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 * 
 */
@With(Seguranca.class)
public class Cursos extends Controller {

	/**
	 * Esse método lista todos os cursos salvos no banco de dados
	 */
	public static void curso() {
		List<Curso> lista = Curso.findAll();
		String id = session.get("usuario.id");
		List<CursoLogin> cl = CursoLogin.find("idLogin = ?1 ", id).fetch();
		for (int i = 0; i < lista.size(); i++) {
			Curso c = lista.get(i);
			for (int j = 0; j < cl.size(); j++) {
				if (cl.get(j).curso.id == c.id && cl.get(j).status == Status.Inscrito) {
					c.inscrito = true;
				} else if (cl.get(j).curso.id == c.id && cl.get(j).status == Status.Em_Espera) {
					c.emEspera = true;
				} else if (cl.get(j).curso.id == c.id && cl.get(j).status == Status.Não_Inscrito) {
					c.naoInscrito = true;
				}
			}

		}
		render(lista, cl);
	}

	/**
	 * Método para salvar no banco de dados
	 * 
	 * @param curso New curso
	 */
	public static void salvar(Curso curso) {
		curso.save();
		curso();
	}

	/**
	 * método editar um objeto salvo no banco
	 * 
	 * @param id new Long
	 */
	public static void editar(Long id) {
		Curso curso = Curso.findById(id);
		renderTemplate("Cursos/curso.html", curso, id);
	}

	/**
	 * método para remover dados do banco de dados
	 * 
	 * @param id Long
	 */
	public static void remover(Long id) {
		Curso curso = Curso.findById(id);
		curso.delete();
		curso();
	}
}
