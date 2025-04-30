public class Photo {
    String path;
    LinkedList<String> tags;

    public Photo(String path, LinkedList<String> tags )
    {
        this.path = path;
         this.tags= tags;

    }
    public String getPath(){
        return path;
    }

    public LinkedList<String> getTags(){
        return tags;
    }

    public boolean equals(Photo p){
        if(p == null)
            return false;
        if(this.path.equals(p.getPath()))
            return true;
        else
            return false;
    }
    // method that prints all tags of a photo.
    public void printTags(){
        LinkedList<String> Tags = tags;
        if (Tags.empty())
            throw new NullPointerException("Photo Has no Tags!");
        System.out.print("Tags in " + path + " \n| ");
        tags.findfirst();
        while(!Tags.last()){
            System.out.print(tags.retrieve() + " | ");
            tags.findnext();
        }
        System.out.println(tags.retrieve() + " | ");
    }

}
