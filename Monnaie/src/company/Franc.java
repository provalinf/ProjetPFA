package company;

import java.util.ArrayList;
import java.util.List;

public class Franc {


   private List<PieceQuantite> lisFrancs  ;





    public Franc(){
        this.lisFrancs=initFranc() ;

    }


    public List<PieceQuantite> getLisFrancs() {
        return lisFrancs;
    }

    public void setLisFrancs(List<PieceQuantite> lisFrancs) {
        this.lisFrancs = lisFrancs;
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



}
