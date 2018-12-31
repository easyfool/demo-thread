package com.wangfengbabe.demo.thread.interview;

import java.util.concurrent.ArrayBlockingQueue;
import lombok.Data;

@Data
class BookShelf {

    private ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

    public void put(String person, String book) {
        System.out.println("person:" + person + " put book " + book + " into bookshelf.");
        try {
            arrayBlockingQueue.put(book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void take(String person) {
        try {
            String book = arrayBlockingQueue.take();
            System.out.println("person:" + person + " take book " + book + " from bookshelf.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Taker implements Runnable {

    private String name;

    private BookShelf bookShelf;

    public Taker(String name, BookShelf bookShelf) {
        this.name = name;
        this.bookShelf = bookShelf;
    }

    @Override
    public void run() {
        bookShelf.take(name);

    }
}

class Putter implements Runnable {

    private String name;
    private String bookName;

    private BookShelf bookShelf;

    public Putter(String name, String bookName, BookShelf bookShelf) {
        this.name = name;
        this.bookName = bookName;
        this.bookShelf = bookShelf;
    }

    @Override
    public void run() {
        bookShelf.put(name, bookName);
    }
}

public class Test4 {

    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();
        for (int i = 0; i < 10; i++) {
            Putter putter = new Putter("person " + i, "book " + i, bookShelf);
            Taker taker = new Taker("person " + i, bookShelf);
            new Thread(putter).start();
            new Thread(taker).start();

        }
    }

}
