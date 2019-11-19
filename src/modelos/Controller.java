package modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import json.JSONRead;
import json.JSONWriter;

public class Controller {
	Map<Integer, Bem> bens;
	Map<Integer, Categoria> categorias;
	Map<String, Localizacao> localizacoes;
	public static final int NAME_SEARCH = 1;
	public static final int DESC_SEARCH = 2;
	
	public Controller() {
		bens = JSONRead.lerJsonBens() != null ? JSONRead.lerJsonBens() : new HashMap<>();
		categorias = JSONRead.lerJsonCats() != null ? JSONRead.lerJsonCats() : new HashMap<>();
		localizacoes = JSONRead.lerJsonLocs() != null ? JSONRead.lerJsonLocs() : new HashMap<>();
	}
	/**
	 * Esse mÃ©todo recebe os dados de uma categoria e retorna uma nova categoria. 
	 * Caso ela jÃ¡ exista, retorna null
	 * @param codigo, nome, descricao
	 * @return categoria cadastrada
	 */
	public Categoria cadastrarCategoria(int codigo, String nome, String descricao) {
		if(this.categorias.get(codigo) == null) {
			Categoria cat = new Categoria(codigo, nome, descricao);
			this.categorias.put(codigo, cat);
			JSONWriter.gravarCat(cat);
			return cat;
		}
		return null;
	}
	
	/**
	 * Esse mÃ©todo recebe os dados de uma localizacao e retorna uma nova localizacao. 
	 * Caso ela jÃ¡ exista, visto pelo nome, retorna null
	 * @param nome, descricao
	 * @return localizacao
	 */
	public Localizacao cadastrarLocalizacao(String nome, String descricao) {
		if(this.localizacoes.get(nome) == null) {
			Localizacao loc = new Localizacao(nome, descricao);
			this.localizacoes.put(nome, loc);
			JSONWriter.gravarLoc(loc);
			return loc;
		}
		return null;
	}
	
	/**
	 * Esse mÃ©todo recebe os dados de um bem e retorna uma novo bem. 
	 * Caso ele jÃ¡ exista, retorna null
	 * @param codigo, nome, descricao, localizacao, categoria
	 * @return localizacao
	 */
	public Bem cadastrarBem(int codigo, String nome, String descricao, String nomeLoc, int codigoCat) {
		Localizacao loc = this.localizacoes.get(nomeLoc);
		Categoria cat = this.categorias.get(codigoCat);
		if(cat != null && loc != null) {
			if(this.bens.get(codigo) == null) {
				Bem bem = new Bem(codigo, nome, descricao, loc, cat);
				this.bens.put(codigo, bem);
				JSONWriter.gravarBem(bem);
				return bem;
			}
		}else {
			// exception
		}
		return null;
	}
	
	/**
	 * @return lista com as localizações
	 */
	public ArrayList<Localizacao> listarLocalizacao() {
		return new ArrayList<Localizacao>(localizacoes.values());
	}
	
	/** 
	 * @return lista com as cateogrias
	 */
	public ArrayList<Categoria> listarCategorias() {
		return new ArrayList<Categoria>(categorias.values());	 	
	}
	
	/**
	 * o metodo percorre a arraylist de bens e verifica se a localização desse bem é igual a que o usuario
	 * entrou, se for ela adiciona esse bem em outro arraylist e retorna o array com todas as localizações
	 * @param nome da localização
	 * @return bens nessa localização
	 */
	public ArrayList<Bem> listarBem(String nome){
		Localizacao localizacao = this.localizacoes.get(nome);
		if(localizacao == null) {
			return null;
		}
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
	 * busca um bem por codigo e retorna se existe, se não retorna null
	 * @param codigo do bem
	 * @return bem, se existir. null caso contrário
	 */
	public Bem buscarBem(int codigo) {
		Bem b = this.bens.get(codigo);
		if(b == null) {
			return null;
		}
		return b; //precisa retornar a localização
	}
	
	/**
	 * o metodo percorre um array com os bens e verifica se tem algum bem com o mesmo nome que o usuario procura
	 * @param busca desejada e o 
	 * @param tipo 1 para nome, 2 para descrição
	 * @return lista com os bens que contenham tal nome ou descrição
	 */
	public ArrayList<Bem> buscarBem(String busca, int tipo) {
		ArrayList<Bem> buscarBem = new ArrayList<>(bens.values());
		ArrayList<Bem> bensPesquisados = new ArrayList<>();
		for(int i = 0; i < buscarBem.size(); i++) {
			Bem a = buscarBem.get(i);
			if(tipo == Controller.NAME_SEARCH) {
				if(a.getNome().contains(busca)){
					bensPesquisados.add(a);
				}
			}
			else if(tipo == Controller.DESC_SEARCH){
				if(a.getDescricao().contains(busca)) {
					bensPesquisados.add(a);
				}
			}
		}
		if(!bensPesquisados.isEmpty()) {
			return bensPesquisados;
		}
		return null;
	}
	
	/**
	 * Adiciona um objeto do tipo Bem ao map
	 * @param novoBem a ser adicionado
	 */
	public void putBem(Bem novoBem) {
		this.bens.put(novoBem.getCodigo(), novoBem);
	}
	/**
	 * Busca uma localização no map com o nome especificado
	 * @param nome
	 * @return a localização, se existir. Null caso contrário
	 */
	public Localizacao buscarLocalizacao(String nome) {
		Localizacao loc = this.localizacoes.get(nome);
		if(loc != null) {
			return loc;
		}
		return null;
	}
	
	/**
	 * Busca uma categoria no map com o código especificado
	 * @param codigo da categoria
	 * @return a categoria, se existir. Null caso contrário
	 */
	public Categoria buscarCategoria(int codigo) {
		Categoria cat = this.categorias.get(codigo);
		if(cat != null) {
			return cat;
		}
		return null;
	}
	/**
	 * Esse método move um bem de uma localização para outra, caso ele exista e a localização também
	 * @param codigo
	 * @param loc
	 * @return true se achou o bem e false se não achou
	 */
	public boolean moverBem(int codigo, Localizacao loc){
		Bem b = this.buscarBem(codigo);
		if(b != null) {
			b.setLocalizacao(loc);
			bens.put(codigo, b);
			return true;
		}
		return false;
	}
	
}
