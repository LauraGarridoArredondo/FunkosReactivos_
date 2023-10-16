package develop.models;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Clase que proporciona la generacion y gestion de identificadores unicos de forma segura en entornos concurrentes.
 * Esta clase utiliza un mecanismo de bloqueo para asegurar que la generacion y manipulacion de identificadores sea segura
 * en escenarios donde multiples hilos pueden acceder al generador de identificadores al mismo tiempo.
 *
 * @version 1.0
 */
public class IdGenerator {
    private static IdGenerator instance;
    private static long id = 0;
    private final Lock locker = new ReentrantLock(true);

    /**
     * Obtiene una instancia del generador de identificadores.
     *
     * @return Una instancia del generador de identificadores.
     */
    public static synchronized IdGenerator getInstance(){
        if(instance == null){
            instance = new IdGenerator();
        }
        return instance;
    }
    /**
     * Genera un nuevo identificador unico y lo incrementa de forma segura.
     *
     * @return El identificador unico generado.
     */
    public Long getIdAndIncrement() {
        Long idCopia = 0L;
        locker.lock();
        id++;
        idCopia = id;
        locker.unlock();
        return idCopia;
    }
    /**
     * Restablece el contador de identificadores a cero.
     */
    public void resetId() {
        this.id = 0L;
    }
}

