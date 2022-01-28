import java.util.Scanner;

/**
 * Aplikasi manajemen data mahasiswa
 *
 * @author Fachrurozi
 *         Github : Fachrz
 *         email  : rfachru3@outlook.com
 */
public class CLI {
    public static final int maxData = 4;
    public static Mahasiswa[] mahasiswa = new Mahasiswa[maxData];
    static int lastRow = 0;

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("Aplikasi Data mahasiswa (CLI)");
        System.out.println("===================================");

        helpProcess();

        Scanner cliInput = new Scanner(System.in);

        String cmdInput;
        do {
            System.out.print(">>");
            cmdInput = cliInput.nextLine();
        }while(cmdProcessing(cmdInput));
    }

    /**
     * Semua perintah didaftarkan dan di proses disini
     *
     * @param in
     */
    public static boolean cmdProcessing(String in){
        boolean result = true;
        String[] cmd = in.split(" ");

        switch (cmd[0]){
            case "add":
                addProcess(cmd);
                break;
            case "delete":
                deleteProcess(cmd);
                break;
            case "edit":
                System.out.println("Sedang dalam pengembangan");
                break;
            case "list":
                listProcess();
                break;
            case "exit":
                System.out.println("dadah!!");
                System.exit(0);
            case "help":
                helpProcess();
                break;
            default:
                System.out.println("Perintah tidak ditemukan!!");
                break;
        }

        return result;
    }

    /**
     * Proses perintah "add"
     *
     */
    public static void addProcess(String[] cmd){
        String nim = cmd[1];
        String nama = cmd[2];

        if (lastRow < maxData){
            mahasiswa[lastRow] = new Mahasiswa();
            if (mahasiswa[lastRow].setData(nim, nama)) {
                lastRow++;
                System.out.println("Data berhasil disimpan");
            }
        }else{
            System.out.println("Maaf penyimpanan sudah penuh");
        }
    }

    /**
     * Proses perintah "delete"
     *
     */
    public static void deleteProcess(String[] cmd) {
        int dindex = Integer.parseInt(cmd[1]);
        for (int i = dindex; i < lastRow; i++){
            if (i + 1 != lastRow){
                mahasiswa[i] = mahasiswa[i+1];
            }else{
                mahasiswa[i] = null;
            }
        }
        lastRow--;
    }

    /**
     * Proses perintah "list"
     *
     */
    public static void listProcess()
    {
        System.out.println("Nim \t\t Nama \t\t Tanggal");
        for (int i = 0; i < lastRow; i++){
            String[] mhsw = mahasiswa[i].showData();
            System.out.println(mhsw[0] + "\t\t" + mhsw[1] + "\t\t" + mhsw[2]);
        }


    }

    /**
     * Proses perintah "help"
     *
     */
    public static void helpProcess(){
        String[][] cmd = {
                {"add", "Menambah data mahasiswa"},
                {"delete", "Menghapus data mahasiswa"},
                {"edit", "Mengubah data mahasiswa"},
                {"list", "Menampilkan semua data mahasiswa yang ada"},
                {"help", "Menampilkan daftar perintah yang bisa dilakukan"},
                {"exit", "Keluar dari aplikasi"},
        };

        System.out.println("Command: ");
        for (String[] command : cmd) {
            System.out.println("\t" + command[0] + "\t\t" + command[1]);
        }
    }
}
