package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Voz extends Model {
	public String titulo;
	public String descricao;
	public String pdf;
}
