import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Siti Mawaddah
 * @version 31/12/2020
 */
public class TestBus {
    public TestBus()
    {

    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int menu;
        boolean lanjut = true;
        Bus bus = new Bus();

        do {
            System.out.println("\nMENU:");
            System.out.println("1. Naikkan Penumpang");
            System.out.println("2. Turunkan Penumpang");
            System.out.println("3. Lihat Penumpang");
            System.out.println("4. Lihat Bus");
            System.out.println("5. Keluar");
            System.out.print("\nPilihan: ");
            try {
                menu = sc.nextInt();
            } catch (InputMismatchException e) {
                menu = 0;
                sc.next(); // next token
            }

            switch (menu) {
                case 1 -> {
                    boolean naik, sudahAda;

                    if(bus.getJumlahPenumpangBiasa() + bus.getJumlahPenumpangPrioritas() + bus.getJumlahPenumpangBerdiri() >= 40) {
                        System.out.println("Maaf, penumpang telah penuh!\nSilakan tunggu bus berikutnya");
                        naik = false;
                    } else {
                        Penumpang penumpang;
                        penumpang = inputDataPenumpang();
                        sudahAda = bus.isSudahAda(penumpang);
                        if (sudahAda) {
                            naik = false;
                        }
                        else {
                            naik = bus.naikkanPenumpang(penumpang);
                        }
                    }

                    if (naik) {
                        System.out.println("Penumpang berhasil naik");
                    }
                }
                case 2 -> {
                    boolean turun;
                    int id;
                    try {
                        System.out.print("ID          : ");
                        id = sc.nextInt();
                    } catch (InputMismatchException e) {
                        id = 0;
                        sc.next();
                    }
                    turun = bus.turunkanPenumpang(id);
                    if (turun) {
                        System.out.println("Penumpang berhasil turun");
                    } else {
                        System.out.println("Maaf, Penumpang tidak ditemukan!");
                    }
                }
                case 3 -> {
                    tampilkanDataPenumpang(bus);
                }
                case 4 -> {
                    System.out.println(bus);
                }
                case 5 -> {
                    lanjut = false;
                }
                default -> {
                    System.out.println("Maaf, Pilihan tidak tersedia!");
                }
            }
        } while (lanjut);

    }

    private static Penumpang inputDataPenumpang()
    {
        Scanner sc = new Scanner(System.in);
        int id, umur;
        String nama;
        boolean hamil;
        Penumpang penumpang;

        while (true) {
            try {
                System.out.print("ID          : ");
                id = sc.nextInt();
            } catch (InputMismatchException e) {
                id = 0;
                sc.next();
            }
            if (id > 0) {
                break;
            }
            System.out.println("\nID yang Anda masukkan tidak valid!");
            System.out.println("Harap input ulang ID anda!\n");
        }

        System.out.print("Nama        : ");
        sc.nextLine();
        nama = sc.nextLine();

        while (true) {
            try {
                System.out.print("Umur        : ");
                umur = sc.nextInt();
            } catch (InputMismatchException e) {
                umur = -1;
                sc.next();
            }
            if (umur >= 0) {
                break;
            }
            System.out.println("\nUmur yang Anda masukkan tidak valid!");
            System.out.println("Harap input ulang umur anda!\n");
        }

        while (true) {

            System.out.print("Hamil (Y/N) : ");
            String h = sc.next();

            if (h.equalsIgnoreCase("y")){
                hamil = true;
                break;
            } else if (h.equalsIgnoreCase("n")){
                hamil = false;
                break;
            }

            System.out.println("\nHarap input Y atau N!\n");
        }

        penumpang = new Penumpang(id, nama, umur, hamil);

        return penumpang;
    }

    private static void tampilkanDataPenumpang(Bus bus)
    {
        int jBiasa = bus.getJumlahPenumpangBiasa();
        int jPrioritas = bus.getJumlahPenumpangPrioritas();
        int jBerdiri = bus.getJumlahPenumpangBerdiri();

        System.out.println("+--------------------------------------------------------------------------------------------------+");
        System.out.println("|                                        DATA PENUMPANG BUS                                        |");
        System.out.println("+--------+-----------------------------+-----------------------------+-----------------------------+");
        System.out.println("|        |            BIASA            |          PRIORITAS          |           BERDIRI           |");
        System.out.println("|   NO   +--------------+--------------+--------------+--------------+--------------+--------------+");
        System.out.println("|        |      ID      |     Nama     |      ID      |     Nama     |      ID      |     Nama     |");
        System.out.println("+--------+--------------+--------------+--------------+--------------+--------------+--------------+");
        for (int i = 0; i < jBiasa || i < jPrioritas || i < jBerdiri; ++i) {
            String nBiasa = "", nPrioritas = "", nBerdiri = "", iBiasa = "", iPrioritas = "", iBerdiri = "";
            if (i < bus.getJumlahPenumpangBiasa()) {
                nBiasa = bus.getPenumpangBiasa().get(i).getNama();
                iBiasa = Integer.toString(bus.getPenumpangBiasa().get(i).getID());
            }
            if (i < bus.getJumlahPenumpangPrioritas()) {
                nPrioritas = bus.getPenumpangPrioritas().get(i).getNama();
                iPrioritas = Integer.toString(bus.getPenumpangPrioritas().get(i).getID());
            }
            if (i < bus.getJumlahPenumpangBerdiri()) {
                nBerdiri = bus.getPenumpangBerdiri().get(i).getNama();
                iBerdiri = Integer.toString(bus.getPenumpangBerdiri().get(i).getID());
            }
            System.out.printf("|   %-4d | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n", i + 1, iBiasa, nBiasa, iPrioritas, nPrioritas, iBerdiri, nBerdiri);
        }
        System.out.println("+--------+--------------+--------------+--------------+--------------+--------------+--------------+");
        System.out.printf("| JUMLAH |              %-14d |              %-14d |              %-14d |\n", jBiasa, jPrioritas, jBerdiri);
        System.out.println("+--------+-----------------------------+-----------------------------+-----------------------------+");
        System.out.printf("| TOTAL  |                                            %-44d |\n", jBiasa + jPrioritas + jBerdiri);
        System.out.println("+--------+-----------------------------------------------------------------------------------------+");
    }

}
