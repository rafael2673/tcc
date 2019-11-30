package controllers;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import models.Foto;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Funcionalidade: Essa classe tem como função salvar e expor fotos do núcleo de
 * artes
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 * 
 */
@With(Seguranca.class)
public class Galerias extends Controller {

	/**
	 * Listar fotos no template
	 */
	public static void fotos() {
		List<Foto> lista = Foto.findAll();
		render(lista);
	}

	/**
	 * salvar fotos no servidor
	 * 
	 * @param ft  new Foto
	 * @param pic new File
	 */
	public static void picture(Foto ft, File pic) {

		if (!(new File("./uploads/fotos").exists())) {
			new File("./uploads/fotos").mkdirs();
		}

		File dest = new File("./uploads/fotos/" + pic.getName());
		// Teste para sobrepor uma imagem com o mesmo nome
		if (dest.exists()) {
			flash.error("Imagem ou Nome já existente");
			dest.delete();
			// Passando a imagem para a pasta do play e salvando seu nome no banco de dados
		}
		pic.renameTo(dest);
		ft.nomeFoto = pic.getName();
		try {
			ft.save();
		} catch (Exception e) {
			flash.error("Erro de constraint");
		}
		fotos();

	}
	
	public static void deletar(Long id) {
		Foto f = Foto.findById(id);
		File dest = new File("./uploads/pdf/" + f.nomeFoto);
		dest.delete();
		f.delete();
		flash.success("Deletado com sucesso");
		fotos();
	}
}
