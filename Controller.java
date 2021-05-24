package Calculatrice.controller;

import Calculatrice.View.View;
import Calculatrice.model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        model.addObserver(view);

        view.setCalcButtonListener(new CalcButtonListener());

    }

    class CalcButtonListener implements ActionListener {
        boolean OperationAlreadyHappened = false;

        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            String computationText = view.getComputationDisplayText();

            ButtonInfo buttonInfo = new ButtonInfo(buttonText);
            StringInfo stringInfo = new StringInfo(computationText);

            if (buttonInfo.isClear) {model.Clear();return;}

            if (stringInfo.isEmpty) {

                if (buttonInfo.isNumber) {setComputationText(computationText + buttonText);
                } else if (buttonInfo.isDot) {setComputationText(computationText + buttonText);
                } else if (buttonInfo.isOperator) {} // do nothing
                else if (buttonInfo.isEquals) {} // do nothing

            } else { // string is NOT empty

                if (stringInfo.isLastCharacterNumber) {

                    if (buttonInfo.isNumber) {
                        if (OperationAlreadyHappened) {setComputationText(buttonText);OperationAlreadyHappened = false;}
                        else {setComputationText(computationText + buttonText);}
                    }

                    else if (buttonInfo.isOperator) {setComputationText(computationText + buttonText);}
                    else if (buttonInfo.isDot) {setComputationText(computationText + buttonText);}
                    else if (buttonInfo.isEquals) {OperationAlreadyHappened = true; performComputation();}
                }

                else if (stringInfo.isLastCharacterOperator) {

                    if (buttonInfo.isNumber) {setComputationText(computationText + buttonText);}
                    else if (buttonInfo.isOperator) {setComputationText(computationText.substring(0,stringInfo.lastCharIndex) + buttonText);}
                    else if (buttonInfo.isDot) {setComputationText(computationText + buttonText);}
                    else if (buttonInfo.isEquals) {} // do nothing

                }

                else if (stringInfo.isLastCharacterDot) {

                    if (buttonInfo.isNumber) {setComputationText(computationText + buttonText);}
                    else if (buttonInfo.isOperator) {} // do nothing
                    else if (buttonInfo.isDot) {setComputationText(computationText.substring(0,stringInfo.lastCharIndex));}
                    else if (buttonInfo.isEquals) {} // do nothing

                }

            }

        }

        private void performComputation() {
            model.computeString();
        }

        private void setComputationText(String text) {
            model.setComputationText(text);
        }

    }

    public class ButtonInfo {

        boolean isOperator;
        boolean isEquals;
        boolean isDot;
        boolean isNumber;
        boolean isClear;

        public ButtonInfo (String buttonText){

            readButtonValue(buttonText);

        }

        private void readButtonValue(String buttonText) {
            switch (buttonText){
                case "C" : isClear = true; break;

                case "1" :
                case "2" :
                case "3" :
                case "4" :
                case "5" :
                case "6" :
                case "7" :
                case "8" :
                case "9" :
                case "0" :
                    isNumber = true; break;

                case "." :  isDot = true; break;

                case "+" :
                case "-" :
                case "*" :
                case "/" :
                    isOperator = true; break;

                case "=" : isEquals = true; break;

                default : System.out.println("BUTTON TYPE NOT FOUND");
            }

        }

        public boolean isNumber() {
            return isNumber;
        }

        public boolean isOperator() {
            return isOperator;
        }

        public boolean isEquals() {
            return isEquals;
        }

        public boolean isDot() {
            return isDot;
        }

        public boolean isClear() {
            return isClear;
        }
    }

    public class StringInfo {

        boolean isEmpty;
        boolean isLastCharacterNumber;
        boolean isLastCharacterOperator;
        boolean isLastCharacterDot;
        int lastCharIndex;

        public StringInfo(String computationText){

            readStringValue(computationText);

        }

        private void readStringValue(String computationText) {

            if (computationText.equals("")) {
                isEmpty = true;
            } else {
                lastCharIndex = computationText.length()-1;
                String lastChar = computationText.substring(lastCharIndex);

                switch(lastChar){
                    case "0" :
                    case "1" :
                    case "2" :
                    case "3" :
                    case "4" :
                    case "5" :
                    case "6" :
                    case "7" :
                    case "8" :
                    case "9" :
                        isLastCharacterNumber = true; break;

                    case "+" :
                    case "-" :
                    case "*" :
                    case "/" :
                        isLastCharacterOperator= true; break;

                    case "." : isLastCharacterDot = true; break;

                    default : System.out.println("Cannot parse last character!");

                }

            }


        }

        public boolean isComputationTextEmpty() {
            return isEmpty;
        }


        public boolean isLastCharacterNumber() {
            return isLastCharacterNumber;
        }

        public boolean isLastCharacterOperator() {
            return isLastCharacterOperator;
        }

        public boolean isLastCharacterDot() {
            return isLastCharacterDot;
        }

        public int getLastCharIndex() {
            return lastCharIndex;
        }

    }

}
