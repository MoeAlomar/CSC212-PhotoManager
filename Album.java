public class Album {
    String name;
    String condition;
    PhotoManager manager;
    public Album(String name, String condition, PhotoManager manager){
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }

    public String getName(){
        return name;
    }
    public String getCondition(){
        return condition;
    }
    public PhotoManager getManager(){
        return manager;
    }

    public LinkedList<Photo> getPhotos(){
        return manager.getPhotos();
    }

    public int getNbComps(){
        String[] conditionsArray = condition.split(" AND "); //to Split conditions into multiple Strings in an array
        for (int i = 0; i < conditionsArray.length; i++)
        {  while(!(manager.getPhotos()..equals(conditionsArray[i]))


        }

    }
}
