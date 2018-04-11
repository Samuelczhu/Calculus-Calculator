import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Integral {

    @FXML private TextField functionInput;
    @FXML private TextField aInput;
    @FXML private TextField bInput;
    @FXML private TextField nInput;
    @FXML private Label answerLabel;
    @FXML private TextField answerText;

    public void Clear() {
        functionInput.clear();
        aInput.clear();
        bInput.clear();
        nInput.clear();
        answerLabel.setText("");
        answerText.clear();
    }

    public void Calculate() {
        try {
            String function = functionInput.getText();
            double a = eval(aInput.getText());
            double b = eval(bInput.getText());
            int n = Integer.valueOf(nInput.getText());

            double answer = integral(function, a, b, n);
            answerLabel.setText("Integral f(x) from " + a + " to " + b + " = ");
            answerText.setText(String.valueOf(answer));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Warning");
            alert.setHeaderText("Please enter appropriate inputs!");
            alert.setContentText("Oops! The inputs are too extreme for me...");
            alert.showAndWait();
        }
    }

    private double integral(String function, double a, double b, int n) {
        double width = (b-a)/n;
        // finding the Riemann sum
        double sum = 0.0;
        for (int i=0;i<n;i++) {
            sum += eval(subin(function,a+i*width)) * width;
        }
        return sum;
    }

    private String subin(String function, double x) {
        StringBuilder fun = new StringBuilder(function);
        for (int i=0;i<fun.length();i++) {
            if (fun.charAt(i)=='x') {
                fun.deleteCharAt(i);
                fun.insert(i,x);
            }
        }
        return fun.toString();
    }

    // modified code from github: https://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
    // enter angle in radian mode
    private static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(x);
                    else if (func.equals("cos")) x = Math.cos(x);
                    else if (func.equals("tan")) x = Math.tan(x);
                    else if (func.equals("sec")) x = 1/Math.cos(x);
                    else if (func.equals("csc")) x = 1/Math.sin(x);
                    else if (func.equals("cot")) x = 1/Math.tan(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }


    //switch scene
    public void toMain() {
        Main.window.setScene(Main.mainScene);
    }
    public void toDerivative() {
        Main.window.setScene(Main.derivativeScene);
    }
    public void toIntegral() {
        Main.window.setScene(Main.integralScene);
    }

    //showing info
    public void showInfo() throws Exception {
        About about = new About();
        about.display();
    }
}
