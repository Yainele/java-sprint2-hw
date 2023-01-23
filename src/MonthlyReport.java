import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    HashMap<String, Integer> income = new HashMap<>();
    HashMap<String, Integer> expenditure = new HashMap<>();
    HashMap<String, ArrayList<MonthlyRow>> monthlyReports = new HashMap<>();
    int availableMonthsCount = 3;
    public void readMonthFile() {

        for (int j = 1; j <= availableMonthsCount; j++) {
            String path = "m.20210" + j + ".csv";
            List<String> content = readFileContents("./resources/" + path);
            ArrayList<MonthlyRow> monthlyReportsList  = new ArrayList<>();
            if(content.size() != 0){
                for (int i = 1; i < content.size(); i++) {

                    String line = content.get(i);
                    String[] parts = line.split(",");
                    String itemName = parts[0];
                    boolean isExpense = Boolean.parseBoolean(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int sumOfOne = Integer.parseInt(parts[3]);
                    MonthlyRow monthlyRow = new MonthlyRow(itemName, isExpense, quantity, sumOfOne);

                    monthlyReportsList.add(monthlyRow);
                }
                monthlyReports.put(getMonthByNumber(j), monthlyReportsList);
            }
        }
    }

    boolean isMonthlyReportsRead(){
        boolean isRead = true;
        for (int i = 1; i <= availableMonthsCount; i++) {
            if (monthlyReports.get(getMonthByNumber(i)) == null) {
                System.out.println("Сначала нужно считать отчет за месяц: " + getMonthByNumber(i));
                isRead = false;
            }
        }
        return isRead;
    }


    public void qualifier(String month) {
        income = new HashMap<>();
        expenditure = new HashMap<>();
        ArrayList<MonthlyRow> fileInfo = monthlyReports.get(month);

        for (MonthlyRow monthlyRow : fileInfo) {
            int amountValue = monthlyRow.quantity * monthlyRow.sumOfOne;
            if (!monthlyRow.isExpense) {
                income.put(monthlyRow.itemName, amountValue);
            }
            else {
                expenditure.put(monthlyRow.itemName, amountValue);
            }
        }
    }
    public HashMap<String,Integer> getGeneralIncome(){
        HashMap<String,Integer> generalIncome = new HashMap<>();
        for (String month : monthlyReports.keySet()) {
            int income = 0;
            ArrayList<MonthlyRow> fileInfo = monthlyReports.get(month);
            for (MonthlyRow monthlyRow : fileInfo) {
                if(!monthlyRow.isExpense){
                    income += monthlyRow.quantity * monthlyRow.sumOfOne;
                    generalIncome.put(month,income);
                }
            }
        }
        return generalIncome;
    }
    public HashMap<String,Integer> getGeneralExpenditure(){
        HashMap<String,Integer> generalExpenditure = new HashMap<>();
        for (String month : monthlyReports.keySet()) {
            int expenditure = 0;
            ArrayList<MonthlyRow> fileInfo = monthlyReports.get(month);
            for (MonthlyRow monthlyRow : fileInfo) {
                if(monthlyRow.isExpense){
                    expenditure += monthlyRow.quantity * monthlyRow.sumOfOne;
                    generalExpenditure.put(month,expenditure);
                }
            }
        }
        return generalExpenditure;
    }

    public String getTopValue(HashMap<String, Integer> fileInfo) {
        String maxTitle = null;
        for (String title : fileInfo.keySet()) {
            if (maxTitle == null) {
                maxTitle = title;
                continue;
            }
            if (fileInfo.get(maxTitle) < fileInfo.get(title)) {
                maxTitle = title;
            }
        }
        return maxTitle;
    }

    void printMonthInfo() {
        for (String month : monthlyReports.keySet()) {

            System.out.println("Рассматриваемый месяц: " + month);

            qualifier(month);

            String maxIncomeTitle = getTopValue(income);
            Integer maxIncomeValue = income.get(maxIncomeTitle);

            String maxExpenditureTitle = getTopValue(expenditure);
            Integer maxExpenditureValue = expenditure.get(maxExpenditureTitle);

            System.out.println("Самый прибыльный товар: " + maxIncomeTitle + ". На сумму: " + maxIncomeValue);
            System.out.println("Самая большая трата: " + maxExpenditureTitle + ". На сумму: " + maxExpenditureValue + "\n");
        }
    }

    public String getMonthByNumber(int monthNumber) {
        if (monthNumber == 1) {
            return "Январь";
        } else if (monthNumber == 2) {
            return "Февраль";
        } else if (monthNumber == 3) {
            return "Март";
        } else {
            return "Недоступный месяц";
        }
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
