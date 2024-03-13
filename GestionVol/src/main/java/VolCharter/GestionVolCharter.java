package VolCharter;
import Avion.Avion;
import Vol.ControlleurVol;
import Vol.Vol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class GestionVolCharter extends JFrame {
    private JTable tableVol;
    private TextField txtFldNumVol;
    private TextField txtFldDestination;
    private TextField txtFldIdAvion;
    private TextField txtFldReservation;
    private TextField txtFldDepart;
    private JLabel lblNumVol;
    private JLabel lblDestination;
    private JLabel lblIdAvion;
    private JLabel lblReservation;
    private JLabel lblDepart;
    private JCheckBox chkRepas= new JCheckBox("Repas");
    private JCheckBox chkSiege= new JCheckBox("ReservSiege");
    private JCheckBox chkBar= new JCheckBox("Bar");
    private JCheckBox chkDivertissement= new JCheckBox("Divertissement");
    private JCheckBox chkservPayant= new JCheckBox("ServPayant");
    private JCheckBox chkPrise= new JCheckBox("Prise");
    private JCheckBox chkWifi= new JCheckBox("Wifi");

    private JPanel panelGestionVol;
    private boolean quitter = false;


    public GestionVolCharter()throws Exception {
        initUI();
    }

    private void initUI() throws Exception {

        //Couleur de fond et taille de la fenetre
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);

        //Titre du panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setBounds(0,0,1000,50);
        add(titlePanel);
        JLabel titleLabel = new JLabel("Gestion de vol");
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(titleLabel);

        //Creer le tableau
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.white);
        tablePanel.setBounds(50,100,900,250);
        add(tablePanel);
        tablePanel.setLayout(new BorderLayout());

        //Model pour la table avion
        DefaultTableModel model =  new DefaultTableModel();
        model.addColumn("idvolType");
        model.addColumn("numvol");
        model.addColumn("destination");
        model.addColumn("idavion");
        model.addColumn("datedepart");
        model.addColumn("nbreservations");
        model.addColumn("Repas");
        model.addColumn("Siege reserve");
        model.addColumn("Bar");
        model.addColumn("Divertissement");
        model.addColumn("Service payant");
        model.addColumn("Prise");
        model.addColumn("Wifi");
        model.addColumn("idVol");

        //Inserer les champs de la BD dams la table
        ResultSet resultSet = ControlleurVol.getInstance().lister();
        ResultSet resultSetType = ControlleurVolCharter.getInstance().lister();


        while (resultSet.next() && resultSetType.next()) {
            //Champ de la table vol
            int fk = resultSet.getInt("idvol");
            String numvol = resultSet.getString("numvol");
            String destination = resultSet.getString("destination");
            int idavion = resultSet.getInt("idavion");
            int nbreservations = resultSet.getInt("nbreservations");
            String datedepart = resultSet.getString("datedepart");

            //Champ de la table VolType
            int pk = resultSetType.getInt("idvolcharter");
            Boolean repas = resultSetType.getBoolean("isrepasfourni");
            Boolean reservSiege = resultSetType.getBoolean("issiegereserve");
            Boolean bar = resultSetType.getBoolean("isservicebar");
            Boolean servdivertissement = resultSetType.getBoolean("issysdivertissement");
            Boolean servpayant = resultSetType.getBoolean("isservicepayant");
            Boolean prise = resultSetType.getBoolean("isprisealimentation");
            Boolean wifi = resultSetType.getBoolean("iswifi");


            VolCharter vol = new VolCharter(pk, numvol, destination, datedepart,nbreservations,idavion,repas,prise,bar,
                    servpayant,wifi,servdivertissement,reservSiege,fk);

            model.addRow(new Object[] { vol.getIdVol(), vol.getNumVol(), vol.getDestination(), vol.getIdAvion(),
                    vol.getDateDepart(),vol.getNbReservations(),vol.getIsRepasFourni(),vol.getIsSiegeReserve(),vol.getIsServiceBar(),
                    vol.getIsSysDivertissement(),vol.getIsServicePayant(),vol.getIsPriseAlimentation(),vol.getIsWifi(),
                    vol.getIdVolType()
            });
        }

        //Creer la table
        tableVol = new JTable(model);

        //Barre de defilement
        JScrollPane jScrollPane = new JScrollPane(tableVol);
        tablePanel.add(jScrollPane, BorderLayout.CENTER);

        //Formulaire
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(0, 370, 800, 300);
        add(formPanel);

        //Ajouter les txtfld et les lbl
        lblNumVol = new JLabel("Numero: ");
        lblNumVol.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldNumVol = new TextField(20);

        lblDestination = new JLabel("Destination: ");
        lblDestination.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldDestination = new TextField(20);

        lblIdAvion = new JLabel("idavion: ");
        lblIdAvion.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldIdAvion = new TextField(20);

        lblReservation = new JLabel("NbReservations: ");
        lblReservation.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldReservation = new TextField(20);

        lblDepart = new JLabel("DateDepart: ");
        lblDepart.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldDepart = new TextField(20);

        //Ajouter les checkboxes
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setBackground(Color.WHITE);
        checkBoxPanel.setLayout(new FlowLayout());

        checkBoxPanel.add(chkRepas);
        checkBoxPanel.add(chkSiege);
        checkBoxPanel.add(chkBar);
        checkBoxPanel.add(chkDivertissement);
        checkBoxPanel.add(chkservPayant);
        checkBoxPanel.add(chkPrise);
        checkBoxPanel.add(chkWifi);

        //Les boutons
        JButton buttonAjouter = new JButton("Ajouter");
        buttonAjouter.setBackground(Color.DARK_GRAY);
        buttonAjouter.setForeground(Color.WHITE);
        buttonAjouter.addActionListener(e -> {
            try {
                ajouter();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton buttonModifier = new JButton("Modifier");
        buttonModifier.setBackground(Color.ORANGE);
        buttonModifier.addActionListener(e -> {
            try {
                modifier();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton buttonSupprimer = new JButton("Supprimer");
        buttonSupprimer.setBackground(Color.RED);
        buttonSupprimer.addActionListener(e-> {
            try {
                supprimer();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton buttonQuitter = new JButton("Quitter");
        buttonQuitter.setBackground(Color.BLUE);
        buttonQuitter.setForeground(Color.WHITE);
        buttonQuitter.addActionListener(e->quitter());


        //Positioner les composants de la parti formulaire du Jframe
        GroupLayout layoutForm = new GroupLayout(formPanel);
        formPanel.setLayout(layoutForm);
        layoutForm.setAutoCreateGaps(true);
        layoutForm.setAutoCreateContainerGaps(true);
        layoutForm.setHorizontalGroup(layoutForm.createSequentialGroup()
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblNumVol)
                        .addComponent(lblDestination)
                        .addComponent(lblIdAvion)
                        .addComponent(lblReservation)
                        .addComponent(lblDepart))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtFldNumVol)
                        .addComponent(txtFldDestination)
                        .addComponent(txtFldIdAvion)
                        .addComponent(txtFldReservation)
                        .addComponent(txtFldDepart)
                        .addGroup(layoutForm.createSequentialGroup()
                                .addComponent(checkBoxPanel))
                        .addGroup(layoutForm.createSequentialGroup()
                                .addComponent(buttonAjouter)
                                .addComponent(buttonModifier)
                                .addComponent(buttonSupprimer)
                                .addComponent(buttonQuitter))));

        layoutForm.linkSize(SwingConstants.HORIZONTAL, buttonAjouter, buttonModifier, buttonSupprimer,
                buttonQuitter);

        layoutForm.setVerticalGroup(layoutForm.createSequentialGroup()
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumVol)
                        .addComponent(txtFldNumVol))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDestination)
                        .addComponent(txtFldDestination))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIdAvion)
                        .addComponent(txtFldIdAvion))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblReservation)
                        .addComponent(txtFldReservation))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDepart)
                        .addComponent(txtFldDepart))
                .addGroup(layoutForm.createSequentialGroup()
                        .addComponent(checkBoxPanel)
                        .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(buttonAjouter)
                                .addComponent(buttonModifier)
                                .addComponent(buttonSupprimer)
                                .addComponent(buttonQuitter))));


        panelGestionVol = new JPanel();
        panelGestionVol.setBackground(Color.WHITE);
        panelGestionVol.setBounds(0, 80, 1000, 800);
        add(panelGestionVol);
        panelGestionVol.setLayout(null);
        panelGestionVol.add(titlePanel);
        panelGestionVol.add(tablePanel);
        panelGestionVol.add(formPanel);

        //Rendre la fenetre visible
        setVisible(true);
    }


    //FONCTIONS POUR LA GESTION
    private void ajouter() throws Exception {
        int pk = 0;
        int fk = 0;
        String numVol = txtFldNumVol.getText();
        String dest = txtFldDestination.getText();
        int idAvion = Integer.parseInt(txtFldIdAvion.getText());
        int reserv = Integer.parseInt(txtFldReservation.getText());
        String depart = txtFldDepart.getText();
        Boolean repas = chkRepas.isSelected();
        Boolean reservSiege = chkSiege.isSelected();
        Boolean bar = chkBar.isSelected();
        Boolean servdivertissement = chkDivertissement.isSelected();
        Boolean servpayant = chkservPayant.isSelected();
        Boolean prise = chkPrise.isSelected();
        Boolean wifi = chkWifi.isSelected();

        VolCharter vol = new VolCharter(fk,numVol,dest,depart,reserv,idAvion,repas,prise,bar,servpayant,wifi,
                servdivertissement,reservSiege,pk);

        //Ajoute le vol dans la liste
        ControlleurVol.getInstance().enregistrer(vol);

        //Prendre le fk de la bd et le mettre dans vol
        ResultSet resultSetVol = ControlleurVol.getInstance().lister();
        resultSetVol.last();
        fk = resultSetVol.getInt("idvol");
        vol.setIdVol(fk);
        ControlleurVolCharter.getInstance().enregistrer(vol);


        //Prendre le pk de la bd
        ResultSet resultSetVolBasPrix = ControlleurVolCharter.getInstance().lister();
        resultSetVolBasPrix.last();
        pk = resultSetVolBasPrix.getInt("idvolcharter");
        vol.setIdVolType(pk);


        //Ajouter le dernier champ de la table dans le tableau
        DefaultTableModel model = (DefaultTableModel) tableVol.getModel();
        model.addRow(new Object[] { vol.getIdVolType(), vol.getNumVol(), vol.getDestination(), vol.getIdAvion(),
                vol.getDateDepart(),vol.getNbReservations(),vol.getIsRepasFourni(),vol.getIsSiegeReserve(),vol.getIsServiceBar(),
                vol.getIsSysDivertissement(),vol.getIsServicePayant(),vol.getIsPriseAlimentation(),vol.getIsWifi(),
                vol.getIdVol()
        });

        //Effacer les textsFields
        txtFldReservation.setText("");
        txtFldIdAvion.setText("");
        txtFldDestination.setText("");
        txtFldDepart.setText("");
        txtFldNumVol.setText("");

        //Decocher les checkbox
        chkBar.setSelected(false);
        chkDivertissement.setSelected(false);
        chkPrise.setSelected(false);
        chkRepas.setSelected(false);
        chkSiege.setSelected(false);
        chkservPayant.setSelected(false);
        chkWifi.setSelected(false);
    }

    private void modifier() throws Exception {
        int rowIndex;
        ResultSet resultSet;
        int id;
        ResultSet resultSetType;

        //Se Positioner dans le champ choisit
        rowIndex = tableVol.getSelectedRow()+1;//+1 parce que result set doit commencer à 1
        resultSet = ControlleurVol.getInstance().lister();
        resultSetType = ControlleurVolCharter.getInstance().lister();
        resultSetType.absolute(rowIndex);
        resultSet.absolute(rowIndex);

        //Champ de la table vol
        int fk = resultSet.getInt("idvol");
        String numvol = resultSet.getString("numvol");
        String destination = resultSet.getString("destination");
        int idavion = resultSet.getInt("idavion");
        int nbreservations = resultSet.getInt("nbreservations");
        String datedepart = resultSet.getString("datedepart");

        //Champ de la table VolType
        int pk = resultSetType.getInt("idvolcharter");
        Boolean repas = resultSetType.getBoolean("isrepasfourni");
        Boolean reservSiege = resultSetType.getBoolean("issiegereserve");
        Boolean bar = resultSetType.getBoolean("isservicebar");
        Boolean servdivertissement = resultSetType.getBoolean("issysdivertissement");
        Boolean servpayant = resultSetType.getBoolean("isservicepayant");
        Boolean prise = resultSetType.getBoolean("isprisealimentation");
        Boolean wifi = resultSetType.getBoolean("iswifi");

        //Verifier si les checkbox sont cocher
        if (chkRepas.isSelected()) {
            repas = true;
        } else {
            repas = false;
        }
        if (chkSiege.isSelected()) {
            reservSiege = true;
        } else {
            reservSiege = false;
        }
        if (chkBar.isSelected()) {
            bar = true;
        } else {
            bar = false;
        }
        if (chkDivertissement.isSelected()) {
            servdivertissement = true;
        } else {
            servdivertissement = false;
        }
        if (chkservPayant.isSelected()) {
            servpayant = true;
        } else {
            servpayant = false;
        }
        if (chkPrise.isSelected()) {
            prise = true;
        } else {
            prise = false;
        }
        if (chkWifi.isSelected()) {
            wifi = true;
        } else {
            wifi = false;
        }

        //Si le txtFld est vide , la valeur sera modifier
        if (!txtFldNumVol.getText().isEmpty()) {
            numvol = txtFldNumVol.getText();
        }
        if (!txtFldDestination.getText().isEmpty()) {
            destination = txtFldDestination.getText();
        }
        if (!txtFldReservation.getText().isEmpty()) {
            nbreservations = Integer.parseInt(txtFldReservation.getText());
        }
        if (!txtFldDepart.getText().isEmpty()) {
            datedepart = txtFldDepart.getText();
        }


        VolCharter vol = new VolCharter(fk, numvol, destination, datedepart,nbreservations,idavion,repas,prise,bar,
                servpayant,wifi,servdivertissement,reservSiege,pk);

        ControlleurVol.getInstance().modifier(vol);
        ControlleurVolCharter.getInstance().modifier(vol);

        // Mettre à jour le modèle de tableau avec les nouvelles valeurs
        DefaultTableModel model = (DefaultTableModel) tableVol.getModel();
        model.setValueAt( numvol, rowIndex-1, 1);
        model.setValueAt( destination, rowIndex-1, 2);
        model.setValueAt( datedepart, rowIndex-1, 4);
        model.setValueAt( nbreservations, rowIndex-1, 5);
        model.setValueAt( reservSiege,rowIndex-1, 7);
        model.setValueAt( bar, rowIndex-1, 8);
        model.setValueAt( servdivertissement, rowIndex-1, 9);
        model.setValueAt( servpayant, rowIndex-1, 10);
        model.setValueAt( prise, rowIndex-1, 11);
        model.setValueAt( wifi, rowIndex-1, 12);

        //Effacer les textsFields
        txtFldReservation.setText("");
        txtFldIdAvion.setText("");
        txtFldDestination.setText("");
        txtFldDepart.setText("");
        txtFldNumVol.setText("");

        //Decocher les checkbox
        chkBar.setSelected(false);
        chkDivertissement.setSelected(false);
        chkPrise.setSelected(false);
        chkRepas.setSelected(false);
        chkSiege.setSelected(false);
        chkservPayant.setSelected(false);
        chkWifi.setSelected(false);
    }

    private void supprimer() throws Exception {
        int rowIndex;
        ResultSet resultSetVolType;
        ResultSet resultSetVol;
        int idVolBasPrix;
        int idVol;

        //Se Positioner dans le champ choisit
        rowIndex = tableVol.getSelectedRow()+1;//+1 parce que result set doit commencer à 1
        resultSetVolType = ControlleurVolCharter.getInstance().lister();
        resultSetVolType.absolute(rowIndex);

        resultSetVol = ControlleurVol.getInstance().lister();
        resultSetVol.absolute(rowIndex);


        //Prendre le id de cette position
        idVolBasPrix = resultSetVolType.getInt("idvolcharter");
        idVol = resultSetVolType.getInt("idvol");

        //Supprime le vol dans la liste
        ControlleurVolCharter.getInstance().supprimer(idVolBasPrix);
        ControlleurVol.getInstance().supprimer(idVol);


        //Supprimer le champ choisit
        DefaultTableModel model = (DefaultTableModel) tableVol.getModel();
        model.removeRow(rowIndex-1);
    }

    private void quitter() {
        dispose();
    }
}
