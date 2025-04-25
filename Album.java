public class Album {
    String name;
    String condition;
    PhotoManager manager;
    static int nbCompscount = 0;
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
        if(manager.getPhotos().empty())
            throw new NullPointerException("No photos in the manager");
        // New LinkedList to store photos in
        LinkedList<Photo> albumPhotos = new LinkedList<Photo>();
        //to Split conditions into multiple Strings in an array
        String[] conditionsArray = condition.split(" AND ");
        // getting manager photos LL to run through
        LinkedList<Photo> photosTmp = manager.getPhotos();
        // Starting with the first photo 'head'
        photosTmp.findfirst();
        // Storing all tags of a photo in a LL to check them freely


        // loop to run through all photos of a LL
        while (photosTmp.retrieve() != null) {
            boolean allConditionsMet = true;
            // get the tags of the current photo.
            LinkedList<String> tagsTmp = photosTmp.retrieve().getTags();

            // loop to run through all conditions of an album and cmp to PhotoTags
            for (int i = 0; i < conditionsArray.length; i++) {
                boolean conditionIsMet = false;
                tagsTmp.findfirst();

                // a loop to run through all tags and check if there exist a tag that == a condition
                while (( tagsTmp.retrieve() != null) ) {
                    nbCompscount++;
                    if(tagsTmp.retrieve().equals(conditionsArray[i])) {
                        conditionIsMet = true;
                        break;
                    }
                    tagsTmp.findnext();
                }
                // if no condition is met in tags, break and go for the next photo.
                if (!conditionIsMet) {
                    allConditionsMet = false;
                    break;
                }
            }
            // if all conditions are met, add the photo to the new linkedlist to return later.
            if (allConditionsMet){
                albumPhotos.insert(photosTmp.retrieve());
            }
            // next photo
            photosTmp.findnext();
        }

        return albumPhotos;
    }

    public int getNbComps() {
        nbCompscount = 0;
        getPhotos();
        return nbCompscount;
    }
}
