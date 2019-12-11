package controllers;

import java.io.File;
import java.util.List;

import models.Login;
import models.StatusPadrao;
import models.Voz;
import models.VozLogin;
import play.modules.paginate.ValuePaginator;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class VozDigital extends Controller {
	public static void forum() {
		List<VozLogin> v = VozLogin.find("status != 'ATIVO'").fetch();
		List<VozLogin> v2 = VozLogin.find("status != 'INATIVO'").fetch();
		List<VozLogin> v3 = VozLogin.find("idLogin like ?1", session.get("usuario.id")).fetch();
		ValuePaginator listaPaginada = new ValuePaginator(v);
		ValuePaginator listaPaginada2 = new ValuePaginator(v2);
		listaPaginada.setPageSize(20);
		listaPaginada2.setPageSize(20);
		render(listaPaginada, listaPaginada2, v3);
	}

	public static void salvar(Voz v, File pdf) {
		Login l = Login.findById(Long.parseLong(session.get("usuario.id")));
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
			v.save();
			VozLogin vl = new VozLogin();
			vl.login = l;
			vl.submissao = v;
			vl.save();
			
		} catch (Exception e) {
			flash.error("Não foi possível salvar, erro: " + e.getStackTrace());
		}
		forum();
	}

	public static void editar(Long id) {
		Voz v = Voz.findById(id);
		renderTemplate("/VozDigital/forum", v);
	}

	public static void deletar(Long id) {
		VozLogin vl = VozLogin.findById(id);
		Voz v = Voz.findById(vl.submissao.id);
		File dest = new File("./uploads/pdf/" + v.pdf);
		dest.delete();
		vl.delete();
		v.delete();
		flash.success("Deletado com sucesso");
		forum();
	}

	public static void publicacao(Long id, StatusPadrao n) {
		VozLogin vl = VozLogin.findById(id);
		Voz v = Voz.findById(vl.submissao.id);
		if (n == StatusPadrao.INATIVO) {
			File dest = new File("./uploads/pdf/" + v.pdf);
			dest.delete();
			vl.delete();
			v.delete();
		} else {
			vl.status = n;
			vl.save();
		}
		forum();
	}
}
