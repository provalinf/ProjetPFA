package company;

import company.Devise.Devise;
import company.Devise.Euro;
import company.Devise.Franc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MachineConversion {
    private static Scanner scanner = new Scanner(System.in);

    private Euro euro;
    private Franc franc;

    public MachineConversion(Euro euro , Franc franc) {
        this.euro = euro;
        this.franc = franc;
    }

    private static final String EURO_FRANCS = "get-chef";
    private static final String FRANCS_EURO = "get-euros";
    private static final String END = "end";

    public void conversion() {
        String saisie;
        do {
            System.out.println("En quelle devise faire la conversion ?");
            System.out.println("\t- Si Euro vers Franc-Suisse tapez : " + EURO_FRANCS);
            System.out.println("\t- Si Franc-Suisse vers Euro tapez : " + FRANCS_EURO);
            saisie = scanner.nextLine();
        } while (!(saisie.equals(EURO_FRANCS)) && !(saisie.equals(FRANCS_EURO)));

        // Avant d'entrer dans le switch, on est sur que l'utilisateur a bien précisé la monnaie
        List<Integer> monnaieListUser = new ArrayList<>();
        Devise devise = saisie.equals(EURO_FRANCS) ? new Euro() : new Franc();

        // une boucle pour introduire toutes les pieces dans la machine
        System.out.println("OK. Ce sont des " + devise.getNomDevise() + " qui vont être introduits par l’utilisateur");
        do {
            System.out.println("Veuillez introduire une piece ou un billet :");
            saisie = scanner.nextLine();

            try {
                int piece = Integer.parseInt(saisie);
                if (devise.verification(piece)) {
                    // appel de la methode affichage dans l'objet pieceQuantite
                    devise.affichage(piece, monnaieListUser);
                    monnaieListUser.add(piece);
                } else {
                    System.out.println("Mauvais montant !!!");
                    System.out.println("Transaction invalidée !!!");
                    afficherListe(devise.getNomDevise(), monnaieListUser);
                    break;
                }
            } catch (NumberFormatException e) {
                if (saisie.equals("END")) {
                    System.out.println("Conversion en cours ...");

                } else if (saisie.equals("cancel")) {
                    System.out.println("Vous avez demandé l'annulation de la conversion");
                    System.out.println("Transaction invalidée !!!");
                    afficherListe(devise.getNomDevise(), monnaieListUser);
                    break;
                } else {
                    System.out.println("Symbole inconnu !!!");
                    System.out.println("Transaction invalidée !!!");
                    afficherListe(devise.getNomDevise(), monnaieListUser);
                    break;
                }
            }

        } while (!END.equals(saisie));

        // CONVERSION A FAIRE ICI
    }

    // affiche une liste dont la tete est le type de monnaie suivi des pieces introduites par l'utilisateur
    private void afficherListe(Symbole typeMonnaie, List<Integer> list) {
        switch (typeMonnaie){
            case EURO:
                System.out.print("(EURO \t");
                list.forEach(e -> System.out.print(e + " "));
                System.out.println(")");break;
            case FRANC:
                System.out.print("(FRANCS \t");
                list.forEach(e -> System.out.print(e + " "));
                System.out.println(")");break;

            default:
                System.out.println(" erreur y as  d autre devise ");break;
        }


    }


    public List<PieceQuantite> conversionEuroversFrancs(List<Integer> list) {

        // init de la liste a renvoyer

        List<PieceQuantite> listFrancs = new ArrayList<>();

        // je cree une variable taill , parce que le plus grand element de ma liste ce trouve dans
        // la dernier positon  de ma liste
        int taill = franc.getListPieces().size() - 1;

        // la somme du mantant qu'il a saisie
        double somme = 0;
        for (int i : list) {
            somme += i;
        }

        // la conversion  de la somme vers le francs
        double sommeFrancs = somme * 1.1;



        System.out.println(sommeFrancs);

        double motantPieceCourante  ;



        boolean fin = false;

        while (!fin) {

            System.out.println("------------------------------------------------");

            System.out.println(" debut la taill =="+taill);

            motantPieceCourante =(double)franc.getListPieces().get(taill).getPiece() ;
            System.out.println(motantPieceCourante);
            if (motantPieceCourante <= sommeFrancs) {
                // un teste pour savoir si y as des pieces  dans la machine
                if (franc.getListPieces().get(taill).getQuantite() >= 1) {
                    int quantite = (int) (sommeFrancs / motantPieceCourante);

                    // la on verfie si la quantié retourner exsite dans la machine

                    if (quantite <= franc.getListPieces().get(taill).getQuantite()) {

                        franc.getListPieces().get(taill).setQuantite(franc.getListPieces().get(taill).getQuantite() - quantite);

                        //ajout de l'objet a ma list

                        listFrancs.add(new PieceQuantite(franc.getListPieces().get(taill).getPiece() ,
                                                         quantite,
                                                         Symbole.FRANC,
                                                         franc.getListPieces().get(taill).getTypeMonnaie()));

                        // decrementer la sommeFrancs  maitenant
                        System.out.println(" add : ("+franc.getListPieces().get(taill).getPiece()+","+ quantite+","+ Symbole.FRANC+","+ franc.getListPieces().get(taill).getTypeMonnaie()+")" );

                        sommeFrancs = sommeFrancs - (motantPieceCourante*quantite) ;

                        System.out.println("somme Francs qui reste a transformer  ="+sommeFrancs);

                            // decrementation de la indice
                            taill-- ;


                        System.out.println("taill apres ="+taill);


                    }else{

                        if(taill>0){
                            taill-- ;
                        }else{
                            fin=true ;
                        }

                    }


                }else{
                    if(taill > 0){
                        taill-- ;
                    }else{
                        fin=true ;
                    }
                }


            }else{
                if(taill>0){

                    taill-- ;
                }else{
                    System.out.println("je suis dans le dernier element de la liste faut faire le round ");
                    fin=true ;
                }


            }

        }


        return listFrancs;
    }

}
