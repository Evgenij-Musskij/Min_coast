package Tasks2;

public class Route {
    private int indexFrom;
    private int indexWhere;
    private int price;

    public Route() {
        this.indexFrom = -1;
        this.indexWhere = -1;
        this.price = -1;
    }

    public Route(int indexFrom, int indexWhere) {
        this.indexFrom = indexFrom;
        this.indexWhere = indexWhere;
        this.price = -1;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj == null) {
           return isEqual;
        }else if (obj instanceof Route) {
            Route temp = (Route)obj;
            isEqual = this.indexWhere == temp.getIndexWhere() && this.indexFrom == temp.getIndexFrom();
        }
        return isEqual;
    }

    public int getIndexWhere() {
        return indexWhere;
    }

    public void setIndexWhere(int indexWhere) {
        if (indexWhere >= 1) {
            this.indexWhere = indexWhere;
        } else {
            System.out.println("Индекс города связанного с данным должен быть >=1");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price > 0 & price < 200000) {
            this.price = price;
        }
        else{
            System.out.println("Некоректно задана стоимость перевозки");
        }
    }

    public int getIndexFrom() {
        return indexFrom;
    }

    public void setIndexFrom(int indexFrom) {
        this.indexFrom = indexFrom;
    }
}
