package pl.edu.ug.tent.springweb.Servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import pl.edu.ug.tent.springweb.Exception.NumberException;
import pl.edu.ug.tent.springweb.Exception.OperationException;
import pl.edu.ug.tent.springweb.Exception.OperatorException;
import pl.edu.ug.tent.springweb.Models.CalculatorHistory;
import pl.edu.ug.tent.springweb.Models.CollectionBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CalculatorServlet extends HttpServlet {

    private static double doMathOperation(String operation, Double number, Double sum, List<CalculatorHistory> history)
            throws OperatorException, NumberException, OperationException {

        if (number == null) {
            throw new NumberException("No number given");
        } else {
            if (sum == null) {
                sum = 0d;
            }

            String preOperationSum = String.valueOf(sum);

            switch (operation) {
                case "+":
                    sum += number;
                    break;
                case "-":
                    sum -= number;
                    break;
                case "*":
                    sum *= number;
                    break;
                case "/":
                    if (number == 0d) {
                        throw new OperationException("You cannot divide by 0");
                    } else {
                        sum /= number;
                    }
                    break;
                default:
                    throw new OperatorException("Wrong operator usage");
            }

            history.add(new CalculatorHistory(preOperationSum, String.valueOf(number), operation, String.valueOf(sum)));

            return sum;
        }
    }

    private static double tryParse(String number) throws NumberException {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new NumberException("Wrong number format");
        }
    }

    private static void printForm(PrintWriter out, Double sum) {

        out.println("<h3>" + (sum == null ? "" : sum) + "</h3>");
        out.println("<form action='/Calculator' method='POST'>");
        out.println("<input type='hidden' name='sum' value='" + (sum == null ? "" : sum) + "' />");
        out.println("<input type='text' name='number'><br><br>");
        out.println("<button name='operation' value='+'>+</button>");
        out.println("<button name='operation' value='-'>-</button>");
        out.println("<button name='operation' value='*'>*</button>");
        out.println("<button name='operation' value='/'>/</button><br><br>");
        out.println("<button name='operation' value='='>=</button>");
        out.println("</form>");

    }

    private static void printResult(PrintWriter out, Double sum) {

        out.println("<h3>" + (sum == null ? "" : sum) + "</h3>");
        out.println("<form action='/Calculator' method='GET'>");
        out.println("<input type='submit' value='BACK' />");
        out.println("</form>");

    }

    private static void printHistory(PrintWriter out, List<CalculatorHistory> history) {

        out.println("<h2>Last operations</h2>");
        for (CalculatorHistory h : history) {
            out.println(h.getSumFromForm() + " " + h.getOperationFromForm() + " " + h.getNumberFromForm() + " = " + h.getResult() + "<br>");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);

        CollectionBean collectionsBean = ctx.getBean(CollectionBean.class);
        List<CalculatorHistory> history = collectionsBean.getCalculatorHistory();

        collectionsBean.resetHistory();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String sumFromForm = req.getParameter("sum");
        String numberFromForm = req.getParameter("number");
        String operationFromForm = req.getParameter("operation");

        Double sum = 0d;
        Double number = null;

        if (numberFromForm != null && numberFromForm.length() > 0) {
            try {
                number = tryParse(numberFromForm);
            } catch (NumberException e) {
                out.println(e.getMessage());
            }
        }

        if (sumFromForm != null && sumFromForm.length() > 0) {
            try {
                sum = tryParse(sumFromForm);
            } catch (NumberException e) {
                out.println(e.getMessage());
            }

            try {
                sum = doMathOperation(numberFromForm, number, sum, history);
            } catch (OperationException e) {
                if(!numberFromForm.equals("="))
                    out.println(e.getMessage());
            }
            catch (OperatorException | NumberException e) {
                out.println(e.getMessage());
            }
        }

        out.println("<html><body>");

        if (operationFromForm != null && operationFromForm.equals("=")) {
            printResult(out, sum);
        } else {
            printForm(out, sum);
        }

        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);

        CollectionBean collectionsBean = ctx.getBean(CollectionBean.class);
        List<CalculatorHistory> history = collectionsBean.getCalculatorHistory();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String sumFromForm = req.getParameter("sum");
        String numberFromForm = req.getParameter("number");
        String operationFromForm = req.getParameter("operation");

        Double sum = 0d;
        Double number = null;

        out.println("<html><body>");

        if (numberFromForm != null && numberFromForm.length() > 0) {
            try {
                number = tryParse(numberFromForm);
            } catch (NumberException e) {
                out.println(e.getMessage());
            }
        }

        if (sumFromForm != null && sumFromForm.length() > 0) {
            try {
                sum = tryParse(sumFromForm);
            } catch (NumberException e) {
                out.println(e.getMessage());
            }

            try {
                sum = doMathOperation(operationFromForm, number, sum, history);
            } catch (OperatorException | NumberException | OperationException e) {
                out.println(e.getMessage());
            }
        }

        printHistory(out, history);

        if (operationFromForm != null && operationFromForm.equals("=")) {
            printResult(out, sum);
        } else {
            printForm(out, sum);
        }

        out.println("</body></html>");
    }
}

