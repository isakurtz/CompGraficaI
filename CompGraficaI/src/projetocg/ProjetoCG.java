package projetocg;

import java.util.ArrayList;

/**
 *
 * @author Marina
 */
  
public class ProjetoCG {

    private ManipulacaoArquivos m;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
        //	ArrayList<ArrayList<String>> dados = new ArrayList<ArrayList<String>>();
        //	dados = ManipulacaoArquivos.leArquivo("C:\\Paths_D.csv");
        //ArrayList<ArrayList<String>> leArq = ManipulacaoArquivos.leArquivo("C:\\Users\\Marina\\Desktop\\teste.txt");
        //ManipulacaoArquivos.readFile("C:\\Users\\PC\\Desktop\\Paths_D.txt");
       //ExemploImagem ei = new ExemploImagem();
       VideoDTO video = ManipulacaoArquivos.readFile("C:\\Users\\isadora_goncalves\\Downloads\\Paths_D.txt");
       DetecPassBy detec = new DetecPassBy(video);
       DetecGroup detecg = new DetecGroup(video);
       detec.analize();
       detecg.analize();

     // ManipulacaoArquivos.teste();
        
    }

//    public ProjetoCG() {
//        this.m = new ManipulacaoArquivos();
//    }
    
}
