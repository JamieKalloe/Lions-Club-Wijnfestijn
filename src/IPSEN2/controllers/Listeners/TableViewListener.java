package IPSEN2.controllers.listeners;

import java.util.ArrayList;

/**
 * Created by Philip on 06-11-15.
 */
public interface TableViewListener {
    /**
     * Sets selected rows.
     *
     * @param selectedRows the selected rows
     */
    void setSelectedRows(ArrayList selectedRows);

    /**
     * Sets selected item.
     *
     * @param selectedItemId the selected item id
     */
    void setSelectedItem(int selectedItemId);

    /**
     * Open edit menu.
     */
    void openEditMenu();
}
