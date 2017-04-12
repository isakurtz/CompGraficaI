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
       VideoDTO video = ManipulacaoArquivos.readFile("C:\\Users\\PC\\Desktop\\Paths_D1.txt");
       DetecPassBy detec = new DetecPassBy(video);
       detec.analize();

     // ManipulacaoArquivos.teste();
        
    }

//    public ProjetoCG() {
//        this.m = new ManipulacaoArquivos();
//    }
    
}
