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
	 * Esse método recebe os dados de uma categoria e retorna uma nova categoria. 
	 * Caso ela já exista, retorna null
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
	 * Esse método recebe os dados de uma localizacao e retorna uma nova localizacao. 
	 * Caso ela já exista, visto pelo nome, retorna null
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
	 * Esse método recebe os dados de um bem e retorna uma novo bem. 
	 * Caso ele já exista, retorna null
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
	
	
}
