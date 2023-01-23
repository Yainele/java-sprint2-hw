import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {
    HashMap<String, Integer> income = new HashMap<>();
    HashMap<String, Integer> expenditure = new HashMap<>();
    HashMap<String, Integer> incomePerMonth = new HashMap<>();
    int year = 2021;
    public void readYearFile() {
        String path = "resources/y." + year + ".csv";
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++) {

            String line = content.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            YearlyRow yearlyRow = new YearlyRow(month, amount, isExpense);

            if(!yearlyRow.isExpense){
                income.put(getMonthByNumber(yearlyRow.month), yearlyRow.amount);
            }
            else {
                expenditure.put(getMonthByNumber(yearlyRow.month), yearlyRow.amount);
            }
        }
    }
    boolean isYearlyReportsRead(){
        boolean isRead = true;
        if(income.isEmpty() || expenditure.isEmpty()){
            System.out.println("Сначала нужно считать отчет за год: " + year);
            isRead = false;
        }
        return isRead;
    }

    public void getIncomePerMonth(){
        for (String month : income.keySet()) {
                    int incomeSum = income.get(month) - expenditure.get(month);
                    incomePerMonth.put(month,incomeSum);
        }
    }

    public Integer getAvgValue(HashMap<String,Integer> fileInfo){
        int avgValue = 0;
        for(Integer amount : fileInfo.values()){
            avgValue += amount;
        }
        avgValue /= fileInfo.size();
        return avgValue;
    }
    void printYearInfo() {

        System.out.println("Рассматриваемый год: " + year);
        System.out.println("Прибыль по каждому месяцу - " + "\n");

        getIncomePerMonth();
        for (String month : incomePerMonth.keySet()) {
            System.out.println("Рассматриваемый месяц: " + month);
            System.out.println("Прибыль: " + incomePerMonth.get(month) + "\n");
        }

        System.out.println("Средний расход за все месяцы в году: " + getAvgValue(expenditure));
        System.out.println("Средний доход за все месяцы в году: " + getAvgValue(income) + "\n");
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
