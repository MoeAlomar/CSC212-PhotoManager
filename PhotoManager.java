public class PhotoManager {
    LinkedList<Photo> photos;
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
        while(!photos.retrieve().equals(Path))
            photos.findnext();
        photos.remove();

    }
}
