package bracketsgui;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BracketGui {
    public BracketGui() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();

        frame.getContentPane().setPreferredSize(new Dimension(400,400));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        mainPanel.setLayout(new GridLayout(5, 1, 0, 10));

        textInput(mainPanel);
        JCheckBox[] checkBoxes = bracketCheckbox(mainPanel);
        JRadioButton[] radioBoxes = bracketRadio(mainPanel);
        setUpButtons(mainPanel);
        setUpTextOutput(mainPanel);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Brackets");
        frame.pack();
        frame.setVisible(true);
    }

    private void textInput(JPanel mainPanel) {
        JTextArea inputText = new JTextArea(4, 10);
        JScrollPane scroll = new JScrollPane(inputText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        inputText.setLineWrap(true);
        inputText.setWrapStyleWord(true);
        mainPanel.add(scroll);
    }
    
    private JCheckBox[] bracketCheckbox(JPanel mainPanel) {
        String[] bracketTypes = {"{", "}", "[", "]", "(", ")"};
        JCheckBox[] bracketsBoxes = new JCheckBox[bracketTypes.length];

        JPanel bracketsContainer = new JPanel();
        bracketsContainer.setLayout(new GridLayout(1, 6));
        mainPanel.add(bracketsContainer);
        for (int i = 0; i < bracketsBoxes.length; i ++) {
            bracketsBoxes[i] = new JCheckBox(bracketTypes[i]);
            bracketsBoxes[i].setHorizontalAlignment(JLabel.CENTER);
            bracketsContainer.add(bracketsBoxes[i]);
        }

        return bracketsBoxes;
        
    }

    private JRadioButton[] bracketRadio(JPanel mainPanel) {
        String[] bracketTypes = {"{ }", "[ ]", "( )"};
        ButtonGroup radioGroup = new ButtonGroup();
        JRadioButton[] radioBoxes = new JRadioButton[bracketTypes.length];

        JPanel bracketsContainer = new JPanel();
        bracketsContainer.setLayout(new GridLayout(1, 3));
        mainPanel.add(bracketsContainer, BorderLayout.CENTER);
        for (int i = 0; i < radioBoxes.length; i ++) {
            radioBoxes[i] = new JRadioButton(bracketTypes[i]);
            radioBoxes[i].setHorizontalAlignment(JLabel.CENTER);
            radioGroup.add(radioBoxes[i]);
            bracketsContainer.add(radioBoxes[i]);
        }
        return radioBoxes;
    }

    private void setUpButtons(JPanel mainPanel) {
        JButton submitBtn = new JButton("Convert");
        JButton copyButton = new JButton("Copy to Clipboard");
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new GridLayout(1, 2));
        buttonsContainer.add(submitBtn);
        buttonsContainer.add(copyButton);

        mainPanel.add(buttonsContainer);
    }

    private void setUpTextOutput(JPanel mainPanel) {
        JTextArea outputText = new JTextArea(4, 10);
        JScrollPane scroll = new JScrollPane(outputText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        outputText.setLineWrap(true);
        outputText.setWrapStyleWord(true);
        outputText.setEditable(false);
        mainPanel.add(scroll);
    }
}
