package modelos;

public class Bem {
	int codigo;
	String nome;
	String descricao;
	Localizacao localizacao;
	Categoria categoria;

	public Bem(int codigo, String nome, String descricao, 
			Localizacao localizacao, 
			Categoria categoria){
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.categoria = categoria;
	}
	public Bem(){
		
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
	
	public void setLocalizacao(Localizacao localizacao){
		this.localizacao = localizacao;
	}
	
	public Localizacao getLocalizacao(){
		return this.localizacao;
	}
	
	public void setCategoria(Categoria categoria){
		this.categoria = categoria;
	}
	
	public Categoria getCategoria(){
		return this.categoria;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String toString() {
		return "Nome: "+this.nome+"\nDescricao: "+this.descricao+"\nLocalizacao: "+this.localizacao.getNome()
		+"\nCategoria: "+this.categoria.getNome();
	}
}
