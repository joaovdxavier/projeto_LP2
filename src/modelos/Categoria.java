package modelos;
/**
 * 
 * @author Joao Vitor Dias Xavier
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
	public Categoria(){

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
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String toString() {
		return "Nome: "+this.getNome()+"\nDescrição: "+ this.descricao;
	}
}

