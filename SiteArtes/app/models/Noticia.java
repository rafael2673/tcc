package models;

import javax.persistence.Column;
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
public class Noticia extends Model {
	@Column(columnDefinition = "text")
	public String texto;
	

}
