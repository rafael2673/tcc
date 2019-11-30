package jobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Curso;
import models.StatusPadrao;
import play.jobs.*;

@On("20 * * ? * * *")
public class CursoDates extends Job {
	@Override
	public void doJob() throws Exception {
		Date d = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String date = fmt.format(d);
		List<Curso> c = Curso.findAll();
		if (c != null) {
			for (Curso curso : c) {
				if (date.equals(curso.dataFinal)) {
					if (curso.status == StatusPadrao.ATIVO) {
						curso.status = StatusPadrao.INATIVO;
						curso.save();
						System.out.println(curso.status);
					}
				}

			}
		}
	}

}
