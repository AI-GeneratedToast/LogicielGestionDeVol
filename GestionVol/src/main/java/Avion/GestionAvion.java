package Avion;

import Avion.Avion;
import Avion.ControlleurAvion;

import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class GestionAvion extends JFrame{
    private JTable tableAvion;
    private TextField txtFldNumAvion;
    private TextField txtFldTypeAvion;
    private TextField txtFldNbPlaces;
    private TextField txtFldCatgorie;
    private JLabel lblNumAvion;
    private JLabel lblTypeAvion;
    private JLabel lblNbPlaces;
    private JLabel lblCategorie;
    private JPanel panelGestionAvion;
    private boolean quitter = false;


    public GestionAvion()throws Exception {
        initUI();
    }

    private void initUI() throws Exception {

        //Couleur de fond et taille de la fenetre
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800,620);
        setLocationRelativeTo(null);

        //Titre du panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setBounds(0,0,800,50);
        add(titlePanel);
        JLabel titleLabel = new JLabel("Gestion d'avions");
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(titleLabel);

        //Creer le tableau
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.white);
        tablePanel.setBounds(50,100,700,250);
        add(tablePanel);
        tablePanel.setLayout(new BorderLayout());

        //Model pour la table avion
        DefaultTableModel model =  new DefaultTableModel();
        model.addColumn("idavion");
        model.addColumn("numavion");
        model.addColumn("typeavion");
        model.addColumn("nbplaces");
        model.addColumn("categorie");

        //Inserer les champs de la BD dams la table
        ResultSet resultSet = ControlleurAvion.getInstance().lister();
        Avion avion;
        while (resultSet.next()) {
            int pk = resultSet.getInt("idavion");
            String numavion = resultSet.getString("numavion");
            String typeavion = resultSet.getString("typeavion");
            int nbplaces = resultSet.getInt("nbplaces");
            String categorie = resultSet.getString("categorie");

            avion = new Avion(pk, numavion, nbplaces, typeavion, categorie);

            model.addRow(new Object[] { avion.getId(), avion.getNumAvion(), avion.getTypeAvion(), avion.getNbPlaces(),
                    avion.getCategorie()});
        }

        //Creer la table
        tableAvion = new JTable(model);

        //Barre de defilement
        JScrollPane jScrollPane = new JScrollPane(tableAvion);
        tablePanel.add(jScrollPane, BorderLayout.CENTER);

        //Formulaire
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(50, 370, 600, 200);
        add(formPanel);

        //Ajouter les txtfld et les lbl
        lblNumAvion = new JLabel("Numero: ");
        lblNumAvion.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldNumAvion = new TextField(20);

        lblTypeAvion = new JLabel("Type: ");
        lblTypeAvion.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldTypeAvion = new TextField(20);

        lblNbPlaces = new JLabel("Places: ");
        lblNbPlaces.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldNbPlaces = new TextField(20);

        lblCategorie = new JLabel("Categorie: ");
        lblCategorie.setFont(new Font("Arial", Font.BOLD, 14));
        txtFldCatgorie = new TextField(20);

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
                        .addComponent(lblNumAvion)
                        .addComponent(lblTypeAvion)
                        .addComponent(lblNbPlaces)
                        .addComponent(lblCategorie))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtFldNumAvion)
                        .addComponent(txtFldTypeAvion)
                        .addComponent(txtFldNbPlaces)
                        .addComponent(txtFldCatgorie)
                        .addGroup(layoutForm.createSequentialGroup()
                                .addComponent(buttonAjouter)
                                .addComponent(buttonModifier)
                                .addComponent(buttonSupprimer)
                                .addComponent(buttonQuitter))));

        layoutForm.linkSize(SwingConstants.HORIZONTAL, buttonAjouter, buttonModifier, buttonSupprimer,
                buttonQuitter);

        layoutForm.setVerticalGroup(layoutForm.createSequentialGroup()
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumAvion)
                        .addComponent(txtFldNumAvion))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTypeAvion)
                        .addComponent(txtFldTypeAvion))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNbPlaces)
                        .addComponent(txtFldNbPlaces))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCategorie)
                        .addComponent(txtFldCatgorie))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAjouter)
                        .addComponent(buttonModifier)
                        .addComponent(buttonSupprimer)
                        .addComponent(buttonQuitter)));


        panelGestionAvion = new JPanel();
        panelGestionAvion.setBackground(Color.WHITE);
        panelGestionAvion.setBounds(0, 80, 1000, 800);
        add(panelGestionAvion);
        panelGestionAvion.setLayout(null);
        panelGestionAvion.add(titlePanel);
        panelGestionAvion.add(tablePanel);
        panelGestionAvion.add(formPanel);

        //Rendre la fenetre visible
        setVisible(true);
    }

    //FONCTIONS POUR LA GESTION
    private void ajouter() throws Exception {
        int pk = 0;
        String numAvion = txtFldNumAvion.getText();
        String type = txtFldTypeAvion.getText();
        int nbPlace = Integer.parseInt(txtFldNbPlaces.getText());
        String categorie = txtFldCatgorie.getText();
        Avion avion = new Avion(pk,numAvion,nbPlace,type,categorie);

        //Ajoute l'avion dans la liste
        ControlleurAvion.getInstance().enregistrer(avion);

        //Prend le dernier champ de la table pour prendre le id
        ResultSet resultSet = ControlleurAvion.getInstance().lister();
        resultSet.last();
        pk = resultSet.getInt("idavion");

        //Ajouter le dernier champ de la table dans le tableau
        DefaultTableModel model = (DefaultTableModel) tableAvion.getModel();
        model.addRow(new Object[] {pk,numAvion,type,nbPlace,categorie});

        //Effacer les textsFields
        txtFldNumAvion.setText("");
        txtFldNbPlaces.setText("");
        txtFldCatgorie.setText("");
        txtFldTypeAvion.setText("");
    }

    private void modifier() throws Exception {
        int rowIndex;
        ResultSet resultSet;
        int id;

        //Se Positioner dans le champ choisit
        rowIndex = tableAvion.getSelectedRow()+1;//+1 parce que result set doit commencer à 1
        resultSet = ControlleurAvion.getInstance().lister();
        resultSet.absolute(rowIndex);

        //Valeur des champs dans la bdd
        id = resultSet.getInt("idavion");
        String numAvion = resultSet.getString("numavion");
        String type = resultSet.getString("typeavion");
        int nbPlace = Integer.parseInt(resultSet.getString("nbplaces"));
        String categorie = resultSet.getString("categorie");

        //Si le txtFld est vide , la valeur sera modifier
        if(!txtFldNumAvion.getText().isEmpty()){
            numAvion = txtFldNumAvion.getText();
        }
        if(!txtFldTypeAvion.getText().isEmpty()){
            type = txtFldTypeAvion.getText();
        }
        if(!txtFldNbPlaces.getText().isEmpty() ){
            nbPlace = Integer.parseInt(txtFldNbPlaces.getText());
        }
        if(!txtFldCatgorie.getText().isEmpty()){
            categorie = txtFldCatgorie.getText();
        }

        Avion avion = new Avion(id,numAvion,nbPlace,type,categorie);

        ControlleurAvion.getInstance().modifier(avion);

        // Mettre à jour le modèle de tableau avec les nouvelles valeurs
        DefaultTableModel model = (DefaultTableModel) tableAvion.getModel();
        model.setValueAt( numAvion, rowIndex-1, 1);
        model.setValueAt( type, rowIndex-1, 2);
        model.setValueAt( nbPlace, rowIndex-1, 3);
        model.setValueAt( categorie,rowIndex-1, 4);
    }

    private void supprimer() throws Exception {
        int rowIndex;
        ResultSet resultSet;
        int id;

        //Se Positioner dans le champ choisit
        rowIndex = tableAvion.getSelectedRow()+1;//+1 parce que result set doit commencer à 1
        resultSet = ControlleurAvion.getInstance().lister();
        resultSet.absolute(rowIndex);

        //Prendre le id de cette position
        id = resultSet.getInt("idavion");

        //Supprime l'avion dans la liste
        ControlleurAvion.getInstance().supprimer(id);

        //Supprimer le champ choisit
        DefaultTableModel model = (DefaultTableModel) tableAvion.getModel();
        model.removeRow(rowIndex-1);
    }

    private void quitter() {
        dispose();
    }
}
