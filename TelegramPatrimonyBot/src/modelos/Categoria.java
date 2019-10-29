package modelos;
/**
 * 
 * @author João Vitor Dias Xavier
 *
 */

public class Categoria {
	private int codigo;
	private String nome;
	private String descricao;
	
	public Categoria(int codigo, String nome, String descricao){
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
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
	
	public int getCodigo() {
		return this.codigo;
	}
	
}

