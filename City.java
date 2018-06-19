package Tasks2;

public class City {
    private String name;
    private int index = 1;//увеличивается с каждым новым городом
    private Route routes[];

    City(String name, int index, int neighbors) {
        this.name = name;
        this.index = index;
        this.routes = new Route[neighbors];
    }

    public Route[] getRoutes() {
        return routes;
    }

    public int getID() {
        return index;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeighbors() {
        return this.routes.length;
    }

    public void insertNeighbors(Route neighbors, int i) {
        this.routes[i] = neighbors;
    }

}
