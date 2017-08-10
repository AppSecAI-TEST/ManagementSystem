import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CheckBoxModelListener implements TableModelListener {
   @Override
   public void tableChanged(TableModelEvent e) {
      int row = e.getFirstRow();
      int column = e.getColumn();
      if(column == 0){
         TableModel model = (TableModel)e.getSource();
         String subject = (String) model.getValueAt(row, 2);
         Boolean checked = (Boolean) model.getValueAt(row, column);
         
         if(checked){
            System.out.println(subject + ": "+ true);
         }else{
            System.out.println(subject + ": "+ false);
         }
         
      }
   }

}