package mx.ucol;

public class Drop {
    private String messages[] = new String[10];
    private boolean empty = true;
    private boolean full = false;
    private int position = 0;

    public synchronized String take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Someone interrupted this thread." + e);
            }
        }

        String response = messages[position - 1];
        position--;

        if(position == 0) empty = true;

        full = false;
        notifyAll();

        return response;
    }

    public synchronized void put(String message) {
        while (full) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        messages[position] = message;
        position++;

        if(position == 9) full = true;

        empty = false;
        notifyAll();
    }
}
