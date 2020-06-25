package controllers;

import java.io.File;
import java.util.List;

import models.StatusPadrao;
import models.Voz;
import play.modules.paginate.ValuePaginator;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class VozDigital extends Controller {
	public static void forum() {
		List<Voz> v = Voz.find("status != 'ATIVO'").fetch();
		List<Voz> v2 = Voz.find("status != 'INATIVO'").fetch();

		ValuePaginator listaPaginada = new ValuePaginator(v);
		ValuePaginator listaPaginada2 = new ValuePaginator(v2);
		listaPaginada.setPageSize(20);
		listaPaginada2.setPageSize(20);
		render(listaPaginada, listaPaginada2);
	}

	public static void salvar(Voz v, File pdf) {
		if (!(new File("./uploads/pdf").exists())) {
			new File("./uploads/pdf").mkdirs();
		}

		File dest = new File("./uploads/pdf/" + pdf.getName());
		// Teste para sobrepor uma imagem com o mesmo nome
		if (dest.exists()) {
			flash.error("PDF ou Nome já existente");
			dest.delete();
			// Passando a imagem para a pasta do play e salvando seu nome no banco de dados
		}
		pdf.renameTo(dest);
		v.pdf = pdf.getName();
		try {
			v.nome = session.get("usuario.nome");
			v.save();
		} catch (Exception e) {
			flash.error("Não foi possível salvar erro: " + e.getStackTrace());
		}
		forum();
	}

	public static void editar(Long id) {
		Voz v = Voz.findById(id);
		renderTemplate("/VozDigital/forum", v);
	}

	public static void deletar(Long id) {
		Voz v = Voz.findById(id);
		File dest = new File("./uploads/pdf/" + v.pdf);
		dest.delete();
		v.delete();
		flash.success("Deletado com sucesso");
		forum();
	}

	public static void publicacao(Long id, StatusPadrao n) {
		Voz v = Voz.findById(id);
		if (n == StatusPadrao.INATIVO) {
			File dest = new File("./uploads/pdf/" + v.pdf);
			dest.delete();
			v.delete();
			
		} else {
			v.status = n;
			v.save();
		}
		forum();
	}
}
