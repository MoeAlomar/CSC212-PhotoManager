public class Album {
    String name;
    String condition;
     PhotoManager manager;
    static int nbCompsCount = 0;
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
    public LinkedList<Photo> getPhotos()throws NullPointerException{
        String[] conditionsArray = condition.split(" AND ");
        if ( manager instanceof InvIndexPhotoManager) {
            InvIndexPhotoManager invManager = (InvIndexPhotoManager) manager;
            BST<LinkedList<Photo>> invIndex = invManager.getInvIndexPhotos();

            if (conditionsArray.length == 0) {
                return new LinkedList<Photo>();
            }
            else if (invIndex.empty()) {
                throw new NullPointerException("No photos in the manager");
            }

            LinkedList<Photo> result = new LinkedList<Photo>();

            if (invIndex.findkey(conditionsArray[0])) {
                LinkedList<Photo> photosWithTag = invIndex.retrieve();

                if (!photosWithTag.empty()){
                    photosWithTag.findfirst();
                    while(true){
                        result.insert(photosWithTag.retrieve());
                        if (photosWithTag.last())
                            break;
                        photosWithTag.findnext();
                    }
                }
            }
            for (int i = 1; i < conditionsArray.length; i++) {
                if (result.empty()) {
                    break;
                }

                if(! invIndex.findkey(conditionsArray[i])){
                    return new LinkedList<Photo>();
                }
                LinkedList<Photo> photosWithTag = invIndex.retrieve();
                if (photosWithTag.empty()){
                    return new LinkedList<Photo>();
                }

                LinkedList<Photo> filteredResult = new LinkedList<Photo>();
                result.findfirst();
                while(true){
                    Photo currentPhoto = result.retrieve();
                    photosWithTag.findfirst();

                    boolean hasTag = false;
                    while (true){
                        nbCompsCount++;
                        if ( photosWithTag.retrieve().getPath().equals(currentPhoto.getPath()) ){
                            hasTag = true;
                            break;
                        }
                        if (photosWithTag.last())
                            break;
                        photosWithTag.findnext();
                    }
                    if(hasTag) {
                    filteredResult.insert(currentPhoto);
                    }
                    if (result.last())
                        break;
                    result.findnext();
                }
            result = filteredResult;

            }
            return result;
        }
        else {
            if (manager.getPhotos().empty())
                throw new NullPointerException("No photos in the manager");
            // New LinkedList to store photos in
            LinkedList<Photo> albumPhotos = new LinkedList<Photo>();
            //to Split conditions into multiple Strings in an array

            // getting manager photos LL to run through
            LinkedList<Photo> photosTmp = manager.getPhotos();
            // Starting with the first photo 'head'
            photosTmp.findfirst();
            // Storing all tags of a photo in a LL to check them freely


            // loop to run through all photos of a LL
            while (true) {
                boolean allConditionsMet = true;
                // get the tags of the current photo.
                LinkedList<String> tagsTmp = photosTmp.retrieve().getTags();

                // loop to run through all conditions of an album and cmp to PhotoTags
                for (int i = 0; i < conditionsArray.length; i++) {
                    boolean conditionIsMet = false;
                    tagsTmp.findfirst();

                    // a loop to run through all tags and check if there exist a tag that == a condition
                    while (true) {
                        nbCompsCount++;
                        if (tagsTmp.retrieve().equals(conditionsArray[i])) {
                            conditionIsMet = true;
                            break;
                        }
                        if (tagsTmp.last())
                            break;
                        tagsTmp.findnext();
                    }
                    // if no condition is met in tags, break and go for the next photo.
                    if (!conditionIsMet) {
                        allConditionsMet = false;
                        break;
                    }
                }
                // if all conditions are met, add the photo to the new linkedlist to return later.
                if (allConditionsMet) {
                    albumPhotos.insert(photosTmp.retrieve());
                }
                // next photo
                if (photosTmp.last())
                    break;
                photosTmp.findnext();
            }

            return albumPhotos;
        }
    }

    public int getNbComps() {
        nbCompsCount = 0;
        getPhotos();
        return nbCompsCount;
    }
    // method  that prints all photos of an album.
    public void printPhotos() throws NullPointerException
    {
        LinkedList<Photo> photos = getPhotos();
        if(photos.empty())
            throw new NullPointerException("No photos in the manager!!");
        photos.findfirst();
        System.out.println("Photos in " + name + ":");
        while (!photos.last()) {
            System.out.println(photos.retrieve().getPath());
            photos.findnext();
        }
        System.out.println(photos.retrieve().getPath());
    }
}
