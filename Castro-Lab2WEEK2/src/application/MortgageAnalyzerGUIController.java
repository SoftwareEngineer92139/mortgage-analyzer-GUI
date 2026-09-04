package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class MortgageAnalyzerGUIController implements Initializable {

    // setting the two decimal format
    private static final NumberFormat twoDecimal = NumberFormat.getNumberInstance();
    static {
        twoDecimal.setMaximumFractionDigits(2);
    }

    // private class of type MortgageAnalyzer
    MortgageAnalyzer mortgageInterface;

    @FXML
    private TextField amountLoanTextField;

    @FXML
    private TextField annualInterestTextField;

    @FXML
    private Button calculateMortgageButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField durationTextField;

    @FXML
    private Button exitButton;

    @FXML
    private Label messageTextLabel;

    @FXML
    private Button validateButton;

    @FXML
    void handleClearNumbers(ActionEvent event) {

        // clearing the text for the numbers
        amountLoanTextField.setText(null);
        annualInterestTextField.setText(null);
        durationTextField.setText(null);
        messageTextLabel.setText("clearing numbers!");

    }

    @FXML
    void handleExitApplication(ActionEvent event) {

        // closing the application
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();

    }

    @FXML
    void handleMortgageCalculation(ActionEvent event) {
        Integer loanAmoutInteger = Integer.parseInt(amountLoanTextField.getText());
        Double interestAmountDouble = Double.parseDouble(annualInterestTextField.getText());
        Integer loanDurationInteger = Integer.parseInt(durationTextField.getText());

        MortgageAnalyzer proceedOperation = new MortgageAnalyzer(loanAmoutInteger, interestAmountDouble, loanDurationInteger);
        Double payment = proceedOperation.getMonthlyPayment();
        Double totalInterest = proceedOperation.getTotalInterest();

        String resultMessage = "Mortage Loan Details:\n" + 
                                "\nAmount of Loan:\t\t\t" + "$" + amountLoanTextField.getText() +
                                "\nAnnual Interest Rate:\t\t" + annualInterestTextField.getText() + "%" +
                                "\nDuration of loan in months:\t" + durationTextField.getText() +
                                "\nMonthly payment:\t\t\t" + "$" + String.valueOf(twoDecimal.format(payment)) +
                                "\nTotal interest paid:\t\t\t" + "$" + String.valueOf(twoDecimal.format(totalInterest));
        
        messageTextLabel.setText(resultMessage);
    }

    @FXML
    void handleVerificationCheck(ActionEvent event) {

        // fetching the data
        String loanAmount = amountLoanTextField.getText();
        String interestRate = annualInterestTextField.getText();
        String loanDuration = durationTextField.getText();

        // calling the verification method
        if(mortgageInterface.verificationCheck(loanAmount, interestRate, loanDuration))
        {
            messageTextLabel.setText("Data validation check passed!\nPlease click on calculate mortgage");
        }
        else
        {
            messageTextLabel.setText(null);
            if(!mortgageInterface.verifyLoanAmount(loanAmount))
            {
                String message = "Loan Amount";
                diplayAnomalousDataMessage(message);
            }
            if(!mortgageInterface.verifyInterestRate(interestRate))
            {
                String message = "Annual Interest Rate";
                diplayAnomalousDataMessage(message);
            }
            if(!mortgageInterface.verifyDuration(loanDuration))
            {
                String message = "Loan Duration";
                diplayAnomalousDataMessage(message);
            }
        }

    }


    // called by FXMLLoader to initialize the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        // private reference of type MortgageAnalyzer
        mortgageInterface = new MortgageAnalyzer();

    }

    void diplayAnomalousDataMessage(String anomalousData)
    {
        Dialog<String> messageBox = new Dialog<String>();
        messageBox.setTitle("Alert: verify your data!");
        ButtonType ok_ButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        messageBox.setContentText("Please correct the data entered in the " + anomalousData);
        messageBox.getDialogPane().getButtonTypes().add(ok_ButtonType);
        messageBox.show();
    }

}
