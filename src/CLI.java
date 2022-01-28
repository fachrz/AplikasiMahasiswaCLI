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
    public static void main(String[] args) throws Exception {
        System.out.println("===================================");
        System.out.println("Aplikasi Data mahasiswa (CLI)");
        System.out.println("===================================");

        helpProcess();

        Scanner cliInput = new Scanner(System.in);

        String cmdInput;

        try {
            do {
                System.out.print(">>");
                cmdInput = cliInput.nextLine();
            }while(cmdProcessing(cmdInput));
        }catch (Exception e){
            throw e;
        }

    }

    /**
     * Semua perintah didaftarkan dan di proses disini
     *
     * @param in
     */
    public static boolean cmdProcessing(String in) {
        boolean result = true;
        String[] cmd = in.split(" ");

        try {
            switch (cmd[0]){
                case "add":
                    if (cmd.length == 4){
                        addProcess(cmd[1], cmd[2], cmd[3]);
                    }else if(cmd.length < 4){
                        System.out.println("NIM, Nama, Tngl. Lahir dibutuhkan (ex: add <nim> <nama> <tngl_lahir>)");
                    }else{
                        System.out.println("Perintah \"add\" tidak sesuai!");
                    }
                    break;
                case "delete":
                    if (cmd.length == 2){
                        deleteProcess(cmd[1]);
                    }else if(cmd.length == 1){
                        System.out.println("NIM dibutuhkan (ex: delete <nim>)");
                    }else{
                        System.out.println("Perintah \"delete\" tidak sesuai!");
                    }
                    break;
                case "edit":
                    if (cmd.length == 4){
                        editProcess(cmd);
                    }else if(cmd.length < 4){
                        System.out.println("NIM, Nama, Tngl. Lahir dibutuhkan (ex: edit <nim> <nama> <tngllahir>)");
                    }else{
                        System.out.println("Perintah \"edit\" tidak sesuai!");
                    }
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
                    System.out.println("Perintah tidak tersedia");
                    break;
            }
        }catch (Exception e){

        }

        return result;
    }

    /**
     * Proses perintah "add"
     *
     */
    public static void addProcess(String nim, String nama, String tglLahir) {
        if (lastRow < maxData){
            try {
                mahasiswa[lastRow] = new Mahasiswa();
                if (mahasiswa[lastRow].setMahasiswa(nim, nama, tglLahir)) {
                    lastRow++;
                    System.out.println("Data berhasil disimpan");
                }
            }catch (Exception e){
                System.out.println("Gagal menambahkan, terjadi masalah pada aplikasi");
            }

        }else{
            System.out.println("Maaf penyimpanan sudah penuh");
        }
    }

    /**
     * Proses perintah "delete"
     *
     * delete <nim>
     */
    public static void deleteProcess(String nim){
        int dindex = 0;
        boolean flag = false;

        //mencari index
        for (int x = 0; x < lastRow; x++){
            if (nim.equals(mahasiswa[x].getMahasiswa()[0])){
                dindex = x;
                flag = true;
            }
        }

        if (flag){
            //delete process begin
            for (int i = dindex; i < lastRow; i++){
                if (i + 1 != lastRow){
                    mahasiswa[i] = mahasiswa[i+1];
                }else{
                    mahasiswa[i] = null;
                }
            }
            System.out.println("NIM " + nim + " berhasil dihapus");
            lastRow--;
        }else{
            System.out.println("NIM tidak ditemukan");
        }
    }

    /**
     * Proses perintah "edit"
     *
     */
    public static void editProcess(String[] input) throws Exception {
        //edit <nim> optional: <nama> <tngllahir>
        int eindex = Integer.parseInt(input[1]);
        String nim = input[1];
        String nama = input[2];
        String tnglLahir = input[3];
        boolean flag = false;

        //mencari index
        for (int x = 0; x < lastRow; x++){
            if (input[1].equals(mahasiswa[x].getMahasiswa()[0])){
                eindex = x;
                flag = true;
            }
        }

        //process edit
        if (flag) {
            mahasiswa[eindex].setMahasiswa(nim, nama, tnglLahir);
        }
    }

    /**
     * Proses perintah "list"
     *
     */
    public static void listProcess()
    {
        System.out.println("Nim \t\t Nama \t\t Tanggal");
        for (int i = 0; i < lastRow; i++){
            String[] mhsw = mahasiswa[i].getMahasiswa();
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
