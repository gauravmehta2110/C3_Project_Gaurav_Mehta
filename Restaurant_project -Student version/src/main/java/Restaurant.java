import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    //opening time

    public boolean isRestaurantOpen() {
        if(getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime))
        return true;
        else {
            return false;
        }

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    //get menu
    public List<Item> getMenu() {
        return Collections.unmodifiableList(menu);

    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //TDD

    public int calculateBill (String[] orders) throws itemNotFoundException{
        if(orders.length>0){
            int total=0;
            for(String order:orders){
                Item item = findItemByName(order);
                if(item == null){
                    throw new itemNotFoundException("Item not Found");
                }
                else{
                    total = total + item.getPrice();
                }
            }
            return total;
        }
        else{
            return 0;
        }
    }

}
