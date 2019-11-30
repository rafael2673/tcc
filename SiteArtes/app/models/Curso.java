package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity

/** @author Rafael Ribeiro, Brenda Vitória
 * 
 * 
 * Funcionalidade: Essa classe tem como função criar uma tabela no banco de 
 * dados h2 com o nome Curso e persistir dados.
 * 
 * */
public class Curso extends Model {
	
	//Colunas que serão adicionadas no bd
	public String nome;
	public String descricao;
	public String dataFinal;
	@Enumerated(EnumType.STRING)
	public StatusPadrao status;
	
	public Curso() {
		status = StatusPadrao.ATIVO;
	}
	
	
	//Variáveis booleanas que não serão persistidas
	@Transient
	public boolean inscrito = false;
	@Transient
	public boolean emEspera = false;
	@Transient
	public boolean naoInscrito = false;
	
}
