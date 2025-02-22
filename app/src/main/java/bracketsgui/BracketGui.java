package bracketsgui;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.SwingConstants;

public class BracketGui implements ActionListener {
    private JTextArea inputText;
    JCheckBox[] checkBoxes;
    JRadioButton[] radioBoxes;
    private JButton submitBtn, copyBtn;
    private JTextArea outputText;


    public BracketGui() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();

        frame.getContentPane().setPreferredSize(new Dimension(400,400));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        mainPanel.setLayout(new GridLayout(7, 1, 0, 10));

        textInput(mainPanel);
        bracketCheckbox(mainPanel);
        bracketRadio(mainPanel);
        setUpButtons(mainPanel);
        setUpTextOutput(mainPanel);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Brackets");
        frame.pack();
        frame.setVisible(true);
    }

    private void textInput(JPanel mainPanel) {
        this.inputText = new JTextArea(4, 10);
        JScrollPane scroll = new JScrollPane(inputText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        inputText.setLineWrap(true);
        inputText.setWrapStyleWord(true);
        mainPanel.add(scroll);
    }
    
    private void bracketCheckbox(JPanel mainPanel) {
        String[] bracketTypes = {"{", "}", "[", "]", "(", ")"};
        JCheckBox[] bracketsBoxes = new JCheckBox[bracketTypes.length];

        JPanel parentContainer = new JPanel();
        JPanel bracketsContainer = new JPanel();
        parentContainer.setLayout(new GridLayout(2, 1));
        bracketsContainer.setLayout(new GridLayout(1, 6));
        parentContainer.add(new JLabel("Convert From", SwingConstants.CENTER));
        parentContainer.add(bracketsContainer);
        mainPanel.add(parentContainer);
        for (int i = 0; i < bracketsBoxes.length; i ++) {
            bracketsBoxes[i] = new JCheckBox(bracketTypes[i]);
            bracketsBoxes[i].setHorizontalAlignment(JLabel.CENTER);
            bracketsContainer.add(bracketsBoxes[i]);
        }

        this.checkBoxes = bracketsBoxes;
    }

    private void bracketRadio(JPanel mainPanel) {
        String[] bracketTypes = {"{ }", "[ ]", "( )"};
        ButtonGroup radioGroup = new ButtonGroup();
        JRadioButton[] radioBoxes = new JRadioButton[bracketTypes.length];

        JPanel parentContainer = new JPanel();
        JPanel bracketsContainer = new JPanel();
        parentContainer.setLayout(new GridLayout(2, 1));
        bracketsContainer.setLayout(new GridLayout(1, 3));
        parentContainer.add(new JLabel("Convert To", SwingConstants.CENTER));
        parentContainer.add(bracketsContainer);
        mainPanel.add(parentContainer, BorderLayout.CENTER);
        for (int i = 0; i < radioBoxes.length; i ++) {
            radioBoxes[i] = new JRadioButton(bracketTypes[i]);
            radioBoxes[i].setHorizontalAlignment(JLabel.CENTER);
            radioGroup.add(radioBoxes[i]);
            bracketsContainer.add(radioBoxes[i]);
        }

        this.radioBoxes = radioBoxes;
    }

    private void setUpButtons(JPanel mainPanel) {
        this.submitBtn = new JButton("Convert");
        this.copyBtn = new JButton("Copy to Clipboard");
        submitBtn.addActionListener(this);
        copyBtn.addActionListener(this);

        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new GridLayout(1, 2));
        buttonsContainer.add(submitBtn);
        buttonsContainer.add(copyBtn);

        mainPanel.add(buttonsContainer);
    }

    private void setUpTextOutput(JPanel mainPanel) {
        this.outputText = new JTextArea(4, 10);
        JScrollPane scroll = new JScrollPane(outputText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        outputText.setLineWrap(true);
        outputText.setWrapStyleWord(true);
        outputText.setEditable(false);

        mainPanel.add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String input = inputText.getText();
            ArrayList<String> selectedBrackets = new ArrayList<>();
            String replacementBracket = null;
            // Getting checkbox selections
            for (JCheckBox checkBox: this.checkBoxes) {
                if (checkBox.isSelected()) {
                    selectedBrackets.add(checkBox.getText());
                }
            }
            // Getting radio selection
            for (JRadioButton radio: this.radioBoxes) {
                if (radio.isSelected()) {
                    replacementBracket = radio.getText();
                    break;
                }
            }
            if (replacementBracket == null) {
                return;
            }
            outputText.setText(StringModify.replaceBrackets(input, selectedBrackets, replacementBracket));
            
        } else if (e.getSource() == copyBtn) {
            StringSelection selectOutput = new StringSelection(this.outputText.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selectOutput, null);
        }

    }
}
