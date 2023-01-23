import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Checker checker = new Checker(monthlyReport, yearlyReport);

        while (true) {
            printMenu();
            int userInput = readInt(scanner);
            if (userInput == 1) {
                monthlyReport.readMonthFile();
            } else if (userInput == 2) {
                yearlyReport.readYearFile();
            } else if (userInput == 3) {
                if(monthlyReport.isMonthlyReportsRead() && yearlyReport.isYearlyReportsRead()){
                    if(checker.check()){
                        System.out.println("Операция успешно завершена!");
                    }
                }
            } else if (userInput == 4) {
                if(monthlyReport.isMonthlyReportsRead()){
                    monthlyReport.printMonthInfo();
                }
            } else if (userInput == 5) {
                if(yearlyReport.isYearlyReportsRead()){
                    yearlyReport.printYearInfo();
                }
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.\n");
            }
        }
        System.out.println("Программа завершена");
    }

    private static int readInt(Scanner s) {
        while (true) {
            String line = s.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.err.println("Ошибка! Введите целое число");
            }
        }
    }
    public static void printMenu() {
        System.out.println("1. Считать все месячные отчёты \n" +
                "2. Считать годовой отчёт \n" +
                "3. Сверить отчёты \n" +
                "4. Вывести информацию о всех месячных отчётах \n" +
                "5. Вывести информацию о годовом отчёте \n" +
                "0. Завершить программу");
    }
}

