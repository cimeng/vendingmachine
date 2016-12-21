import javax.swing.*;

public class VendMachine { 
	public static void main(String args[]) {
		BuildMachine(); 
		//mengaktifkan JFrame dan ditampilkan di layar
		// bersambung dengan VMGUI.class
	} // END main(String args[])

	public static void BuildMachine() {
		// membuat objek baru VendingMachine yangmerupakan objek JFrame
		JFrame VendingMachine = new VMGUI();
		// membuat objek JFrame
		VendingMachine.show(); 
	} // END BuildMachine()

} // END VendMachine