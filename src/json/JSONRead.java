package json;

import org.json.JSONArray;
import org.json.JSONObject;

import modelos.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

public abstract class JSONRead {
	public static String lerString(String tipo) {
		JSONObject jsonObject;
		// Cria o parse de tratamento
		File arquivo = new File(tipo+".json");
		@SuppressWarnings("deprecation")
		String jsonPego = "";
		try {
			jsonPego = FileUtils.readFileToString(arquivo);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		return jsonPego;
	}
	
	public static JSONArray lerArray(String tipo) {
		//System.out.println(lerString(tipo));
		JSONObject j = new JSONObject(lerString(tipo));
		JSONArray jArray = j.getJSONArray(tipo);
		//if(tipo.equals("localizacoes"))
			//System.out.println(jArray.get(0));
		return jArray;
	}

	public static Map<Integer, Bem> lerJsonBens() {
		Map<Integer, Bem> bens = new HashMap<>();
		Map<Integer, Categoria> cats = lerJsonCats();
		Map<String, Localizacao> locs = lerJsonLocs();

		// Salva no objeto JSONObject o que o parse tratou do arquivo
		JSONArray j = lerArray("bens");
		if(j.length() != 0) {
			for(int i = 0; i < j.length(); i++) {
				Bem b = new Bem();
				JSONObject j1 = j.getJSONObject(i);
				b.setCodigo(j1.getInt("codigo"));
				b.setNome(j1.getString("nome"));
				b.setDescricao(j1.getString("descricao"));
				b.setCategoria(cats.get(j1.getInt("categoria")));
				b.setLocalizacao(locs.get(j1.getString("localizacao")));
				bens.put(b.getCodigo(), b);
			}
			return bens;
		}else {
			return null;
		}
	}
	
	public static Map<Integer, Categoria> lerJsonCats() {
		Map<Integer, Categoria> categorias = new HashMap<>();
		// Variaveis que irao armazenar os dados do arquivo JSON
		// Salva no objeto JSONObject o que o parse tratou do arquivo
		JSONArray j = lerArray("categorias");
		if(j.length() != 0) {
			for(int i = 0; i < j.length(); i++) {
				Categoria c = new Categoria();
				JSONObject j1 = j.getJSONObject(i);
				c.setCodigo(j1.getInt("codigo"));
				c.setNome(j1.getString("nome"));
				c.setDescricao(j1.getString("descricao"));
				categorias.put(c.getCodigo(), c);
			}
			return categorias;
		}else {
			return null;
		}
		// Salva nas variaveis os dados retirados do arquivo
	}
	
	public static Map<String, Localizacao> lerJsonLocs() {
		Map<String, Localizacao> locs = new HashMap<>();
		// Variaveis que irao armazenar os dados do arquivo JSON
		// Salva no objeto JSONObject o que o parse tratou do arquivo
		JSONArray j = lerArray("localizacoes");
		
		if(j.length() != 0) {
			for(int i = 0; i < j.length(); i++) {
				Localizacao l = new Localizacao();
				JSONObject j1 = j.getJSONObject(i);
				l.setNome(j1.getString("nome"));
				l.setDescricao(j1.getString("descricao"));
				locs.put(l.getNome(), l);
			}
			return locs;
		}else {
			return null;
		}
		
		// Salva nas variaveis os dados retirados do arquivo
		
	}

}
