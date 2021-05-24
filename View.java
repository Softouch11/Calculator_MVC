package Calculatrice.View;

import Calculatrice.model.CalcDisplayData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;


public class View extends JFrame implements Observer  {

    private JTextField totalDisplay;
    private JTextField computationDisplay;
    private ArrayList <JButton> buttonList;

    public View() {

        super("Calculate MVC");
        setWindowPreferences();

        totalDisplay = new JTextField();
        configureTotalDisplay();

        computationDisplay = new JTextField();
        configureComputationDisplay();

        createButtonList();
        implementGridLayout();
    }

    private void configureComputationDisplay() {
        computationDisplay.setPreferredSize(new Dimension(600, 40));
        computationDisplay.setColumns(40);
        computationDisplay.setEditable(false);
        computationDisplay.setText("");
        computationDisplay.setHorizontalAlignment(JTextField.CENTER);
    }

    private void implementGridLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        addComponentsToGrid(gbc);
    }

    private void addComponentsToGrid(GridBagConstraints gbc) {
        //text display//
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 4;
        add (totalDisplay, gbc);

        //computation display//
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        add(computationDisplay, gbc);

        //creating iterator to iterate through all buttons
        Iterator <JButton> buttonRetriever = buttonList.iterator();

        //first row//
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        gbc.fill = GridBagConstraints.BOTH;;
        gbc.weightx = .1;
        gbc.weighty = .1;

        add(buttonRetriever.next(), gbc);

        gbc.gridx = 1;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 2;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 3;
        add(buttonRetriever.next(), gbc);

        //second row//

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 1;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 2;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 3;
        add(buttonRetriever.next(), gbc);

        //third row//

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 1;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 2;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 3;
        add(buttonRetriever.next(), gbc);

        //fourth row//

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 1;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 2;
        add(buttonRetriever.next(), gbc);

        gbc.gridx = 3;
        add(buttonRetriever.next(), gbc);

        //last row, equals button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth=4;
        add(buttonRetriever.next(), gbc);
    }

    private void createButtonList() {
        buttonList = new ArrayList<JButton> ();
        buttonList.add(new JButton("1"));
        buttonList.add(new JButton("2"));
        buttonList.add(new JButton("3"));
        buttonList.add(new JButton("+"));
        buttonList.add(new JButton("4"));
        buttonList.add(new JButton("5"));
        buttonList.add(new JButton("6"));
        buttonList.add(new JButton("-"));
        buttonList.add(new JButton("7"));
        buttonList.add(new JButton("8"));
        buttonList.add(new JButton("9"));
        buttonList.add(new JButton("/"));
        buttonList.add(new JButton("0"));
        buttonList.add(new JButton("."));
        buttonList.add(new JButton("C"));
        buttonList.add(new JButton("*"));
        buttonList.add(new JButton("="));
    }

    private void configureTotalDisplay() {
        totalDisplay.setPreferredSize(new Dimension(600, 40));
        totalDisplay.setColumns(40);
        totalDisplay.setEditable(false);
        totalDisplay.setText("0");
        totalDisplay.setHorizontalAlignment(JTextField.CENTER);

        Font newFont = new Font("SansSerif", Font.PLAIN, 40);
        totalDisplay.setFont(newFont);
    }

    private void setWindowPreferences() {
        setVisible(true);
        setSize(new Dimension(267, 309));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(420, 220);
    }

    public void setCalcButtonListener(ActionListener actionListener) {
        for (JButton button : buttonList){
            button.addActionListener(actionListener);
        }
    }

    public void setComputationDisplayText(String string){
        computationDisplay.setText(string);
    }

    public String getComputationDisplayText(){
        return computationDisplay.getText();
    }

    public void setTotalDisplayText(String string) {
        totalDisplay.setText(string);

    }

    @Override
    public void update(Observable o, Object arg) {
        CalcDisplayData updateObject = (CalcDisplayData) arg;

        if (updateObject.getCurrentInputString()!=null){
            computationDisplay.setText(updateObject.getCurrentInputString());
        }

        if (updateObject.getCurrentTotal()!=null){
            totalDisplay.setText(updateObject.getCurrentTotal());
        }

    }


}
