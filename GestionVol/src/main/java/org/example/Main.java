package org.example;

import Avion.GestionAvion;
import VolBasPrix.GestionVolBasPrix;
import VolCharter.GestionVolCharter;
import VolPrive.GestionVolPrive;
import VolRegulier.GestionVolRegulier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws Exception {

        //MENU POUR LA GESTIONS DES VOLS
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        JButton btnGestionVolBasPrix = new JButton("GestionVolBasPrix");
        btnGestionVolBasPrix.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new GestionVolBasPrix();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnGestionVolCharter = new JButton("GestionVolCharter");
        btnGestionVolCharter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new GestionVolCharter();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnGestionVolPrive = new JButton("GestionVolPrive");
        btnGestionVolPrive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new GestionVolPrive();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnGestionVolRegulier = new JButton("GestionVolRegulier");
        btnGestionVolRegulier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new GestionVolRegulier();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnGestionAvion = new JButton("GestionAvion");
        btnGestionAvion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new GestionAvion();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.add(btnGestionVolBasPrix);
        frame.add(btnGestionVolCharter);
        frame.add(btnGestionVolPrive);
        frame.add(btnGestionVolRegulier);
        frame.add(btnGestionAvion);

        frame.setVisible(true);
    }

}