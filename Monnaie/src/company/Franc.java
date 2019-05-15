package company;

import java.util.ArrayList;
import java.util.List;

public class Franc {


   private List<PieceQuantite> listFrancs  ;





    public Franc(){
        this.listFrancs=initFranc() ;

    }


    public List<PieceQuantite> getLisFrancs() {
        return listFrancs;
    }

    public void setLisFrancs(List<PieceQuantite> listFrancs) {
        this.listFrancs = listFrancs;
    }

    public List<PieceQuantite> initFranc(){

        List<PieceQuantite> list=new ArrayList<>() ;

        PieceQuantite piece50F_C =new PieceQuantite(50,10,Symbole.CENTIME_FRANC,TypeMonnaie.PIECE) ;
        PieceQuantite piece1F =new PieceQuantite(1,10,Symbole.FRANC,TypeMonnaie.PIECE) ;
        PieceQuantite piece2F =new PieceQuantite(2,5,Symbole.FRANC,TypeMonnaie.PIECE) ;
        PieceQuantite billet5F =new PieceQuantite(5,5,Symbole.FRANC,TypeMonnaie.PIECE) ;
        PieceQuantite billet10F =new PieceQuantite(10,5,Symbole.FRANC,TypeMonnaie.BILLET) ;
        PieceQuantite billet20F =new PieceQuantite(20,5,Symbole.FRANC,TypeMonnaie.BILLET) ;
        PieceQuantite billet50F =new PieceQuantite(50,5,Symbole.FRANC,TypeMonnaie.BILLET) ;
        PieceQuantite billet100F =new PieceQuantite(50,4,Symbole.FRANC,TypeMonnaie.BILLET) ;


        list.add(piece50F_C) ;
        list.add(piece1F) ;
        list.add(piece2F) ;
        list.add(billet5F) ;
        list.add(billet10F) ;
        list.add(billet20F) ;
        list.add(billet50F) ;

        return list ;


    }

    public void affichage(int valeur, List<Integer> list) {

        if( list.isEmpty() ){
            if(valeur>5){
                System.out.println("Introduction d’un billet de "+valeur+" francs");

            }else{
                System.out.println("Introduction d’une piece de "+valeur +"francs");
            }
        }else{
            if(valeur>2){
                System.out.println("puis d’un billet de "+valeur+" francs");

            }else{
                System.out.println("puis d’une piece de "+valeur +"francs");
            }
        }

    }

    public Boolean verifcation(int valeur ){

        for(PieceQuantite pieceQuantite :listFrancs){
            if(pieceQuantite.getPiece()==valeur){
                return true ;
            }
        }

        return false ;


    }



}
