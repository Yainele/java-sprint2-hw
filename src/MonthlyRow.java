public class MonthlyRow {
    public String itemName; //название товара
    public boolean isExpense; //Обозначает, является ли запись тратой (TRUE) или доходом (FALSE)
    public int quantity; //количество закупленного или проданного товара
    public int sumOfOne;//стоимость одной единицы товара. Целое число.
    public MonthlyRow(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
