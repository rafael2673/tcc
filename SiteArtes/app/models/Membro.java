package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Membro extends Model {
	public String nome;
	public String descricao;
	public String nomeFoto;
}
