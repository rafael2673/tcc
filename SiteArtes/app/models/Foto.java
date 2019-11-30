package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;
/**
 * 
 * classe representativa da tabela no banco salvando o nome das imagens.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 *
 * 
 * 
 **/

@Entity
public class Foto extends Model {
	@Column(unique = true)
	public String nomeFoto;	
	public String titulo;
	public String descricao;
}
