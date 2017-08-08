package br.sodadrink.sp.controller;

import javax.swing.JComboBox;
import br.sodadrink.sp.model.ItemComboBox;

public class ComboboxFuncionalidade {

	public static void selecionaComboboxUsandoValorDoBanco(JComboBox<ItemComboBox> nomeDoCombobox, int id_banco) {

		ItemComboBox comboboxComOsItens = (ItemComboBox) nomeDoCombobox.getSelectedItem();

		int id_do_item = comboboxComOsItens.getId_item();
		int posicao_do_item = comboboxComOsItens.getPosicao();

		int proximoItemDoCombobox = 0;
		
		if(id_do_item != id_banco){
			
			while (id_do_item != id_banco) {

				nomeDoCombobox.setSelectedIndex(proximoItemDoCombobox);

				comboboxComOsItens = (ItemComboBox) nomeDoCombobox.getSelectedItem();
				id_do_item = comboboxComOsItens.getId_item();

				if (id_do_item == id_banco) {

					posicao_do_item = comboboxComOsItens.getPosicao();

					nomeDoCombobox.setSelectedIndex(posicao_do_item);

				}

				proximoItemDoCombobox = proximoItemDoCombobox + 1;

			}
			
		}/*else{
			
			//nomeDoCombobox.setSelectedIndex(proximoItemDoCombobox);
			JOptionPane.showMessageDialog(null, "Erro Combobox!");
			
		}*/
		

	}

}
