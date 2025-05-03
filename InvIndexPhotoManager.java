
public class InvIndexPhotoManager extends PhotoManager {
    BST<LinkedList<Photo>> invIndexPhotos;

    // Constructor
    public InvIndexPhotoManager() {
        invIndexPhotos = new BST<LinkedList<Photo>>();
    }

    public void printKeys() {
        System.out.println("Keys: ");
        invIndexPhotos.traverse(Order.INORDER);
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

    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getInvIndexPhotos() {
        return invIndexPhotos;
    }

    // Delete a photo
    public void deletePhoto(String path) {
        if (invIndexPhotos.empty()) {
            System.out.println("No photos found in the manager!");
            return;
        }

        LinkedList<String> keysToRemove = new LinkedList<>();

        // Traverse BST and delete photo
        deletePhotoFromBST(path, keysToRemove);

        // Remove any empty tag lists
        removeEmptyTagLists(keysToRemove);

       /* if (photoFound.get()) {
            System.out.println("Photo has been deleted successfully!");
        } else {
            System.out.println("Photo not found!");
        }*/

    }

    public void deletePhotoEfficient(Photo p){
        if (invIndexPhotos.empty()) {
            System.out.println("No photos found in the manager!");
            return;
        }
            Photo photoToDelete = p;
        LinkedList<String> keysToRemove = new LinkedList<>();
        LinkedList<String> tags = photoToDelete.getTags();
        tags.findfirst();
        String[] tmpTags = new String[1000];
        int size = 0;
        while (true) {

            tmpTags[size++] = tags.retrieve();
            if (tags.last())
                break;
            tags.findnext();
        }

        for ( int i = 0; i < size; i++) {
            if (invIndexPhotos.findkey(tmpTags[i])) {
                LinkedList<Photo> photoList = invIndexPhotos.retrieve();
                photoList.findfirst();
                while( !photoList.retrieve().getPath().equals(photoToDelete.getPath())) {
                    if (photoList.last()) {
                        break;
                    }
                    photoList.findnext();
                }
                if(photoList.retrieve().getPath().equals(photoToDelete.getPath())){
                    System.out.println("| " + photoList.retrieve().getPath() + " has been deleted successfully from: " + tmpTags[i] + "!");
                    photoList.remove();
                if (photoList.empty()) {
                    keysToRemove.insert(tmpTags[i]);
                    }
                }
            }
        }
        removeEmptyTagLists(keysToRemove);

    }

    private void deletePhotoFromBST(String path, LinkedList<String> keysToRemove) {
        // This method will iterate through all tags in the BST
        LinkedList<String> allTags = getAllBSTKeys();
        if (allTags.empty()) {
            return;
        }
        allTags.findfirst();
        while(true){
            String tag = allTags.retrieve();

            if(invIndexPhotos.findkey(tag)){
                LinkedList<Photo> photoList = invIndexPhotos.retrieve();
                photoList.findfirst();

                while(!photoList.retrieve().getPath().equals(path)){
                    if (photoList.last())
                        break;
                    photoList.findnext();
                }
                if (photoList.retrieve().getPath().equals(path)) {
                    System.out.println("| " + photoList.retrieve().getPath() + " has been deleted successfully from: " + tag + "!");
                    photoList.remove();
                    if (photoList.empty()) {
                        keysToRemove.insert(tag);
                    }
                }
            }
            if(allTags.last())
                break;
            allTags.findnext();

        }

    }

    private void removeEmptyTagLists(LinkedList<String> keysToRemove) {
        if (keysToRemove.empty()) return;

        keysToRemove.findfirst();
        while (true) {
            invIndexPhotos.remove_key(keysToRemove.retrieve());
            if (keysToRemove.last())
                break;

            keysToRemove.findnext();
        }
    }

    private LinkedList<String> getAllBSTKeys() {
        LinkedList<String> keys = new LinkedList<>();
        collectAllBSTKeysHelper(invIndexPhotos.root, keys);
        return keys;
    }

    private void collectAllBSTKeysHelper(BSTNode<LinkedList<Photo>> node, LinkedList<String> keys) {
        if (node == null) return;

        // Process left subtree
        collectAllBSTKeysHelper(node.left, keys);

        // Add current node's key to keys
        keys.insert(node.key);

        // Process right subtree
        collectAllBSTKeysHelper(node.right, keys);
    }







}