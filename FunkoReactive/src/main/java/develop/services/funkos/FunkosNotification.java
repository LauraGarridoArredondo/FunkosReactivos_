package develop.services.funkos;

import develop.models.Funko;
import develop.models.Notificacion;
import reactor.core.publisher.Flux;
/**
 * Interfaz que define operaciones para notificar eventos relacionados con objetos Funko.
 */
public interface FunkosNotification {
    /**
     * Obtiene notificaciones de objetos Funko como un Flux continuo.
     *
     * @return Un Flux que emite notificaciones relacionadas con objetos Funko.
     */
    Flux<Notificacion<Funko>> getNotificationAsFlux();
    /**
     * Notifica un evento relacionado con un objeto Funko.
     *
     * @param notificacion La notificacion que describe el evento relacionado con un objeto Funko.
     */
    void notify(Notificacion<Funko> notificacion);
}