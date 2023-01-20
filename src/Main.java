import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearlyReportManager yearlyReportManager = new YearlyReportManager();
        MonthlyReportManager monthlyReportManager = new MonthlyReportManager();
        Checker checker = new Checker(monthlyReportManager,yearlyReportManager);

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReportManager.readMonthFile();
            } else if (userInput == 2) {
                yearlyReportManager.readYearFile();
            } else if (userInput == 3) {
                if(monthlyReportManager.isMonthlyReportsRead() && yearlyReportManager.isYearlyReportsRead()){
                    if(checker.check()){
                        System.out.println("Операция успешно завершена!");
                    }
                }
            } else if (userInput == 4) {
                if(monthlyReportManager.isMonthlyReportsRead()){
                    monthlyReportManager.printMonthInfo();
                }
            } else if (userInput == 5) {
                if(yearlyReportManager.isYearlyReportsRead()){
                    yearlyReportManager.printYearInfo();
                }
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
        System.out.println("Программа завершена");
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

