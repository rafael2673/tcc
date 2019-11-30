package controllers;

import java.util.List;

import models.Agenda;
import play.mvc.Controller;

/**
 * 
 * classe para manipulação da agenda.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 *
 * 
 * 
 **/
public class Agendas extends Controller {
	public static void index() {
		render();
	}
	
	/**
	 * Adicionando eventos na agenda, salvando no banco de dados.
	 * @param a new agenda
	 **/
	public static void salvar(Agenda a) {
		a.save();
		Noticias.inicial();
	}
}
