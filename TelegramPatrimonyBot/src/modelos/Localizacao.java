package modelos;

/**
 * 
 * @author João Vitor Dias Xavier
 *
 */
public class Localizacao {
	private String nome;
	private String descricao;
	
	public Localizacao(String nome, String descricao){
		this.nome = nome;
		this.descricao = descricao;
	}
	public Localizacao(){
		
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}
