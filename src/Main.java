
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String arquivo = "C:\\Users\\Cristian\\Documents\\arquivosTeste\\teste.txt";
        HuffmanComBitSet h = new HuffmanComBitSet();

        String texto = h.lerArquivo(arquivo);
        HashMap<Integer, String> codigos = h.gerarCodigos(texto);
        BitSet bitSet = h.codificar(texto, codigos);
        String textoDecodificado = h.decodificar(bitSet, codigos);
        //h.comprimir("C:\\Users\\Cristian\\Documents\\arquivosTeste\\teste.txt");
        //h.descomprimir("C:\\Users\\Cristian\\Documents\\arquivosTeste\\teste.txt.key");
        System.out.println("Tamanho do texto original: " + texto.length() * 16 + " bits");
        System.out.println("Tamanho do texto comprimido: " + bitSet.length() + " bits");
        System.out.println("Taxa de compressao: " + (bitSet.length() * 100.0 / (texto.length() * 16)) + "%");
        System.out.println("BitSet dos Dados: " + bitSet);
        System.out.println("Dados processados: " + codigos);
        System.out.println("Texto deCodificado: " + textoDecodificado);
    }
}
