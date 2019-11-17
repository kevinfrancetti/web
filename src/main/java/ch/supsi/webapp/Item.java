package ch.supsi.webapp;

class Item {


    Integer id = null;
    String name = null;
    String description = null;
    String author = null;

    public String getName(){
        return name;
    }

    public Item(String name, String description, String author){
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public void setName(String s){
        name = s;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String s){
        description = s;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String a){
        author = a;
    }

    public boolean equals(Object o){
        Item i = (Item) o;
        if(!name.equals(i.name)) return false;
        if(!description.equals(i.description)) return false;
        if(!author.equals(i.author)) return false;
        return true;
    }

    public String toString(){
        return name + "\n" + description + "\n" + author;
    }

}
