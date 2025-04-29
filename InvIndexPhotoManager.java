public class InvIndexPhotoManager extends PhotoManager {
    BST<LinkedList<Photo>> invIndexPhotos;

    // Constructor
    public InvIndexPhotoManager() {
        invIndexPhotos = new BST<LinkedList<Photo>>();
    }

    // Add a photo
    public void addPhoto(Photo p) {
        if (p == null) {
            System.out.println("Can't pass Null Photo!");
            return;
        }
        LinkedList<String> tags = p.getTags();
        if (tags.empty()) {
            System.out.println("Photo must have tags!");
            return;
        }


        tags.findfirst();
        while (true) {
            LinkedList<Photo> listOfPhotos;

            if (invIndexPhotos.findkey(tags.retrieve())) {
                listOfPhotos = invIndexPhotos.retrieve();
                listOfPhotos.findfirst();
                while (!listOfPhotos.retrieve().equals(p)) {
                    if (listOfPhotos.last())
                        break;
                    listOfPhotos.findnext();
                }
                if (listOfPhotos.retrieve().equals(p)) {
                    System.err.println("Photo already exists in: " + tags.retrieve() + "!");
                } else {
                    listOfPhotos.insert(p);
                }
            } else {
                 listOfPhotos = new LinkedList<>();
                listOfPhotos.insert(p);
                invIndexPhotos.insert(tags.retrieve(), listOfPhotos);
            }
            if (tags.last())
                break;

            tags.findnext();
        }


    }


    // Delete a photo
    public void deletePhoto(String path) {
        // we will look in each key's linkedlist for this path and we'll remove ir after doing so if the linkedlist is empty we will delete the key itself !
        invIndexPhotos.traverse(Order.INORDER);

    }

    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getInvIndexPhotos() {
        return null;
    }

}