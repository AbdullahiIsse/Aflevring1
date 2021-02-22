package temperature.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import temperature.mediator.TemperatureModel;
import temperature.model.Temperature;
import temperature.model.TemperatureList;

public class TemperaturViewModel {


    private final TemperatureModel model;
    private TemperatureList temperatureList;
    private StringProperty Temperatur;
    private StringProperty id;
    private StringProperty filterlabel;
    private String thermometerId;

    private StringProperty temperatur1;
    private StringProperty temperatur2;


    /*public void getLastTemp() {
    getLastTemp() = temperatureList.getLastTemperature();
        }*/

        public TemperaturViewModel(TemperatureModel model) {
            this.model=model;
            Temperatur = new SimpleStringProperty();
            id = new SimpleStringProperty();
            filterlabel = new SimpleStringProperty();
            temperatur1 = new SimpleStringProperty();
            temperatur2 = new SimpleStringProperty();
        }

    public StringProperty temperaturProperty() {
        return Temperatur;
    }
    public StringProperty temperaturProperty1() {
        return temperatur1;
    }
    public StringProperty temperaturProperty2() {
        return temperatur2;
    }


    public StringProperty filterProperty() {
        return id;
    }

    public StringProperty filterLabelProperty() {
        return filterlabel;
    }


    public void update() {
        Temperature t = model.getLastInsertedTemperature("0");
        try{
            Temperatur.setValue(("Undendørs") + ": " + model.getLastInsertedTemperature("0").getValue());
            temperatur1.setValue(("Langt fra radiator") + ": " + model.getLastInsertedTemperature("1").getValue());
            temperatur2.setValue(("tæt på radiator") + ": " + model.getLastInsertedTemperature("2").getValue());
        }
        catch (NullPointerException e)
        {

            unFilter();
        }

    }

    public void unFilter() {
        String oldValue = filterlabel.getValue();
       // if (oldValue.equals("All"))
        //{
        //    oldValue = null;
        //}
        thermometerId = id.getValue();
        if (thermometerId.isEmpty())
        {
            thermometerId = null;
            filterlabel.setValue("All");
        }
        else
        {
            filterlabel.setValue(thermometerId);
        }
        id.setValue(null);
        update();
    }
}