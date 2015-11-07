package IPSEN2.models;

/**
 * Created by Philip on 06-11-15.
 */
public class TableViewItem {

     public boolean selected;
     public int id;

     public void setSelected(boolean selected){
          this.selected = selected;
     }

     public void setId(int id){
          this.id = id;
     }

     public boolean getSelected(){
          return this.selected;
     }

     public int getId() {
          return this.id;
     }


}
