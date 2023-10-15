package develop.services.funkos;


import develop.models.Funko;
import develop.services.cache.Cache;

interface FunkoCache extends Cache<Long, Funko> {
}