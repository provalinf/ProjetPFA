package company;

import company.Devise.Euro;
import company.Devise.Franc;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {


		List<Integer> list= Arrays.asList(1,2,20,50,10,10,5,1);

		Euro euro =new Euro() ;
		Franc franc=new Franc() ;

		MachineConversion machineConversion=new MachineConversion(euro,franc) ;

	  List<PieceQuantite> pieceQuantites=	machineConversion.conversionEuroversFrancs(list);



		//MachineConversion machineConversion = new MachineConversion();
		//machineConversion.conversion();
	}
}
