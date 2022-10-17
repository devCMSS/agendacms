package agenda.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import agenda.io.AgendaIO;
import agenda.utils.AgendaUtils;
import agenda.utils.PeriodicidadeEnum;
import agenda.vo.Evento;

import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class CadastroEventoPanel extends JPanel {
	private JTextField tfDescEvento;
	private JTextField tfEmailEvento;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfDtEvento;
	public JCheckBox chckbxAlarme;
	private JRadioButton rdbtnUmaVez;
	private ListaEventosPanel listaEventos;
	private JRadioButton rdbUmaVez;
	private JRadioButton rdbDiario;
	private JRadioButton rdbSemanal;
	private JRadioButton rdbMensal;

	/**
	 * Create the panel.
	 * 
	 * @param listaEventosPanel
	 */

	public CadastroEventoPanel(ListaEventosPanel listaEventos) {
		this.listaEventos = listaEventos;
		setLayout(null);

		JLabel lblDescEvento = new JLabel("Descrição do Evento");
		lblDescEvento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescEvento.setBounds(10, 30, 114, 14);
		add(lblDescEvento);

		tfDescEvento = new JTextField();
		tfDescEvento.setBounds(10, 55, 182, 20);
		add(tfDescEvento);
		tfDescEvento.setColumns(10);

		JLabel lblEncaminharEmail = new JLabel("Encaminhar E-mail");
		lblEncaminharEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEncaminharEmail.setBounds(10, 129, 104, 14);
		add(lblEncaminharEmail);

		tfEmailEvento = new JTextField();
		tfEmailEvento.setBounds(124, 126, 240, 20);
		add(tfEmailEvento);
		tfEmailEvento.setColumns(10);

		JLabel lblDataEvento = new JLabel("Data do Evento");
		lblDataEvento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataEvento.setBounds(10, 94, 86, 14);
		add(lblDataEvento);

		tfDtEvento = new JTextField();
		tfDtEvento.setColumns(10);
		tfDtEvento.setBounds(106, 91, 86, 20);
		add(tfDtEvento);

		JRadioButton rdbSemanal = new JRadioButton("Semanal");
		this.rdbSemanal = rdbSemanal;
		buttonGroup.add(rdbSemanal);
		rdbSemanal.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbSemanal.setBounds(10, 212, 75, 23);
		add(rdbSemanal);

		JRadioButton rdbMensal = new JRadioButton("Mensal");
		this.rdbMensal = rdbMensal;
		buttonGroup.add(rdbMensal);
		rdbMensal.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbMensal.setBounds(87, 212, 65, 23);
		add(rdbMensal);

		JLabel lblPeriodicidadeEvento = new JLabel("Periodicidade do Evento");
		lblPeriodicidadeEvento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPeriodicidadeEvento.setBounds(10, 165, 135, 14);
		add(lblPeriodicidadeEvento);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setBounds(295, 270, 69, 23);
		add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpar.setBounds(374, 270, 71, 23);
		add(btnLimpar);

		JRadioButton rdbtnUmaVez = new JRadioButton("Uma vez");
		this.rdbtnUmaVez = rdbtnUmaVez;
		buttonGroup.add(rdbtnUmaVez);
		rdbtnUmaVez.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnUmaVez.setBounds(49, 186, 75, 23);
		add(rdbtnUmaVez);

		JCheckBox chckbxAlarme = new JCheckBox("Alarme");
		this.chckbxAlarme = chckbxAlarme;
		chckbxAlarme.setBounds(17, 252, 97, 23);
		add(chckbxAlarme);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chamaCadastroEvento();
			}
		});

		btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});

	}

	private void chamaCadastroEvento(){
		  AgendaIO io = new AgendaIO();
		  Evento evento = new Evento();

		  Object[] novaLinha = new Object[5];

		  evento.setDataEvento(AgendaUtils.getDateFromString(tfDtEvento.getText()));
		  evento.setDescEvento(tfDescEvento.getText());
		  evento.setAlarme(chckbxAlarme.isSelected() ? 1 : 0);
		  evento.setEmailEncaminhar(tfEmailEvento.getText());

		  novaLinha[0] = tfDtEvento.getText();
		  novaLinha[1] = tfDescEvento.getText();
		  novaLinha[4] = chckbxAlarme.isSelected() ? "LIGADO" : "DESLIGADO";
		  novaLinha[3] = tfEmailEvento.getText();

		  if(rdbtnUmaVez.isSelected()){
		   evento.setPeriodicidade(PeriodicidadeEnum.UNICO);
		   novaLinha[2] = PeriodicidadeEnum.UNICO;
		  }
		  else if(rdbSemanal.isSelected()){
		   evento.setPeriodicidade(PeriodicidadeEnum.SEMANAL);
		   novaLinha[2] = PeriodicidadeEnum.SEMANAL;
		  }
		  else {
		   evento.setPeriodicidade(PeriodicidadeEnum.MENSAL);
		   novaLinha[2] = PeriodicidadeEnum.MENSAL;
		  }

		  try {
		   io.gravarEvento(evento);
		  }catch(Exception ex){
		   JOptionPane.showMessageDialog(null, "ERRO", ex.getMessage(),
		   JOptionPane.ERROR_MESSAGE);
		  }
		  listaEventos.addNewRow(novaLinha);
		  limparCampos();
		}

	private void limparCampos() {
		tfDtEvento.setText("");
		tfDescEvento.setText("");
		chckbxAlarme.setSelected(false);
		tfEmailEvento.setText("");
		rdbtnUmaVez.setSelected(true);
	}

}
