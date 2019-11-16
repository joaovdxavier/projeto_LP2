package json;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import modelos.Bem;
import modelos.Categoria;
import modelos.Localizacao;

public abstract class JSONWriter {
	
	public static int gravarBem(Bem b) {
		//Cria um Objeto JSON
		//JSONArray array = new JSONArray();
	    JSONObject jsonObject = new JSONObject();
	     
	    FileWriter writeFile = null;
	     
	    //Armazena dados em um Objeto JSON
	    jsonObject.put("codigo", b.getCodigo());
	    jsonObject.put("nome", b.getNome());
	    jsonObject.put("descricao", b.getDescricao());
	    jsonObject.put("categoria", b.getCategoria().getCodigo());
	    jsonObject.put("localizacao", b.getLocalizacao().getNome());
	    
	    //array.put(jsonObject);
	    try{
	        writeFile = new FileWriter("bens.json", true);
	        //Escreve no arquivo conteudo do Objeto JSON
	        writeFile.append(jsonObject.toString());
	        writeFile.close();
	    }
	    catch(IOException e){
	        e.printStackTrace();
	        return 0;
	    }
	     
	    //Imprimne na Tela o Objeto JSON para vizualização
	    System.out.println(jsonObject);
        return 1;
	}
	
	public static int gravarCat(Categoria c) {
		//JSONArray array = new JSONArray();
	    JSONObject jsonObject = new JSONObject();
	     
	    FileWriter writeFile = null;
	     
	    //Armazena dados em um Objeto JSON
	    jsonObject.put("codigo", c.getCodigo());
	    jsonObject.put("nome", c.getNome());
	    jsonObject.put("descricao", c.getDescricao());
	    
	    //array.put(jsonObject);
	     
	    try{
	        writeFile = new FileWriter("categorias.json", true);
	        //Escreve no arquivo conteudo do Objeto JSON
	        writeFile.append(jsonObject.toString());
	        writeFile.close();
	    }
	    catch(IOException e){
	        e.printStackTrace();
	        return 0;
	    }
	     
	    //Imprimne na Tela o Objeto JSON para vizualização
	    System.out.println(jsonObject);
        return 1;
	}
	
	public static int gravarLoc(Localizacao l) {
		//JSONArray array = new JSONArray();
	    JSONObject jsonObject = new JSONObject();
	     
	    FileWriter writeFile = null;
	     
	    //Armazena dados em um Objeto JSON
	    jsonObject.put("nome", l.getNome());
	    jsonObject.put("descricao", l.getDescricao());
	    
	    //array.put(jsonObject);
	     
	    try{
	        writeFile = new FileWriter("localizacoes.json", true);
	        //Escreve no arquivo conteudo do Objeto JSON
	        writeFile.append(jsonObject.toString());
	        writeFile.close();
	    }
	    catch(IOException e){
	        e.printStackTrace();
	        return 0;
	    }
	     
	    //Imprimne na Tela o Objeto JSON para vizualização
	    System.out.println(jsonObject);
        return 1;
	}
	
}
