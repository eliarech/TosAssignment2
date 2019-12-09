////////////////////////////////////////////////////////////////////
// Elia Rech 1123583
////////////////////////////////////////////////////////////////////
package it.unipd.tos;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;
import java.util.List;
public class App implements TakeAwayBill {
@Override 
public double getOrderPrice(List<MenuItem> items_ordered) 
throws TakeAwayBillException {
//NUMERO TOTALE ORDINAZIONE
double count = 0;
//IL COSTO MINIMO DEL PANINO
double minP = Double.MAX_VALUE;
//NUMERO TOTALE PANINI
int nP = 0;
        //NUMERO TOTALE PANINO E FRITTO
        double totalepf=0;
        //Calcolo totale
        for (int i =0;i < items_ordered.size(); i++) {
            count += items_ordered.get(i).getPrice();

            if (items_ordered.get(i).getItemType() == ItemType.PANINO) {
                nP++;
                if (items_ordered.get(i).getPrice() < minP) {
                    minP = items_ordered.get(i).getPrice();
                }
            }

            if (items_ordered.get(i).getItemType() == ItemType.PANINO || items_ordered.get(i).getItemType() == ItemType.FRITTO) {
                totalepf += items_ordered.get(i).getPrice();
            }
        }

        //caso 2
        if (nP > 5) {
            count -= minP;
            count += minP * 0.5;
        }
        
        //Se l’importo totale delle ordinazioni (Panini e Fritti) supera i 50 euro viene fatto il 10% di sconto;
        if (totalepf > 50) {
            count = count*0.9;
        }
        
        //Il caso per  un’ordinazione con più di 30 elementi (se accade prevedere un messaggio d’errore);
        if (items_ordered.size() > 30) {
            throw new TakeAwayBillException();
        }
       
        //Se l’importo totale è inferiore a 10 € viene aggiunta una commissione di 0,50 €
        if (count < 10 && count > 0) {
            count =count + 0.5;
        }
        return count;
}
}
