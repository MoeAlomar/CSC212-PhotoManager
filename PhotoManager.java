public class PhotoManager {
    public LinkedList<Photo> photos;
    public PhotoManager(){
    photos = new LinkedList<Photo>();
    }
    public LinkedList<Photo> getPhotos()
    {
    return photos;
    }

    public void addPhoto(Photo p)
    {
    photos.insert(p);
    }

    public void deletePhoto(String Path)
    {
        if(photos.empty())
            return;
        photos.findfirst();
        while( photos.retrieve() != null && !photos.retrieve().getPath().equals(Path))
            photos.findnext();
        if(photos.retrieve() == null)
            System.out.println("Photo not found!");

        photos.remove();
        System.out.println("Photo has been deleted");

    }
}
