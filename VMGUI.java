/*
    user interface vending machine.
*/

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.JOptionPane; 

public class VMGUI extends JFrame implements ActionListener {
    // fungsi user interface
    private int StartingBalance = 100000; // balance awal
    public boolean CloseAfter = false; // setelah beli produk langsung close atau tidak
    private int Width = 350; 
    private int Height = 225;
    private JButton Buttons[]; // array untuk button
    private int i; // integer untuk loop
    public VM Machine = new VM(StartingBalance, CloseAfter); // membuat mesin baru
    public JLabel About = new JLabel("   Vending Machine");
    public JLabel Bal = new JLabel("   Balance: " + Machine.getPrettyBalance());
    public VMGUI() { // fungsi untuk membuat GUI
        setTitle("Vending Machine");
        Toolkit myTK = Toolkit.getDefaultToolkit();
        Dimension myD = myTK.getScreenSize();
        setBounds((myD.width - Width)/2, (myD.height - Height)/2, Width, Height);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            } // END windowClosing(WindowEvent e)
        }// END WindowAdapter()
        ); // END addWindowListener()

        Buttons = new JButton[12]; 
        Machine.Initialize(); 
        for (i = 0 ; i < 12 ; i++) {
            Buttons[i] = new JButton(Machine.getDescription(i) + " - " + Machine.PrettyMoney(Machine.getPrice(i)));
            
        } // END for (i = 0 ; i < 6 ; i++)
        /*
        Buttons[6] = new JButton("Rp. 100.000");
        Buttons[7] = new JButton("Rp. 50.000");
        Buttons[8] = new JButton("Rp. 25.000");
        Buttons[9] = new JButton("Rp. 10.000");
        Buttons[10] = new JButton("Rp. 5.000");*/
        Buttons[11] = new JButton("SCAN");
         
        Container pane = getContentPane(); 
        pane.setLayout(new GridLayout(7,2));
        for(i = 0 ; i < 6; i++) { // memberi tombol pada panel
            pane.add(Buttons[i]); 
            pane.add(Buttons[i+6]);
            Buttons[i].addActionListener(this); 
            Buttons[i+6].addActionListener(this); //memastikan listener berfungsi
        } // END for(i = 0 ; i < 12; i++)

        pane.add(Bal); // memberi Balance label pada content pane
        pane.add(About); // memberi About label pada content pane
    } // END VMGUI()
    public void actionPerformed(ActionEvent e) { 
        boolean Success;
        Machine.Initialize();
        Object theButton = e.getSource();
        int pilih = Machine.getPilihan();
            if(theButton == Buttons[i]) { 
                switch(i) { // memberitahu user menambahkan uang dari masing2 button
                 
                    case 11: 
                        Success = Machine.BuyItem(pilih);
                    if ((Machine.getCA() == true) || (Success == true)) {
                        Machine.ReturnChange();
                        //System.exit(0); // fungsi BuyItem return kembalian
                    }
                        break;
                    
                        
                } // END switch(i)
            } // END if(theButton == Buttons[i])
        
        for(i = 0; i < 11; i++) { // loop untuk pembelian produk
            if(theButton == Buttons[i]) { 
                Machine.setPilihan(i); 
                
            } // END if(theButton == Buttons[i])
        } // END for(i = 0; i < 6; i++)
        Bal.setText("   Balance: " + VM.getPrettyBalance());
        
    } // END actionPerformed(ActionEvent e)
} // END VMGUI
