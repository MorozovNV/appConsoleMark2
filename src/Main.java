import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void control(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c, String nik, String dim, String ari) {
        Scanner sc = new Scanner(System.in);
        int langForHelp = 0;
        while(true){
            outHelp(langForHelp);
            var g = sc.nextLine();
            if(g.equals("e")) break;

            switch (g) {
                case ("h"):
                    break;
                case ("помогите"):
                    break;
                case ("a"):
                    addListsControl(a, b, c, nik, dim, ari, sc);
                    break;
                case ("s"):
                    outLists(a, b, c, nik, dim, ari);
                    break;
                case("c"): clearLists(a, b, c);
                    break;
                case("sum"): sumControl(a,b,c,nik,dim,ari);
                    break;
                case("rl"): langForHelp = 1;
                    break;
                case("el"): langForHelp = 0;
                    break;
                case("b"): balance(a,b,c);
                outLists(a, b, c, nik, dim, ari);
                    break;
                case("save"): save(a,b,c,sc);
                    break;
                case("load"): load(a,b,c,sc);
                    break;
                default:
                    System.out.println("Нет такой команды, по крайней мере пока. Читай че написано:");
                    break;
            }
        }
    }

    public static void outHelp(int lang){
        if (lang==0) System.out.println("Список команд: h-help, a-add, s-show, c-clear, sum-summing, b-balance, save-saving, load-loading, rl-russian commands, e-esc");
        if (lang==1) System.out.println("Список команд: помогите-помочь, a-внести в общаг, s-фанеру к осмотру, c-убрать с глаз, sum-сложить2+2, b-подвести итоги, save-добавить в сохраненки, load-выгрузить из сохраненок, l-перейти на англосакский, e-сьебать");
    }

    public static void addListControl(List<ArrayList<Double>> arL, List<ArrayList<Double>> b,List<ArrayList<Double>> c, Scanner sc) {
        boolean f = true;

        while (true) {

            if (f) System.out.println("Введите сумму чека: ");
            else System.out.println("Введите еще один чек или команду d-done");

            var g = sc.nextLine();
            if(g.equals("d")) break;

            int j = 0;
            double ch = 0;
            while (j < 3) {
                try {
                    ch = Double.parseDouble(g);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Введите число типа Double");
                    g = sc.nextLine();
                    j++;
                }
                if (j == 3) System.out.println("Заебал тупить");
            }
            f = false;
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

    }

    public static void sumControl(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c, String nik, String dim, String ari){
        outLists(a,b,c,nik, dim, ari);
        System.out.println("_________________Сумма_________________");
        if (a.get(0).size()==0) {
            System.out.println("Че считать то? Список пуст, епта");
            return;
        }

        System.out.print((Double) a.get(0).stream().mapToDouble(Double::doubleValue).sum() + "   ");
        System.out.print((Double) a.get(1).stream().mapToDouble(Double::doubleValue).sum() + "  |  ");
        System.out.print((Double) b.get(0).stream().mapToDouble(Double::doubleValue).sum() + "   ");
        System.out.print((Double) b.get(1).stream().mapToDouble(Double::doubleValue).sum() + "  |  ");
        System.out.print((Double) c.get(0).stream().mapToDouble(Double::doubleValue).sum() + "   ");
        System.out.print((Double) c.get(1).stream().mapToDouble(Double::doubleValue).sum() + "  |  ");
        System.out.println();
//System.out.print(c.get(1).stream().collect(Collectors.summingDouble(Double::doubleValue)) + "   "); было так, но айдиа предложила заменить
    }

    public static void balance(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c){
        double a0 = a.get(0).stream().mapToDouble(Double::doubleValue).sum();
        double b0 = b.get(0).stream().mapToDouble(Double::doubleValue).sum();
        if(a0>b0) {
            a0 -= b0;
            b0 = 0;
        } else {
            b0 -= a0;
            a0 = 0;
        }

        double a1 = a.get(1).stream().mapToDouble(Double::doubleValue).sum();
        double c0 = c.get(0).stream().mapToDouble(Double::doubleValue).sum();
        if(a1>c0) {
            a1 -= c0;
            c0 = 0;
        } else {
            c0 -= a1;
            a1 = 0;
        }

        double b1 = b.get(1).stream().mapToDouble(Double::doubleValue).sum();
        double c1 = c.get(1).stream().mapToDouble(Double::doubleValue).sum();
        if(b1>c1) {
            b1 -= c1;
            c1 = 0;
        } else {
            c1-= b1;
            b1 = 0;
        }
        System.out.println(a0 + " " + a1+ " | " +b0 + " " + b1 + " | " + c0 + " " + c1);
        clearLists(a,b,c);
        a.get(0).add(a0);
        a.get(1).add(a1);
        b.get(0).add(b0);
        b.get(1).add(b1);
        c.get(0).add(c0);
        c.get(1).add(c1);
    }

    public static void save(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c, Scanner sc) {
        PrintWriter pw = null;

        System.out.println("Введите имя сохранения: ");
        var fileName = sc.nextLine();
        File fileOut = new File(fileName+".txt");
        if (fileOut.exists()) {
            System.out.println("Фаил с таким именем уже существует, перезаписать? y/n ");
            if(sc.nextLine().equals("n")) return;
        }


        try {
            if (!fileOut.exists()) fileOut.createNewFile();
        } catch (IOException e) {
            System.out.println("Невозможно создать фаил");
            return;
        }

        try {
            pw = new PrintWriter(fileOut);
        } catch (FileNotFoundException e){
            System.out.println("Фаил не найден, втф");
            return;
        }
        for(int i=0; i<a.get(0).size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                pw.print(a.get(i).get(j) + " ");
            }
        }

        pw.close();

    }

    public static void load(List<ArrayList<Double>> a, List<ArrayList<Double>> b, List<ArrayList<Double>> c, Scanner sc) {
        System.out.println("Введите имя фаила");

        while (true) {
            var fileName = sc.nextLine();
            if(fileName.equals("d")) break;

            try {
                Scanner scFile = new Scanner(new File(fileName + ".txt"));
                System.out.println("Фаил найден, все четко.");
                String a1 = scFile.nextLine();
                System.out.println(a1);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Данный фаил не найден. Попробуйте еще раз или завершить командой d-done");
            }
        }

    }

    public static void main(String[] args) {

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
