package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Voz extends Model {
	public String nome;
	public String titulo;
	public String descricao;
	public String pdf;
	@Enumerated(EnumType.STRING)
	public StatusPadrao status;
	
	public Voz() {
		status = StatusPadrao.INATIVO;
	}
}
