/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cifrado_Archivos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Cifrado_Archivos extends JFrame {

    private final JTextArea area1;
    private final JButton BotonEncriptar;
    private final JButton BotonDesencriptar;
    private final JTextArea area2;
    private final JButton Guardarvge;
    private final JButton leervge;
    private final JButton Guardartexto;
    private final JButton leertexto;

    public Cifrado_Archivos() {
        super("Cifrado");
        setLayout(new FlowLayout());
        area1 = new JTextArea(10, 30);
        add(area1);

        Guardarvge = new JButton("Guardar encriptacion");
        add(Guardarvge);

        leervge = new JButton("Leer encriptado");
        add(leervge);

        Guardartexto = new JButton("Guardar texto");
        add(Guardartexto);

        leertexto = new JButton("Leer texto");
        add(leertexto);

        BotonEncriptar = new JButton("encriptar");
        add(BotonEncriptar);

        BotonDesencriptar = new JButton("desencriptar");
        add(BotonDesencriptar);

        area2 = new JTextArea(10, 30);
        add(area2);

        Cifrado_Archivos.HandlerLeerVge handler7 = new Cifrado_Archivos.HandlerLeerVge();
        leervge.addActionListener(handler7);

        Cifrado_Archivos.HandlerVge handler6 = new Cifrado_Archivos.HandlerVge();
        Guardarvge.addActionListener(handler6);

        Cifrado_Archivos.HandlerLeer handler5 = new Cifrado_Archivos.HandlerLeer();
        leertexto.addActionListener(handler5);

        Cifrado_Archivos.HandlerGuardar handler4 = new Cifrado_Archivos.HandlerGuardar();
        Guardartexto.addActionListener(handler4);

        Cifrado_Archivos.HandlerEncriptar handler = new Cifrado_Archivos.HandlerEncriptar();
        BotonEncriptar.addActionListener(handler);

        Cifrado_Archivos.HandlerDesenncriptar handler2 = new Cifrado_Archivos.HandlerDesenncriptar();
        BotonDesencriptar.addActionListener(handler2);

    }

    private class HandlerLeer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            leerTxt();
        }
    }

    private class HandlerGuardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String str;
            str = area1.getText();
            escribrirTxt(str);
        }
    }

    private class HandlerVge implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String str;
            str = area2.getText();
            escribrirVge(str);
        }
    }

    private class HandlerLeerVge implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            leerVge();
        }
    }

    private class HandlerEncriptar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String str;
            str = area1.getText();
            //encriptar
            area2.setText(encriptar(str));
        }
    }

    private class HandlerDesenncriptar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String str;
            str = area1.getText();
            //encriptar
            area2.setText(desincriptar(str));
        }
    }

    public String encriptar(String palabra) {
        String encriptado = "";
        char caracteres[] = palabra.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            int b = caracteres[i];
            if (b > 64 && b < 123) {
                caracteres[i] = (char) (caracteres[i] + (char) 3);
            }
        }
        int inicio = 0;
        int fin = caracteres.length - 1;
        char temp;
        while (fin > inicio) {
            temp = caracteres[inicio];
            caracteres[inicio] = caracteres[fin];
            caracteres[fin] = temp;
            fin--;
            inicio++;
        }
        double a = caracteres.length / 2;
        int valor = (int) a;
        for (int i = valor; i < caracteres.length; i++) {
            caracteres[i] = (char) (caracteres[i] - (char) 1);
        }
        encriptado = String.valueOf(caracteres);

        return encriptado;
    }

    public String desincriptar(String palabra) {
        char caracteres[] = palabra.toCharArray();
        double a = caracteres.length / 2;
        int valor = (int) a;
        for (int i = valor; i < caracteres.length; i++) {
            caracteres[i] = (char) (caracteres[i] + (char) 1);
        }
        int inicio = 0;
        int fin = caracteres.length - 1;
        char temp;
        while (fin > inicio) {
            temp = caracteres[inicio];
            caracteres[inicio] = caracteres[fin];
            caracteres[fin] = temp;
            fin--;
            inicio++;
        }
        for (int i = 0; i < caracteres.length; i++) {
            int b = caracteres[i];
            if (b > 64 && b < 123) {
                caracteres[i] = (char) (caracteres[i] - (char) 3);
            }
        }
        String encriptado = String.valueOf(caracteres);
        return encriptado;
    }

    public void escribrirTxt(String palabra) {
        String ruta = JOptionPane.showInputDialog("Nombra el archivo a guardar");
        File file = new File(ruta + ".txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(palabra);
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar archivo");
        }
    }

    public void leerTxt() {
        try {
            String ruta = JOptionPane.showInputDialog("Nombra el archivo a mostrar");
            Scanner input = new Scanner(new File(ruta + ".txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                JOptionPane.showMessageDialog(this, line);
            }
            input.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo leer archivo");
        }

    }

    public void escribrirVge(String palabra) {
        String ruta = JOptionPane.showInputDialog("Nombra el archivo vge a guardar");
        File file = new File(ruta + ".vge");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(palabra);
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar archivo");
        }
    }

    public void leerVge() {
        try {
            String ruta = JOptionPane.showInputDialog("Nombra el archivo a mostrar");
            Scanner input = new Scanner(new File(ruta + ".vge"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                JOptionPane.showMessageDialog(this, line);
            }
            input.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo leer archivo");
        }

    }

}
