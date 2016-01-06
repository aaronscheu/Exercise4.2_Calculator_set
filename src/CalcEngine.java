import java.util.HashSet;
import java.util.Set;

/**
 * The main part of the calculator doing the calculations.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class CalcEngine {

    protected String displayValue;
    private int tmp;


    /**
     * Create a CalcEngine.
     */
    public CalcEngine() {
        tmp = 0;
        clear();
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void input(String input) {
        if (input.equals("=")) {
           displayValue = calculateSet(displayValue);
        } else if (input.equals("AC")) {
            clear();
        } else if (input.equals("?")) {
            displayValue = showInfo();
        } else if (input.matches("\\d")) {
            if (tmp != 0) {
                int last = displayValue.lastIndexOf(' ') + 1;
                displayValue = displayValue.substring(0, last);
            }
            tmp = tmp * 10 + Integer.parseInt(input);
            displayValue += String.valueOf(tmp);

        } else {
            tmp = 0;
            displayValue += " " + input + " ";
        }
    }

    public String calculateSet(String input) {
        String output = "{";
        String op = "";
        String[] split = input.split(" ");
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        boolean firstSet = true;

        for (String st: split) {
            if (st.matches("[∪∩-]"))
                op = st;
            if (st.equals("}"))
                firstSet = false;
            else if(st.matches("\\d+"))
                if (firstSet)
                    set1.add(Integer.valueOf(st));
                else
                    set2.add(Integer.valueOf(st));
        }

        if (op.equals("-"))
            set1.removeAll(set2);

        if (op.equals("∪"))
            set1.addAll(set2);

        if (op.equals("∩"))
            set1.retainAll(set2);

        for (Object o : set1) {
            output += " " + o.toString() + ";";
        }

        output = output.substring(0, output.length() -1) + " ";

        return output + "}";
    }

    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear() {
        displayValue = "";
        tmp = 0;
    }

    public String getTitle() {
        return "Java Calculator";
    }

    public String showInfo() {
        return "Author: Aaron und Till 2015";
    }

}
