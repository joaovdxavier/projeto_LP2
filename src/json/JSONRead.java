package json;

import org.json.JSONArray;
import org.json.JSONObject;

import modelos.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public abstract class JSONRead {
	public String lerString(String tipo) {
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
	
	public JSONArray lerArrayBem() {
		JSONObject j = new JSONObject(lerString("bens"));
		JSONArray jArray = j.getJSONArray();
	}
	public JSONArray lerArrayCat() {
			
		}
	public JSONArray lerArrayLoc() {
		
	}

	public static ArrayList<Bem> lerJsonBens() {
		JSONObject jsonObject;
		// Cria o parse de tratamento
		File arquivo = new File("bens.json");
		@SuppressWarnings("deprecation")
		String jsonPego = "";
		try {
			jsonPego = FileUtils.readFileToString(arquivo);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		// Variaveis que irao armazenar os dados do arquivo JSON
		String nome;
		String sobrenome;
		String estado;
		String pais;

		// Salva no objeto JSONObject o que o parse tratou do arquivo
		jsonObject = new JSONObject(jsonPego);
		// JSONArray j = jsonObject.getJSONArray(key)

		// Salva nas variaveis os dados retirados do arquivo
		nome = (String) jsonObject.get("nome");
		sobrenome = (String) jsonObject.get("sobrenome");
		estado = (String) jsonObject.get("estado");
		pais = (String) jsonObject.get("pais");

		System.out.printf("Nome: %s\nSobrenome: %s\nEstado: %s\nPais: %s\n", nome, sobrenome, estado, pais);
		return 1;
	}

}
