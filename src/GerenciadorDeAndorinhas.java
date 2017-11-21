import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeAndorinhas {
	public static List<Andorinha> getAndorinhas() throws IOException {
		File file = new File("src/andorinhas.txt");
						
		FileInputStream fis = new FileInputStream(file);
		
		return gerarAndorinhas(fis);
	}
	
	public static void listarAndorinhas(List<Andorinha> list) {
		String resultado = "[";
		StringBuilder builder = new StringBuilder(resultado);
		
		for (int i = 0; i < list.size(); i++) {
				builder.append(list.get(i).nome);
			if (i < list.size() - 1) 
				builder.append(", ");
		}
		
		builder.append("]");
		
		System.out.println(builder.toString());
	}
	
	public static List<Andorinha> andorinhasPorLocalDeOrigem(LocalDeOrigem origem, ArrayList<Andorinha> andorinhas) {
		List<Andorinha> resultado = new ArrayList<Andorinha>();
		
		for (Andorinha andorinha: andorinhas) {
			if (andorinha.localDeOrigem == origem) 
				resultado.add(andorinha);
		}
		
		return resultado;
	}
	
	public static List<String> getNomesDasAndorinhas(List<Andorinha> andorinhas) {
		List<String> nomes = new ArrayList<String>();
		andorinhas.stream().map(n -> nomes.add(n.nome));
		return nomes;
	}
	
	private static List<Andorinha> gerarAndorinhas(FileInputStream fis) throws IOException {
		
		List<Andorinha> resultado = new ArrayList<Andorinha>();
		
//		for (String linha: texto.split("\n")) {
//			String[] atributos = linha.split(" ");
//			resultado.add(new Andorinha(atributos[0], LocalDeOrigem.valueOf(atributos[1])));
//		}
		
		int content;
		String linha = "";
		while ((content = fis.read()) != -1) {
			if ((char) content != '\n') {
				linha += (char) content;
			} else {
				resultado.add(processarLinha(linha));
				linha = "";
			}
		}
		resultado.add(processarLinha(linha));
		return resultado;
	}
	
	private static Andorinha processarLinha(String linha) {
		String[] atributos = linha.split(" ");
		return new Andorinha(atributos[0], LocalDeOrigem.valueOf(atributos[1]));
	}
}
