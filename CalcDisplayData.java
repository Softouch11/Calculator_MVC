package Calculatrice.model;

public class CalcDisplayData {

    String currentTotal;
    String currentInputString;

    public void setComputationText(String computationText) {
        currentInputString = computationText;
    }

    public void setCurrentTotal(String newTotal) {
        currentTotal = newTotal;
    }

    public String getCurrentTotal() {
        return currentTotal;
    }

    public String getCurrentInputString() {
        return currentInputString;
    }

}
