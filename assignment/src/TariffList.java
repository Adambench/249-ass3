public class TariffList implements TariffPolicy {

    private TariffNode head;
    private int size;

    public TariffList(){
        this.head = null;
        this.size = 0;
    }


    public TariffList(TariffList tariffList){
        this.head = tariffList.head;
        this.size = tariffList.size;
    }
    
    public void addToStart(TariffNode tariffNode){
        this.head = new TariffNode(tariffNode.tariff, tariffNode.next);
        
        this.size++;
    }






    public class TariffNode
    {

        private TariffNode tariff;
        private TariffNode next;

        public TariffNode(){
            this.tariff = null;
            this.next = null;
        }
        public TariffNode(TariffNode tariff, TariffNode next){
            this.tariff = tariff;
            this.next = next;
        }

        public TariffNode(TariffNode tariffNode){
            this.tariff = tariffNode.tariff;
            this.next = tariffNode.next;
        }


        public void clone(TariffNode tariffNode){
            this.tariff = tariffNode.tariff;
            this.next = tariffNode.next;
        }

        public boolean equals(TariffNode tariffNode){
            return this.tariff.equals(tariffNode.tariff) && this.next.equals(tariffNode.next);
        }

        public TariffNode getTariff(){
            return this.tariff;
        }
        public TariffNode getNext(){
            return this.next;
        }

        public void setTariff(TariffNode tariff){
            this.tariff = tariff;
        }

        public void setNext(TariffNode next){
            this.next = next;
        }



    }

    
}
