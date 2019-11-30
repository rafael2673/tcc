package controllers;

import java.util.List;

import models.Curso;
import models.CursoLogin;
import models.Login;
import models.Status;
import play.mvc.Controller;

/** 
 * Funcionalidade: Essa classe tem como função criar métodos para intermediar
 * a comunicação entre o Model e a view. 
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 * 
 * */
public class CursosLogin extends Controller {

	/*
	 * objetos estáticos recebendo nulo
	 * 
	 */
	static CursoLogin cursologin = null;
	static Curso c = null;
	static Login l = null;

	/**
	 *  Esse método tem como função salvar o curso e o login na tabela CursoLogin
	 *  Recebe da view id do Curso e id do Login
	 *  @param idCurso new Long
	 *  @param idLogin new Long
	**/
	public static void salvar(Long idCurso, Long idLogin) {

		/**
		 * objeto da tabela CursoLogin
		 * */
		cursologin = new CursoLogin();

		/** 
		 * Buscando o objeto pelo id e salvando na variavel
		 * 
		 **/
		c = Curso.findById(idCurso);
		l = Login.findById(idLogin);

		/* 
		 * salvando os objetos Curso e Login na tabela CursoLogin
		 * 
		 */
		cursologin.curso = c;
		cursologin.login = l;

		/* 
		 * Persistindo os dados no Banco de dados
		 * 
		 */
		cursologin.save();
		/* 
		 * Retornando para a view curso
		 * 
		 */
		Cursos.curso();
	}

	/**
	 * Esse método tem como função buscar uma lista de inscritos na tabela
	 * CursoLogin do bd com condição de status "Em_Espera" e do id igual 
	 * ao recebido pelo método.
	 * 
	 * @param idcurso new Long
	 * 
	 **/
	public static void avaliar(Long idcurso) {

		// Recebendo a lista com as condições
		List<CursoLogin> cl = CursoLogin.find(" status = 'Em_Espera'and curso.id = ?1 ", idcurso).fetch();

		// Passando a lista para o template
		renderTemplate("/Cursos/avaliar.html", cl);
	}

	/**
	 * Esse método tem como função substituir o status Em_Espera do banco de dados
	 * Caso tenha recebido o número em questão Recebe um id do CursoLogin, um
	 * inteiro (dependendo da opção escolhida pelo administrador) e o id do curso
	 * @param id new Long
	 * @param i new Integer
	 * @param idcurso new Long
	 **/
	public static void mudar(Long id, Integer i, Long idcurso) {
		
		//Procurando no banco de dados o CursoLogin pelo id e salvando na variável
		cursologin = CursoLogin.findById(id);
		
		//Teste de mudança de status
		if (i == 1) {
			cursologin.status = Status.Inscrito;
		} else {
			cursologin.status = Status.Não_Inscrito;
		}
		
		//Persistindo os dados no bd
		cursologin.save();
		
		//retornando para a view avaliar
		avaliar(idcurso);
	}
}
