package com.example.decathlon.gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.example.decathlon.common.Competitor;

import java.awt.*;
import java.io.IOException;

import com.example.decathlon.core.CompetitionService;
import com.example.decathlon.deca.*;
import com.example.decathlon.excel.ExcelPrinter;
import com.example.decathlon.heptathlon.*;

import static javax.swing.JOptionPane.showMessageDialog;


public class MainGUI {

    private JTextField nameField;
    private JTextField resultField;
    private JComboBox<String> disciplineBox;
    private JTextArea outputArea;
    private Competitor competitor;
    public static void main(String[] args) {
        new MainGUI().createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Track and Field Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(6, 1));

        // Input for competitor's name
        nameField = new JTextField(20);
        panel.add(new JLabel("Enter Competitor's Name:"));
        panel.add(nameField);

        // Dropdown for selecting discipline
        String[] disciplines = {
                "100m (s)", "400m (s)", "1500m (s)", "110m Hurdles (s)",
                "Long Jump (cm)", "High Jump (cm)", "Pole Vault (cm)",
                "Discus Throw (m)", "Javelin Throw (m)", "Shot Put (m)",
                "Hep 200m (s)", "Hep 800m (s)", "Hep 100m Hurdles (s)",
                "Hep High Jump (cm)", "Hep Long Jump (cm)", "Hep Shot Put (m)",
                "Hep Javelin Throw (m)"};

        disciplineBox = new JComboBox<>(disciplines);
        panel.add(new JLabel("Select Discipline:"));
        panel.add(disciplineBox);

        // Input for result
        resultField = new JTextField(10);
        panel.add(new JLabel("Enter Result:"));
        panel.add(resultField);

        // Button to calculate and display result
        JButton calculateButton = new JButton("Calculate Score");
        calculateButton.addActionListener(new CalculateButtonListener());
        panel.add(calculateButton);

        JButton exportButton = new JButton("Export to Excel");
        exportButton.addActionListener(new ExportButtonListener());  // New export button listener
        panel.add(exportButton);  // Add export button to the panel

        // Output area
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String discipline = (String) disciplineBox.getSelectedItem();
            String resultText = resultField.getText();
            competitor = new Competitor(name);
            if (competitor == null) {
                competitor = new Competitor(name);  // Create a new competitor
            }
            try {
                double result = Double.parseDouble(resultText);

                int score = 0;
                switch (discipline) {
                    case "100m (s)":
                        Deca100M deca100M = new Deca100M();
                        score = deca100M.calculateResult(result);
                        break;
                    case "400m (s)":
                        Deca400M deca400M = new Deca400M();
                        score = deca400M.calculateResult(result);
                        break;
                    case "1500m (s)":
                        Deca1500M deca1500M = new Deca1500M();
                        score = deca1500M.calculateResult(result);
                        break;
                    case "110m Hurdles (s)":
                        Deca110MHurdles deca110MHurdles = new Deca110MHurdles();
                        score = deca110MHurdles.calculateResult(result);
                        break;
                    case "Long Jump (cm)":
                        DecaLongJump decaLongJump = new DecaLongJump();
                        score = decaLongJump.calculateResult(result);
                        break;
                    case "High Jump (cm)":
                        DecaHighJump decaHighJump = new DecaHighJump();
                        score = decaHighJump.calculateResult(result);
                        break;
                    case "Pole Vault (cm)":
                        DecaPoleVault decaPoleVault = new DecaPoleVault();
                        score = decaPoleVault.calculateResult(result);
                        break;
                    case "Discus Throw (m)":
                        DecaDiscusThrow decaDiscusThrow = new DecaDiscusThrow();
                        score = decaDiscusThrow.calculateResult(result);
                        break;
                    case "Javelin Throw (m)":
                        DecaJavelinThrow decaJavelinThrow = new DecaJavelinThrow();
                        score = decaJavelinThrow.calculateResult(result);
                        break;
                    case "Shot Put (m)":
                        DecaShotPut decaShotPut = new DecaShotPut();
                        score = decaShotPut.calculateResult(result);
                        break;
                    case "Hep 100m Hurdles (s)":
                        score = new Hep100MHurdles().calculateResult(result);
                        break;
                    case "Hep High Jump (cm)":
                        score = new HeptHightJump().calculateResult(result);
                        break;
                    case "Hep Shot Put (m)":
                        score = new HeptShotPut().calculateResult(result);
                        break;
                    case "Hep 200m (s)":
                        score = new Hep200M().calculateResult(result);
                        break;
                    case "Hep Long Jump (cm)":
                        score = new HeptLongJump().calculateResult(result);
                        break;
                    case "Hep Javelin Throw (m)":
                        score = new HeptJavelinThrow().calculateResult(result);
                        break;
                    case "Hep 800m (s)":
                        score = new Hep800M().calculateResult(result);
                        break;
                }
                competitor.setScore(discipline, score);

                outputArea.append("Competitor: " + name + "\n");
                outputArea.append("Discipline: " + discipline + "\n");
                outputArea.append("Result: " + result + "\n");
                outputArea.append("Score: " + score + "\n\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the result.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                exportToExcel();
                showMessageDialog(null, "Results exported successfully!", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                showMessageDialog(null, "Failed to export results to Excel.", "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportToExcel() throws IOException {
        String[][] data = new String[1][];
        int i = 0;

        Object[] rowData = competitor.getRowData(); // Get the competitor's row data

        // Ensure the array size matches the number of columns in rowData
        data[i] = new String[rowData.length];

        // Safely copy rowData to data array
        for (int j = 0; j < rowData.length; j++) {
            data[i][j] = (rowData[j] != null) ? rowData[j].toString() : "";  // Handle null values
        }


        ExcelPrinter printer = new ExcelPrinter("TrackAndFieldResults");
        printer.add(data, "Results");
        printer.write();
    }
}

