import java.io.*;
import java.util.*;


public class HuffmanComBitSetEArvore {
    public String lerArquivo(String arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String texto = "";
        String linha = br.readLine();
        while (linha != null) {
            texto += linha + "\n";
            linha = br.readLine();
        }
        br.close();
        return texto;
    }

    public HashMap<Integer, String> gerarCodigos(String texto) {
        HashMap<Integer, Integer> frequencias = new HashMap<Integer, Integer>();
        for (int i = 0; i < texto.length(); i++) {
            int c = texto.charAt(i);
            if (frequencias.containsKey(c)) {
                frequencias.put(c, frequencias.get(c) + 1);
            } else {
                frequencias.put(c, 1);
            }
        }
        PriorityQueue<Node> fila = new PriorityQueue<Node>();
        for (Integer key : frequencias.keySet()) {
            fila.add(new Node(key, frequencias.get(key)));
        }
        while (fila.size() > 1) {
            Node esquerda = fila.poll();
            Node direita = fila.poll();
            Node pai = new Node(esquerda, direita);
            fila.add(pai);
        }
        Node raiz = fila.poll();
        HashMap<Integer, String> codigos = new HashMap<Integer, String>();
        raiz.gerarCodigos(codigos, "");
        return codigos;
    }

    public BitSet codificar(String texto, HashMap<Integer, String> codigos) {
        BitSet bitSet = new BitSet();
        int posicao = 0;
        for (int i = 0; i < texto.length(); i++) {
            int c = texto.charAt(i);
            String codigo = codigos.get(c);
            for (int j = 0; j < codigo.length(); j++) {
                if (codigo.charAt(j) == '1') {
                    bitSet.set(posicao);
                }
                posicao++;
            }
        }
        return bitSet;
    }

    public String decodificar(BitSet bitSet, HashMap<Integer, String> codigos) {
        HashMap<String, Integer> codigosInvertidos = new HashMap<>();
        for (Integer key : codigos.keySet()) {
            codigosInvertidos.put(codigos.get(key), key);
        }
        String texto = "";
        String codigo = "";
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                codigo += "1";
            } else {
                codigo += "0";
            }
            if (codigosInvertidos.containsKey(codigo)) {
                texto += (char) codigosInvertidos.get(codigo).intValue();
                codigo = "";
            }
        }
        return texto;
    }


    public void comprimirArquivo(String arquivo) throws IOException {
        String texto = lerArquivo(arquivo);
        HashMap<Integer, String> codigos = gerarCodigos(texto);
        BitSet bitSet = codificar(texto, codigos);
        System.out.println("Tamanho do texto original: " + texto.length() * 16 + " bits");
        System.out.println("Tamanho do texto comprimido: " + bitSet.length() + " bits");
        System.out.println("Taxa de compressão: " + (bitSet.length() * 100.0 / (texto.length() * 16)) + "%");
        String textoDecodificado = decodificar(bitSet, codigos);
        System.out.println("Texto decodificado: " + textoDecodificado);
        String nomeArquivo = arquivo.substring(0, arquivo.length() - 4);
        String extensao = arquivo.substring(arquivo.length() - 4, arquivo.length());
        String novoArquivo = nomeArquivo + "_comprimido" + extensao;
        BufferedWriter bw = new BufferedWriter(new FileWriter(novoArquivo));
        for (Integer key : codigos.keySet()) {
            bw.write(key + " " + codigos.get(key) + "\n");
        }
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                bw.write("1");
            } else {
                bw.write("0");
            }
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        HuffmanComBitSetEArvore huffman = new HuffmanComBitSetEArvore();
        huffman.comprimirArquivo("C:\\Users\\Cristian\\Documents\\arquivosTeste\\teste.txt");
        //huffman.descomprimirArquivo("C:\\Users\\Cristian\\Documents\\arquivosTeste\\teste_comprimido.txt");
    }
}
