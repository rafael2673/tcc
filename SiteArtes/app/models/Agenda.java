package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
/**
 * 
 * classe representativa da tabela no banco.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 *
 * 
 * 
 **/
@Entity
public class Agenda extends Model {
	public String nomeEvento;
	public String horaInicio;
	public String horaFim;
	public String dia;
}
