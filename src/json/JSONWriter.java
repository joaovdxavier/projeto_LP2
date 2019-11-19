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
	    JSONArray j = JSONRead.lerArray("bens");
	     
	    FileWriter writeFile = null;
	     
	    //Armazena dados em um Objeto JSON
	    jsonObject.put("codigo", b.getCodigo());
	    jsonObject.put("nome", b.getNome());
	    jsonObject.put("descricao", b.getDescricao());
	    jsonObject.put("categoria", b.getCategoria().getCodigo());
	    jsonObject.put("localizacao", b.getLocalizacao().getNome());
	    
	    j.put(jsonObject);
	    
	    JSONObject save = new JSONObject();
	    save.put("bens", j);
	    
	    //array.put(jsonObject);
	    try{
	        writeFile = new FileWriter("bens.json");
	        //Escreve no arquivo conteudo do Objeto JSON
	        writeFile.write(save.toString());
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
	    JSONArray j = JSONRead.lerArray("categorias");
	    FileWriter writeFile = null;
	     
	    //Armazena dados em um Objeto JSON
	    jsonObject.put("codigo", c.getCodigo());
	    jsonObject.put("nome", c.getNome());
	    jsonObject.put("descricao", c.getDescricao());
	    
	    j.put(jsonObject);
	    
	    JSONObject save = new JSONObject();
	    save.put("categorias", j);
	     
	    try{
	        writeFile = new FileWriter("categorias.json");
	        //Escreve no arquivo conteudo do Objeto JSON
	        writeFile.write(save.toString());
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
	    JSONArray j = JSONRead.lerArray("localizacoes");
	    FileWriter writeFile = null;
	    System.out.println(j.toString());
	     
	    //Armazena dados em um Objeto JSON
	    jsonObject.put("nome", l.getNome());
	    jsonObject.put("descricao", l.getDescricao());
	    
	    j.put(jsonObject);
	    JSONObject save = new JSONObject();
	    save.put("localizacoes", j);
	     
	    try{
	        writeFile = new FileWriter("localizacoes.json");
	        //Escreve no arquivo conteudo do Objeto JSON
	        writeFile.write(save.toString());
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
