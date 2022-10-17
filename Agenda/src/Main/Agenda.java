package Main;

import agenda.gui.AgendaFrame;

public class Agenda {

	public static void main(String[] args) {
		new Agenda().iniciarTela();

	}

	private void iniciarTela() {
		AgendaFrame frame = new AgendaFrame();
		frame.setVisible(true);
	}
}