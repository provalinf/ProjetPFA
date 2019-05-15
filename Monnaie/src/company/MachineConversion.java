package company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MachineConversion {

    Scanner scanner = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);

    private Euro euro;
    private Franc franc;
    private  final String end ="end";

    List<Integer> euroListValeur = new ArrayList<>() ;
    List<Integer> francListValeur = new ArrayList<>() ;

    public MachineConversion(Euro euro, Franc franc) {
        this.euro = euro;
        this.franc = franc;
    }


    public int Conversion() {

        System.out.println(" En Quoi Vous voulez  faire la conversion  ?");
        System.out.println("si  Euro vers  Francs tapez : get-chef");
        System.out.println("si  Francs vers Euro  tapez : get-euros");

        String sens = scanner.nextLine();
        switch (sens) {
            case "get-chef":

                // une boucle pour introduire toute les pieces dans la machine .
                String fin ="";
                do {
                    System.out.println("OK. Ce sont des euros qui vont être introduits par l’utilisateur");
                    System.out.println("veulliez introduire une piece ou un billtet svp :");
                    int piece=scanner.nextInt() ;
                    if(euro.verifcation(piece)){


                         // appele de la methode affichage dans l 'objet pieceQuantite

                        euro.affichage(piece,euroListValeur);

                        euroListValeur.add(piece) ;

                        System.out.println("  si vous avez fini tapez << end >> :");
                         fin =scanner2.nextLine() ;


                    }else{
                        System.out.println(" le cas ou il introduit une piece non existante  .");
                    }

                }while (!end.equals(fin)) ;



                break;
            case "get-euros":
                System.out.println("bonjours TABBOU ");
                break;
            default:
                System.out.println("erreur dans la saisie ");
                break;


        }

        return 0;

    }
}
