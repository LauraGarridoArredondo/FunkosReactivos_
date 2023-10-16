package develop.services.funkos;

import develop.models.Funko;
import develop.models.Notificacion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
/**
 * Implementacion de la interfaz FunkosNotification que proporciona notificaciones relacionadas con objetos Funko
 * a traves de un Flux continuo.
 */
public class FunkosNotificationImpl implements FunkosNotification {
    private static FunkosNotificationImpl INSTANCE = new FunkosNotificationImpl();

    private final Flux<Notificacion<Funko>> funkosNotificationFlux;

    private FluxSink<Notificacion<Funko>> funkosNotification;
    /**
     * Constructor privado para garantizar una unica instancia de FunkosNotificationImpl.
     * Inicializa el Flux que emite notificaciones y lo comparte para que varios consumidores puedan suscribirse.
     */
    private FunkosNotificationImpl() {
        this.funkosNotificationFlux = Flux.<Notificacion<Funko>>create(emitter -> this.funkosNotification = emitter).share();
    }
    /**
     * Obtiene una instancia unica de FunkosNotificationImpl utilizando el patron Singleton.
     *
     * @return La instancia de FunkosNotificationImpl.
     */
    public static FunkosNotificationImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FunkosNotificationImpl();
        }
        return INSTANCE;
    }
    /**
     * Obtiene un Flux continuo de notificaciones relacionadas con objetos Funko.
     *
     * @return Un Flux que emite notificaciones relacionadas con objetos Funko.
     */
    @Override
    public Flux<Notificacion<Funko>> getNotificationAsFlux() {
        return funkosNotificationFlux;
    }
    /**
     * Notifica un evento relacionado con un objeto Funko, enviando la notificacion al Flux.
     *
     * @param notificacion La notificacion que describe el evento relacionado con un objeto Funko.
     */
    @Override
    public void notify(Notificacion<Funko> notificacion) {
        funkosNotification.next(notificacion);
    }
}