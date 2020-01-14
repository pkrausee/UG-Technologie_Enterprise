package pl.edu.ug.tent.springweb.Models;

public class CalculatorHistory {

    private String sumFromForm;
    private String numberFromForm;
    private String operationFromForm;
    private String result;

    public CalculatorHistory() { };

    public CalculatorHistory(String sumFromForm, String numberFromForm, String operationFromForm, String result) {
        this.sumFromForm = sumFromForm;
        this.numberFromForm = numberFromForm;
        this.operationFromForm = operationFromForm;
        this.result = result;
    }

    public String getSumFromForm() {
        return sumFromForm;
    }

    public void setSumFromForm(String sumFromForm) {
        this.sumFromForm = sumFromForm;
    }

    public String getNumberFromForm() {
        return numberFromForm;
    }

    public void setNumberFromForm(String numberFromForm) {
        this.numberFromForm = numberFromForm;
    }

    public String getOperationFromForm() {
        return operationFromForm;
    }

    public void setOperationFromForm(String operationFromForm) {
        this.operationFromForm = operationFromForm;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
