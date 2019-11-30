package controllers;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import models.Membro;
import play.mvc.Controller;
import play.mvc.With;

/** 
 * Funcionalidade: Essa classe tem como função salvar, editar ou remover
 *  os membros do núcleo no banco.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 * 
 * */
@With(Seguranca.class)
public class Membros extends Controller {
	
	public static void salvar(@Valid Membro membro, File foto) {
		if (foto != null) {
			new File("./uploads/" + membro.nome).mkdirs();
		 File dest = new File("./uploads/" + membro.nome + "/" + foto.getName());
			
			if (dest.exists()) {
				dest.delete();
			}
			
			foto.renameTo(dest);
			membro.nomeFoto = foto.getName();
		}

		membro.save();

		Noticias.inicial();
	}
	
	public static void editar (Long id) {
		Membro membro = Membro.findById(id);
		renderTemplate("Noticias/inicial.html", membro);
	}
	public static void deletar (Long id) {
		Membro membro = Membro.findById(id);
		membro.delete();
		flash.success("Removido com sucesso");
		Noticias.inicial();
	}
}
