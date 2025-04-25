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
        PhotoManager manager = new PhotoManager();
        Photo p1 = new Photo("AboNasserWarrior.jpg", toTagsLinkedList("goat, player, warrior, AboNasser, legend"));
        manager.addPhoto(p1);
        Photo p2 = new Photo("AboNasserSalute.jpeg", toTagsLinkedList("Salute, goat, player, legend"));
        manager.addPhoto(p2);
        Album album1 = new Album("A1", "goat AND player", manager);
        Album album2 = new Album("A2", "Salute", manager);

        System.out.println("p1 Path: " +p1.getPath());

    }
}
