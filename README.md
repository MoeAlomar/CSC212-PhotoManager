# CSC212-PhotoManager
Here we will implement our CSC212 Photo Manager Project using Java
public class InvIndexPhotoManager extends PhotoManager  {
    BST<LinkedList<Photo>> invIndexPhotos;
    // Constructor
    public InvIndexPhotoManager(){
        invIndexPhotos = new BST<LinkedList<Photo>>();
    }
    // Add a photo
    public void addPhoto(Photo p){
        if (p == null){
            java.lang.System.err.println("Can't pass Null Photo!");
            return;
        }
        LinkedList<String> tags = p.getTags();
        if (tags == null){
            System.err.println("Photo must have tags!");
            return;
        }


        tags.findfirst();
        while (true)
        {
            LinkedList<Photo> lPhotos = new LinkedList<>();

            if (invIndexPhotos.findkey(tags.retrieve())) {
                lPhotos = invIndexPhotos.retrieve();
                lPhotos.findfirst();
                while (!lPhotos.retrieve().equals(p)) {
                    if (lPhotos.last())
                        break;
                    lPhotos.findnext();
                }
                if (lPhotos.retrieve().equals(p)) {
                    System.err.println("Photo already exists in: " + tags.retrieve() + "!");
                }
                else {
                    lPhotos.insert(p);
                }
            }
            else {
                lPhotos.insert(p);
                invIndexPhotos.insert(tags.retrieve(),lPhotos);
            }
            if (tags.last())
                break;

            tags.findnext();
        }


    }


}