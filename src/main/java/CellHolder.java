public class CellHolder {
    private  long address;
    private long min;
    private long max;
    private int used;
    private boolean tab;
    private boolean iterator;
    private boolean init;
    private String name;

    public CellHolder(){};


    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public boolean isTab() {
        return tab;
    }

    public void setTab(boolean tab) {
        this.tab = tab;
    }

    public boolean isIterator() {
        return iterator;
    }

    public void setIterator(boolean iterator) {
        this.iterator = iterator;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }
}
