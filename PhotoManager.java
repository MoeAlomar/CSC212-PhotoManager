public class PhotoManager <T> {
    public LinkedList<Photo> photos;
    public PhotoManager(){
    photos = new LinkedList<Photo>();
    }
    public T getPhotos()
    {
            return (T) photos;
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
        while( !photos.last()  && !photos.retrieve().getPath().equals(Path) ) {
            photos.findnext();
        }
        if (photos.retrieve().getPath().equals(Path)) {
            photos.remove();
            System.out.println("Photo has been deleted successfully!");
        }
        else
            System.out.println("Photo not found!");



    }
}
