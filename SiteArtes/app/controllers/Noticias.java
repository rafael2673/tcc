package controllers;

import java.io.File;
import java.util.List;

import models.Membro;
import models.Noticia;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;

/** 
 * Funcionalidade: Essa classe tem como função detalhar as noticias
 * criar, editar ou remover noticias, adicionar membros ou eventos na agenda.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 * 
 * */
@With(Seguranca.class)
public class Noticias extends Controller {
	/**
	 * página inicial, detalhamento de membros, noticias
	 * e da agenda.
	 * */
	public static void inicial() {
		List<Noticia> lista = Noticia.find("order by id desc").fetch();
		List<Membro> lista2  = Membro.findAll();
		render(lista, lista2);
	}

	public static void salvar(Noticia not) {
		if (not.texto.equals("") || not.texto.equals(null)) {
			flash.error("Adicione uma Mensagem");
		} else {
			not.save();
			flash.success("Notícia adicionada com sucesso");
		}
		inicial();
	}

	/**
	 * tem como função listar as noticias com um filtro de pesquisa
	 * */
	public static void mostrar() {
		String busca = params.get("busca");
		List<Noticia> not = null;
		if (busca == null || busca.isEmpty()) {
			not = Noticia.findAll();
		} else {
			not = Noticia.find("lower(texto) like ?1 order by id desc",
					"%" + busca.toLowerCase() + "%").fetch();
		}
		render(not);
	}

	public static void editar(long id) {
		Noticia not = Noticia.findById(id);
		renderTemplate("Noticias/inicial.html", not);
	}

	public static void deletar(long id) {
		Noticia not = Noticia.findById(id);
		not.delete();
		flash.success("Removido com sucesso");
		inicial();
	}
	public static void teste() {
		render();
	}
}
