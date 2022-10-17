package agenda.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class AgendaFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaFrame frame = new AgendaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgendaFrame() {

		setBounds(100, 100, 536, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		ListaEventosPanel listaEventosPanel = new ListaEventosPanel();
		tabbedPane.setBounds(10, 11, 500, 392);
		contentPane.add(tabbedPane);
		tabbedPane.addTab("Cadastro de Eventos", new CadastroEventoPanel(listaEventosPanel));
		tabbedPane.addTab("Lista de Eventos", listaEventosPanel);

	}
}
