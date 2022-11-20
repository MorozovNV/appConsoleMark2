import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void control(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c, String nik, String dim, String ari) {
        Scanner sc = new Scanner(System.in);

        while(true){
            outHelp();
            var g = sc.nextLine();
            if(g.equals("e")) break;

            switch (g) {
                case ("h"):
                    break;
                case ("a"):
                    addListsControl(a, b, c, nik, dim, ari, sc);
                    break;
                case ("s"):
                    outLists(a, b, c, nik, dim, ari);
                    break;
                case("c"): clearLists(a, b, c);
                    break;
                default:
                    System.out.println("Нет такой команды, по крайней мере пока. Читай че написано:");
                    break;
            }
        }
    }

    public static void outHelp(){
        System.out.println("Список команд: h-help, a-add, s-show, c-clear, e-esc");
    }

    public static void addListControl(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c, String nik, String dim, String ari, Scanner sc){

        boolean f = true;

        while (true){
            System.out.println("Массив чеков, допустим Ника");
            outLists(a, b, c, nik, dim, ari);
            System.out.println("_______");

            if(f) System.out.println("Введите сумму чека");
            else System.out.println("Введите еще число или команду d-done");
            var g = sc.nextLine();

            if(g.equals("d")) break;
            int j=0;
            while(j<3) {
                try {
                    // a.add(Double.parseDouble(g));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Введите число типа Double");
                    g = sc.nextLine();
                    j++;
                }
                if (j==3) {
                    System.out.println("Заебал тупить");
                    break;
                }
            }
            f = false;
        }
    }

    public static void addListControl(List<ArrayList<Double>> arL, List<ArrayList<Double>> b,List<ArrayList<Double>> c, Scanner sc) {
        boolean f = true;

        while (true) {

           if (f) System.out.println("Введите сумму чека: ");
            int j = 0;
            double ch = 0;
            while (j < 3) {
                try {
                    ch = sc.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Введите число типа Double");
                    sc.nextLine();
                    j++;
                }
                if (j == 3) System.out.println("Заебал тупить");
            }
            arL.get(0).add(ch / 2);
            arL.get(1).add(ch / 2);
            b.get(0).add(0.0);
            b.get(1).add(0.0);
            c.get(0).add(0.0);
            c.get(1).add(0.0);
        }
    }

    public static void clearLists(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c){
        a.get(0).clear();
        a.get(1).clear();
        b.get(0).clear();
        b.get(1).clear();
        c.get(0).clear();
        c.get(1).clear();
    }

    public static void outLists(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c, String nik, String dim, String ari){
        ArrayList<Double> temp = new ArrayList<>();
        temp = a.get(0);
        int size = temp.size();
        System.out.println("Размер списка " + size);
        if(a.size()==0) return;

        System.out.println("   " + nik + "         " + dim + "         " + ari);
        System.out.println(dim + " " + ari + " | " + nik + "  " + ari + " | " + nik + "  " + dim + " | ");

        for(int i=0; i<size; i++){
            System.out.print(a.get(0).get(i) + "   "); //заменить везде
            temp = a.get(1);
            System.out.print(temp.get(i) + "  |  ");
            temp = b.get(0);
            System.out.print(temp.get(i) + "   ");
            temp = b.get(1);
            System.out.print(temp.get(i) + "  |  ");
            temp = c.get(0);
            System.out.print(temp.get(i) + "   ");
            temp = c.get(1);
            System.out.print(temp.get(i) + "  |  ");
            temp = a.get(0);
            System.out.println();
        }
    }

    public static void addListsControl(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c, String nik, String dim, String ari, Scanner sc){

        String nameBank = "";
        ArrayList<String> nameBase = new ArrayList<>();
        nameBase.add(nik);
        nameBase.add(dim);
        nameBase.add(ari);

        boolean f = false;
        while(!f){
            System.out.println("Кто платил? Nik, Dima, Arina");
            nameBank = sc.nextLine();

            for(int i=0;i<3;i++){
                if (nameBank.equals(nameBase.get(i))) {
                    f = true;
                    break;
                }
            }
            if (!f) System.out.println("Попробуй еще раз");
        }
        System.out.println("Красава, у тебя получилось!)");

        switch (nameBank) {
            case ("Nik"): addListControl(a, b, c, sc);
            break;
            case ("Dima"): addListControl(b, a, c, sc);
            break;
            case ("Arina"): addListControl(c, a, b, sc);
            break;
        }
        sc.nextLine();


    }

    public static void main(String[] args) throws IOException {

        List<ArrayList<Double>> checksNik = new ArrayList<ArrayList<Double>>();
        List<ArrayList<Double>> checksDima = new ArrayList<ArrayList<Double>>();
        List<ArrayList<Double>> checksArina = new ArrayList<ArrayList<Double>>();
        String nameNik = "Nik";
        String nameDima = "Dima";
        String nameArina = "Arina";

        ArrayList<Double> debtNikDima = new ArrayList<>();
        ArrayList<Double> debtNikArina = new ArrayList<>();
        debtNikDima.add((double)1);
        debtNikDima.add((double)2);
        debtNikArina.add((double)3);
        debtNikArina.add((double)4);

        checksNik.add(0, debtNikDima);
        checksNik.add(1, debtNikArina);

        ArrayList<Double> debtDimaNik = new ArrayList<>();
        ArrayList<Double> debtDimaArina = new ArrayList<>();
        debtDimaNik.add((double)5);
        debtDimaNik.add((double)6);
        debtDimaArina.add((double)7);
        debtDimaArina.add((double)8);

        checksDima.add(0, debtDimaNik);
        checksDima.add(1, debtDimaArina);

        ArrayList<Double> debtArinaNik = new ArrayList<>();
        ArrayList<Double> debtArinaDima = new ArrayList<>();
        debtArinaNik.add((double)9);
        debtArinaNik.add((double)10);
        debtArinaDima.add((double)11);
        debtArinaDima.add((double)12);

        checksArina.add(0, debtArinaNik);
        checksArina.add(1, debtArinaDima);

        System.out.println(checksNik);
        System.out.println(checksDima);
        System.out.println(checksArina);

        control(checksNik, checksDima, checksArina, nameNik, nameDima, nameArina);
        
    }
}
