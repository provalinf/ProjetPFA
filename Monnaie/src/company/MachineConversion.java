package company;

import java.util.Scanner;

public class MachineConversion {

    Scanner scanner = new Scanner(System.in);

    private Euro euro;
    private Franc franc;

    public MachineConversion(Euro euro, Franc franc) {
        this.euro = euro;
        this.franc = franc;
    }


    public int Conversion() {

        System.out.println(" En Quoi Vous voulez  faire la conversion  ?");
        System.out.println("si  frans vers  Euro tapez : get-chef");
        System.out.println("si  Euro vers Frans  tapez : get-euros");

        String sens = scanner.nextLine();
        switch (sens) {
            case "get-chef":
                System.out.println("OK. Ce sont des euros qui vont être introduits par l’utilisateur");

                System.out.println("veulliez introduire une piece ou un billtet svp :");
                int piece=scanner.nextInt() ;
                if(euro.getListEuro().contains(piece)){




                }else{
                    System.out.println(" le cas ou il introduit une piece non existante  .");
                }


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
