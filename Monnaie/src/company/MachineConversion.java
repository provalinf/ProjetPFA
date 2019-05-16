package company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MachineConversion {

    Scanner scanner = new Scanner(System.in);

    private Euro euro;
    private Franc franc;
    private  final String end ="end";

    List<PieceQuantite> euroListValeur;
    List<PieceQuantite> francListValeur;

    List<Integer> euroListUser = new ArrayList<>();
    List<Integer> francListUser = new ArrayList<>();

    public MachineConversion() {
       euro = new Euro();
       franc = new Franc();
       euroListValeur = euro.getInitListEuro();
       francListValeur = franc.getInitListFrancs();
    }


    public int conversion() {

        System.out.println(" En Quoi Vous voulez  faire la conversion  ?");
        System.out.println("si  Euro vers  Francs tapez : get-chef");
        System.out.println("si  Francs vers Euro  tapez : get-euros");

        String monnaie = scanner.nextLine();

        while (!(monnaie.equals("get-chef")) && !(monnaie.equals("get-euros"))){
            System.out.println("Message non compris, veuillez preciser la monnaie !!!");
            System.out.println(" En Quoi Vous voulez  faire la conversion  ?");
            System.out.println("si  Euro vers  Francs tapez : get-chef");
            System.out.println("si  Francs vers Euro  tapez : get-euros");
            monnaie = scanner.nextLine();
        }

        // Avant d'entrer dans le switch, on est sur que l'utilisateur a bien precisé la monnaie
        switch (monnaie) {

            case "get-chef":

                // une boucle pour introduire toutes les pieces dans la machine
                euroListUser = new ArrayList<>();
                String saisie ="";

                System.out.println("OK. Ce sont des euros qui vont être introduits par l’utilisateur");
                do {

                    System.out.println("veuillez introduire une piece ou un billet :");

                    saisie = scanner.nextLine() ;

                    try{
                       int piece = Integer.parseInt(saisie);
                        if(euro.verification(piece)){

                            // appele de la methode affichage dans l'objet pieceQuantite
                            euro.affichage(piece,euroListUser);
                            euroListUser.add(piece) ;

                        }else{
                            System.out.println("Mauvais montant !!!");
                            System.out.println("Transaction invalidée !!!");
                            afficherListe("euro", euroListUser);
                            break;
                        }
                    }catch (NumberFormatException e){
                        if (saisie.equals("end")){
                            System.out.println("Conversion en cours ...");

                        }else if (saisie.equals("cancel")){
                            System.out.println("Vous avez demandé l'annulation de la conversion");
                            System.out.println("Transaction invalidée !!!");
                            afficherListe("euro", euroListUser);
                            break;
                        }else{
                            System.out.println("Symbole inconnu !!!");
                            System.out.println("Transaction invalidée !!!");
                            afficherListe("euro", euroListUser);
                            break;
                        }
                    }



                }while (!end.equals(saisie)) ;

                // conversion du montant introduit vers le franc suisse

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

    // affiche une liste dont la tete est le type de monnaie suivi des pieces introduites par l'utilisateur
    public void afficherListe(String typeMonnaie, List<Integer> list){
        System.out.print("(" + typeMonnaie + "\t");
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + "\t");
        }
        System.out.println(")");
    }


}
