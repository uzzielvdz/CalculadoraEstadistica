package com.mycompany.calculadoraestadistica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CalculadoraEstadistica extends JFrame implements ActionListener {

    private JLabel lblInstruccion, lblResultado;
    private JTextField txtNumeros;
    private JButton btnCalcular, btnLimpiar;
    private JComboBox<String> cboOperacion;
    
    public CalculadoraEstadistica() {
        setTitle("Calculadora Estadística");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblInstruccion = new JLabel("Ingrese números separados por comas:");
        lblResultado = new JLabel("");
        txtNumeros = new JTextField(20);
        btnCalcular = new JButton("Calcular");
        btnLimpiar = new JButton("Limpiar");
        String[] operaciones = {"Moda", "Media", "Mediana", "Desviación Media", "Desviación Estándar"};
        cboOperacion = new JComboBox<>(operaciones);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(lblInstruccion);
        panel.add(txtNumeros);
        panel.add(cboOperacion);
        panel.add(btnCalcular);

        JPanel panelResultado = new JPanel();
        panelResultado.add(lblResultado);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnLimpiar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(panelResultado, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        btnCalcular.addActionListener(this);
        btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            calcular();
        } else if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }

    private void calcular() {
        String numerosStr = txtNumeros.getText();
        String[] numerosArray = numerosStr.split(",");
        ArrayList<Double> numeros = new ArrayList<>();

        for (String numStr : numerosArray) {
            try {
                double num = Double.parseDouble(numStr);
                numeros.add(num);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese números válidos separados por comas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String operacionSeleccionada = (String) cboOperacion.getSelectedItem();
        double resultado = 0;

        switch (operacionSeleccionada) {
            case "Moda":
                resultado = calcularModa(numeros);
                break;
            case "Media":
                resultado = calcularMedia(numeros);
                break;
            case "Mediana":
                resultado = calcularMediana(numeros);
                break;
            case "Desviación Media":
                resultado = calcularDesviacionMedia(numeros);
                break;
            case "Desviación Estándar":
                resultado = calcularDesviacionEstandar(numeros);
                break;
        }

        lblResultado.setText("Resultado: " + resultado);
    }

    private void limpiar() {
        txtNumeros.setText("");
        lblResultado.setText("");
    }

    private double calcularModa(ArrayList<Double> numeros) {
        // Implementa aquí el cálculo de la moda
        return 0;
    }

    private double calcularMedia(ArrayList<Double> numeros) {
        // Implementa aquí el cálculo de la media
        double suma = 0;
        for (double num : numeros) {
            suma += num;
        }
        return suma / numeros.size();
    }

    private double calcularMediana(ArrayList<Double> numeros) {
        // Implementa aquí el cálculo de la mediana
        Collections.sort(numeros);
        int n = numeros.size();
        if (n % 2 == 0) {
            int middle1 = n / 2 - 1;
            int middle2 = n / 2;
            return (numeros.get(middle1) + numeros.get(middle2)) / 2;
        } else {
            return numeros.get(n / 2);
        }
    }

    private double calcularDesviacionMedia(ArrayList<Double> numeros) {
        // Implementa aquí el cálculo de la desviación media
        double media = calcularMedia(numeros);
        double suma = 0;
        for (double num : numeros) {
            suma += Math.abs(num - media);
        }
        return suma / numeros.size();
    }

    private double calcularDesviacionEstandar(ArrayList<Double> numeros) {
        // Implementa aquí el cálculo de la desviación estándar
        double media = calcularMedia(numeros);
        double sumaCuadrados = 0;
        for (double num : numeros) {
            sumaCuadrados += Math.pow(num - media, 2);
        }
        return Math.sqrt(sumaCuadrados / numeros.size());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraEstadistica calc = new CalculadoraEstadistica();
            calc.setVisible(true);
        });
    }
}
