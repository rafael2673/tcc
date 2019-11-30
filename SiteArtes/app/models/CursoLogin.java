package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
/**
 * 
 * classe representativa da tabela no banco fazendo relacionamentos
 * do tipo muitos para um.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 *
 * 
 * 
 **/
@Entity
public class CursoLogin extends Model {
	
	@ManyToOne
	@JoinColumn(name = "idCurso")
	public Curso curso;
	
	@ManyToOne
	@JoinColumn (name = "idLogin")
	public Login login ;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	public CursoLogin() {
		status = status.Em_Espera;
	}
}
