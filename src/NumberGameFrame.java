import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameFrame extends JFrame implements ActionListener {
    private int previousGuessCount;
    private int remainingGuessCount;
    private final JLabel outputLabel;
    private final RoundedButton guessButton;
    private final JTextField inputGuessTextField;
    private int randomNumber;
    private final JLabel previousGuessLabel;
    private final JLabel remainingGuessLabel;

    NumberGameFrame() {
        previousGuessCount = 0;
        remainingGuessCount = 10;

        generateRandomNumber();

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(123, 49, 83));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Number Guessing Game!");
        headingLabel.setFont(new Font("sans-serif", Font.BOLD, 35));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(headingLabel);

        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel firstLabel = new JLabel("Try and guess a random number between 1 and 100.");
        firstLabel.setFont(new Font("sans-serif", Font.PLAIN, 16));
        firstLabel.setForeground(Color.WHITE);
        firstLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(firstLabel);

        topPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel secondLabel = new JLabel("You have 10 attempts to guess the right number.");
        secondLabel.setFont(new Font("sans-serif", Font.PLAIN, 16));
        secondLabel.setForeground(Color.WHITE);
        secondLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(secondLabel);

        topPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setBackground(new Color(16, 173, 181));

        footerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel guessLabelHeading = new JLabel("Guess a number");
        guessLabelHeading.setFont(new Font("sans-serif", Font.BOLD, 30));
        guessLabelHeading.setForeground(Color.WHITE);
        guessLabelHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(guessLabelHeading);

        footerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        inputGuessTextField = new JTextField();
        inputGuessTextField.setPreferredSize(new Dimension(200, 50));
        inputGuessTextField.setFont(new Font("Tahoma", Font.PLAIN, 30));
        inputGuessTextField.setMaximumSize(new Dimension(300, 60));
        inputGuessTextField.setHorizontalAlignment(JTextField.CENTER);
        inputGuessTextField.setBorder(BorderFactory.createLineBorder(new Color(16, 115, 123), 4));
        footerPanel.add(inputGuessTextField);

        footerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        guessButton = new RoundedButton("Submit Guess");
        guessButton.setPreferredSize(new Dimension(160, 40));
        guessButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        guessButton.setMaximumSize(new Dimension(160, 40));
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessButton.addActionListener(this);
        footerPanel.add(guessButton);

        footerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        previousGuessLabel = new JLabel("Previous Guesses: " + previousGuessCount);
        previousGuessLabel.setFont(new Font("sans-serif", Font.PLAIN, 18));
        previousGuessLabel.setForeground(Color.WHITE);
        previousGuessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(previousGuessLabel);

        footerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        remainingGuessLabel = new JLabel("Remaining Guesses: " + remainingGuessCount);
        remainingGuessLabel.setFont(new Font("sans-serif", Font.PLAIN, 18));
        remainingGuessLabel.setForeground(Color.WHITE);
        remainingGuessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(remainingGuessLabel);

        footerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.setBackground(new Color(123, 49, 83));

        outputLabel = new JLabel("");
        outputLabel.setFont(new Font("sans-serif", Font.BOLD, 30));
        outputLabel.setForeground(Color.WHITE);
        outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputPanel.add(outputLabel);

        footerPanel.add(outputPanel);

        add(topPanel, BorderLayout.NORTH);
        add(footerPanel, BorderLayout.CENTER);

        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            String inputText = inputGuessTextField.getText();

            if (inputText != null && !inputText.isEmpty()) {
                try {
                    int guessedNumber = Integer.parseInt(inputText);

                    if (guessedNumber < randomNumber) {
                        outputLabel.setText("Too low! Try Again!");
                    } else if (guessedNumber > randomNumber) {
                        outputLabel.setText("Too high! Try Again!");
                    } else {
                        outputLabel.setText("Correct! You guessed the number.");
                        guessButton.setEnabled(false); // Disable the button after a correct guess
                    }

                    previousGuessCount++;
                    remainingGuessCount--;

                    updateGuessLabels();

                    if (remainingGuessCount == 0 && guessedNumber != randomNumber) {
                        outputLabel.setText("Out of guesses! The number was " + randomNumber);
                        guessButton.setEnabled(false);
                    }
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid integer
                    JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Handle the case where the input is empty
                JOptionPane.showMessageDialog(this, "Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void updateGuessLabels() {
        inputGuessTextField.setText("");
        inputGuessTextField.requestFocusInWindow();

        // Update the labels showing previous and remaining guesses
        previousGuessLabel.setText("Previous Guesses: " + previousGuessCount);
        remainingGuessLabel.setText("Remaining Guesses: " + remainingGuessCount);
    }

}
