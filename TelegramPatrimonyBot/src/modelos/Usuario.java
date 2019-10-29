package modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
	Map<Integer, Bem> bens;
	Map<Integer, Categoria> categorias;
	Map<String, Localizacao> localizacoes;
	public static final int NAME_SEARCH = 1;
	public static final int DESC_SEARCH = 2;
	
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
	 * @return ArrayList<Localizacao>
	 */
	ArrayList<Localizacao> listarLocalizacao() {
		return new ArrayList<Localizacao>(localizacoes.values());
	}
	
	/** 
	 * @return Arraylist<Categoria>
	 */
	ArrayList<Categoria> listarCategorias() {
		return new ArrayList<Categoria>(categorias.values());	 	
	}
	
	/**
	 * o metodo percorre a arraylist de bens e verifica se a localizaÁ„o desse bem È igual a que o usuario
	 * entrou, se for ela adiciona esse bem em outro arraylist e retorna o array com todas as localizaÁıes
	 * @param localizacao
	 * @return bensLocalizacao
	 */
	ArrayList<Bem> listarBem(Localizacao localizacao){
		ArrayList<Bem> listaBens = new ArrayList<>(bens.values());
		ArrayList<Bem> bensLocalizacao = new ArrayList<>();
		for(int i = 0; i < listaBens.size(); i++){
			Bem b = listaBens.get(i);
			if(b.getLocalizacao().getNome().equals(localizacao.getNome())) {
				bensLocalizacao.add(b);
			}	
		}
		if(bensLocalizacao.isEmpty()) {
			return null;
		}
		return bensLocalizacao;
	}
	/**
	 * busca um bem por codigo e retorna se existe, se n„o retorna null
	 * @param codigo
	 * @return b
	 */
	Bem buscarBem(int codigo) {
		Bem b = this.bens.get(codigo);
		if(b == null) {
			return null;
		}
		return b; //precisa retornar a localizaÁ„o
	}
	
	/**
	 * o metodo percorre um array com os bens e verifica se tem algum bem com o mesmo nome que o usuario procura
	 * @param nome
	 * @return a
	 */
	Bem buscarBem(String busca, int tipo) {
		ArrayList<Bem> buscarBem = new ArrayList<>(bens.values());
		for(int i = 0; i < buscarBem.size(); i++) {
			Bem a = buscarBem.get(i);
			if(tipo == Usuario.NAME_SEARCH) {
				if(a.getNome().equals(busca)){
					return a;
				}
			}
			else if(tipo == Usuario.DESC_SEARCH){
				if(a.getDescricao().equals(busca)) {
					return a;
				}
			}
		}
		return null;
	}
	/**
	 * 
	 * @param codigo
	 * @param loc
	 * @return true se achou o bem e false se n„o achou
	 */
	boolean moverBem(int codigo,Localizacao loc){
		Bem b = this.buscarBem(codigo);
		if(b != null) {
			b.setLocalizacao(loc);
			bens.put(codigo, b);
			return true;
		}
		return false;
	}
	
}
