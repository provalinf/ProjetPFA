package company;

import company.Devise.Devise;
import company.Devise.Euro;
import company.Devise.Franc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MachineConversion {
    private static Scanner scanner = new Scanner(System.in);

    private static final String EURO_FRANCS = "get-chf";
    private static final String FRANCS_EURO = "get-euros";
    private static final String END = "end";
    private static final String CANCEL = "cancel";
    private static final String FIN = "fin";
    private static final String MASTER = "master-players" ;


    private Euro euro;
    private Franc franc;

    public MachineConversion() {
        euro = new Euro();
        franc = new Franc();
    }


    public void conversion() {
        String saisie;
        do {
            System.out.println("En quelle devise faire la conversion ?");
            System.out.println("\t- Si Euro vers Franc-Suisse tapez : " + EURO_FRANCS);
            System.out.println("\t- Si Franc-Suisse vers Euro tapez : " + FRANCS_EURO);
            System.out.println("\t- Pour arreter la machine tapez  : " + FIN);
            saisie = scanner.nextLine();
            if(saisie.equals(FIN)){

                System.out.println("Entrer  mot de passer ADMIN svp :") ;

                String password = scanner.nextLine() ;
                if(password.equals(MASTER)){
                    System.out.println();
                    System.out.println("Je vérifie toujours ce qui'il y a sous mon lit avant de dormir le soir ^^");
                    System.out.println("Shutdown");
                    System.exit(0);

                }else{
                    System.out.println();
                    System.out.println(" This is not the F**Censured***G password, view the readMe file");
                    System.out.println();
                }

            }

        } while (!(saisie.equals(EURO_FRANCS)) && !(saisie.equals(FRANCS_EURO)));

        // Avant d'entrer dans le switch, on est sur que l'utilisateur a bien précisé la monnaie
        List<Double> monnaieListUser = new ArrayList<>();
        Devise devise = saisie.equals(EURO_FRANCS) ? euro : franc;
        // devise inverse a celle des pieces introduites
        Devise deviseInv = saisie.equals(EURO_FRANCS) ? franc : euro;
        // une boucle pour introduire toutes les pieces dans la machine
        System.out.println("OK. Ce sont des " + devise.getNomDevise() + " qui vont être introduits par l’utilisateur");
        do {
            System.out.println("Veuillez introduire une piece ou un billet :");
            saisie = scanner.nextLine();

            try {
                if (devise.verification(saisie)) {
                    // appel de la methode affichageEtAjout dans l'objet pieceQuantite
                    devise.affichageEtAjout(Double.parseDouble(saisie), monnaieListUser);
                } else {
                    System.out.println("Mauvais montant !!!");
                    System.out.println("Transaction invalidée !!!");
                    afficherListe(devise.getNomDevise(), monnaieListUser);
                    break;
                }
            } catch (NumberFormatException e) {
                if (saisie.equals(END)) {
                    System.out.println("Conversion en cours ...");

                } else if (saisie.equals(CANCEL)) {
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

        } while (!saisie.equals(END) && !saisie.equals(CANCEL));

        // CONVERSION A FAIRE ICI

        if (saisie.equals(END)) {
            double somme = monnaieListUser.stream().reduce(0.0, (t1, t2) -> t1 + t2);

            switch (devise.getNomDevise()) {
                case "EUR":
                    if (convert("euroToFranc", somme, deviseInv)) {
                        maj_stock_pieces(devise, monnaieListUser);
                    } else {
                        System.out.println("Montant Indisponible");
                        System.out.println("Transaction invalidée !!!");
                        afficherListe(devise.getNomDevise(), monnaieListUser);
                    }

                    break;
                case "CHF":
                    if (convert("francToEuro", somme, deviseInv)) {
                        maj_stock_pieces(devise, monnaieListUser);
                    } else {
                        System.out.println("Montant Indisponible");
                        System.out.println("Transaction invalidée !!!");
                        afficherListe(devise.getNomDevise(), monnaieListUser);
                    }

                    break;
            }

        }

    }

    // affiche une liste dont la tete est le type de monnaie suivi des pieces introduites par l'utilisateur
    private void afficherListe(String typeMonnaie, List<Double> list) {
        System.out.print("(" + typeMonnaie + "\t");
        list.forEach(e -> {
            if (e < 1.0)
                System.out.print(e + " ");
            else
                System.out.print(e.intValue() + " ");
        });
        System.out.println(")");
    }

    // convert euro to franc
    public boolean convert(String codeConversion, double montant, Devise devise) {
        List<Double> result = new ArrayList<>();
        List<PieceQuantite> list = devise.getListPieces();
        Double montantConv; // montant converti
        int nbPiecesCentimes;

        if (codeConversion.equals("euroToFranc")) {
            montantConv = montant * 1.12;
            nbPiecesCentimes = 1;
        } else {
            montantConv = montant / 1.12;
            nbPiecesCentimes = 3;
        }

        int partieEntiere = montantConv.intValue();
        int partieFract = new Double(((montantConv - partieEntiere) * 100)).intValue();

        // Afficher le montant converti
        if (partieFract == 0)
            System.out.println(partieEntiere + "\t" + devise.getNomDevise());
        else
            System.out.println(partieEntiere + "\t" + devise.getNomDevise() + "\t" + partieFract + " Centimes");

        PieceQuantite piece = new PieceQuantite();
        // retourne les pieces necessaires pour la partie entiere du montant converti
        for (int i = list.size() - 1; list.get(i).getSymbole() != Symbole.CENTIME; i--) {
            piece = list.get(i);

            while ((piece.getQuantite() > 0) && (partieEntiere - piece.getMontant() >= 0)) {
                partieEntiere -= piece.getMontant();
                piece.setQuantite(piece.getQuantite() - 1);
                result.add(Double.parseDouble(Integer.toString(piece.getMontant())));

            }

        }

        // Montant indisponible
        if (partieEntiere != 0)
            return false;

        // retourne les pieces necessaires pour la partie fractionnaire du montant conerti
        for (int i = nbPiecesCentimes - 1; i >= 0; i--) {

            piece = list.get(i);

            while ((piece.getQuantite() > 0) && (partieFract - piece.getMontant() >= 0)) {
                partieFract -= piece.getMontant();
                piece.setQuantite(piece.getQuantite() - 1);
                result.add(Double.parseDouble("0." + piece.getMontant()));

            }
        }

        /*
            considérer le cas où nous sommes à moins de 2 cents du dixième d’euro supérieur
            ou à moins de 10 centimes du demi-franc suisse supérieur
         */
        switch (devise.getNomDevise()){
            case "EUR":
                // le cas où nous sommes à moins de 2 cents du dixième d’euro supérieur
                if (((piece.getMontant() - partieFract ) < 2) && ((piece.getMontant() - partieFract ) > 0)) {
                   if (piece.getQuantite() > 0){
                       piece.setQuantite(piece.getQuantite() - 1);
                       result.add(Double.parseDouble("0." + piece.getMontant()));
                   }else
                       return false;
                }
                break;
            default:
                // le cas où nous sommes à moins de 10 centimes du demi-franc suisse supérieur
                if (((piece.getMontant() - partieFract ) < 10) && ((piece.getMontant() - partieFract ) > 0)){
                   if (piece.getQuantite() > 0){
                       piece.setQuantite(piece.getQuantite() - 1);
                       result.add(Double.parseDouble("0." + piece.getMontant()));
                   }else
                       return false;
                }
                break;
        }



        // Afficher les pieces et billets rendus à l'utilisateur
        afficherListe(devise.getNomDevise(), result);
        // Mise à jour des quantités de pieces en enlevant celle qui ont été rendues à l'utilisateur
        devise.setListPieces(list);

        return true;
    }


    /*
         rajouter les pieces introduites par l'utilisateur au stock de depart
     */
    public void maj_stock_pieces(Devise devise, List<Double> monnaieListUser) {

        for (int i = 0; i < monnaieListUser.size(); i++) {
            PieceQuantite pieceQuantite = new PieceQuantite();
            pieceQuantite.setQuantite(1);

            if (monnaieListUser.get(i) >= 10.0) {
                pieceQuantite.setMontant(monnaieListUser.get(i).intValue());
                pieceQuantite.setTypeMonnaie(TypeMonnaie.BILLET);
                switch (devise.getNomDevise()) {
                    case "EUR":
                        pieceQuantite.setSymbole(Symbole.EURO);
                        break;

                    case "CHF":
                        pieceQuantite.setSymbole(Symbole.FRANC);
                        break;
                }

            } else {
                pieceQuantite.setTypeMonnaie(TypeMonnaie.PIECE);

                if (monnaieListUser.get(i) < 1.0) {
                    pieceQuantite.setSymbole(Symbole.CENTIME);
                    Double montant = monnaieListUser.get(i) * 100;
                    pieceQuantite.setMontant(montant.intValue());
                } else {
                    pieceQuantite.setMontant(monnaieListUser.get(i).intValue());
                    switch (devise.getNomDevise()) {
                        case "EUR":
                            if (monnaieListUser.get(i) == 5)
                                pieceQuantite.setTypeMonnaie(TypeMonnaie.BILLET);
                            pieceQuantite.setSymbole(Symbole.EURO);
                            break;

                        case "CHF":
                            pieceQuantite.setSymbole(Symbole.FRANC);
                            break;

                    }
                }

            }

            PieceQuantite p = devise.getListPieces().stream().
                    filter(t -> (t.getTypeMonnaie() == pieceQuantite.getTypeMonnaie())
                            && (t.getSymbole() == pieceQuantite.getSymbole())
                            && (t.getMontant() == pieceQuantite.getMontant())).
                    collect(Collectors.toList()).get(0);
            p.setQuantite(p.getQuantite() + 1);


        }
    }

}

