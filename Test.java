public class Test {


    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (int i = 0; i < tagsArray.length; i++) {
            result.insert(tagsArray[i]);
        }
        return result;
    }

    public static void main(String []args)
    {
        InvIndexPhotoManager invIndexManager = new InvIndexPhotoManager();
     //   invIndexManager.addPhoto();
        PhotoManager manager = new PhotoManager();
        Photo p1 = new Photo("AboNasserWarrior.png", toTagsLinkedList("goat, player, warrior, AboNasser, legend"));
        manager.addPhoto(p1);
        Photo p2 = new Photo("AboNasserSalute.png", toTagsLinkedList("Salute, goat, player, legend"));
        manager.addPhoto(p2);
        Album album1 = new Album("A1", "goat AND player", manager);
        Album album2 = new Album("A2", "Salute", manager);
        album1.getPhotos();
        System.out.println(album1.getNbComps());
        System.out.println(album2.getNbComps());
        System.out.println("p1 Path: " +p1.getPath());
        album1.printPhotos();
        album2.printPhotos();
        System.out.println("Get album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());
        p1.printTags();



    }
}
