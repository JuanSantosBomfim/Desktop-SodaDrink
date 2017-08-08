package br.sodadrink.sp.controller;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class HoraAtual {

	public static void HoraAtualSistema(JLabel label){
		
		Timer timer = new Timer();
		
		final long SEGUNDOS = (1000 * 1);
		
		label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		TimerTask tarefa = new TimerTask() {
						
			@Override
			public void run() {

				DateFormat data = new SimpleDateFormat("dd/MMMM/yyyy");
				DateFormat hora = new SimpleDateFormat("HH:mm:ss"); 
				Date Sistema = new Date();
				
				label.setText("<html>&emsp&emsp&emsp&emsp&emsp&ensp&ensp&ensp&ensp "+hora.format(Sistema)+" <br> &emsp&emsp&emsp&emsp&emsp&ensp&ensp "+data.format(Sistema)+" &emsp&emsp&emsp&emsp&emsp&ensp&ensp</html>");
				
			}
		};
		
		timer.scheduleAtFixedRate(tarefa, 0, SEGUNDOS);
		
	}

}
