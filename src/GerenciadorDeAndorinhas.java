import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeAndorinhas {
	public static List<Andorinha> getAndorinhas() throws IOException {
		Path file = Paths.get("src/andorinhas.txt");
		
		byte[] fileData = Files.readAllBytes(file);
		
		return GerenciadorDeAndorinhas.gerarAndorinhas(new String(fileData));
	}
	
	public static void listarAndorinhas(ArrayList<Andorinha> andorinhas) {
		String resultado = "[";
		
		for (int i = 0; i < andorinhas.size(); i++) {
			resultado += andorinhas.get(i).nome;
			if (i < andorinhas.size() - 1) 
				resultado += ", ";
		}
		
		resultado += "]";
		
		System.out.println(resultado);
	}
	
	public static List<Andorinha> andorinhasPorLocalDeOrigem(LocalDeOrigem origem, ArrayList<Andorinha> andorinhas) {
		List<Andorinha> resultado = new ArrayList<Andorinha>();
		
		for (Andorinha andorinha: andorinhas) {
			if (andorinha.localDeOrigem == origem) 
				resultado.add(andorinha);
		}
		
		return resultado;
	}
	
	private static List<Andorinha> gerarAndorinhas(String texto) {
		
		ArrayList<Andorinha> resultado = new ArrayList<Andorinha>();
		
		for (String linha: texto.split("\n")) {
			String[] atributos = linha.split(" ");
			resultado.add(new Andorinha(atributos[0], LocalDeOrigem.valueOf(atributos[1])));
		}
		
		return resultado;
	}
}
