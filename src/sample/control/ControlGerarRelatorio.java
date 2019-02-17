package sample.control;

import com.jfoenix.controls.JFXDatePicker;
import sample.model.Relatorio;

public class ControlGerarRelatorio {

    public JFXDatePicker data;

    public void processResult(){
        Relatorio relatorio= new Relatorio();

        relatorio.gerarRelatorio(data.getValue());
    }
}
