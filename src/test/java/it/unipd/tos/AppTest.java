////////////////////////////////////////////////////////////////////
// Elia Rech 1123583
////////////////////////////////////////////////////////////////////

package it.unipd.tos;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;
//import org.junit.Assert;
//import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {
	
	private App bill= new App();
    private MenuItem panino_primavera=  new MenuItem(ItemType.PANINO, "panino primavera", 6.5);
    private MenuItem panino_vegetariano=  new MenuItem(ItemType.PANINO, "panino vegetariano", 7.5);
    private MenuItem olive_ascolane=  new MenuItem(ItemType.FRITTO, "olive ascolane", 5.5);
    private MenuItem arancino=  new MenuItem(ItemType.FRITTO, "arancino", 4.5);
    private MenuItem fanta=  new MenuItem(ItemType.BEVANDA, "fanta", 2.5);
    private MenuItem sprite = new MenuItem(ItemType.BEVANDA, "sprite", 2.5);
    
    //test Per la lista vuota
    @Test
    public void TestListaVuota() throws TakeAwayBillException {
        List<MenuItem> empty = new ArrayList<>();
        assertEquals(0, bill.getOrderPrice(empty),0);
    }
    
     //test per la lista totale
    @Test
    public void TestListaTotale() throws TakeAwayBillException {
        List<MenuItem> BillList = new ArrayList<>(Arrays.asList(panino_primavera, panino_vegetariano, olive_ascolane, arancino, fanta , sprite
        		));
        assertEquals(29.00, bill.getOrderPrice(BillList),0);
    }
    //test per il conto sotto i 10 euro
    @Test
    public void testSottoIDieciEuro() throws TakeAwayBillException {
        List<MenuItem> BillList = new ArrayList<>(Arrays.asList(fanta));
        assertEquals(3.00, bill.getOrderPrice(BillList),0);
    }
    //test per solo i panini in modo tale da applicare uno sconto su quello meno caro
    @Test
    public void testSoloPanino() throws TakeAwayBillException {
        List<MenuItem> BillList = new ArrayList<>(Arrays.asList(    panino_vegetariano, panino_primavera,
        	    panino_vegetariano, panino_primavera,    panino_vegetariano, panino_primavera));
        assertEquals(38.75, bill.getOrderPrice(BillList),0);
    }
    //test per applicare uno sconto del 10 % a una spesa maggiore di 50 tra panini e fritti
    @Test
    public void testPaniniESpesaSopraCinquantaEuro() throws TakeAwayBillException {
        List<MenuItem> BillList = new ArrayList<>(Arrays.asList(panino_primavera, panino_vegetariano, olive_ascolane, arancino,
        		panino_primavera, panino_vegetariano, olive_ascolane, arancino,
        		panino_primavera, panino_vegetariano, olive_ascolane, arancino));
        assertEquals(61.875, bill.getOrderPrice(BillList),0);
    }
    //test per gli ordini sopra i 30 ordini
   
    @Test(expected = TakeAwayBillException.class)
    public void testSopraTrentaOrdini() throws TakeAwayBillException {
        List<MenuItem> BillList = new ArrayList<>(Arrays.asList(panino_primavera, panino_vegetariano, olive_ascolane, arancino, fanta , sprite,
        		panino_primavera, panino_vegetariano, olive_ascolane, arancino, fanta , sprite,
        		panino_primavera, panino_vegetariano, olive_ascolane, arancino, fanta , sprite,
        		panino_primavera, panino_vegetariano, olive_ascolane, arancino, fanta , sprite,
        		panino_primavera, panino_vegetariano, olive_ascolane, arancino, fanta , sprite,
        		panino_primavera, panino_vegetariano, olive_ascolane, arancino, fanta , sprite));
                assertEquals(0,bill.getOrderPrice(BillList),0);
        }
        
    }


