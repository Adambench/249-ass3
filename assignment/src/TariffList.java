
import java.util.NoSuchElementException;

public class TariffList implements TariffPolicy {

    private TariffNode head;
    private int size;

    public TariffList(){
        this.head = null;
        this.size = 0;
    }

    public void printTariffList() {
        TariffNode current = this.head;
        while (current != null) {
            System.out.println(current.getTariff());
            current = current.getNext();
        }
    }

    public TariffList(TariffList tariffList){
        if(tariffList == null || tariffList.head == null){
            this.head = null;
            this.size = 0;
        } else{
            this.head = new TariffNode(tariffList.head);
            this.size = tariffList.size;
        }
    }
    
    public void addToStart(Tariff tariff){
        this.head = new TariffNode(tariff, this.head);
        this.size++;
    }

    public void insertAtIndex(Tariff tariff, int index){
        if(index < 0 || index >= this.size){
            throw new NoSuchElementException();
        }
        if(index == 0){
            addToStart(tariff);
            return;
        }

        TariffNode current = this.head;
        for(int i = 0; i < index-1; i++){
            current = current.next;
        }
        current.next = new TariffNode(tariff, current.next);
        this.size++;
    }

    public void deleteFromIndex(int index){
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if(index == 0){
            this.head = this.head.next;
            this.size--;
            return;
        }
        TariffNode current = this.head;
        for(int i = 0; i < index - 1; i++){
            current = current.next;
        }
        current.next = current.next.next;
        this.size--;
    }

    public void deleteFromStart (){
        if(this.head == null){
            throw new NoSuchElementException("List is empty");
        }
        this.head = this.head.next;
        this.size--;
    }

    public void replaceAtIndex(int index, Tariff tariff){
        if(index < 0 || index >= this.size){
            return;
        }
        TariffNode current = this.head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        current.setTariff(tariff);
    }

    public TariffNode find(String destinationCountry, String originCountry, String productCategory){
        TariffNode current = this.head;
        while (current != null) {
            if (current.getTariff().getDestinationCountry().equals(destinationCountry) &&
                current.getTariff().getOriginCountry().equals(originCountry) &&
                current.getTariff().getProductCategory().equals(productCategory)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean contains(String destinationCountry, String originCountry, String productCategory){
        TariffNode current = this.head;
        while (current != null) {
            if (current.getTariff().getDestinationCountry().equals(destinationCountry) &&
                current.getTariff().getOriginCountry().equals(originCountry) &&
                current.getTariff().getProductCategory().equals(productCategory)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean equals(TariffList tariffList){
        if(this.size != tariffList.size){
            return false;
        }
        TariffNode current1 = this.head;
        TariffNode current2 = tariffList.head;
        while(current1 != null && current2 != null){
            if(!current1.getTariff().equals(current2.getTariff())){
                return false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }
        return true;
    }


    @Override
    public String evaluateTrade(double proposedTariff, double minimumTariff) {
    if (proposedTariff >= minimumTariff) {
        return "accepted";
    } else if (proposedTariff >= 0.8 * minimumTariff) {
        return "conditionally accepted";
    } else {
        return "rejected";
    }
}




    public class TariffNode
    {

        private Tariff tariff;
        private TariffNode next;

        public TariffNode(){
            this.tariff = null;
            this.next = null;
        }
        public TariffNode(Tariff current, TariffNode next){
            this.tariff = new Tariff(current);
            this.next = next;
        }

        public TariffNode(TariffNode tariffNode){
            this.tariff = new Tariff(tariffNode.tariff);
            this.next = tariffNode.next;
        }


        public void clone(TariffNode tariffNode){
            this.tariff = tariffNode.tariff;
            this.next = tariffNode.next;
        }

        public boolean equals(TariffNode tariffNode){
            return this.tariff.equals(tariffNode.tariff) && this.next.equals(tariffNode.next);
        }

        public Tariff getTariff(){
            return this.tariff;
        }
        public TariffNode getNext(){
            return this.next;
        }

        public void setTariff(Tariff tariff){
            this.tariff = new Tariff(tariff);
        }

        public void setNext(TariffNode next){
            this.next = next;
        }

    }

}
