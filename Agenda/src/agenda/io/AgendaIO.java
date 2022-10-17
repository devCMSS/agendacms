package agenda.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.JOptionPane;

import agenda.utils.PeriodicidadeEnum;
import agenda.vo.Evento;

@SuppressWarnings("unused")
public class AgendaIO {

	private final String DIRETORIO = ".";
	private final String ARQUIVO = "eventos.txt";

	public void gravarEvento(Evento evento) throws Exception {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRETORIO, ARQUIVO), true));
			writer.write(evento.toString(), 0, evento.toString().length());
			writer.newLine();
			writer.close();

		} catch (FileNotFoundException fnfe) {
			throw new Exception("Arquivo não encontrado");

		} catch (Exception ex) {
			throw new Exception("Problemas na gravação do arquivo");
		}
	}

	public Vector<Vector<Object>> getEventos() throws Exception {
		Vector<Vector<Object>> lista = new Vector<Vector<Object>>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(DIRETORIO, ARQUIVO)));
			String linha = reader.readLine();
			while (linha != null) {
				Vector<Object> eventoVector = new Vector<Object>();
				String[] tokens = linha.split(";");
				eventoVector.add(tokens[0]);
				eventoVector.add(tokens[1]);
				eventoVector.add(PeriodicidadeEnum.valueOf(tokens[2]));
				eventoVector.add(tokens[3]);
				eventoVector.add(tokens[4].equals("1") ? "LIGADO" : "DESLIGADO");

				lista.add(eventoVector);
				linha = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException fnfe) {
			throw new Exception("Arquivo não encontrado");

		} catch (Exception ex) {
			throw new Exception("Problemas de leitura no arquivo de eventos");

		}
		return lista;
	}

}