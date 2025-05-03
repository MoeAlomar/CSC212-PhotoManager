public class Test {


    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (int i = 0; i < tagsArray.length; i++) {
            result.insert(tagsArray[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        // Create both manager types for comparison
        InvIndexPhotoManager invIndexManager = new InvIndexPhotoManager();
        PhotoManager regularManager = new PhotoManager();

        // Create photos
        Photo p1 = new Photo("owl.jpg", toTagsLinkedList("animal, bird"));
        Photo p2 = new Photo("Panda.jpg", toTagsLinkedList("panda, animal, grass"));
        Photo p3 = new Photo("Fox.jpg", toTagsLinkedList("animal, red, predator, fox"));
        Photo p4 = new Photo("Cheetah.jpg", toTagsLinkedList("animal, predator, cat, sand, cheetah"));
        Photo p5 = new Photo("Tiger.jpg", toTagsLinkedList("animal, predator, cat, grass, claws, tiger"));
        Photo p6 = new Photo("Tiger2.jpg", toTagsLinkedList("animal, predator, cat, grass, tiger"));
        Photo p7 = new Photo("Horse.jpg", toTagsLinkedList("animal, prey, fast, horse, grass, mountain"));
        Photo p8 = new Photo("Bird.jpg", toTagsLinkedList("animal, bird, prey"));
        Photo p9 = new Photo("Deer.jpg", toTagsLinkedList("animal, grass, prey, deer"));
        Photo p10 = new Photo("Tiger3", toTagsLinkedList("animal, predator, tiger, cat, black"));
        Photo p11 = new Photo("Squirrel.jpg", toTagsLinkedList("animal, squirrel, prey, grass,"));

        // Add to both managers
        regularManager.addPhoto(p1);
        regularManager.addPhoto(p2);
        regularManager.addPhoto(p3);
        regularManager.addPhoto(p4);
        regularManager.addPhoto(p5);
        regularManager.addPhoto(p6);
        regularManager.addPhoto(p7);
        regularManager.addPhoto(p8);
        regularManager.addPhoto(p9);
        regularManager.addPhoto(p10);
        regularManager.addPhoto(p11);

        invIndexManager.addPhoto(p1);
        invIndexManager.addPhoto(p2);
        invIndexManager.addPhoto(p3);
        invIndexManager.addPhoto(p4);
        invIndexManager.addPhoto(p5);
        invIndexManager.addPhoto(p6);
        invIndexManager.addPhoto(p7);
        invIndexManager.addPhoto(p8);
        invIndexManager.addPhoto(p9);
        invIndexManager.addPhoto(p10);
        invIndexManager.addPhoto(p11);


        // Create albums with the same conditions but different managers
        Album preys = new Album("preys", "prey", regularManager);
        Album predators = new Album("predators", "predator", invIndexManager);
        Album Tigers = new Album("Regular Tigers", "tiger AND predator",regularManager);
        Album InvTigers = new Album("InvIndex Tigers", "tiger AND predator",invIndexManager);

        // Compare performance
        preys.getPhotos();
        predators.getPhotos();
        Tigers.getPhotos();
        InvTigers.getPhotos();

        System.out.println("Regular Manager Comparisons: " + Tigers.getNbComps());
        System.out.println("Inverted Index Manager Comparisons: " + InvTigers.getNbComps());

        // Test other functionality
        System.out.println("Printing photos from regular album:");
        preys.printPhotos();

        System.out.println("Printing photos from inverted index album:");
        predators.printPhotos();

        InvTigers.printPhotos();
        invIndexManager.printKeys();
        Tigers.printPhotos();
        invIndexManager.deletePhotoEfficient(p8);
        invIndexManager.deletePhoto("owl.jpg");
        invIndexManager.printKeys();
    }

}
