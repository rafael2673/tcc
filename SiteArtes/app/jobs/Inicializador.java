package jobs;

import models.Login;
import models.Nivel;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
/**
 * 
 * Funcionalidade: Essa classe tem como função criar valores
 * genéricos no banco de dados a cada vez que a aplicação é iniciada
 * pela primeira vez.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 *
 * 
 * 
 **/
@OnApplicationStart
public class Inicializador extends Job {
	@Override
	public void doJob() throws Exception {
		if (Login.count() == 0) {
			Login l = new Login();
			l.email = "rafael@gmail.com";
			l.nome = "Rafael";
			l.sobrenome = "Franco";
			l.senha = "12345";
			l.cidade = "João Câmara";
			l.numero = "(84)99412-2586";
			l.nivel = Nivel.ADMIN;
			l.save();
			Login comum = new Login();
			comum.email = "a@gmail.com";
			comum.nome = "a";
			comum.sobrenome = "b";
			comum.senha = "aaaaa";
			comum.cidade = "João Câmara";
			comum.numero = "(XX)XXXXX-XXXX";
			comum.nivel = Nivel.COMUM;
			comum.save();
		}

	}
}
