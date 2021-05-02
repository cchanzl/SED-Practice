package Q4;

// Answer for 4A
// It clearly segregates different modules responsible for rendering the display, computational logic and
// setting up the structure of the application. This allows for multiple teams to work on different parts
// of the application, without interfering with the other components. Changes can also be made independently
// of the other components, making for faster development.

// Also, Testing can be conducted independently of each component and debugging is more efficient as you do not have
// to test the entire system when you just want to test a specific component, say the model logic but not the
// GUI display.

import javax.swing.*;

public class SimpleStats implements Updatable {

  JTextField currentMax = new JTextField(11);
  JTextField currentMean = new JTextField(11);
  Model calc = new Model();

  // view
  private void display() {

    JFrame frame = new JFrame("Simple Stats");
    frame.setSize(250, 350);
    JPanel panel = new JPanel();

    panel.add(new JLabel("Max: value "));
    panel.add(currentMax);
    panel.add(new JLabel("Mean: value "));
    panel.add(currentMean);

    calc.addObserver(this);
    addNumberButtons(calc, panel);

    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  @Override
  public void update(int max, double mean){
    currentMax.setText(String.valueOf(max));
    currentMean.setText(String.valueOf(mean));
  }

  // controller
  private void addNumberButtons(Model calc, JPanel panel){
    for (int i = 1; i <= 12; i++) {
      final int n = i;
      JButton button = new JButton(String.valueOf(i));
      button.addActionListener(e -> calc.action(n));
      panel.add(button);
    }
  }


  public static void main(String[] args) {
    new SimpleStats().display();
  }

}
