package BanditManchot;

public class Main {

	public static void main(String[] args) {
		MachineConversion machineConversion = new MachineConversion();
		// the loop will be stopped if the user rights the fin command
		//noinspection InfiniteLoopStatement
		while (true) machineConversion.conversion();
	}
}
