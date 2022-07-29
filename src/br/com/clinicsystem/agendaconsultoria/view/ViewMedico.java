package br.com.clinicsystem.agendaconsultoria.view;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewMedico {

    public static void main(String[] args) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("2002-02-31 12:24:24");

        Date date = new Date();

        String data = dateFormat.format(date);
        System.out.println("frmtdDate: " + data);

    }

}
