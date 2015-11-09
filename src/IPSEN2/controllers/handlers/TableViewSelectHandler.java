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

    /**
     * Instantiates a new Table view select handler.
     *
     * @param tableView the table view
     * @param listener  the listener
     */
    public TableViewSelectHandler(TableView<TableViewItem> tableView, TableViewListener listener) {
        this.tableView = tableView;
        this.listener = listener;
        selectedRows = new ArrayList();
        handleTableRows();
    }

    /**
     * Handles items that are being clicked inside TableView object<br><br>
     *
     * 1 mouse click selects or deselects clicked item <br>
     * 2 mouse clicks opens the edit menu in the TableViewListener
     */
    private void handleTableRows() {
        tableView.setRowFactory(table -> {
            TableRow row = new TableRow<>();
            row.getStyleClass().add("pane");

            Duration maxTimeBetweenSequentialClicks = Duration.millis(300);

            PauseTransition clickTimer = new PauseTransition(maxTimeBetweenSequentialClicks);
            final IntegerProperty sequentialClickCount = new SimpleIntegerProperty(0);

            clickTimer.setOnFinished(event -> {
                item = tableView.getSelectionModel().getSelectedItem();
                int count = sequentialClickCount.get();

                if (count == 1) {
                    tableView.getSelectionModel().getSelectedItem().setSelected(!item.getSelected());
                    if (!item.getSelected()){
                        listener.setSelectedItem(0);
                        selectedRows.remove(selectedRows.indexOf(item.getId()));
                        listener.setSelectedRows(selectedRows);
                    }else {
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

    /**
     * Creates checkbox cell and listener for all items inside tableView
     *
     * @return returns the CallBack of the attached checkbox cell
     */
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
                    } else if (!newValue.booleanValue()) {
                        selectedRows.remove(selectedRows.indexOf(selectedItemId));
                        listener.setSelectedItem(0);
                    }
                    listener.setSelectedRows(selectedRows);
                });
                return new SimpleObjectProperty(checkBox);
            }
        };
        return  checkBoxCellCallBack;
    }

    /**
     * Creates a checkbox that is able to select and deselect all items in tableView
     */
    public void createSelectAllCheckBox() {
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

    /**
     * Create check box column.
     */
    public void createCheckBoxColumn() {
        this.checkBoxColumn = tableView.getColumns().get(0);
        checkBoxColumn.setCellValueFactory(createCheckBoxCellCallBack());
    }
}
