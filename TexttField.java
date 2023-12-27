import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TexttField {
    JFrame frame = new JFrame("TextFieldOnly");
    JPanel panel = new JPanel(null);
    JLabel label = new JLabel("Name : ");
    JLabel label2 = new JLabel("Age : ");
    JLabel label3 = new JLabel("Your country : ");
    JTextField textField = new JTextField(15);
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JButton button = new JButton("Submit");
    public void setTextField(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the frame on the screen
//        frame.setLocationRelativeTo(null);
        frame.setLocation(540, 200);
        frame.setPreferredSize(new Dimension(500 , 400));

        // setting the font , font-style and font-size of text of labels
        label.setFont(new Font("Arial" , Font.BOLD , 12));
        label2.setFont(new Font("Arial" , Font.BOLD , 12));
        label3.setFont(new Font("Arial" , Font.BOLD , 12));

        // setting the font , font-style and font-size of text of textFields
        textField.setFont(new Font("ArialBlack" , Font.TRUETYPE_FONT , 14));
        textField2.setFont(new Font("ArialBlack" , Font.TRUETYPE_FONT , 14));
        textField3.setFont(new Font("ArialBlack" , Font.TRUETYPE_FONT , 14));
        textField.setPreferredSize(new Dimension(100 , 50));

        // settig the positions of label and textFields components
        label.setBounds(115 , 70 , 100 , 40);
        label2.setBounds(125 , 120 , 100 , 40);
        label3.setBounds(75 , 170 , 100 , 40);
        textField.setBounds(200 ,70, 200, 40);
        textField2.setBounds(200 , 120 , 200 , 40);
        textField3.setBounds(200 , 170 , 200 , 40);

        // customizing the indentation of textFields
        Insets margins = new Insets(5 , 5, 5, 5);
        textField.setMargin(margins);
        textField2.setMargin(margins);
        textField3.setMargin(margins);

        button.setBackground(Color.BLACK);
        button.setForeground(Color.PINK);
        button.setBounds(180 , 270 , 100 , 40);

        label.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);

        textField.setBackground(Color.PINK);
        textField.setForeground(Color.BLACK);
        textField2.setBackground(Color.PINK);
        textField2.setForeground(Color.BLACK);
        textField3.setBackground(Color.PINK);
        textField3.setForeground(Color.BLACK);

        panel.setBackground(Color.BLACK);

        JPanel newPanel = new JPanel(null);
        panel.setVisible(true);
        newPanel.setVisible(false);

        button.addActionListener(new ActionListener() {
        String name = textField.getText() ;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equalsIgnoreCase("") && !textField2.getText().equalsIgnoreCase("")
                        && !textField3.getText().equalsIgnoreCase("")) {
                    setFields();
                    panel.setVisible(false);
                    newPanel.setBackground(Color.BLACK);
                    JLabel text = new JLabel("Thank you for filling the survey!!" );
                    text.setFont(new Font("Arial", Font.BOLD, 12));
                    text.setForeground(Color.PINK);
                    text.setBounds(150, 150, 260, 60);
                    newPanel.add(text);
                    panel.revalidate(); // Ensure the layout is updated
                    newPanel.setVisible(true);
                    frame.add(newPanel);
                }else {

                }
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(button);
        frame.pack();
        frame.add(panel);
//        frame.add(newPanel);
        frame.setVisible(true);

    }

    public void setFields(){
        textField.setText("");
        textField2.setText("");
        textField3.setText("");
    }

    public static void main(String[] args) {
        TexttField texttField = new TexttField();
        texttField.setTextField();
    }
}

//first successful GUI implementation , though too simplee but FIRST...