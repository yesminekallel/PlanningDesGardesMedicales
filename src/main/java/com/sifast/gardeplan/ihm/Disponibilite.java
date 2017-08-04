package com.sifast.gardeplan.ihm;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sifast.gardeplan.controller.Service;
import com.sifast.gardeplan.model.PrefEnum;
import com.toedter.calendar.JDateChooser;

public class Disponibilite extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	// private HashMap<String, PrefEnum> preference = new
	// HashMap<String,PrefEnum>();

	// constructeur

	public Disponibilite() {

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Disponibilit�");
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Nom docteur

		JLabel lblNomDuDocteur = new JLabel(
				MembresDeGarde.table.getValueAt(MembresDeGarde.table.getSelectedRow(), 0).toString());
		lblNomDuDocteur.setBounds(235, 33, 131, 14);
		contentPane.add(lblNomDuDocteur);
		
		
  // Nombre de nuit d�ja fait durant ce mois ( doit etre inf�rieur ou �gal � 4 par mois)
		
		JLabel lblNbrenuit = new JLabel( " a fait "+
  		MembresDeGarde.table.getValueAt(MembresDeGarde.table.getSelectedRow(), 1).toString() + " nuits durant ce mois");
	    lblNbrenuit.setBounds(320, 33, 250, 14);
		contentPane.add(lblNbrenuit);
		
		int n= Integer.parseInt(MembresDeGarde.table.getValueAt(MembresDeGarde.table.getSelectedRow(), 1).toString());
		
		if (n>=4) {
		JOptionPane.showMessageDialog(null, "Ce membre a fait 4 nuits ou plus de garde durant ce mois \n \n         ", "choisir un autre membre",
				JOptionPane.ERROR_MESSAGE);	
		}
		// choisir la date

		JDateChooser dateDispo = new JDateChooser();
		dateDispo.getCalendarButton().setBackground(SystemColor.activeCaption);
		dateDispo.setBounds(221, 89, 105, 20);
		contentPane.add(dateDispo);

		JRadioButton rbDispoBut = new JRadioButton("dispo_but");
		rbDispoBut.setBackground(new Color(176, 224, 230));
		buttonGroup.add(rbDispoBut);
		rbDispoBut.setBounds(159, 149, 87, 23);
		contentPane.add(rbDispoBut);

		JRadioButton rbNotDispo = new JRadioButton("Not_dispo");
		rbNotDispo.setBackground(new Color(176, 224, 230));
		buttonGroup.add(rbNotDispo);
		rbNotDispo.setBounds(331, 149, 95, 23);
		contentPane.add(rbNotDispo);

		// table (affichage de disponibilit�)

		Object[][] data = null;

		String[] colomname = { "Date", "Disponibilit�"};
		 DefaultTableModel model = new DefaultTableModel(data, colomname);
		JTable table1 = new JTable(model);
		table1.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		table1.setForeground(Color.black);
		table1.setRowHeight(30);

			
		
		// Affichage des donn�es existantes
		HashMap<String,List<PrefEnum>> tmpPref = Service.docteurs.get(MembresDeGarde.table.getSelectedRow()).getPreference();

		for (Entry<String, List<PrefEnum>> entry : tmpPref.entrySet()) {

			System.out.println(entry.getKey());
			System.out.println(entry.getValue());

			Object[] row = new Object[2];
			row[0] = entry.getKey();
			row[1] = entry.getValue();
			model.addRow(row);

		}

		// JScrollPane
		JScrollPane pane = new JScrollPane(table1);
		pane.setBounds(96, 274, 385, 197);
		contentPane.add(pane);

		// bouton ajouter

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		btnAjouter.setBounds(175, 210, 89, 23);
		contentPane.add(btnAjouter);
		Object[] row = new Object[2];
		btnAjouter.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String dateDebut ;
				String dateFin ;
				String dateChoisie ;		
				if (AjouterPlanning.dateD.getDate().getMonth()<10)
				{ if (AjouterPlanning.dateD.getDate().getDate()<10)
				{ dateDebut=""+AjouterPlanning.dateD.getDate().getYear()+"0"+AjouterPlanning.dateD.getDate().getMonth()+"0"+AjouterPlanning.dateD.getDate().getDate();}
				else { dateDebut=""+AjouterPlanning.dateD.getDate().getYear()+"0"+AjouterPlanning.dateD.getDate().getMonth()+AjouterPlanning.dateD.getDate().getDate();}
				}	
				else { if (AjouterPlanning.dateD.getDate().getDate()<10)
				{ dateDebut=""+AjouterPlanning.dateD.getDate().getYear()+AjouterPlanning.dateD.getDate().getMonth()+"0"+AjouterPlanning.dateD.getDate().getDate();}
				else { 	dateDebut=""+AjouterPlanning.dateD.getDate().getYear()+AjouterPlanning.dateD.getDate().getMonth()+AjouterPlanning.dateD.getDate().getDate();}
				}
				
				if ( AjouterPlanning.dateF.getDate().getMonth()<10)
				{ if ( AjouterPlanning.dateF.getDate().getDate()<10)
				{ dateFin=""+ AjouterPlanning.dateF.getDate().getYear()+"0"+ AjouterPlanning.dateF.getDate().getMonth()+"0"+ AjouterPlanning.dateF.getDate().getDate();}
				else { dateFin=""+ AjouterPlanning.dateF.getDate().getYear()+"0"+ AjouterPlanning.dateF.getDate().getMonth()+ AjouterPlanning.dateF.getDate().getDate();}
				}	
				else { if ( AjouterPlanning.dateF.getDate().getDate()<10)
				{ dateFin=""+ AjouterPlanning.dateF.getDate().getYear()+ AjouterPlanning.dateF.getDate().getMonth()+"0"+ AjouterPlanning.dateF.getDate().getDate();}
				else { 	dateFin=""+ AjouterPlanning.dateF.getDate().getYear()+ AjouterPlanning.dateF.getDate().getMonth()+ AjouterPlanning.dateF.getDate().getDate();}
				}
				
				if ( dateDispo.getDate().getMonth()<10)
				{ if (dateDispo.getDate().getDate()<10)
				{ dateChoisie=""+dateDispo.getDate().getYear()+"0"+ dateDispo.getDate().getMonth()+"0"+ dateDispo.getDate().getDate();}
				else { dateChoisie=""+ dateDispo.getDate().getYear()+"0"+dateDispo.getDate().getMonth()+ dateDispo.getDate().getDate();}
				}	
				else { if (dateDispo.getDate().getDate()<10)
				{ dateChoisie=""+dateDispo.getDate().getYear()+ dateDispo.getDate().getMonth()+"0"+dateDispo.getDate().getDate();}
				else { 	dateChoisie=""+dateDispo.getDate().getYear()+ dateDispo.getDate().getMonth()+dateDispo.getDate().getDate();}
				}
				
				int dateDebut_int = Integer.parseInt(dateDebut);
				int dateFin_int = Integer.parseInt(dateFin);
				int dateChoisie_int = Integer.parseInt(dateChoisie);
				
				if (dateChoisie_int < dateDebut_int || dateChoisie_int > dateFin_int) {
					JOptionPane
							.showMessageDialog(btnAjouter,
									"La date doit se situer entre le  "
											+ (String.format("%1$td/%1$tm/%1$tY",
													AjouterPlanning.dateD.getDate().getTime()))
											+ " et le "
											+ (String.format("%1$td/%1$tm/%1$tY",
													AjouterPlanning.dateF.getDate().getTime()))
							+ " \n \n                  Svp r�ssayez", "Erreur", JOptionPane.ERROR_MESSAGE);
				}

				else {
					if ((!rbDispoBut.isSelected()) && (!rbNotDispo.isSelected())) {

						JOptionPane.showMessageDialog(btnAjouter,
								"Un ou plusieurs champs sont vide\n \n                  Svp r�ssayez", "Erreur",
								JOptionPane.ERROR_MESSAGE);

					}
					

					else {                 
					    if ((rbDispoBut.isSelected()) && (n<4)){
					    	    row[0] = String.format("%1$td/%1$tm/%1$tY",dateDispo.getDate());
						     	row[1] = PrefEnum.dispo_but;
							    model.addRow(row);
//					Service.preference.put(String.format("%1$td/%1$tm/%1$tY", dateDispo.getDate()),
					//				(PrefEnum) row[1]);
							  								    
							   String key = String.format("%1$td/%1$tm/%1$tY",dateDispo.getDate());;
							    List<PrefEnum> value = Service.preference.getOrDefault(key, new LinkedList<>());
							    value.add(PrefEnum.dispo_but);
							    Service.preference.put(key, value);
						}else {
							if (rbNotDispo.isSelected()) {
					    	        row[0] = String.format("%1$td/%1$tm/%1$tY", dateDispo.getDate());
							    	row[1] = PrefEnum.not_dispo;
							    	model.addRow(row);
							//	Service.preference.put(String.format("%1$td/%1$tm/%1$tY",dateDispo.getDate()),
										//(PrefEnum) row[1]);
								
								   String key = String.format("%1$td/%1$tm/%1$tY",dateDispo.getDate());
								    List<PrefEnum> value = Service.preference.getOrDefault(key, new LinkedList<>());
								    value.add(PrefEnum.not_dispo);
								    Service.preference.put(key, value);					    	
							}
						   }
					}
				}
					
			
			}
		});
		// boutton supprimer

		JButton btnSupprimerMembre = new JButton("Supprimer");
		btnSupprimerMembre.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		btnSupprimerMembre.setBounds(294, 210, 95, 23);
		contentPane.add(btnSupprimerMembre);

		btnSupprimerMembre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int indice = table1.getSelectedRow();
				if (indice >= 0) {
					model.removeRow(indice);
					// fonction pour supprimer disponibilit�
					Service.deletedisponiblity(row);
				} else {
					System.out.println("Delete Error");
				}
			}
		});

		// bouton valider
		JButton btnValider = new JButton("valider ");
		btnValider.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		btnValider.setBounds(235, 506, 89, 23);
		contentPane.add(btnValider);
		btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
				
				Service.docteurs.get(MembresDeGarde.table.getSelectedRow()).setPreference(Service.preference);

				setVisible(false);
				}
		   
		});

	}

	// preference set,get dans la classe Docteur

}
