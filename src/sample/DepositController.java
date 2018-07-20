package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class DepositController {
    @FXML
    private TableView<Line> table;
    @FXML
    private TableColumn<Line, Integer> number;
    @FXML
    private TableColumn<Line, String> razm_vkl;
    @FXML
    private TableColumn<Line, String> nach_percent;
    @FXML
    private TableColumn<Line, String> na_chete;

    @FXML
    private Label total_nach_percent;
    @FXML
    private Label total_na_chete;

    private double CK, KP, PC, NP, monthly_percentage;

    private double T_N_P, T_N_C;


    private void initVar() {
        this.CK = Controller.CK;
        this.KP = Controller.KP;
        this.PC = Controller.PC;
        this.NP = 0;
        this.monthly_percentage = PC / 12;
    }

    private ObservableList<Line> items = FXCollections.observableArrayList();


    public void initialize() {

        initVar();
        for (int i = 0; i < KP; i++) {
            NP = CK * monthly_percentage / 100;
            items.add(new Line(i + 1, String.format(Locale.ENGLISH, "%.2f", CK), String.format(Locale.ENGLISH, "%.2f", NP), String.format(Locale.ENGLISH, "%.2f", CK + NP)));
            CK += NP;
            T_N_P += NP;
        }

        T_N_C = CK;

        table.itemsProperty().setValue(items);


        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Line, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Line, Integer> param) {
                return new SimpleObjectProperty<>(param.getValue().getNumber());
            }
        });

        razm_vkl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Line, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Line, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getRazm_vkl());
            }
        });

        nach_percent.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Line, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Line, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getNach_percent());
            }
        });

        na_chete.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Line, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Line, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getNa_chete());
            }
        });

        total_nach_percent.setText(String.valueOf(new BigDecimal(T_N_P).setScale(2, RoundingMode.UP).doubleValue()));
        total_na_chete.setText(String.valueOf(new BigDecimal(T_N_C).setScale(2, RoundingMode.UP).doubleValue()));

    }










}
