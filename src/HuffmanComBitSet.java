import java.util.*;
import java.io.*;

public class HuffmanComBitSet {
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
}

