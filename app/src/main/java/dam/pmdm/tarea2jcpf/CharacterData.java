package dam.pmdm.tarea2jcpf;

/*
 * Objeto personaje para su definición de atributos con sus getters
 */
public class CharacterData {
    private final int image;
    private final String name;
    private final String description;
    private final String skills;

    //Constructor del personaje
    public CharacterData(int image, String name, String description, String skills) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.skills = skills;
    }


    //getters de las propiedades de los personajes
    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSkills() {
        return skills;
    }
}
