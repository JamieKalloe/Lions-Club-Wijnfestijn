package IPSEN2.controllers.listeners;

import java.util.ArrayList;

/**
 * Created by Philip on 06-11-15.
 */
public interface TableViewListener {
    void setSelectedRows(ArrayList selectedRows);
    void setSelectedItem(int selectedItemId);
    void openEditMenu();
    void showToolTip();
    void hideToolTip();
}
