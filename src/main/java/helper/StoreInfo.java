package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

// TODO: 4.11.2021 burada storeapi gibi yapmak dogrumu sor ozellikle obje kismini
// doğru ama ihtiyacın olmayacaktır burada çok gauge'un kendi storeunu kullanabilirsin birşeyler tutmaya
// ancak o kısımda ihtiyacın olacak zaten

public class StoreInfo {
    //icinde hashmap tanimlandigi icin T hashmap olur
    //map thread localin referansi
    //senaryo boyunca bizim tum bilgilerimiz tutulur burda
//bir kac adim sonra baska  parametreler gerekebilir mesela
//bu class i biz gauge icerisinden aldik!!!
    private static final ThreadLocal<ConcurrentHashMap<String, Object>> MAP_THREAD_LOCAL = ThreadLocal.withInitial(ConcurrentHashMap::new);
    private final Logger log = LogManager.getLogger(StoreInfo.class);
//web sitelerinde login session falan thread local ile tutulur
    //bunun icinde intte mapte stringte tutabiliriz

    //nesne olusturulmasin bu classtan sadece static kullanilsin
    private StoreInfo() {
        throw new IllegalStateException("Utility class");
    }

    public static synchronized void put(String key, Object value) {

        if (key != null && value != null) {
            //direk map gibi kullanamayiz once get ile map getirmeliyiz sonra islem yapariz
            //get ile subsequent methodlari kullaniriz remove,map gibi=== ayni streamsteki gibi
            MAP_THREAD_LOCAL.get().put(key, value);
        }

    }

    public static synchronized Object remove(Object key) {
        return key != null ? MAP_THREAD_LOCAL.get().remove(key) : null;
    }

    public static synchronized void remove() {
        MAP_THREAD_LOCAL.remove();
    }

    public static synchronized Object get(Object key) {
        return key != null ? MAP_THREAD_LOCAL.get().get(key) : null;
    }

    public static synchronized void clear() {
        MAP_THREAD_LOCAL.get().clear();
    }


}
