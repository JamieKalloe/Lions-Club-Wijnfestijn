package IPSEN2.controllers.handlers;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.models.TableViewItem;
import javafx.animation.PauseTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by Philip on 06-11-15.
 */
public class TableViewSelectHandler extends ContentLoader{

    private TableView<TableViewItem> tableView;
    private TableViewItem item;
    private TableViewListener listener;
    private TableColumn checkBoxColumn;
    private ArrayList selectedRows;

    public TableViewSelectHandler(TableView<TableViewItem> tableView, TableViewListener listener) {
        this.tableView = tableView;
        this.listener = listener;
        selectedRows = new ArrayList();
        handleTableRows();
    }

    private void handleTableRows() {
        tableView.setRowFactory(table -> {
            TableRow row = new TableRow<>();
            row.getStyleClass().add("pane");

            Duration maxTimeBetweenSequentialClicks = Duration.millis(300);

            PauseTransition clickTimer = new PauseTransition(maxTimeBetweenSequentialClicks);
            final IntegerProperty sequentialClickCount = new SimpleIntegerProperty(0);
//            selectedRows = listener.getSelectedRows();

            clickTimer.setOnFinished(event -> {
                item = tableView.getSelectionModel().getSelectedItem();
                int count = sequentialClickCount.get();

                if (count == 1) {
                    tableView.getSelectionModel().getSelectedItem().setSelected(!item.getSelected());
                    if (!item.getSelected()){
                        listener.setSelectedItem(0);
                        selectedRows.remove(selectedRows.indexOf(item.getId()));
                        listener.setSelectedRows(selectedRows);
                        listener.hideToolTip();
                    }else {
                        listener.showToolTip();
                        selectedRows.add(item.getId());
                        listener.setSelectedItem(item.getId());
                    }

                    listener.setSelectedRows(selectedRows);
                    tableView.refresh();
                }
                if (count == 2) {
                    listener.setSelectedItem(item.getId());
                    listener.openEditMenu();
                }
                sequentialClickCount.set(0);
            });

            row.setOnMousePressed(event -> {
                if (row.getTableView().getSelectionModel().getSelectedItem() != null) {
                    sequentialClickCount.set(sequentialClickCount.get() + 1);
                    clickTimer.playFromStart();
                }

            });

            return row;
        });
    }



    private Callback createCheckBoxCellCallBack() {
        Callback checkBoxCellCallBack = new Callback<TableColumn.CellDataFeatures<TableViewItem, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<TableViewItem, CheckBox> cellDataFeatures) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(cellDataFeatures.getValue().getSelected());
                checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observableValue,
                                                         Boolean oldValue, Boolean newValue) -> {
                    cellDataFeatures.getValue().setSelected(newValue.booleanValue());

                    int selectedItemId = cellDataFeatures.getValue().getId();
                    if (newValue.booleanValue()) {
                        selectedRows.add(selectedItemId);
                        listener.setSelectedItem(selectedItemId);
                        listener.showToolTip();
                    } else if (!newValue.booleanValue()) {
                        selectedRows.remove(selectedRows.indexOf(selectedItemId));
                        listener.setSelectedItem(0);
                        listener.hideToolTip();
                    }
                    listener.setSelectedRows(selectedRows);
                });
                return new SimpleObjectProperty(checkBox);
            }
        };
        return  checkBoxCellCallBack;
    }

    private void createSelectAllCheckBox() {
        CheckBox selectAllCheckBox = new CheckBox();
        checkBoxColumn.setGraphic(selectAllCheckBox);
        selectAllCheckBox.setOnAction(event -> {
            boolean selected = selectAllCheckBox.isSelected();
            if (selected) {
                selectedRows.clear();
            }

            tableView.getItems().forEach(item -> {

                item.setSelected(selected);
                if (selected) {
                    selectedRows.add(item.getId());
                } else {
                    selectedRows.clear();
                }

                listener.setSelectedRows(this.selectedRows);
                tableView.refresh();
            });
        });
    }

    public void createCheckBoxColumn() {
        this.checkBoxColumn = tableView.getColumns().get(0);
        checkBoxColumn.setCellValueFactory(createCheckBoxCellCallBack());
        createSelectAllCheckBox();
    }
}
