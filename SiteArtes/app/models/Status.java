package models;

/**
 * 
 * Enumerador de status que são utilizados nos
 * cursos disponiveis.
 * 
 * @author Rafael Ribeiro, Brenda Vitória
 * 
 *
 * 
 * 
 **/
public enum Status {
	Em_Espera("Em espera"),
	Inscrito("Inscrito"), Não_Inscrito("Não inscrito");
	
	private String descricao;
	
	
	Status(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
	
	public Status[] getStatus() {
		return Status.values();
	}
}
