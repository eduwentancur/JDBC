
package estancias.entidades;


public class Comentario {
    
    private Integer id_comentario;
    private Casa id_casa;
    private String comentario;

    public Comentario() {
    }

    public Comentario(Integer id_comentario, Casa id_casa, String comentario) {
        this.id_comentario = id_comentario;
        this.id_casa = id_casa;
        this.comentario = comentario;
    }

    public Integer getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(Integer id_comentario) {
        this.id_comentario = id_comentario;
    }

    public Casa getId_casa() {
        return id_casa;
    }

    public void setId_casa(Casa id_casa) {
        this.id_casa = id_casa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
