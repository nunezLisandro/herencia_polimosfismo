import java.util.ArrayList;

// Clase abstracta CanalNotificacion
abstract class CanalNotificacion {
    String usuario;
    String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    abstract void enviarNotificacion();
    abstract void personalizarMensaje(String nuevoMensaje);

    @Override
    public String toString() {
        return "Usuario: " + usuario + ", Mensaje: " + mensaje;
    }
}

// Clase CorreoElectronico
class CorreoElectronico extends CanalNotificacion {
    String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    void enviarNotificacion() {
        System.out.println("Enviando correo a " + direccionCorreo + ": " + mensaje);
    }

    @Override
    void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }
}

// Clase MensajeTexto
class MensajeTexto extends CanalNotificacion {
    String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    void enviarNotificacion() {
        System.out.println("Enviando mensaje de texto a " + numeroTelefono + ": " + mensaje);
    }

    @Override
    void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }
}

// Clase Notificaciones
class Notificaciones {
    private final ArrayList<CanalNotificacion> canales = new ArrayList<>();

    void agregarCanal(CanalNotificacion canal) {
        canales.add(canal);
    }

    void enviarNotificaciones() {
        for (CanalNotificacion canal : canales) {
            canal.enviarNotificacion();
        }
    }

    void personalizarMensajes(String nuevoMensaje) {
        for (CanalNotificacion canal : canales) {
            canal.personalizarMensaje(nuevoMensaje);
        }
    }

    void mostrarCanales() {
        for (CanalNotificacion canal : canales) {
            System.out.println(canal);
        }
    }
}

// Clase principal SistemaNotificaciones
public class ejercicio3 {
    public static void main(String[] args) {
        Notificaciones notificaciones = new Notificaciones();

        CorreoElectronico correo = new CorreoElectronico("Juan Pérez", "Hola, esta es una notificación por correo.", "juan.perez@gmail.com");
        MensajeTexto mensaje = new MensajeTexto("Ana López", "Hola, esta es una notificación por SMS.", "+573001112233");

        notificaciones.agregarCanal(correo);
        notificaciones.agregarCanal(mensaje);

        System.out.println("Canales de notificación disponibles:");
        notificaciones.mostrarCanales();

        System.out.println("\nEnviando notificaciones...");
        notificaciones.enviarNotificaciones();

        System.out.println("\nPersonalizando mensajes...");
        notificaciones.personalizarMensajes("Nuevo mensaje personalizado.");
        notificaciones.mostrarCanales();
    }
}

