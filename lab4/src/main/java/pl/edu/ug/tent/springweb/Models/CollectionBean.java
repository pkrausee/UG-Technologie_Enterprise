package pl.edu.ug.tent.springweb.Models;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CollectionBean {

    @Autowired
    private List<CalculatorHistory> calculatorHistory;

    public List<CalculatorHistory> getCalculatorHistory() {
        return calculatorHistory;
    }

    public void setCalculatorHistory(List<CalculatorHistory> calculatorHistory) {
        this.calculatorHistory = calculatorHistory;
    }

    public void resetHistory(){
        this.calculatorHistory = new ArrayList<>();
    }
}
