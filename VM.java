import javax.swing.JOptionPane;

public class VM { // memanggil fungsi VendMachine.java

    /* harga produk dan deskripsi
    */

    final static int PRICE_0 = 10500;
    final static int PRICE_1 = 14500;
    final static int PRICE_2 = 21500;
    final static int PRICE_3 = 29500;
    final static int PRICE_4 = 9950;
    final static int PRICE_5 = 4050;

    final static String DESC_0 = "Pepsi";
    final static String DESC_1 = "Finto";
    final static String DESC_2 = "Mirinda";
    final static String DESC_3 = "Teh Sisri";
    final static String DESC_4 = "Jas Jus";
    final static String DESC_5 = "Sprata";
    final static String DESC_6 = "Cola";
    final static String DESC_7 = "Finto";
    final static String DESC_8 = "Mirinda";
    final static String DESC_9 = "Teh Sisri";
    final static String DESC_10 = "Jas Jus";
    final static String DESC_11 = "Jas Jus";

    private static int balance; // integer untuk menghitung uang user
    private static boolean CA = false;
    // untuk menjaga vending machine tidak langsung keluar sebelum notifikasi kembalian koin
    private static int Price[] = new int[12];
            // array yang berisi harga 6 produk
    private static String Description[] = new String[12];
            // array yang berisi deskripsi 6 produk
    private static int Stock[] = new int[12];
            //array yang berisi stock 6 produk
    private static int Pilihan;
    private static int MaxLen = 0; // untuk formatting menu supaya terlihat lebih bagus
    public static void setPrice(int ItemNumber, int thePrice) {
        // memberikan harga pada produk
        /* contoh:
            setPrice(0, 165);
            beri harga pada produk 0 menjadi $1.65
        */
        Price[ItemNumber] = thePrice; 
    } // END setPrice(int ItemNumber, int thePrice)

    public static int getPrice(int ItemNumber) {
        // untuk me return harga barang
        /* contoh:
            int x = 0;
            x = getPrice(0);
            memberikan info barang 0 berharga $1.65
        */
        return Price[ItemNumber]; 
    } // END getPrice(int ItemNumber)
    
    public static void setStock(int ItemNumber, int theStock) {
        // untuk memberikan stock pada produk
        /* contoh:
           setStock(0, 6);
           beri stock pada produk 0 sebanyak 6
        */
        Stock[ItemNumber] = theStock;
    } //END setStock(int ItemNumber, int the Stock)
    
    public static void setInitStock() {
        int i;
        for ( i=0 ; i<12 ;i++){
            setStock(i,2);
        }
    } //END setStock(int ItemNumber, int the Stock)
    
    public static void setPilihan(int ItemNumber){
        Pilihan = ItemNumber;
    }
    
    public static int getPilihan(){
        return Pilihan;
    }
    
    public static int getStock(int ItemNumber) {
        // untuk me return stock barang
        /* contoh:
            int x = 0;
            x = getPrice(0);
            memberikan info barang 0 berharga $1.65
        */
        return Stock[ItemNumber]; 
    } // END getPrice(int ItemNumber)
    
    
    public static void setDescription(int ItemNumber, String theDescription) {
        // memberikan deskripsi untuk tiap item
        /* contoh:
            setDescription(0, "Finto");
            memberikan deskripsi Finto untuk item 0.
        */
        Description[ItemNumber] = theDescription; 
    } // END setDescription(int ItemNumber, String theDescription)

    public static String getDescription(int ItemNumber) {
        // memberi info berdasarkan nomor item
        /* contoh :
            String Desc = null;
            Desc = getDescription(0);
            me return "Finto" pada deskripsi.
        */
        return Description[ItemNumber]; 
    } // END getDescription(int ItemNumber)

    public VM(int myBalance, boolean CloseAfter) { // set initial Machine
        balance = myBalance; 
        CA = CloseAfter;
        setInitStock();
        // set CA private variable menjadi CloseAfter variable yang pas.
    } // END VM(int myBalance)

    public static boolean getCA() {
        return CA;
    }

    public static void setBalance(int Balance) {
        // mengatur balance (satuan cents)
        /* contoh:
            setBalance(100);
            set balance menjadi $1.00
        */
        balance = Balance; 
    } // END setBalance(int Balance)

    public static boolean BuyItem(int ItemNumber) {
        int stock;
        boolean returnMe; // return boolean value
        // untuk membeli produk
        /* contoh:
            boolean x;
            x = BuyItem(0);
            membeli item nomor 0.
            jika pembelian berhasil, maka x set to TRUE
            sebaliknya, x set to false.
        */
        if (balance < getPrice(ItemNumber)) {
            // cek balance dan membandingkan dengan harga produk
            Output("Saldo Tidak Mencukupi!", 1);
            // memberi tahu kalau uang tidak cukup dan mengembalikan sisa koin
            returnMe = false; // pembelian gagal
        } // END if (balance < getPrice(ItemNumber))
        
        else if(getStock(ItemNumber)==0){
            Output("Stock Habis", 1);
            returnMe = false;
        }

        else { // kalau uang cukup
            balance -= getPrice(ItemNumber); // mengurangi balance sesuai barang yang dibeli
            stock = getStock(ItemNumber) - 1;
            setStock(ItemNumber, stock);
            Output("Terbeli " + getDescription(ItemNumber) + " Seharga " + PrettyMoney(getPrice(ItemNumber)), 1);
            
            returnMe = true; // pembelian berhasil, return true
        } // END else

        return returnMe; 
    } // END BuyItem(int ItemNumber)

    public static void PrintMenu() {
        /* 
            PrintMenu();
            mengatur menu sesuai array yang sudah diatur sebelumnya
        */
        String myMenu =
              "1  " + getDescription(0) + "   " + PrettyMoney(getPrice(0)) + "    D  $1.00 (Dollar)\n" +
              "2  " + getDescription(1) + "   " + PrettyMoney(getPrice(1)) + "    H  $0.50 (Half Dollar)\n" +
              "3  " + getDescription(2) + "   " + PrettyMoney(getPrice(2)) + "    Q  $0.25 (Quarter)\n" +
              "4  " + getDescription(3) + "   " + PrettyMoney(getPrice(3)) + "    M  $0.10 (Dime)\n" +
              "5  " + getDescription(4) + "   " + PrettyMoney(getPrice(4)) + "    N  $0.05 (Nickel)\n" +
              "6  " + getDescription(5) + "   " + PrettyMoney(getPrice(5)) + "    C  Coin Return";
        
        Output(myMenu, 1); 
    } // END PrintMenu()

    public static void Output(String Message, int Type) {
        // mengambil pesan berupa string dan integer untuk output
        /* contoh:
            Output("Hello", 1);
            memberikan output Hello dengan menggunakan System.out.println("Hello");
        */
        if (Type == 1) {  // tipe output
            JOptionPane.showMessageDialog(null, Message, "Vending Machine", JOptionPane.PLAIN_MESSAGE);
        } // END if (Type == 1)
        else {
            // masih bingung mau diisi apa  
        } // END else
    } // END Output(String message, int Type)

    public static void ReturnChange() {
        /* contoh :
            ReturnChange();
            menghitung dan memberikan output berupa kembalian koin pada user
        */
        int myTotal = getBalance(); // memasukkan balance pada "myTotal"
        int Dollars = 0; 
        int HalfDollars = 0; 
        int Quarters = 0; 
        int Dimes = 0; 
        int Nickels = 0;  

        Dollars = myTotal; // 100; // mengubah jumlah uang jadi satuan dollar
        //myTotal -= (100 * Dollars); 
        //HalfDollars = myTotal / 50;
        //myTotal -= (50 * HalfDollars); 
        //Quarters = myTotal / 25; 
        //myTotal -= (25 * Quarters); 
        //Dimes = myTotal / 10; 
        //myTotal -= (10 * Dimes); 
        //Nickels = myTotal / 5; 
        //myTotal -= (5 * Nickels); 

        /*if (!(myTotal == 0)) { 
            Output("Something went very wrong! You need pennies! How did you get pennies in there?", 1);
        }*/ // END if (!(myTotal == 0))

        Output("Sisa Saldo Anda: \n" +
                //HalfDollars + " Half Dollars\n" +
                //Quarters + " Quarters\n" +
                //Dimes + " Dimes\n" +
                //Nickels + " Nickels\n" +
                PrettyMoney(getBalance())
                ,1); // kembalian untuk user
                
        //setBalance(0); // Clear balance.
        
    } // END ReturnChange()

    public static String getPrettyBalance() {
        /* contoh:
            String x = null;
            x = getPrettyBalance();
            mengubah format balance dari 100 cents menjadi $1.00
        */
        return PrettyMoney(balance); 
    } // END getPrettyBalance()

    public static int getBalance() {
        /* contoh:
            int x = 0;
            x = getBalance;
            mendapat informasi balance dalam satuan cents dan memberikan nilainya pada x.
        */
        return balance; // Return the balance.
    } // END getBalance()

    public static void AddMoney(int Amount) {
        // menambah uang pada mesin dan balance
        /* contoh:
            AddMoney(50);
            menambah 50 cents pada user balance
        */
        balance = balance + Amount; 
    } // END AddMoney(int Amount)

    public static void Initialize() {
        // menginisialisasi mesin, sehingga user tidak perlu mengedit manual untuk list menu dan harga
        int i = 0; 
        String Desc = null; 
        setDescription(0, DESC_0);
        setDescription(1, DESC_1);
        setDescription(2, DESC_2);
        setDescription(3, DESC_3);
        setDescription(4, DESC_4);
        setDescription(5, DESC_5);
        setDescription(6, DESC_0);
        setDescription(7, DESC_1);
        setDescription(8, DESC_2);
        setDescription(9, DESC_3);
        setDescription(10, DESC_4);
        setDescription(11, DESC_5);
        
        setPrice(0, PRICE_0);
        setPrice(1, PRICE_1);
        setPrice(2, PRICE_2);
        setPrice(3, PRICE_3);
        setPrice(4, PRICE_4);
        setPrice(5, PRICE_5);
        setPrice(6, PRICE_1);
        setPrice(7, PRICE_2);
        setPrice(8, PRICE_3);
        setPrice(9, PRICE_4);
        setPrice(10, PRICE_5);
        setPrice(11, PRICE_5);
        
        for(i = 0 ; i < 11; i++) { 
            Desc = getDescription(i); 
            if (Desc.length() > MaxLen) { // mengubah max length apabila ada deskripsi yang lebih panjang
                MaxLen = getDescription(i).length(); // Set max length baru
            } // END if (Desc.length() > MaxLen)
        } // END (i = 0 ; i < 6; i++)
        FormatDescriptions(MaxLen);
    } // END Initialize()

    public static void FormatDescriptions(int ML) {
        // format semua deskriipsi berdasar length terpanjang
        /* contoh:
            FormatDescription(20);
            mengambil semua string dan spasi sampai 20 karakter
        */
    } // END FormatDescription(int ML)

    public static String PrettyMoney (int Money) {
        int Dollars = 0;
        int Cents = 0;
        int Tens = 0; 
        int Ones = 0; 
        String Pretty = null;
        Dollars = Money; // 100;
        //Cents = Money % 100;
        //Tens = Cents / 10;
        //Ones = Cents % 10;
        // mengatur format penulisan uang : "$X.XX"
        Pretty = "Rp." + Dollars; //+ "." + Tens + Ones;
        return Pretty;
    } // END PrettyMoney (int Money)
    
    public static void ScanCard(){
        setBalance(100000);
        Output("Scan Berhasil",1);
    
    }
    
    
    
} // End VM