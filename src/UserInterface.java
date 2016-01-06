import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on
 * screen. It then refers to the "CalcEngine" to do all the real work.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class UserInterface implements ActionListener {

    protected CalcEngine calc;
    protected boolean showingAuthor;

    protected JFrame frame;
    protected JTextField display;


    /**
     * Create a user interface.
     *
     * @param engine The calculator engine.
     */
    public UserInterface(CalcEngine engine) {
        calc = engine;
        showingAuthor = true;
        makeFrame();
        frame.setVisible(true);
    }

    /**
     * Set the visibility of the interface.
     *
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Make the frame for the user interface.
     */
    private void makeFrame() {
        frame = new JFrame(calc.getTitle());

        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 5));

        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addButton(buttonPanel, "?");
        addButton(buttonPanel, "AC");

        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");
        addButton(buttonPanel, "{");
        addButton(buttonPanel, "}");

        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        addButton(buttonPanel, "∩");
        addButton(buttonPanel, "∪");

        buttonPanel.add(new JLabel(" "));
        addButton(buttonPanel, "0");
        addButton(buttonPanel, ";");
        addButton(buttonPanel, "-");
        addButton(buttonPanel, "=");

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        frame.pack();
    }

    /**
     * Add a button to the button panel.
     *
     * @param panel      The panel to receive the button.
     * @param buttonText The text for the button.
     */
    protected void addButton(Container panel, String buttonText) {
            JButton button = new JButton(buttonText);
            button.addActionListener(this);
            panel.add(button);
    }


    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     *
     * @param event The event that has occured.
     */
    public void actionPerformed(ActionEvent event) {
        calc.input(event.getActionCommand());
        redisplay();
    }

    /**
     * Update the interface display to show the current value of the
     * calculator.
     */
    protected void redisplay() {
        display.setText("" + calc.getDisplayValue());
    }

}
