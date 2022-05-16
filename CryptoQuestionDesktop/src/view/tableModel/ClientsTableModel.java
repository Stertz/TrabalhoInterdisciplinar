/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelDominio.User;

/**
 *
 * @author lucas
 */

//classe que define a estrutura e tamanho da jtable
public class ClientsTableModel extends AbstractTableModel {
    // lista que tem os dados referentes a marca
    private ArrayList<User> listaUser;

    public ClientsTableModel(ArrayList<User> listaUser) {
        this.listaUser = listaUser;
    }

    // Informa o número de linhas que tem na jtable
    @Override
    public int getRowCount() {
        return listaUser.size();
    }

    // numero de colunas
    @Override
    public int getColumnCount() {
        return 5;
    }

    // devolve o conteudo de cada celula
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = listaUser.get(rowIndex);
        switch (columnIndex) {
            case 0: return user.getCodUser();
            case 1: return user.getIdUser();
            case 2: return user.getNameUser();
            case 3: return user.getEmail();
            case 4:
                SimpleDateFormat sdF = new SimpleDateFormat("dd/MM/yyyy");
                return sdF.format(user.getDateNasc());
            default: return "";
            
        }
    }

    // para informar o nome de cada coluna
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Código";
            case 1: return "ID";
            case 2: return "Nome";
            case 3: return "E-mail";
            case 4: return "Data Nascimento";
            default: return "Sem nome";
        }
    }
    
    public User getUser (int linha){
        return listaUser.get(linha);
    }
}
