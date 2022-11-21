import java.util.*;

public class No implements Comparable<No>  {
    private int info;
    private No direita;
    private No esquerda;
    private int frequencia;

    public No(int i, No d, No e){
        info = i;
        direita = d;
        esquerda = e;
    }
    public No(int i){
        this(i, null, null);
    }

    public No(Integer key, Integer value) {
        this(key, null, null);
    }

    public No(No esquerda, No direita) {
        this(0, direita, esquerda);
    }

    public void setInfo(int i){
        info = i;
    }
    public void setDireita(No d){
        direita = d;
    }
    public void setEsquerda(No e){
        esquerda = e;
    }
    public No getDireita(){return direita;}
    public No getEsquerda(){return esquerda;}
    public int getInfo(){return info;}

    public String toString(){
        return "" + info;
    }

    public void gerarCodigos(HashMap<Integer, String> codigos, String codigo) {
        if (esquerda == null && direita == null) {
            codigos.put(info, codigo);
        } else {
            if (esquerda != null) {
                esquerda.gerarCodigos(codigos, codigo + "0");
            }
            if (direita != null) {
                direita.gerarCodigos(codigos, codigo + "1");
            }
        }
    }


    public int compareTo(No outro){
        return this.info - outro.info;
    }


}
