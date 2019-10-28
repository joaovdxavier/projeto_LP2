package modelos;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
	Map<Integer, Bem> bens;
	Map<Integer, Categoria> categorias;
	Map<String, Localizacao> localizacoes;
	
	public Usuario() {
		bens = new HashMap<>();
		categorias = new HashMap<>();
		localizacoes = new HashMap<>();
	}
	/**
	 * Esse m√©todo recebe os dados de uma categoria e retorna uma nova categoria. 
	 * Caso ela j√° exista, retorna null
	 * @param codigo, nome, descricao
	 * @return categoria cadastrada
	 */
	Categoria cadastrarCategoria(int codigo, String nome, String descricao) {
		if(this.categorias.get(codigo) == null) {
			Categoria cat = new Categoria(codigo, nome, descricao);
			this.categorias.put(codigo, cat);
			return cat;
		}
		return null;
	}
	
	/**
	 * Esse m√©todo recebe os dados de uma localizacao e retorna uma nova localizacao. 
	 * Caso ela j√° exista, visto pelo nome, retorna null
	 * @param nome, descricao
	 * @return localizacao
	 */
	Localizacao cadastrarLocalizacao(String nome, String descricao) {
		if(this.localizacoes.get(nome) == null) {
			Localizacao loc = new Localizacao(nome, descricao);
			this.localizacoes.put(nome, loc);
			return loc;
		}
		return null;
	}
	
	/**
	 * Esse m√©todo recebe os dados de um bem e retorna uma novo bem. 
	 * Caso ele j√° exista, retorna null
	 * @param codigo, nome, descricao, localizacao, categoria
	 * @return localizacao
	 */
	Bem cadastrarBem(int codigo, String nome, String descricao, Localizacao loc, Categoria cat) {
		if(this.bens.get(codigo) == null) {
			Bem bem = new Bem(codigo, nome, descricao, loc, cat);
			this.bens.put(codigo, bem);
			return bem;
		}
		return null;
	}
	
	/**
	 * Esse Metodo percorre o map e lista todas as localizaÁıes existentes 
	 */
	
	Localizacao listarLocalizacao() {
		for(int i = 1; i <= this.localizacoes.size(); i++){
			  System.out.println(i + " - " + this.localizacao.get(i));
			}
	}
	
	/**
	 * Esse metodo percorre o map e lista todas as Categorias existentes
	 */
	Categoria listarCategorias() {
		for(int i = 1; i <= this.categorias.size(); i++){
			  System.out.println(i + " - " + this.caterogiras.get(i));
			}	
	}
	
	Bem listarBem(String localizacao){
			for(int i = 1; i <= this.bens.size(); i++){
				if(this.bens.get(loc.nome) == localizacao) {
					System.out.println(i + " - " + this.bens.get(i));
				}	
		}
			else {
				System.out.println("N„o existem bens nessa localizaÁ„o!");
			}
	
	
}
