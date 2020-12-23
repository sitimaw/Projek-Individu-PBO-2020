import java.util.InputMismatchException;
import java.util.Scanner;

public class TestBus {
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
            System.out.println("4. Keluar");
            System.out.print("\nPilihan: ");
            try {
                menu = sc.nextInt();
            } catch (InputMismatchException e) {
                menu = 0;
                sc.next(); // next token
            }

            switch (menu) {
                case 1 -> {
                    boolean naik;
                    Penumpang penumpang;
                    penumpang = inputDataPenumpang();
                    naik = bus.naikkanPenumpang(penumpang);
                    if (!naik){
                        System.out.println("Maaf, saldo tidak cukup");
                        System.out.print("Apakah ingin menambah saldo?(pilih 'y' jika ya)");
                        String tambahSaldo = sc.next();
                        if (tambahSaldo.equalsIgnoreCase("y")) {
                            tambahSaldoPenumpang(penumpang);
                            naik = bus.naikkanPenumpang(penumpang);
                        } else {
                            System.out.println("Maaf, Penumpang tidak dapat naik!");
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
        nama = sc.next();

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
        System.out.println("|   NO   |            BIASA            |          PRIORITAS          |           BERDIRI           |");
        System.out.println("+--------+-----------------------------+-----------------------------+-----------------------------+");
        for (int i = 0; i < jBiasa || i < jPrioritas || i < jBerdiri; ++i) {
            String biasa = "", prioritas = "", berdiri = "";
            if (i < bus.getJumlahPenumpangBiasa()) {
                biasa = bus.getPenumpangBiasa().get(i).getNama();
            }
            if (i < bus.getJumlahPenumpangPrioritas()) {
                prioritas = bus.getPenumpangPrioritas().get(i).getNama();
            }
            if (i < bus.getJumlahPenumpangBerdiri()) {
                berdiri = bus.getPenumpangBerdiri().get(i).getNama();
            }
            System.out.printf("|   %-4d | %-27s | %-27s | %-27s |\n", i + 1, biasa, prioritas, berdiri);
        }
        System.out.println("+--------+-----------------------------+-----------------------------+-----------------------------+");
        System.out.printf("| JUMLAH |              %-14d |              %-14d |              %-14d |\n", jBiasa, jPrioritas, jBerdiri);
        System.out.println("+--------+-----------------------------+-----------------------------+-----------------------------+");
        System.out.printf("| TOTAL  |                                            %-44d |\n", jBiasa + jPrioritas + jBerdiri);
        System.out.println("+--------+-----------------------------------------------------------------------------------------+");
    }

    private static void tambahSaldoPenumpang(Penumpang penumpang)
    {
        Scanner sc = new Scanner(System.in);
        int saldoBaru;

        while (true) {
            try {
                System.out.print("Saldo baru: ");
                saldoBaru = sc.nextInt();
            } catch (InputMismatchException e) {
                saldoBaru = 0;
                sc.next();
            }
            if (saldoBaru > Bus.ONGKOS) {
                break;
            }
            System.out.println("\nSaldo yang Anda masukkan tidak cukup!");
            System.out.println("Harap input ulang saldo yang lebih besar dari" + Bus.ONGKOS + " \n");
        }

        penumpang.tambahSaldo(saldoBaru);
    }
}
